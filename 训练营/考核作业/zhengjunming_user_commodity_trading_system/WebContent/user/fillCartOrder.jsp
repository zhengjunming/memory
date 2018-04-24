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
	src="/zhengjunming_user_commodity_trading_system/js/user/fillorder.js"></script>
<title>填写购物车订单</title>
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
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ImproveUserInforServlet">完善个人信息</a></li>
			<li class="current"><a>填写订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/updatePassword.jsp">修改密码</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<center>
			<h2>填写订单</h2>
			<p>您此次共有${orderAmount }条购物订单，请您校对完无误后填写相关信息</p>
			<c:forEach items="${orders }" var="order">
				<center>
					<p>订单号：${order.orderNumber  }，下单时间：${order.orderDate }</p>
					<table bgcolor="#F5F5DC" align="center" border="1" cellspacing="0"
						width="60%">
						<tr>
							<th align="center">商品名称</th>
							<th align="center">商品价格</th>
							<th align="center">商品数量</th>
							<th align="center">价格小计</th>
						</tr>
					</table>
					<c:forEach items="${order.orderItems }" var="orderItem">
						<table bgcolor="#F5F5DC" align="center" border="1" cellspacing="0"
							width="60%">
							<tr>
								<td align="center">${orderItem.goodsName }</td>
								<td align="center">￥${orderItem.goodsPrice }</td>
								<td align="center">${orderItem.goodsAmount }</td>
								<td align="center">￥${orderItem.totalPrice }</td>
							</tr>
						</table>
					</c:forEach>
					<br>
					<br>

				</center>
			</c:forEach>
			<p>金额总计：￥${totalPrice }</p>
			<h2>请在下面填写相关信息，带*为必填</h2>
			<div id="main">
				<div>
					<form
						action="/zhengjunming_user_commodity_trading_system/HandleCartOrderServlet"
						method="post" class="myform" onsubmit="return check()">
						<input type="text" name="receiver" id="receiver" class="receiver"
							placeholder="*收货人"><br> <input type="text"
							name="address" id="address" class="address" placeholder="*收货地址"><br>
						<input type="text" name="phone" id="phone" class="phone"
							placeholder="*联系电话"><br>
						<button type="submit" class="button" id="submit">提交</button>
					</form>
				</div>
			</div>
		</center>
	</c:if>
</body>
</html>