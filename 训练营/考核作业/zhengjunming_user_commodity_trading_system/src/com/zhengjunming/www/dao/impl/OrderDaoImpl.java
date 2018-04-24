package com.zhengjunming.www.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.zhengjunming.www.dao.OrderDao;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.po.OrderItem;
import com.zhengjunming.www.po.other.OrderStatus;
import com.zhengjunming.www.util.DbUtil;

public class OrderDaoImpl implements OrderDao {
	private static DbUtil dbUtil = new DbUtil();

	@Override
	public List<Order> getOrderByStoreId(int id) throws Exception {
		List<Order> orders = new ArrayList<Order>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from tb_order where store_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				int orderId = resultSet.getInt(1);
				order.setId(orderId);
				order.setOrderNumber(resultSet.getString(2));
				order.setReceiver(resultSet.getString(3));
				order.setAddress(resultSet.getString(4));
				order.setPhone(resultSet.getString(5));
				order.setTotalPrice(resultSet.getString(6));
				order.setMessage(resultSet.getString(7));
				order.setOrderDate(resultSet.getTimestamp(8));
				order.setOrderStatus(resultSet.getString(9));
				order.setEvaluation(resultSet.getString(10));
				order.setPicture(resultSet.getString(11));
				order.setReply(resultSet.getString(12));
				order.setOrderItems(getOrderItemByOrderId(orderId));
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

		return null;
	}

	@Override
	public List<OrderItem> getOrderItemByOrderId(int id) throws Exception {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
		try {
			String sql = "select * from order_item where order_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				
				OrderItem orderItem = new OrderItem();
				orderItem.setId(resultSet.getInt(1));
				orderItem.setGoodsId(resultSet.getInt(2));
				orderItem.setGoodsName(resultSet.getString(3));
				orderItem.setGoodsPrice(resultSet.getString(4));
				orderItem.setGoodsAmount(resultSet.getInt(5));
				orderItem.setTotalPrice(resultSet.getString(6));
				orderItem.setStore(storeDaoImpl.getStoreInfo(resultSet.getInt(8)));
				orderItems.add(orderItem);
			}
			return orderItems;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return null;
	}

