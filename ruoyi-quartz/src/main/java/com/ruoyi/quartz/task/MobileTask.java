package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.OrderLogistics;
import com.ruoyi.system.domain.mobileRequest.DSAirpickinstallQueryOrderRequest;
import com.ruoyi.system.domain.mobileRequest.QueryChooseNumberListRequest;
import com.ruoyi.system.domain.mobileResponse.DSAirpickinstallQueryOrderResponse;
import com.ruoyi.system.domain.mobileResponse.QueryChooseNumberListResponse;
import com.ruoyi.system.mapper.OrderLogisticsMapper;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.service.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("mobileTask")
public class MobileTask {

    private static final Logger log = LoggerFactory.getLogger(MobileTask.class);

    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private MobileService mobileService;

    public void refreshOrderMsg(){
        List<String> orderIds = orderMapper.selectOrderIds(new Order(){{
            setStatus("1");
        }});
        if(null == orderIds || orderIds.isEmpty()){
            return;
        }
        Integer num = 0;
        for (int i=0;i<orderIds.size();i++){
            int finalI = i;
            DSAirpickinstallQueryOrderResponse response = mobileService.getOrderMsg(
                    new DSAirpickinstallQueryOrderRequest(){{ setOrderId(orderIds.get(finalI));
                    }});
            if("激活成功".equals(response.getOrderRemark())){
                //更新订单表状态
                orderMapper.updateOrder(new Order(){{
                    setFdId(orderIds.get(finalI));
                    setStatus("3");
                }});
            }
            OrderLogistics orderLogistics = new OrderLogistics();
            BeanUtils.copyProperties(response, orderLogistics);
            orderLogistics.setFdId(orderIds.get(finalI));
            orderLogisticsMapper.deleteOrderLogisticsById(orderIds.get(finalI));
            int nums = orderLogisticsMapper.insertOrderLogistics(orderLogistics);
            num += nums;
        }
        log.info("本次成功更新 "+num+" 物流记录，详情请查询系统日志");
    }


    public void installBZOrder(){
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
            log.info("系统没有未下单的订单");
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
