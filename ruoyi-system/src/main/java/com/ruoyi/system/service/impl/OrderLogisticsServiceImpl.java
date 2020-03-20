package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderLogisticsMapper;
import com.ruoyi.system.domain.OrderLogistics;
import com.ruoyi.system.service.IOrderLogisticsService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 订单物流Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-15
 */
@Service
public class OrderLogisticsServiceImpl implements IOrderLogisticsService 
{
    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;

    /**
     * 查询订单物流
     * 
     * @param fdId 订单物流ID
     * @return 订单物流
     */
    @Override
    public OrderLogistics selectOrderLogisticsById(String fdId)
    {
        return orderLogisticsMapper.selectOrderLogisticsById(fdId);
    }

    /**
     * 查询订单物流列表
     * 
     * @param orderLogistics 订单物流
     * @return 订单物流
     */
    @Override
    public List<OrderLogistics> selectOrderLogisticsList(OrderLogistics orderLogistics)
    {
        return orderLogisticsMapper.selectOrderLogisticsList(orderLogistics);
    }

    /**
     * 新增订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    @Override
    public int insertOrderLogistics(OrderLogistics orderLogistics)
    {
        return orderLogisticsMapper.insertOrderLogistics(orderLogistics);
    }

    /**
     * 修改订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    @Override
    public int updateOrderLogistics(OrderLogistics orderLogistics)
    {
        return orderLogisticsMapper.updateOrderLogistics(orderLogistics);
    }

    /**
     * 删除订单物流对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderLogisticsByIds(String ids)
    {
        return orderLogisticsMapper.deleteOrderLogisticsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单物流信息
     * 
     * @param fdId 订单物流ID
     * @return 结果
     */
    @Override
    public int deleteOrderLogisticsById(String fdId)
    {
        return orderLogisticsMapper.deleteOrderLogisticsById(fdId);
    }
}