	@Override
	public List<Order> getOrderByStoreIdAndOrderStatus(int pageSize, int page,
			int id, String status) throws Exception {
		List<Order> orders = new ArrayList<Order>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from tb_order where store_id = ? and order_status = ? order by id desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, status);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				int orderId = resultSet.getInt(1);
				order.setId(orderId);
				order.setOrderNumber(resultSet.getString(2));
				order.setReceiver(resultSet.getString(3));
				order.setAddress(resultSet.getString(4));
				order.setPhone(resultSet.getString(5));
				order.setTotalPrice(resultSet.getString(6));
				order.setMessage(resultSet.getString(7));
				order.setOrderDate(resultSet.getTimestamp(8));
				order.setOrderStatus(resultSet.getString(9));
				order.setEvaluation(resultSet.getString(10));
				order.setPicture(resultSet.getString(11));
				order.setReply(resultSet.getString(12));
				order.setOrderItems(getOrderItemByOrderId(orderId));
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return null;
	}

	@Override
	public List<Order> getOrderByUserIdAndOrderStatus(int pageSize, int page,
			int id, String status) throws Exception {
		List<Order> orders = new ArrayList<Order>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from tb_order where customer_id = ? "
					+ "and order_status = ? order by id desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, status);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				int orderId = resultSet.getInt(1);
				order.setId(orderId);
				order.setOrderNumber(resultSet.getString(2));
				order.setReceiver(resultSet.getString(3));
				order.setAddress(resultSet.getString(4));
				order.setPhone(resultSet.getString(5));
				order.setTotalPrice(resultSet.getString(6));
				order.setMessage(resultSet.getString(7));
				order.setOrderDate(resultSet.getTimestamp(8));
				order.setOrderStatus(resultSet.getString(9));
				order.setEvaluation(resultSet.getString(10));
				order.setPicture(resultSet.getString(11));
				order.setReply(resultSet.getString(12));
				order.setOrderItems(getOrderItemByOrderId(orderId));
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return null;
	}

	@Override
	public int getOrderCountByUserIdAndOrderStatus(int id, String status)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from tb_order where customer_id = ? and order_status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, status);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return count;
	}

	@Override
	public int getOrderCountByStoreIdAndOrderStatus(int id, String status)
			throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			String sql = "select * from tb_order where store_id = ? and order_status = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, status);
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return count;
	}

	@Override
	public Order getOrderByOrderId(int id) throws Exception {
		Order order = new Order();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "select * from tb_order where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				int orderId = resultSet.getInt(1);
				order.setId(orderId);
				order.setOrderNumber(resultSet.getString(2));
				order.setReceiver(resultSet.getString(3));
				order.setAddress(resultSet.getString(4));
				order.setPhone(resultSet.getString(5));
				order.setTotalPrice(resultSet.getString(6));
				order.setMessage(resultSet.getString(7));
				order.setOrderDate(resultSet.getTimestamp(8));
				order.setOrderStatus(resultSet.getString(9));
				order.setEvaluation(resultSet.getString(10));
				order.setPicture(resultSet.getString(11));
				order.setReply(resultSet.getString(12));
				order.setOrderItems(getOrderItemByOrderId(orderId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return order;
	}

	@Override
	public void shipOrder(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update tb_order set order_status = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, OrderStatus.SHIPPED.getName());
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void cancelOrder(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update tb_order set order_status = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, OrderStatus.CANCEL_ORDER.getName());
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public List<Order> getOrderForModify(int pageSize, int page, int id)
			throws Exception {
		List<Order> orders = new ArrayList<Order>();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from tb_order where store_id = ? and order_status = ? order by id desc limit "
					+ (page - 1) * pageSize + "," + pageSize;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, OrderStatus.UNDER_REVIEW.getName());
			resultSet = (ResultSet) pstmt.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				int orderId = resultSet.getInt(1);
				order.setId(orderId);
				order.setOrderNumber(resultSet.getString(2));
				order.setReceiver(resultSet.getString(3));
				order.setAddress(resultSet.getString(4));
				order.setPhone(resultSet.getString(5));
				order.setTotalPrice(resultSet.getString(6));
				order.setMessage(resultSet.getString(7));
				order.setOrderDate(resultSet.getTimestamp(8));
				order.setOrderStatus(resultSet.getString(9));
				order.setEvaluation(resultSet.getString(10));
				order.setPicture(resultSet.getString(11));
				order.setReply(resultSet.getString(12));
				order.setOrderItems(getOrderItemByOrderId(orderId));
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return null;
	}

	@Override
	public void modifyOrderInfo(Order order, String receiver, String address,
			String phone) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update tb_order set receiver = ?, address = ? , phone = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, receiver);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setInt(4, order.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public Order getOrderByOrderNumber(String orderNumber) throws Exception {
		Order order = new Order();
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from tb_order where order_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNumber);
			resultSet = (ResultSet) pstmt.executeQuery();
			if (resultSet.next()) {
				int orderId = resultSet.getInt(1);
				order.setId(orderId);
				order.setOrderNumber(resultSet.getString(2));
				order.setReceiver(resultSet.getString(3));
				order.setAddress(resultSet.getString(4));
				order.setPhone(resultSet.getString(5));
				order.setTotalPrice(resultSet.getString(6));
				order.setMessage(resultSet.getString(7));
				order.setOrderDate(resultSet.getTimestamp(8));
				order.setOrderStatus(resultSet.getString(9));
				order.setEvaluation(resultSet.getString(10));
				order.setPicture(resultSet.getString(11));
				order.setReply(resultSet.getString(12));
				order.setOrderItems(getOrderItemByOrderId(orderId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		return order;
	}

	@Override
	public void addOrder(Order order) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into tb_order (order_number, receiver, address, "
					+ "phone, total_price, message, order_date, order_status, "
					+ "customer_id, store_id) values (?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getOrderNumber());
			pstmt.setString(2, order.getReceiver());
			pstmt.setString(3, order.getAddress());
			pstmt.setString(4, order.getPhone());
			pstmt.setString(5, order.getTotalPrice());
			pstmt.setString(6, order.getMessage());
			pstmt.setTimestamp(7, order.getOrderDate());
			pstmt.setString(8, OrderStatus.UNDER_REVIEW.getName());
			pstmt.setInt(9, order.getUser().getId());
			pstmt.setInt(10, order.getStore().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}

	}

	@Override
	public void addOrderItem(OrderItem item) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into order_item (goods_id, goods_name, goods_price, "
					+ "goods_amount, total_price, order_id, store_id) values (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item.getGoodsId());
			pstmt.setString(2, item.getGoodsName());
			pstmt.setString(3, item.getGoodsPrice());
			pstmt.setInt(4, item.getGoodsAmount());
			pstmt.setString(5, item.getTotalPrice());
			pstmt.setInt(6, item.getOrder().getId());
			pstmt.setInt(7, item.getStore().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void deleteOrderById(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from tb_order where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void deleteOrderItemById(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from order_item where order_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void confirmReceipt(int id) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update tb_order set order_status = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, OrderStatus.RECEIVED.getName());
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}

	@Override
	public void addEvaluate(Order order) throws Exception {
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update tb_order set order_evaluation = ?, picture = ?, order_status = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getEvaluation());
			pstmt.setString(2, order.getPicture());
			pstmt.setString(3, OrderStatus.EVALUATED.getName());
			pstmt.setInt(4, order.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
		
	}

	@Override
	public void addReply(int id, String reply) throws Exception {
		
		Connection con = dbUtil.getCon();
		PreparedStatement pstmt = null;
		try {
			String sql = "update tb_order set reply = ?where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reply);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, con);
		}
	}
	
}
