package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.OrderCucc;
import com.ruoyi.system.domain.OrderLogistics;
import com.ruoyi.system.domain.cuccMobileResponse.CreateOrderResponse;
import com.ruoyi.system.domain.mobileRequest.DSAirpickinstallQueryOrderRequest;
import com.ruoyi.system.domain.mobileRequest.QueryChooseNumberListRequest;
import com.ruoyi.system.domain.mobileResponse.DSAirpickinstallQueryOrderResponse;
import com.ruoyi.system.domain.mobileResponse.QueryChooseNumberListResponse;
import com.ruoyi.system.mapper.OrderCuccMapper;
import com.ruoyi.system.mapper.OrderLogisticsMapper;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.service.CuccMobileService;
import com.ruoyi.system.service.MobileService;
import net.sf.ehcache.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("mobileTask")
public class MobileTask {

    private static final Logger log = LoggerFactory.getLogger(MobileTask.class);

    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderCuccMapper orderCuccMapper;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private CuccMobileService cuccMobileService;

    //每30个任务一个线程
    private static final Integer MAX_NUMBER =  30;
    private static Integer countStep(Integer size){
        if(size<=0){
            throw  new RuntimeException();
        }
        return (size+MAX_NUMBER -1)/MAX_NUMBER;
    }

    //内部任务类
    class refreshOrderTask implements Callable<Integer> {

        private List<Order> orders;

        public refreshOrderTask(List<Order> orders){
            this.orders = orders;
        }

        @Override
        public Integer call() throws Exception {
            Integer num = 0;
            for (int i=0;i<orders.size();i++){
                int finalI = i;
                DSAirpickinstallQueryOrderResponse response = mobileService.getOrderMsg(
                        new DSAirpickinstallQueryOrderRequest(){{  setServnumber(orders.get(finalI).getServnumber());
                        }});
                //超过两周没有收货
                if(DateUtils.isLastTwoWeeks(response.getCreateTime())){
                    //更新订单表状态 改为拒收状态
                    orderMapper.updateOrder(new Order(){{
                        setFdId(orders.get(finalI).getFdId());
                        setStatus("4");
                    }});
                }
                if("激活成功".equals(response.getOrderRemark()) &&"已完成".equals(response.getOrderStatus())){
                    //更新订单表状态
                    orderMapper.updateOrder(new Order(){{
                        setFdId(orders.get(finalI).getFdId());
                        setStatus("3");
                    }});
                }
                // 如果状态有其他变化或者是新入库的订单 删掉原数据并重新insert订单详情
                OrderLogistics orderLogistics = new OrderLogistics();
                BeanUtils.copyProperties(response, orderLogistics);
                orderLogistics.setFdId(orders.get(finalI).getFdId());
                orderLogisticsMapper.deleteOrderLogisticsById(orders.get(finalI).getFdId());
                int nums = orderLogisticsMapper.insertOrderLogistics(orderLogistics);
                num += nums;
            }
            return num;
        }
    }


    /**
     * 通过线程池去创建线程异步更新 提升效率
     */
    public void refreshOrderMsg(){
        List<Order> orderIds = orderMapper.selectOrderList(new Order(){{
            setStatus("1");
        }});
        if(null == orderIds || orderIds.isEmpty()){
            return;
        }
        int stepNum = countStep(orderIds.size());
        //通过分段拆分list
        List<List<Order>> splitList = Stream.iterate(0, n -> n + 1).
                limit(stepNum).parallel().map(a -> orderIds.stream().skip(a * MAX_NUMBER).
                limit(MAX_NUMBER).parallel().collect(Collectors.toList())).collect(Collectors.toList());
        ThreadPoolExecutor pool = null;
        try{
            pool = new ThreadPoolExecutor(
                    stepNum+1,stepNum+1 ,60L, TimeUnit.SECONDS,new ArrayBlockingQueue(orderIds.size()),new NamedThreadFactory("更新订单物流"));
            ArrayList<Future<Integer>> futures = new ArrayList<>();
            for(int i=0;i<splitList.size();i++){
                refreshOrderTask refreshOrderTask = new refreshOrderTask(splitList.get(i));
                Future<Integer> f = pool.submit(refreshOrderTask);
                futures.add(f);
            }
        }finally {
            log.info("本次成功更新 "+orderIds.size()+" 物流记录，详情请查询系统日志");
            pool.shutdown();
        }
    }

    public void installDwOrder(){
        List<OrderCucc> orderCuccs = orderCuccMapper.selectOrderCuccList(new OrderCucc(){{
            setStatus("0");
        }});

        if (orderCuccs.isEmpty()){
            log.info("系统没有未下单大王卡的订单");
            return;
        }
        for(OrderCucc orderCucc:orderCuccs){
            CreateOrderResponse orderResponse =  cuccMobileService.installOrder(orderCucc);
            log.info(orderResponse.getCode());
            log.info(orderResponse.getMessage());
        }
    }


    public void installBzOrder(){
        JSONObject queryChooseNumberListString = JSONObject.parseObject(
                MobileUtil.getResponse(
                        MobileUrl.QueryChooseNumberList.getUrl(),
                        MobileUtil.getBodyByClass(new QueryChooseNumberListRequest(){{
                            setPackagecode("prod.10086000025892");
                            setTag("0");
                            setRegion("200");
                        }})));
        //获取选号号码列表失败
        String respcode = queryChooseNumberListString.getString("respcode");
        if(null==queryChooseNumberListString||null==respcode ||!"0".equals(respcode)){
            log.info("查询选号号码列表异常:"+queryChooseNumberListString.toJSONString());
        }
        List<QueryChooseNumberListResponse> numberLists = JSONArray.parseArray(JSONObject.parseObject(
                queryChooseNumberListString.getString("result")).getString( "infos"), QueryChooseNumberListResponse.class);
        //成功状态下，可能没有号码选
        if(null==numberLists||numberLists.isEmpty()){
            log.info("下单失败，宝藏卡无号码，稍等重新尝试获取号码。。");
            return;
        }
        //暂时默认2分钟下单一次  如果一次查询超过10个号码 则取10条订单记录 ， 低于10个号码则取 查出的号码记录为下单数量
        Integer orderNum = 0;
        if(numberLists.size()>=10){
            orderNum = 10;
        }else{
            orderNum = numberLists.size();
        }
        List<Order> orderList = orderMapper.selectOrderList10(orderNum);
        if(null == orderList||orderList.isEmpty()){
            log.info("系统没有未下单宝藏卡的订单");
            return;
        }
        Integer num  = 0;
        for (Order order:orderList){
            if(mobileService.AirpickinstallnewOrder(order)){
                num ++;
            }
        }
        log.info("本次下单 "+orderList.size()+" 记录，成功"+ num +"条，详情请查询系统日志");
    }

}



