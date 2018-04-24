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
 * 进入某一店铺控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/GoIntoStoreServlet")
public class GoIntoStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
		StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
		int pageSize = 5; // 一页显示的数据
		String page = request.getParameter("page");
		Store store = new Store();

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id")); // 得到店家id
			store = storeDaoImpl.getStoreInfo(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int count = 0;
		try {
			count = daoImpl.GetStoreGoodsCount(store);
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
		List<Goods> goodses = new ArrayList<Goods>();
		try {
			goodses = daoImpl.ListStoreGoods(pageSize, Integer.parseInt(page),
					store);
			request.getSession().setAttribute("count", count);
			request.getSession().setAttribute("goodses", goodses);
			request.getSession().setAttribute("store", store);
			request.getSession().setAttribute("id", id); // 把店家id存入Session
			request.getRequestDispatcher("/user/goIntoStoreIndex.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
