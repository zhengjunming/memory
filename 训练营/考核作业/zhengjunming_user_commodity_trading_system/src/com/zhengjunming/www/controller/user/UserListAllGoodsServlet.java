package com.zhengjunming.www.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.po.Goods;

/**
 * 罗列商品控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserListAllGoodsServlet")
public class UserListAllGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GoodsDaoImpl daoImpl = new GoodsDaoImpl();

		int pageSize = 5; // 一页显示的数据
		String page = request.getParameter("page");

		int count = 0;
		try {
			count = daoImpl.GetGoodsCount();
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
		request.getSession().setAttribute("count", count);
		request.getSession().setAttribute("totalPageNum",
				new Integer(totalPageNum));
		request.getSession().setAttribute("page", new Integer(page));
		List<Goods> goodses = new ArrayList<Goods>();
		try {
			goodses = daoImpl.ListGoods(pageSize, Integer.parseInt(page));
			request.setAttribute("goodses", goodses);
			request.getRequestDispatcher("/user/index.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
