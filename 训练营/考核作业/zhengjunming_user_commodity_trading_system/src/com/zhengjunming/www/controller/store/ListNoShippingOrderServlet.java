package com.zhengjunming.www.controller.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.OrderDaoImpl;
import com.zhengjunming.www.po.Order;
import com.zhengjunming.www.po.Store;
import com.zhengjunming.www.po.other.OrderStatus;

/**
 * 查看未发货订单控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ListNoShippingOrderServlet")
public class ListNoShippingOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Store store = (Store) request.getSession().getAttribute("store"); // 获得店家信息
		OrderDaoImpl daoImpl = new OrderDaoImpl();

		int pageSize = 5; // 一页显示的数据
		String page = request.getParameter("page");
		int count = 0;
		try {
			count = daoImpl.getOrderCountByStoreIdAndOrderStatus(store.getId(),
					OrderStatus.UNDER_REVIEW.getName()); // 总量
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		int totalPageNum = count / pageSize; // 总页数
		if ((count % pageSize) > 0) {
			totalPageNum++;
		}
		if (page == null) {
			page = "1";
		}
		request.setAttribute("totalPageNum", new Integer(totalPageNum));
		request.getSession().setAttribute("page", new Integer(page));
		request.getSession().setAttribute("count", count);
		List<Order> orders = new ArrayList<Order>();

		try {
			orders = daoImpl.getOrderByStoreIdAndOrderStatus(pageSize,
					Integer.parseInt(page), store.getId(),
					OrderStatus.UNDER_REVIEW.getName());
			request.getSession().setAttribute("orders", orders);
			request.getRequestDispatcher("/store/listNoShippingOrder.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
