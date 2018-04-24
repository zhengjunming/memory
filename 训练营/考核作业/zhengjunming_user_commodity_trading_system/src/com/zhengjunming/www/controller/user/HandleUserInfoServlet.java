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

/**
 * 处理用户修改完信息的控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HandleUserInfoServlet")
public class HandleUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String securityQuestion = request.getParameter("question");
		String classifiedAnswer = request.getParameter("answer");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		DataService dataService = new DataService();
		if (dataService.isPhoneRight(phone) == DataService.USERNAME_FORMAT_ERROR) {
			request.setAttribute("message", "手机格式不正确！");
			request.getRequestDispatcher("/ImproveUserInforServlet").forward(
					request, response);
			return;
		}
		if (dataService.isRightEmail(email) == DataService.EMAIL_FORMAT_ERROR) {
			request.setAttribute("message", "邮箱格式不正确");
			request.getRequestDispatcher("/ImproveUserInforServlet").forward(
					request, response);
		}
		User user = (User) request.getSession().getAttribute("user");
		user.setEmail(email);
		user.setSecurityQuestion(securityQuestion);
		user.setClassifiedAnswer(classifiedAnswer);
		user.setPhone(phone);
		user.setAddress(address);

		UserDaoImpl daoImpl = new UserDaoImpl();
		try {
			daoImpl.improveUserInfo(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/UserListAllGoodsServlet");

	}

}
