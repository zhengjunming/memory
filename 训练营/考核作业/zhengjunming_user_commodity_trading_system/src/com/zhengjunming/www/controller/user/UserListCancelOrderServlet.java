package com.zhengjunming.www.controller.user;

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
import com.zhengjunming.www.po.User;
import com.zhengjunming.www.po.other.OrderStatus;

/**
 * 用户查看已取消订单控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserListCancelOrderServlet")
public class UserListCancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user"); // 获得用户信息
		OrderDaoImpl daoImpl = new OrderDaoImpl();

		int pageSize = 5; // 一页显示的数据
		String page = request.getParameter("page");
		int count = 0;
		try {
			count = daoImpl.getOrderCountByUserIdAndOrderStatus(user.getId(),
					OrderStatus.CANCEL_ORDER.getName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		int totalPageNum = count / pageSize;
		if ((count % pageSize) > 0) {
			totalPageNum++;
		}
		if (page == null) {
			page = "1";
		}
		request.setAttribute("totalPageNum", new Integer(totalPageNum));
		request.getSession().setAttribute("page", new Integer(page));
		List<Order> orders = new ArrayList<Order>();

		try {
			orders = daoImpl.getOrderByUserIdAndOrderStatus(pageSize,
					Integer.parseInt(page), user.getId(),
					OrderStatus.CANCEL_ORDER.getName());
			request.getSession().setAttribute("orders", orders);
			request.getSession().setAttribute("count", count);
			request.getRequestDispatcher("/user/listCancelOrder.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
