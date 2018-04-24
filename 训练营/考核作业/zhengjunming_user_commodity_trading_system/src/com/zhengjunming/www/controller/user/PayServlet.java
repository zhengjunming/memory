package com.zhengjunming.www.controller.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.CartDaoImpl;
import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.po.CartItem;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.po.OrderItem;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.util.OrderNumberUtil;

/**
 * 购物车结账控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/PayServlet")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user"); // 获得用户信息
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		String totalPrice = "0"; // 订单总金额
		String totalPrice2 = "0";
		List<CartItem> cartItems = new ArrayList<CartItem>();
		Set<Integer> set = new HashSet<Integer>();
		List<Order> orders = new ArrayList<Order>(); // 订单集合
		Goods goods = new Goods();
		List<String> messages = new ArrayList<String>(); // 存储提示信息
		GoodsDaoImpl godosDaoImpl = new GoodsDaoImpl();
		int cartId = 0;
		try {
			cartItems = cartDaoImpl.getCartItem(user); // 获得购物车条目信息
			cartId = cartDaoImpl.getCartByUserId(user.getId()).getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (CartItem cartItem : cartItems) {
			try {
				goods = godosDaoImpl.getGoodsById(cartItem.getGoods().getId());
				if (cartItem.getGoodsAmount() > goods.getQuantity()) {
					String message = goods.getGoodsName() + "只剩下"
							+ goods.getQuantity() + "件了，请重新设置购买数量";
					messages.add(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			set.add(cartItem.getGoods().getStore().getId()); // 把商家id存入set
		}

		if (messages.size() > 0) { // 如果提示信息不为空，则证明购买失败，提示用户重新设置购买量
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("/user/buyFail.jsp").forward(request,
					response);
			return;
		}
		int orderAmount = set.size(); // 订单数量，即一家店家一份订单
		for (Integer storeId : set) {
			Order order = new Order();
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			order.setOrderNumber(OrderNumberUtil.getOrderIdByUUId()); // 订单编号
			try {
				cartItems = cartDaoImpl.getCartItemByStoreIdAndCartId(storeId,
						cartId); // 得到属于一个商家的购物车条目
			} catch (Exception e) {
				e.printStackTrace();
			}
			Store store = new Store();
			for (CartItem cartItem : cartItems) {
				// 把条目加入订单清单
				OrderItem orderItem = new OrderItem();
				orderItem.setGoodsId(cartItem.getGoods().getId());
				orderItem.setGoodsName(cartItem.getGoods().getGoodsName());
				orderItem.setGoodsAmount(cartItem.getGoodsAmount());
				orderItem.setGoodsPrice(cartItem.getGoods().getSellPrice());
				orderItem.setTotalPrice(cartItem.getTotalPrice());
				// 总价
				totalPrice = new BigDecimal(cartItem.getTotalPrice()).add(
						new BigDecimal(totalPrice)).toString();
				totalPrice2 = new BigDecimal(cartItem.getTotalPrice()).add(
						new BigDecimal(totalPrice2)).toString(); // 不清零，计算所有订单总价
				orderItem.setStore(cartItem.getStore());
				store = cartItem.getStore();
				orderItems.add(orderItem);
			}
			order.setOrderItems(orderItems); // 加入订单
			order.setOrderDate(new Timestamp(System.currentTimeMillis()));
			order.setTotalPrice(totalPrice);
			totalPrice = "0"; // 清零，计算下一份订单总价
			order.setStore(store);
			orders.add(order);
		}
		request.getSession().setAttribute("orders", orders);
		request.getSession().setAttribute("orderAmount", orderAmount);
		request.getSession().setAttribute("totalPrice", totalPrice2);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/fillCartOrder.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
