package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.UserDaoImpl;
import com.zhengjunming.www.po.User;

/**
 * 完善用户个人信息控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ImproveUserInforServlet")
public class ImproveUserInforServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user = (User) request.getSession().getAttribute("user");

		UserDaoImpl daoImpl = new UserDaoImpl();
		try {
			user = daoImpl.UserData(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("user", user);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/improveUserInfo.jsp");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
