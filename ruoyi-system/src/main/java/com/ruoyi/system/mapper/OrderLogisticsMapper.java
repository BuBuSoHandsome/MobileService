package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OrderLogistics;
import java.util.List;

/**
 * 订单物流Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-15
 */
public interface OrderLogisticsMapper 
{
    /**
     * 查询订单物流
     * 
     * @param fdId 订单物流ID
     * @return 订单物流
     */
    public OrderLogistics selectOrderLogisticsById(String fdId);

    /**
     * 查询订单物流列表
     * 
     * @param orderLogistics 订单物流
     * @return 订单物流集合
     */
    public List<OrderLogistics> selectOrderLogisticsList(OrderLogistics orderLogistics);

    /**
     * 新增订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    public int insertOrderLogistics(OrderLogistics orderLogistics);

    /**
     * 修改订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    public int updateOrderLogistics(OrderLogistics orderLogistics);

    /**
     * 删除订单物流
     * 
     * @param fdId 订单物流ID
     * @return 结果
     */
    public int deleteOrderLogisticsById(String fdId);

    /**
     * 批量删除订单物流
     * 
     * @param fdIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderLogisticsByIds(String[] fdIds);
}
