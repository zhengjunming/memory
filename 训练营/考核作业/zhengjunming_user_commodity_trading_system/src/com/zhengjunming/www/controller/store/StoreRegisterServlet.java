package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.store.StoreService;

/**
 * 店家注册Servlet
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/StoreRegisterServlet")
public class StoreRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 得到注册信息
		String storeUsername = request.getParameter("username");
		String storePassword = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String storeOwnerName = request.getParameter("realname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String storeName = request.getParameter("storeName");
		String storeDescription = request.getParameter("storeDescription");
		String checkcode = request.getParameter("checkcode");

		DataService dataService = new DataService();
		if (dataService.isUserNameRight(storeUsername) == DataService.USERNAME_FORMAT_ERROR) {
			request.setAttribute("message", "用户名格式不正确！");
			request.getRequestDispatcher("/store/openStore.jsp").forward(
					request, response);
			return;
		}

		if (dataService.isRightPassword(storePassword) == DataService.PASSWORD_FORMAT_ERROR) {
			request.setAttribute("message", "密码格式不对！");
			request.getRequestDispatcher("/store/openStore.jsp").forward(
					request, response);
			return;
		}
		if (dataService.isConfirmRight(storePassword, repassword) == DataService.CHECK_PASSWORD_ERROR) {
			request.setAttribute("message", "两次输入的密码不一致");
			request.getRequestDispatcher("/store/openStore.jsp").forward(
					request, response);
			return;
		}
		if (dataService.isRightEmail(email) == DataService.EMAIL_FORMAT_ERROR) {
			request.setAttribute("message", "邮箱格式不正确");
			request.getRequestDispatcher("/store/openStore.jsp").forward(
					request, response);
			return;
		}
		if (dataService.isPhoneRight(phone) == DataService.PHONE_FORMAT_ERROR) {
			request.setAttribute("message", "手机号码格式不正确");
			request.getRequestDispatcher("/store/openStore.jsp").forward(
					request, response);
			return;
		}
		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session"); // 获取Session中的验证码信息
		if (checkcode_session == null || !checkcode_session.equals(checkcode)) {
			request.setAttribute("message", "验证码错误");
			request.getRequestDispatcher("/store/openStore.jsp").forward(
					request, response);
			return;
		}

		// 封装数据
		Store store = new Store();
		store.setStoreUsername(storeUsername);
		store.setStorePassword(storePassword);
		store.setStoreOwnerName(storeOwnerName);
		store.setPhone(phone);
		store.setEmail(email);
		store.setStoreName(storeName);
		store.setStoreDescription(storeDescription);

		// 进行注册操作
		StoreService storeService = new StoreService();
		try {
			int result = storeService.register(store);
			if (result == StoreService.USERNAME_EXIST) {
				request.setAttribute("message", "该用户名已被注册");
				request.getRequestDispatcher("/store/openStore.jsp").forward(
						request, response);
			} else if (result == StoreService.EMAIL_EXIST) {
				request.setAttribute("message", "该邮箱已被注册");
				request.getRequestDispatcher("/store/openStore.jsp").forward(
						request, response);
			} else if (result == StoreService.SUCCESS) {
				response.setHeader("refresh",
						"2;URL=/zhengjunming_user_commodity_trading_system/store/storeLogin.jsp");
				response.getWriter().println("<h1>注册成功，2秒后转向登录界面</h1>");
			} else {
				request.setAttribute("message", "注册失败");
				request.getRequestDispatcher("/store/openStore.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
