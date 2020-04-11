package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.OrderLogistics;
import com.ruoyi.system.domain.mobileRequest.DSAirpickinstallQueryOrderRequest;
import com.ruoyi.system.domain.mobileResponse.DSAirpickinstallQueryOrderResponse;
import com.ruoyi.system.mapper.OrderLogisticsMapper;
import com.ruoyi.system.service.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-10
 */
@Service
public class OrderServiceImpl implements IOrderService 
{

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;

    @Autowired
    private MobileService mobileService;

    /**
     * 查询订单
     * 
     * @param fdId 订单ID
     * @return 订单
     */
    @Override
    public Order selectOrderById(String fdId)
    {
        return orderMapper.selectOrderById(fdId);
    }

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        return orderMapper.updateOrder(order);
    }

    /**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(String ids)
    {
        return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param fdId 订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderById(String fdId)
    {
        return orderMapper.deleteOrderById(fdId);
    }

    /**
     * 线上放号下单
     * @param ids
     * @return
     */
    @Override
    public String installOrder(String ids) {
        List<Order> orders = orderMapper.selectOrderListByIds(Convert.toStrArray(ids));
        Integer num  = 0;
        for (Order order:orders){
            if(mobileService.AirpickinstallnewOrder(order)){
                num ++;
            }
        }
        return "本次下单 "+orders.size()+" 记录，成功"+ num +"条， 详情请查询系统日志";
    }

    /**
     * 订单状态更新
     * @param ids
     * @return
     */
    @Override
    public String refreshOrderStatus(String ids) {
        String[] idS = Convert.toStrArray(ids);
        Integer num = 0;
        for (int i =0;i<idS.length;i++){
            int finalI = i;
            DSAirpickinstallQueryOrderResponse response = mobileService.getOrderMsg(
                    new DSAirpickinstallQueryOrderRequest(){{ setOrderId(idS[finalI]);
            }});
            if(DateUtils.isLastTwoWeeks(response.getCreateTime())){
                //更新订单表状态 改为拒收状态
                orderMapper.updateOrder(new Order(){{
                    setFdId(idS[finalI]);
                    setStatus("4");
                }});
            }
            if("激活成功".equals(response.getOrderRemark())){
                //更新订单表状态 改为激活成功
                orderMapper.updateOrder(new Order(){{
                    setFdId(idS[finalI]);
                    setStatus("3");
                }});
            }
     OrderLogistics orderLogistics = new OrderLogistics();
     BeanUtils.copyProperties(response, orderLogistics);
     orderLogistics.setFdId(idS[finalI]);
     orderLogisticsMapper.deleteOrderLogisticsById(idS[finalI]);
     int nums = orderLogisticsMapper.insertOrderLogistics(orderLogistics);
     num += nums;
     }
     return "本次成功更新 "+num+" 记录，详情请查询系统日志";
     }


     /**
     * 订单数据导入
     * @param orderList
     * @return
     */
    @Override
    public String importOrder(List<Order> orderList) {
        int num = 0;
        if(null==orderList || orderList.isEmpty()){
            return "导入数据为空";
        }
        for(Order order:orderList){
            order.setFdId(StringUtils.generateRandomString(12).toUpperCase());
            order.setCardtype("01");
            order.setStatus("0");
            orderMapper.insertOrder(order);
            num++;
        }
        return "本次成功导入 " +num+" 条订单数据";
    }
}
