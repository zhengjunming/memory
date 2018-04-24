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
import com.zhengjunming.www.dao.impl.StoreDaoImpl;
import com.zhengjunming.www.po.Goods;
import com.zhengjunming.www.po.Store;

/**
 * 用户查询商品控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("query_key"); // 搜索关键字
		String choose = request.getParameter("choose"); // 搜索选择
		String page = request.getParameter("page");
		if (choose.equals("goods")) { // 搜索商品
			List<Goods> goodses = new ArrayList<Goods>();
			GoodsDaoImpl daoImpl = new GoodsDaoImpl();

			int pageSize = 5; // 一页显示的数据

			int count = 0;
			try {
				count = daoImpl.GetGoodsCountByKey(key);
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
				goodses = daoImpl.queryGoodsByKey(pageSize,
						Integer.parseInt(page), key);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("totalPageNum",
					new Integer(totalPageNum));
			request.getSession().setAttribute("count", count);
			request.getSession().setAttribute("page", new Integer(page));
			request.getSession().setAttribute("goodses", goodses);
			request.getSession().setAttribute("key", key);
			request.getSession().setAttribute("choose", choose);
			request.getRequestDispatcher("/user/searchResult.jsp").forward(
					request, response);
		} else if (choose.equals("store")) { // 搜索店家
			List<Store> stores = new ArrayList<Store>();
			StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
			int pageSize = 5; // 一页显示的数据

			int count = 0;
			if (page == null) {
				page = "1";
			}
			try {
				count = storeDaoImpl.getStoreCountByKey(pageSize,
						Integer.parseInt(page), key);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			int totalPageNum = count / pageSize;
			if ((count % pageSize) > 0) {
				totalPageNum++;
			}

			try {
				stores = storeDaoImpl.queryStoreByKey(pageSize,
						Integer.parseInt(page), key);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("totalPageNum",
					new Integer(totalPageNum));
			request.getSession().setAttribute("count", count);
			request.getSession().setAttribute("page", new Integer(page));
			request.getSession().setAttribute("stores", stores);
			request.getSession().setAttribute("choose", choose);
			request.getSession().setAttribute("key", key);
			request.getRequestDispatcher("/user/searchResult.jsp").forward(
					request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
