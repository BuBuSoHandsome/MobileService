package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderCuccMapper;
import com.ruoyi.system.domain.OrderCucc;
import com.ruoyi.system.service.IOrderCuccService;

/**
 * 联通订单 服务层实现
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
@Service
public class OrderCuccServiceImpl implements IOrderCuccService 
{
	@Autowired
	private OrderCuccMapper orderCuccMapper;

	/**
     * 查询联通订单信息
     * 
     * @param fdId 联通订单ID
     * @return 联通订单信息
     */
    @Override
	public OrderCucc selectOrderCuccById(String fdId)
	{
	    return orderCuccMapper.selectOrderCuccById(fdId);
	}
	
	/**
     * 查询联通订单列表
     * 
     * @param orderCucc 联通订单信息
     * @return 联通订单集合
     */
	@Override
	public List<OrderCucc> selectOrderCuccList(OrderCucc orderCucc)
	{
	    return orderCuccMapper.selectOrderCuccList(orderCucc);
	}
	
    /**
     * 新增联通订单
     * 
     * @param orderCucc 联通订单信息
     * @return 结果
     */
	@Override
	public int insertOrderCucc(OrderCucc orderCucc)
	{
	    return orderCuccMapper.insertOrderCucc(orderCucc);
	}
	
	/**
     * 修改联通订单
     * 
     * @param orderCucc 联通订单信息
     * @return 结果
     */
	@Override
	public int updateOrderCucc(OrderCucc orderCucc)
	{
	    return orderCuccMapper.updateOrderCucc(orderCucc);
	}

	/**
     * 删除联通订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrderCuccByIds(String[] ids)
	{
		return orderCuccMapper.deleteOrderCuccByIds(ids);
	}
	
}
