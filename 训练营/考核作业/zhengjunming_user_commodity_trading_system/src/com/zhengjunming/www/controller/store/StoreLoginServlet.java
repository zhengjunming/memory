package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.StoreDaoImpl;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.po.other.GoodsStatus;
import com.zhengjunming.www.po.other.StoreStatus;
import com.zhengjunming.www.service.other.DataService;
import com.zhengjunming.www.service.store.StoreService;

/**
 * 店家登录控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/StoreLoginServlet")
public class StoreLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			request.getRequestDispatcher("/store/storeLogin.jsp").forward(
					request, response);
			return;
		}
		if (dataService.isRightPassword(password) == DataService.PASSWORD_FORMAT_ERROR) {
			request.setAttribute("message", "密码格式不对！");
			request.getRequestDispatcher("/store/storeLogin.jsp").forward(
					request, response);
			return;
		}

		if (checkcode_session == null || !checkcode_session.equals(checkcode)) {
			request.setAttribute("message", "验证码错误");
			request.getRequestDispatcher("/store/storeLogin.jsp").forward(
					request, response);
			return;
		}
		Store store = new Store();
		store.setStoreUsername(username);
		store.setStorePassword(password);

		// 进行登录比对
		StoreService storeService = new StoreService();

		StoreDaoImpl storeDaoImpl = new StoreDaoImpl();

		try {
			int result = storeService.login(store);
			if (result == StoreService.SUCCESS) {
				store = storeDaoImpl.storeData(store);
				System.out.println(store.toString());
				request.getSession().setAttribute("store", store); // 登录的用户信息存入Session
				// 把店家状态存入Session
				request.getSession().setAttribute("storeStatus1",
						StoreStatus.UNDER_REVIEW.getName());
				request.getSession().setAttribute("storeStatus2",
						StoreStatus.BE_REJECTED.getName());
				request.getSession().setAttribute("storeStatus3",
						StoreStatus.SUSEESSFULLY_OPENED.getName());
				// 把商品状态存入Session
				request.getSession().setAttribute("goodsStatus1",
						GoodsStatus.OUT_OF_STOCK);
				request.getSession().setAttribute("goodsStatus2",
						GoodsStatus.HAS_BEEN_SHELVED);
				request.getSession().setAttribute("goodsStatus3",
						GoodsStatus.HAS_BEEN_UNDER_SHELVED);
				request.setAttribute("message", "登录成功");
				response.sendRedirect("/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet");
			} else if (result == StoreService.USERNAME_NOT_EXIST) {
				request.setAttribute("message", "该用户名不存在！");
				request.getRequestDispatcher("/store/storeLogin.jsp").forward(
						request, response);
			} else if (result == StoreService.FAIL) {
				request.setAttribute("message", "密码输入错误！");
				request.getRequestDispatcher("/store/storeLogin.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
