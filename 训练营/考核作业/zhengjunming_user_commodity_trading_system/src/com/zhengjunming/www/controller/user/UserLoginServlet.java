package com.zhengjunming.www.controller.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zhengjunming.www.dao.impl.CartDaoImpl;
import com.zhengjunming.www.dao.impl.UserDaoImpl;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.user.UserService;

/**
 * 用户登录控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");

		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session"); // 获取Session中的验证码信息

		// 对用户输入的信息进行一一比对
		DataService dataService = new DataService();

		if (dataService.isUserNameRight(username) == DataService.USERNAME_FORMAT_ERROR) {
			request.setAttribute("message", "手机格式不正确！");
			request.getRequestDispatcher("/user/UserLogin.jsp").forward(
					request, response);
			return;
		}
		if (dataService.isRightPassword(password) == DataService.PASSWORD_FORMAT_ERROR) {
			request.setAttribute("message", "密码格式不对！");
			request.getRequestDispatcher("/user/UserLogin.jsp").forward(
					request, response);
			return;
		}

		if (checkcode_session == null || !checkcode_session.equals(checkcode)) {
			request.setAttribute("message", "验证码错误");
			request.getRequestDispatcher("/user/UserLogin.jsp").forward(
					request, response);
			return;
		}

		User user = new User();
		// 进行登录比对
		UserService us = new UserService();

		// 将User数据进行封装
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		UserDaoImpl daoImpl = new UserDaoImpl();
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		try {
			int result = us.login(user);
			if (result == UserService.SUCCESS) {
				user = daoImpl.UserData(user);
				if (!user.isStatus()) {
					request.setAttribute("message", "当前用户还未激活，请前往邮箱激活");
					request.getRequestDispatcher("/user/UserLogin.jsp")
							.forward(request, response);
					return;
				}
				request.getSession().setAttribute("user", user); // 登录的用户信息存入Session
				if (!cartDaoImpl.isUserhasCart(user)) {
					cartDaoImpl.addCart(user);
				}
				request.setAttribute("message", "登录成功");
				response.sendRedirect("/zhengjunming_user_commodity_trading_system/UserListAllGoodsServlet");
			} else if (result == UserService.USERNAME_NOT_EXIST) {
				request.setAttribute("message", "该用户名不存在！");
				request.getRequestDispatcher("/user/UserLogin.jsp").forward(
						request, response);
			} else if (result == UserService.FAIL) {
				request.setAttribute("message", "密码输入错误！");
				request.getRequestDispatcher("/user/UserLogin.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
