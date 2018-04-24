package com.zhengjunming.www.controller.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 帮助店家得到订单id，并把id存入Session
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HelpStoreGetIdServlet")
public class HelpStoreGetIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id"); 
		request.getSession().setAttribute("id", id);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/store/reply.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
