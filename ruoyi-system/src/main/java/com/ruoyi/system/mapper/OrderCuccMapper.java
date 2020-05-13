package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OrderCucc;
import java.util.List;	

/**
 * 联通订单 数据层
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
public interface OrderCuccMapper 
{
	/**
     * 查询联通订单信息
     * 
     * @param fdId 联通订单ID
     * @return 联通订单信息
     */
	public OrderCucc selectOrderCuccById(String fdId);
	
	/**
     * 查询联通订单列表
     * 
     * @param orderCucc 联通订单信息
     * @return 联通订单集合
     */
	public List<OrderCucc> selectOrderCuccList(OrderCucc orderCucc);
	
	/**
     * 新增联通订单
     * 
     * @param orderCucc 联通订单信息
     * @return 结果
     */
	public int insertOrderCucc(OrderCucc orderCucc);
	
	/**
     * 修改联通订单
     * 
     * @param orderCucc 联通订单信息
     * @return 结果
     */
	public int updateOrderCucc(OrderCucc orderCucc);
	
	/**
     * 删除联通订单
     * 
     * @param fdId 联通订单ID
     * @return 结果
     */
	public int deleteOrderCuccById(String fdId);
	
	/**
     * 批量删除联通订单
     * 
     * @param fdIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderCuccByIds(String[] fdIds);
	
}