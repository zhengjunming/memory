package com.zhengjunming.www.controller.user;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.po.User;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.user.UserService;
import com.zhengjunming.www.util.DigestUtils;

/**
 * 用户修改密码控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserUpdatePasswordServlet")
public class UserUpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得用户输入的旧密码和新密码
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String repassword = request.getParameter("repassword");
		// 检验用户输入的密码格式
		DataService dataService = new DataService();
		if (dataService.isRightPassword(oldPassword) == DataService.PASSWORD_FORMAT_ERROR
				|| dataService.isRightPassword(newPassword) == DataService.PASSWORD_FORMAT_ERROR
				|| dataService.isRightPassword(repassword) == DataService.PASSWORD_FORMAT_ERROR) {
			request.setAttribute("message", "密码格式不对");
			request.getRequestDispatcher("/user/updatePassword.jsp").forward(
					request, response);
			return;
		}
		User user = (User) request.getSession().getAttribute("user"); // 获得Session中的用户信息

		if (user == null) {
			request.setAttribute("message", "你还未登录！");
			request.getRequestDispatcher("/user/UserLogin.jsp").forward(
					request, response);

		} else {
			oldPassword = DigestUtils.md5(oldPassword); // 对输入的旧密码进行加密
			newPassword = DigestUtils.md5(newPassword); // 对输入的新密码进行加密
			UserService us = new UserService();
			try {
				int result = us.passwordRight(user, oldPassword, newPassword); // 判断密码是否正确
				if (result == UserService.SUCCESS) {
					response.setHeader("refresh",
							"3;URL=/zhengjunming_user_commodity_trading_system/user/UserLogin.jsp");
					request.getSession().invalidate(); // 销毁Session
					response.getWriter().println("<h1>修改密码成功，3秒后转向登录界面</h1>");
				} else if (result == UserService.FAIL) {
					request.setAttribute("message", "旧密码输入错误，请重新输入！");
					request.getRequestDispatcher("/user/updatePassword.jsp")
							.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		try {
			DriverManager
					.deregisterDriver(DriverManager
							.getDriver("jdbc:mysql://localhost:3306/user_commodity_trading_system"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
