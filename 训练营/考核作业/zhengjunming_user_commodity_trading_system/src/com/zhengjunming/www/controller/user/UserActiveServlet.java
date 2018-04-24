package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.service.user.UserService;

/**
 * 用户激活控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserActiveServlet")
public class UserActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code"); // 用户激活码
		UserService userService = new UserService();
		try {
			if (userService.Active(code)) {
				response.setHeader("refresh",
						"2;URL=/zhengjunming_user_commodity_trading_system/user/UserLogin.jsp");
				response.getWriter().println(
						"<center><h1>激活成功，2秒后转向登录页面</h1></center>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
