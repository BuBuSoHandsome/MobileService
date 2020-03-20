package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Order;
import java.util.List;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-10
 */
public interface OrderMapper 
{
    /**
     * 查询订单
     * 
     * @param fdId 订单ID
     * @return 订单
     */
    public Order selectOrderById(String fdId);

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单集合
     */
    public List<Order> selectOrderList(Order order);


    /**
     * 批量查询byIds
     */
    public List<Order> selectOrderListByIds(String[] ids);


    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除订单
     * 
     * @param fdId 订单ID
     * @return 结果
     */
    public int deleteOrderById(String fdId);

    /**
     * 批量删除订单
     * 
     * @param fdIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderByIds(String[] fdIds);
}
