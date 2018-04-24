package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.OrderDaoImpl;
import com.zhengjunming.www.po.Order;

/**
 * 获取订单信息控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/GetOrderInfoServlet")
public class GetOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		Order order = new Order();
		try {
			order = daoImpl.getOrderByOrderId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("orderInfo", order);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/store/modifyOrderInfo.jsp");
	}

}
