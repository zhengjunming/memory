package com.zhengjunming.www.controller.store;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.store.StoreService;
import com.zhengjunming.www.util.DigestUtils;

/**
 * 店家修改密码控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/StoreModifyPasswordServlet")
public class StoreModifyPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
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
			request.getRequestDispatcher("/store/modifyPassword.jsp").forward(
					request, response);
			return;
		}
		Store store = (Store) request.getSession().getAttribute("store");
		if (store == null) {
			request.setAttribute("message", "你还未登录！");
			request.getRequestDispatcher("/store/storeLogin.jsp").forward(
					request, response);

		} else {

			oldPassword = DigestUtils.md5(oldPassword); // 对输入的旧密码进行加密
			newPassword = DigestUtils.md5(newPassword); // 对输入的新密码进行加密

			StoreService storeService = new StoreService();

			try {
				int result = storeService.passwordRight(store, oldPassword,
						newPassword); // 判断密码是否正确
				if (result == StoreService.SUCCESS) {
					response.setHeader("refresh",
							"3;URL=/zhengjunming_user_commodity_trading_system/store/storeLogin.jsp");
					request.getSession().invalidate(); // 销毁Session
					response.getWriter().println("<h1>修改密码成功，3秒后转向登录界面</h1>");
				} else if (result == StoreService.FAIL) {
					request.setAttribute("message", "旧密码输入错误，请重新输入！");
					request.getRequestDispatcher("/store/modifyPassword.jsp")
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
