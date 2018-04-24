package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.CartDaoImpl;
import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.po.CartItem;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.User;

/**
 * 把商品加入购物车控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/AddGoodsToCartServlet")
public class AddGoodsToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user"); // 获取用户信息
		Goods goods = new Goods();
		CartItem cartItem = new CartItem();
		GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();
		CartDaoImpl cartDaoImpl = new CartDaoImpl();

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			goods = goodsDaoImpl.getGoodsById(id);
			int cartId = cartDaoImpl.getCartByUserId(user.getId()).getId();

			// 如果购物车已存在该商品，则数量加一
			if (cartDaoImpl.goodsIsExist(goods, cartId)) {
				cartItem = cartDaoImpl.getCartItemByGoodsIdAndCartId(
						goods.getId(), cartId);
				cartDaoImpl.increaseGoodsCount(cartItem, cartId);
			} else { // 不存在则加入购物车
				cartItem.setCart(cartDaoImpl.getCartByUserId(user.getId()));
				cartItem.setGoods(goods);
				cartDaoImpl.addCartItem(cartItem);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/addCartSuccess.jsp");
	}

}
