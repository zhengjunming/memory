package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.OrderDaoImpl;

/**
 * 订单发货控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ShipServlet")
public class ShipServlet extends HttpServlet {
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
			daoImpl.shipOrder(id); // 发货
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("refresh",
				"2;URL=/zhengjunming_user_commodity_trading_system/ListNoShippingOrderServlet");
		response.getWriter().println("<h1>发货成功，2秒后转向未发货订单页面</h1>");
	}

}
