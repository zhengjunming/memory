package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.UserDaoImpl;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.user.UserService;

/**
 * 用户找回密码控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/RetrieverPasswordServlet")
public class RetrieverPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username"); // 获得用户的用户名
		String choose = request.getParameter("choose"); // 获得找回密码方式
		User user = new User();
		DataService dataService = new DataService();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		UserService userService = new UserService();
		if (dataService.isUserNameRight(username) == DataService.USERNAME_FORMAT_ERROR) {
			request.setAttribute("message", "用户名格式不正确！");
			request.getRequestDispatcher("/user/retrieverPasswordIndex.jsp")
					.forward(request, response);
			return;
		}
		try {
			if (!userDaoImpl.usernameIsExist(username)) {
				request.setAttribute("message", "用户名不存在！");
				request.getRequestDispatcher("/user/retrieverPasswordIndex.jsp")
						.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (choose.equals("email")) { // 通过邮箱方式找回密码
			try {
				user = userDaoImpl.getUserByUsername(username);
				userService.findPasswordByEmail(user.getEmail());
				response.getWriter().println(
						"<center><h1>已将找回面链接发至您的邮箱，请尽快前往确认</h1></center>");
				request.getSession().setAttribute("user", user);
				System.out.println(user.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (choose.equals("question")) { // 通过密保问题找回密码
			try {
				user = userDaoImpl.getUserByUsername(username);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/answerQuestion.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
