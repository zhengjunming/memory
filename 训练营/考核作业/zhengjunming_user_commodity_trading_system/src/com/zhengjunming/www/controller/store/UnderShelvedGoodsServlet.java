package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.StoreDaoImpl;

/**
 * 下架商品控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UnderShelvedGoodsServlet")
public class UnderShelvedGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id1 = request.getParameter("id"); // 商品id
		int id = 0;
		try {
			id = Integer.parseInt(id1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		try {
			daoImpl.underShelvedGoods(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/ListNotUnderShelvedGoodsServlet");
	}

}
