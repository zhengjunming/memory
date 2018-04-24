package com.zhengjunming.www.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.AdminDaoImpl;
import com.zhengjunming.www.po.Store;

/**
 * 管理店铺控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ManageShopServlet")
public class ManageShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Store> stores = new ArrayList<Store>();
		
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		try {
			stores = adminDaoImpl.getAllHadOpenStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("stores", stores);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/admin/listStoreInOpen.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
