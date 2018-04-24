package com.zhengjunming.www.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhengjunming.www.po.User;

/**
 * 检查密保问题控制器
 * 
 * @author 郑俊铭
 *
 */
@WebServlet("/CheckAnswerServlet")
public class CheckAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String answer = request.getParameter("answer"); // 得到用户输入的答案
		User user = (User) request.getSession().getAttribute("user");
		if (answer.equals(user.getClassifiedAnswer())) { // 回答正确
			request.getRequestDispatcher("/user/setNewPassword.jsp").forward(
					request, response);
		} else {
			request.getSession().setAttribute("message", "密保回答错误");
			request.getRequestDispatcher("user/answerQuestion.jsp").forward(
					request, response);
		}
	}

}
