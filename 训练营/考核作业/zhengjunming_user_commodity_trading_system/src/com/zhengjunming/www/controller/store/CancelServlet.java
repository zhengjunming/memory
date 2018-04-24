package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.OrderDaoImpl;

/**
 * 取消已发货的订单
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {
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
		try {
			daoImpl.cancelOrder(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("refresh",
				"1;URL=/zhengjunming_user_commodity_trading_system/ListShippedOrderServlet");
		response.getWriter().println("<h1>取消订单成功，1秒后转向已发货订单页面</h1>");
	}

}
