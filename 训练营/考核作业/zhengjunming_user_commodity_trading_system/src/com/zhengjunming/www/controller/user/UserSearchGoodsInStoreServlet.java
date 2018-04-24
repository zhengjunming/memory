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
 * 用户在店内搜索商品
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserSearchGoodsInStoreServlet")
public class UserSearchGoodsInStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int storeId = 0;
		try {
			storeId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String key = request.getParameter("key"); // 关键字
		List<Goods> goodses = new ArrayList<Goods>();
		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
		int pageSize = 5; // 一页显示的数据
		String page = request.getParameter("page");

		int count = 0;
		try {
			count = daoImpl.GetGoodsCountByKeyAndStoreId(key, storeId);
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
		try {
			goodses = daoImpl.queryGoodsByKeyAndStoreId(pageSize,
					Integer.parseInt(page), key, storeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("totalPageNum",
				new Integer(totalPageNum));
		request.getSession().setAttribute("count", count);
		request.getSession().setAttribute("page", new Integer(page));
		request.getSession().setAttribute("goodses", goodses);
		request.getSession().setAttribute("key", key);
		request.getSession().setAttribute("id", storeId);
		request.getRequestDispatcher("/user/searchInStoreResult.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
