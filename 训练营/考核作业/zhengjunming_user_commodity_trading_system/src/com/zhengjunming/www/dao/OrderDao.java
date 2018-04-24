package com.zhengjunming.www.dao;

import java.util.List;

import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.po.OrderItem;

public interface OrderDao {

	/**
	 * 根据店家id获得订单
	 * 
	 * @param id 店家id
	 * @return 订单集合
	 * @throws Exception
	 */
	public List<Order> getOrderByStoreId(int id) throws Exception;

	/**
	 * 根据订单id获得订单条目
	 * 
	 * @param id 订单id
	 * @return 订单条目集合
	 * @throws Exception
	 */
	public List<OrderItem> getOrderItemByOrderId(int id) throws Exception;

	/**
	 * 根据店家id和订单状态获得订单
	 * 
	 * @param pageSize 一页数量
	 * @param page 第几页
	 * @param id 店家id
	 * @param status 订单状态
	 * @return 订单集合
	 * @throws Exception
	 */
	public List<Order> getOrderByStoreIdAndOrderStatus(int pageSize, int page,
			int id, String status) throws Exception;

	/**
	 * 根据用户id和订单状态获得订单
	 * 
	 * @param pageSize 一页数量
	 * @param page 第几页
	 * @param id 用户id
	 * @param status 订单状态
	 * @return 订单集合
	 * @throws Exception
	 */
	public List<Order> getOrderByUserIdAndOrderStatus(int pageSize, int page,
			int id, String status) throws Exception;

	/**
	 * 根据店家id和订单状态获取订单的数量
	 * 
	 * @param id 店家id
	 * @param status 订单状态
	 * @return 订单数量
	 * @throws Exception
	 */
	public int getOrderCountByStoreIdAndOrderStatus(int id, String status)
			throws Exception;

	/**
	 * 根据用户id和订单状态获取订单的数量
	 * 
	 * @param id 用户id
	 * @param status 订单状态
	 * @return 订单数量
	 * @throws Exception
	 */
	public int getOrderCountByUserIdAndOrderStatus(int id, String status)
			throws Exception;

	/**
	 * 根据订单id获得订单
	 * 
	 * @param id 订单id
	 * @return 订单实体
	 * @throws Exception
	 */
	public Order getOrderByOrderId(int id) throws Exception;

	/**
	 * 发货
	 * 
	 * @param id 订单id
	 * @throws Exception
	 */
	public void shipOrder(int id) throws Exception;

	/**
	 * 取消已发货的订单
	 * 
	 * @param id 订单id
	 * @throws Exception
	 */
	public void cancelOrder(int id) throws Exception;

	/**
	 * 获得待修改的订单
	 * 
	 * @param pageSize 一页的数量
	 * @param page 第几页
	 * @param id 店家id
	 * @return 订单集合
	 * @throws Exception
	 */
	public List<Order> getOrderForModify(int pageSize, int page, int id)
			throws Exception;

	/**
	 * 修改订单信息
	 * 
	 * @param order 订单实体类
	 * @param receiver 收货人
	 * @param address 收货地址
	 * @param phone 电话
	 * @throws Exception 
	 */
	public void modifyOrderInfo(Order order, String receiver, String address,
			String phone) throws Exception;

	/**
	 * 根据订单号获得订单
	 * 
	 * @param orderNumber 订单号
	 * @return 订单实体类
	 * @throws Exception
	 */
	public Order getOrderByOrderNumber(String orderNumber) throws Exception;

	/**
	 * 添加订单
	 * 
	 * @param order 订单实体
	 * @throws Exception
	 */
	public void addOrder(Order order) throws Exception;

	/**
	 * 添加订单条目
	 * 
	 * @param item 订单条目实体
	 * @throws Exception
	 */
	public void addOrderItem(OrderItem item) throws Exception;

	/**
	 * 根据订单id删除订单
	 * 
	 * @param id 订单ID
	 * @throws Exception
	 */
	public void deleteOrderById(int id) throws Exception;

	/**
	 * 删除订单对应的订单条目
	 * 
	 * @param id 订单id
	 * @throws Exception
	 */
	public void deleteOrderItemById(int id) throws Exception;

	/**
	 * 确认收货
	 * 
	 * @param id 订单id
	 * @throws Exception
	 */
	public void confirmReceipt(int id) throws Exception;

	/**
	 * 添加评论
	 * 
	 * @param order 订单id
	 * @throws Exception
	 */
	public void addEvaluate(Order order) throws Exception;

	/**
	 * 添加回复
	 * 
	 * @param id 订单id
	 * @param reply 店家回复
	 * @throws Exception
	 */
	public void addReply(int id, String reply) throws Exception;
}
