package com.zhengjunming.www.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户退出登录控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserDropOutServlet")
public class UserDropOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); // 销毁Session
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/ListAllGoodsServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
