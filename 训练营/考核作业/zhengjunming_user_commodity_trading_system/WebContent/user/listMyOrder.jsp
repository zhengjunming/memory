<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/user/listMyOrder.css">
<title>订单处理首页</title>
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
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/user/listMyOrder.jsp">处理订单首页</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListNoShippingOrderServlet">待发货</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListShippedOrderServlet">待收货</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListReceivedOrderServlet">已收货订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListEvaluatedOrderServlet">已评价订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListCancelOrderServlet">已取消订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListAllGoodsServlet">返回主页面</a></li>
		</ul>
		</nav>
		<h2></h2>
		<p>您可以对您的订单进行相应的操作！</p>
	</c:if>
</body>
</html>