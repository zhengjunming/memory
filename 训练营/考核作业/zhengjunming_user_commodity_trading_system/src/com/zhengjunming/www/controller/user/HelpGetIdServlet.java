package com.zhengjunming.www.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取订单id后存入Session(无奈之举)
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HelpGetIdServlet")
public class HelpGetIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		request.getSession().setAttribute("id", id);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/evaluateOrder.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
