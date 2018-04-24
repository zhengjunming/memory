package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.po.User;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.user.UserService;

/**
 * 会员注册控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String checkcode = request.getParameter("checkcode"); // 获取验证码信息
		String username = request.getParameter("username"); // 获取用户名
		String password = request.getParameter("password"); // 获取密码
		String repassword = request.getParameter("repassword"); // 获取重复输入的密码
		String email = request.getParameter("email"); // 获得邮箱
		String securityQuestion = request.getParameter("question"); // 获得密保问题
		String classifiedAnswer = request.getParameter("answer"); // 获得密保答案

		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session"); // 获取Session中的验证码信息

		// 对用户输入的信息进行一一比对
		DataService dataService = new DataService();
		if (dataService.isUserNameRight(username) == DataService.USERNAME_FORMAT_ERROR) {
			request.setAttribute("message", "用户名格式不正确！");
			request.getRequestDispatcher("/user/UserRegister.jsp").forward(
					request, response);
			return;
		}

		if (dataService.isRightPassword(password) == DataService.PASSWORD_FORMAT_ERROR) {
			request.setAttribute("message", "密码格式不对！");
			request.getRequestDispatcher("/user/UserRegister.jsp").forward(
					request, response);
			return;
		}
		if (dataService.isConfirmRight(password, repassword) == DataService.CHECK_PASSWORD_ERROR) {
			request.setAttribute("message", "两次输入的密码不一致");
			request.getRequestDispatcher("/user/UserRegister.jsp").forward(
					request, response);
			return;
		}
		if (dataService.isRightEmail(email) == DataService.EMAIL_FORMAT_ERROR) {
			request.setAttribute("message", "邮箱格式不正确");
			request.getRequestDispatcher("/user/UserRegister.jsp").forward(
					request, response);
			return;
		}
		if (checkcode_session == null || !checkcode_session.equals(checkcode)) {
			request.setAttribute("message", "验证码错误");
			request.getRequestDispatcher("/user/UserRegister.jsp").forward(
					request, response);
			return;
		}

		// 封装数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(repassword);
		user.setEmail(email);
		user.setSecurityQuestion(securityQuestion);
		user.setClassifiedAnswer(classifiedAnswer);

		// 进行注册操作
		UserService us = new UserService();
		try {
			int result = us.register(user);
			if (result == UserService.USERNAME_EXIST) {
				request.setAttribute("message", "该用户名已被注册");
				request.getRequestDispatcher("/user/UserRegister.jsp").forward(
						request, response);
			} else if (result == UserService.EMAIL_EXIST) {
				request.setAttribute("message", "该邮箱已被注册");
				request.getRequestDispatcher("/user/UserRegister.jsp").forward(
						request, response);
			} else if (result == UserService.SUCCESS) {
				response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/registerSuccess.jsp");
			} else {
				request.setAttribute("message", "注册失败");
				request.getRequestDispatcher("/user/UserRegister.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
