<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/user/user.css">
<script type="text/javascript"
	src="/zhengjunming_user_commodity_trading_system/js/user/improveUserInfo.js"></script>
<title>完善用户信息</title>
</head>
<body>
	<c:if test="${empty user }">
		<center>
			<h1>您还未登录，不能访问此页面</h1>
			<h3>
				可前往<a
					href="/zhengjunming_user_commodity_trading_system/user/UserLogin.jsp">登录</a>或者<a
					href="/zhengjunming_user_commodity_trading_system/user/UserRegister.jsp">注册</a>
			</h3>
		</center>
	</c:if>
	<c:if test="${not empty user }">
		<h2>您好：${user.username }用户</h2>
		<nav class="nav">
		<ul>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListAllGoodsServlet">首页</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListCartServlet">我的购物车</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/listMyOrder.jsp">我的订单</a></li>
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/ImproveUserInforServlet">完善个人信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/updatePassword.jsp">修改密码</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<div id="main">
			<div>
				<center>
					<h2 style="color: red;">${message }</h2>
					<form
						action="/zhengjunming_user_commodity_trading_system/HandleUserInfoServlet"
						method="post" class="myform" onsubmit="return check()">
						<h1>完善个人信息</h1>

						<font size="5">用户名：</font><input type="text" name="username"
							class="username" id="username" maxlength="30"
							placeholder="*用户名：手机号码" disabled="true" value="${user.username }" /><br>

						<font size="5">*邮&nbsp;&nbsp;箱：</font><input type="text"
							name="email" class="email" id="email" maxlength="30"
							placeholder="邮箱" value="${user.email }" /><span id="checkEmail"></span><br>

						<font size="5">*密&nbsp;&nbsp;保：</font><input type="text"
							name="question" class="question" id="question" maxlength="30"
							placeholder="密保问题" value="${user.securityQuestion }" /><span
							id="checkQuestion"></span><br> <font size="5">*答&nbsp;&nbsp;案：</font><input
							type="text" name="answer" class="answer" id="answer"
							maxlength="30" placeholder="密保答案"
							value="${user.classifiedAnswer }" /><span id="checkAnswer"></span><br>

						<font size="5">手&nbsp;&nbsp;&nbsp;&nbsp;机：</font><input
							type="text" name="phone" class="phone" id="phone" maxlength="16"
							placeholder="手机号码" value="${user.phone }"><span
							id="checkPhone"></span> <br> <font size="5">住&nbsp;&nbsp;&nbsp;&nbsp;址：</font><input
							type="text" name="address" class="address" id="address"
							maxlength="30" placeholder="住址" value="${user.address }"><span
							id="checkAddress"></span><br> <br />
						<button type="submit" class="button" id="submit">提交</button>
					</form>
				</center>
			</div>
		</div>
	</c:if>
</body>
</html>