package com.zhengjunming.www.controller.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.OrderDaoImpl;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.po.OrderItem;
import com.zhengjunming.www.po.other.OrderStatus;

/**
 * 获得订单详细信息控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/GetOrderDetailsServlet")
public class GetOrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id")); // 得到订单id
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Order order = new Order();
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		try {
			orderItems = daoImpl.getOrderItemByOrderId(id); // 获得订单的订单条目
			order = daoImpl.getOrderByOrderId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("orderStatus", order.getOrderStatus());
		request.getSession().setAttribute("orderItems", orderItems);
		// 把订单状态枚举类存入Session
		request.getSession().setAttribute("shipped", OrderStatus.SHIPPED.getName());
		request.getSession().setAttribute("underReview", OrderStatus.UNDER_REVIEW.getName());
		request.getSession().setAttribute("received", OrderStatus.RECEIVED.getName());
		request.getSession().setAttribute("cancel", OrderStatus.CANCEL_ORDER.getName());
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/store/listOrderItem.jsp");
	}

}
