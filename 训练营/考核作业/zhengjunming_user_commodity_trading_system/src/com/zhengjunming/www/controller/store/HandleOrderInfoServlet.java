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
 * 修改订单信息控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HandleOrderInfoServlet")
public class HandleOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 得到订单信息
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		Order order = (Order) request.getSession().getAttribute("orderInfo");
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		try {
			daoImpl.modifyOrderInfo(order, receiver, address, phone); // 修改订单
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/ListOrderForModifyServlet");
	}

}
