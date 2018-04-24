package com.zhengjunming.www.controller.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.util.OrderNumberUtil;

/**
 * 购买单种商品控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/BuyGoodsServlet")
public class BuyGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Goods goods = new Goods();
		Order order = new Order();

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String goodsAmount = request.getParameter("goodsAmount");

		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
		try {
			goods = daoImpl.getGoodsById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 判断库存量是否够
		if (Integer.parseInt(goodsAmount) > goods.getQuantity()) {
			request.getSession().setAttribute("message",
					"该商品库存量只剩" + goods.getQuantity() + "件了，请重新设置购买量");
			request.getRequestDispatcher("/user/fillAmount.jsp").forward(
					request, response);
			return;
		}
		order.setOrderNumber(OrderNumberUtil.getOrderIdByUUId()); // 生成订单号
		order.setTotalPrice(new BigDecimal(goods.getSellPrice()).multiply(
				new BigDecimal(goodsAmount)).toString()); // 计算总价格
		order.setOrderDate(new Timestamp(System.currentTimeMillis()));
		request.getSession().setAttribute("amount", goodsAmount);
		request.getSession().setAttribute("singleOrder", order);
		request.getSession().setAttribute("goodsInfo", goods);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/user/fillOrder.jsp");
	}
}
