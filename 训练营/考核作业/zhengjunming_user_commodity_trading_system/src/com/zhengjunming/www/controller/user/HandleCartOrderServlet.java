package com.zhengjunming.www.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.CartDaoImpl;
import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.dao.impl.OrderDaoImpl;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.po.OrderItem;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.po.other.GoodsStatus;

/**
 * 处理购物车订单控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HandleCartOrderServlet")
public class HandleCartOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Order> orders = (List<Order>) request.getSession().getAttribute(
				"orders");

		User user = (User) request.getSession().getAttribute("user"); // 获取用户信息
		Goods goods = new Goods();
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		if (receiver == "" || address == "" || phone == "") {
			request.getSession().setAttribute("msg", "必填信息不能为空");
			request.getRequestDispatcher("/user/fillCartOrder.jsp").forward(
					request, response);
			return;
		}

		for (Order order : orders) { // 遍历订单
			order.setAddress(address);
			order.setPhone(phone);
			order.setReceiver(receiver);
			order.setUser(user);
			order.setStore(order.getStore());

			// 添加订单
			OrderDaoImpl daoImpl = new OrderDaoImpl();
			Order order2 = new Order();
			try {
				daoImpl.addOrder(order);
				order2 = daoImpl.getOrderByOrderNumber(order.getOrderNumber());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(order.getOrderItems().toString());
			for (OrderItem item : order.getOrderItems()) {
				// 添加订单条目
				item.setOrder(order2);
				try {
					daoImpl.addOrderItem(item);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 修改销售量和库存量
				GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();
				try {
					goods = goodsDaoImpl.getGoodsById(item.getGoodsId());
					goodsDaoImpl.updateSellCountAndQuantity(
							item.getGoodsAmount(),
							goodsDaoImpl.getGoodsById(item.getGoodsId()));
					if (goods.getQuantity() == item.getGoodsAmount()) {
						// 如果库存量与购买量相等，则商品状态设置断货
						goodsDaoImpl.updateGoodsStatus(
								GoodsStatus.OUT_OF_STOCK.getName(),
								goods.getId());
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 清空购物车
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		try {
			int cartId = cartDaoImpl.getCartByUserId(user.getId()).getId();
			cartDaoImpl.clearCart(cartId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("refresh",
				"2;URL=/zhengjunming_user_commodity_trading_system/UserListAllGoodsServlet");
		response.getWriter().println("<center><h1>购买成功，2秒后转向商城主页</h1></center>");
	}
}
