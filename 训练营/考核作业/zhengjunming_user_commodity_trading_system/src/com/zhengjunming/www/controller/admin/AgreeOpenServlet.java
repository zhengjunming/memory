package com.zhengjunming.www.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.dao.impl.AdminDaoImpl;

/**
 * 同意店家开店操作控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/AgreeOpenServlet")
public class AgreeOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		
		try {
			adminDaoImpl.agreeOpenStore(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/zhengjunming_user_commodity_trading_system/ProcessingRequestServlet");
	}

}
