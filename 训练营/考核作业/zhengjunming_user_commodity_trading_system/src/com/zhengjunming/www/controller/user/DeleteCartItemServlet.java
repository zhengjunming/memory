package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.CartDaoImpl;
import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.User;

/**
 * 删除购物车条目控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/DeleteCartItemServlet")
public class DeleteCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user"); // 获取用户信息
		Goods goods = new Goods();
		GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			goods = goodsDaoImpl.getGoodsById(id);
			int cartId = cartDaoImpl.getCartByUserId(user.getId()).getId();
			cartDaoImpl.deleteCartItem(goods.getId(), cartId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/ListCartServlet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
