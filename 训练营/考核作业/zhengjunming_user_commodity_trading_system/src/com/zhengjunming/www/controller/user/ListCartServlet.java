package com.zhengjunming.www.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.CartDaoImpl;
import com.zhengjunming.www.po.CartItem;
import com.zhengjunming.www.po.User;

/**
 * 进入购物车控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ListCartServlet")
public class ListCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user"); // 获取用户信息
		CartDaoImpl daoImpl = new CartDaoImpl();
		List<CartItem> cartItems = new ArrayList<CartItem>();
		try {
			cartItems = daoImpl.getCartItem(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("cartItems", cartItems);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/cartIndex.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
