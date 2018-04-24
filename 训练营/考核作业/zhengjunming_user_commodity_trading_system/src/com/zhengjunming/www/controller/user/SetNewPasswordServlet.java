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
import com.zhengjunming.www.util.DigestUtils;

/**
 * 处理找回密码后的新密码控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/SetNewPasswordServlet")
public class SetNewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("newPassword");
		String repassword = request.getParameter("repassword");
		User user = (User) request.getSession().getAttribute("user"); // 获得Session中的用户信息
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		// 检验用户输入的密码格式
		DataService dataService = new DataService();
		if (dataService.isRightPassword(newPassword) == DataService.PASSWORD_FORMAT_ERROR
				|| dataService.isRightPassword(repassword) == DataService.PASSWORD_FORMAT_ERROR) {
			request.setAttribute("message", "密码格式不对");
			request.getRequestDispatcher("/user/setNewPassword.jsp").forward(
					request, response);
			return;
		}
		newPassword = DigestUtils.md5(newPassword); // 对输入的新密码进行加密
		try {
			userDaoImpl.update(user, newPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("refresh",
				"2;URL=/zhengjunming_user_commodity_trading_system/user/UserLogin.jsp");
		request.getSession().invalidate(); // 销毁Session
		response.getWriter().println("<h1>找回密码成功，2秒后转向登录界面</h1>");
	}
}
