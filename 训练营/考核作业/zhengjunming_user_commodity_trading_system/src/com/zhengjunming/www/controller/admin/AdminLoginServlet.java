package com.zhengjunming.www.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.AdminDaoImpl;
import com.zhengjunming.www.po.Admin;
import com.zhengjunming.www.po.other.StoreStatus;
import com.zhengjunming.www.service.admin.AdminService;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.store.StoreService;
import com.zhengjunming.www.util.DigestUtils;

/**
 * 管理员登录控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
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

		if (dataService.isRightPassword(password) == DataService.PASSWORD_FORMAT_ERROR) {
			request.setAttribute("message", "密码格式不对！");
			request.getRequestDispatcher("/admin/login.jsp").forward(request,
					response);
			return;
		}

		if (checkcode_session == null || !checkcode_session.equals(checkcode)) {
			request.setAttribute("message", "验证码错误");
			request.getRequestDispatcher("/admin/login.jsp").forward(request,
					response);
			return;
		}

		Admin admin = new Admin();
		password = DigestUtils.md5(DigestUtils.md5(password)); // 进行两次MD5加密
		admin.setAdminName(username);
		admin.setAdminPassword(password);

		// 进行登录比对
		AdminService adminService = new AdminService();

		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();

		try {
			int result = adminService.login(admin);
			if (result == AdminService.SUCCESS) {
				admin = adminDaoImpl.adminData(admin);
				request.getSession().setAttribute("admin", admin);
				request.getSession().setAttribute("status1",
						StoreStatus.UNDER_REVIEW.getName());
				request.getSession().setAttribute("status2",
						StoreStatus.BE_REJECTED.getName());
				request.getSession().setAttribute("status3",
						StoreStatus.SUSEESSFULLY_OPENED.getName());
				request.setAttribute("message", "登录成功");
				response.sendRedirect("/zhengjunming_user_commodity_trading_system/admin/index.jsp");
			} else if (result == StoreService.USERNAME_NOT_EXIST) {
				request.setAttribute("message", "该用户名不存在！");
				request.getRequestDispatcher("/admin/login.jsp").forward(
						request, response);
			} else if (result == StoreService.FAIL) {
				request.setAttribute("message", "密码输入错误！");
				request.getRequestDispatcher("/admin/login.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
