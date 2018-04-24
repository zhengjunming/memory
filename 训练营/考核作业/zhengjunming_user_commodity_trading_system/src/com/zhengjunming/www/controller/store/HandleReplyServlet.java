package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.OrderDaoImpl;

/**
 * 回复评价控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HandleReplyServlet")
public class HandleReplyServlet extends HttpServlet {
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
		String reply = request.getParameter("reply");
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		try {
			orderDaoImpl.addReply(id, reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("refresh",
				"2;URL=/zhengjunming_user_commodity_trading_system/ListEvaluateOrderServlet");
		response.getWriter().println("<center><h1>回复成功，将转向原界面</h1></center>");
	}

}
