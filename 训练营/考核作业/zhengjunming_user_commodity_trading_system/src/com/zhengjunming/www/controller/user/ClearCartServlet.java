package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.CartDaoImpl;
import com.zhengjunming.www.po.User;

/**
 * 清空购物车
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		CartDaoImpl cartDaoImpl = new CartDaoImpl();

		try {
			int cartId = cartDaoImpl.getCartByUserId(user.getId()).getId();
			cartDaoImpl.clearCart(cartId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/ListCartServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
