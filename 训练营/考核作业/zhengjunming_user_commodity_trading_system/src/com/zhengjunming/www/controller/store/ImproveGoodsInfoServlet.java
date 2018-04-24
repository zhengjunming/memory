package com.zhengjunming.www.controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.GoodsDaoImpl;
import com.zhengjunming.www.po.Goods;

/**
 * 修改商品信息控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/ImproveGoodsInfoServlet")
public class ImproveGoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id1 = request.getParameter("id");
		int id = 0;
		if (id1 != null) {
			id = Integer.parseInt(id1);
		}
		GoodsDaoImpl daoImpl = new GoodsDaoImpl();
		Goods goods = new Goods();
		try {
			goods = daoImpl.getGoodsById(id); // 得到待修改的商品
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("goodsInfo", goods);
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/store/improveGoodsInfo.jsp");

	}

}
