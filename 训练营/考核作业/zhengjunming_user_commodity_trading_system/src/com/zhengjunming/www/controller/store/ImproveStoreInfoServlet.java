package com.zhengjunming.www.controller.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.StoreDaoImpl;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.service.other.DataService;

/**
 * 完善/修改店铺信息控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ImproveStoreInfoServlet")
public class ImproveStoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String storeOwnerName = request.getParameter("realname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String storeName = request.getParameter("storeName");
		String storeDescription = request.getParameter("storeDescription");

		DataService dataService = new DataService();

		// 信息校对
		if (dataService.isRightEmail(email) == DataService.EMAIL_FORMAT_ERROR) {
			request.setAttribute("message", "邮箱格式不正确");
			request.getRequestDispatcher("/store/improveStoreInfo.jsp")
					.forward(request, response);
			return;
		}
		if (dataService.isPhoneRight(phone) == DataService.PHONE_FORMAT_ERROR) {
			request.setAttribute("message", "手机号码格式不正确");
			request.getRequestDispatcher("/store/improveStoreInfo.jsp")
					.forward(request, response);
			return;
		}

		Store store = (Store) request.getSession().getAttribute("store");
		StoreDaoImpl daoImpl = new StoreDaoImpl();

		List<String> emails = null;
		List<String> phones = null;

		// 判断手机号或者邮箱是否已被人使用
		try {
			emails = daoImpl.queryEmail(store);
			phones = daoImpl.queryPhone(store);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for (String string : phones) {
			if (string.equals(phone)) {
				request.setAttribute("message", "手机号码已被人使用");
				request.getRequestDispatcher("/store/improveStoreInfo.jsp")
						.forward(request, response);
				return;
			}
		}
		for (String string : emails) {
			if (string.equals(email)) {
				request.setAttribute("message", "邮箱已被人使用");
				request.getRequestDispatcher("/store/improveStoreInfo.jsp")
						.forward(request, response);
				return;
			}
		}
		try {
			daoImpl.improveStoreInfo(store, storeOwnerName, phone, email,
					storeName, storeDescription); // 修改店家信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		store.setStoreOwnerName(storeOwnerName);
		store.setPhone(phone);
		store.setEmail(email);
		store.setStoreName(storeName);
		store.setStoreDescription(storeDescription);
		request.getSession().setAttribute("store", store);
		response.setHeader("refresh",
				"3;URL=/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet");
		response.getWriter().println("<h1>3秒后跳转回主页</h1>");

	}

}
