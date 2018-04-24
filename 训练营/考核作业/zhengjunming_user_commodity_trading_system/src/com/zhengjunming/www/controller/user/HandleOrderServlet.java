package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.dao.impl.OrderDaoImpl;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.po.OrderItem;
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.po.other.GoodsStatus;

/**
 * 处理单种商品订单控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/HandleOrderServlet")
public class HandleOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Order order = (Order) request.getSession().getAttribute("singleOrder"); // 获取订单信息
		Goods goods = (Goods) request.getSession().getAttribute("goodsInfo"); // 获取商品信息
		User user = (User) request.getSession().getAttribute("user"); // 获取用户信息
		String goodsAmount = (String) request.getSession().getAttribute(
				"amount"); // 获取商品数量
		OrderItem item = new OrderItem();
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		String message = request.getParameter("message");
		if (receiver == "" || address == "" || phone == "") {
			request.getSession().setAttribute("msg", "必填信息不能为空");
			request.getRequestDispatcher("/user/fillOrder.jsp").forward(
					request, response);
			return;
		}
		order.setAddress(address);
		order.setMessage(message);
		order.setPhone(phone);
		order.setReceiver(receiver);
		order.setUser(user);
		order.setStore(goods.getStore());
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		// 添加订单
		try {
			daoImpl.addOrder(order);
			order = daoImpl.getOrderByOrderNumber(order.getOrderNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 添加订单条目
		item.setOrder(order);
		item.setStore(goods.getStore());
		item.setGoodsAmount(Integer.parseInt(goodsAmount));
		item.setGoodsId(goods.getId());
		item.setGoodsName(goods.getGoodsName());
		item.setGoodsPrice(goods.getSellPrice()); // 单品价格
		item.setTotalPrice(order.getTotalPrice()); // 总价格
		try {
			daoImpl.addOrderItem(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 修改销售量和库存量
		GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();
		try {
			goodsDaoImpl.updateSellCountAndQuantity(
					Integer.parseInt(goodsAmount), goods);
			if (goods.getQuantity() == Integer.parseInt(goodsAmount)) {
				goodsDaoImpl.updateGoodsStatus(
						GoodsStatus.OUT_OF_STOCK.getName(), goods.getId());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/shoppingSuccess.jsp");
	}

}
