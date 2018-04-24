package com.zhengjunming.www.controller.store;

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
import com.zhengjunming.www.po.Store;

/**
 * 店家搜索店铺商品控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/StoreSearchServlet")
public class StoreSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Store store = (Store) request.getSession().getAttribute("store");
		String key = request.getParameter("key"); // 搜索关键字
		List<Goods> goodses = new ArrayList<Goods>();
		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
		int pageSize = 5; // 一页显示的数据
		String page = request.getParameter("page");

		int count = 0;
		try {
			count = daoImpl.storeGetGoodsCountByKeyAndStoreId(key,
					store.getId());
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
			goodses = daoImpl.storeQueryGoodsByKeyAndStoreId(pageSize,
					Integer.parseInt(page), key, store.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("totalPageNum",
				new Integer(totalPageNum));
		request.getSession().setAttribute("count", count);
		request.getSession().setAttribute("page", new Integer(page));
		request.getSession().setAttribute("goodses", goodses);
		request.getSession().setAttribute("key", key);
		request.getRequestDispatcher("/store/storeSearchResult.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
