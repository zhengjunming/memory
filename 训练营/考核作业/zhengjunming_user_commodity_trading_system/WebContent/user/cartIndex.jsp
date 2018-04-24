<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/user/user.css">
<title>我的购物车</title>
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
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/ListCartServlet">我的购物车</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/listMyOrder.jsp">我的订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ImproveUserInforServlet">完善个人信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/updatePassword.jsp">修改密码</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<h2></h2>
		<c:if test="${empty cartItems }">
			<center>
				<img
					src="/zhengjunming_user_commodity_trading_system/image/cart.jpg"><br>
				<p>购物车空空如也，快去购物</p>
			</center>
		</c:if>
		<c:if test="${not empty cartItems }">
			<center>
				<p>您的购物车有以下商品</p>
				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">商品名称</th>
							<th align="center">商品图片</th>
							<th align="center">商品描述</th>
							<th align="center">商品数量</th>
							<th align="center">商品单价</th>
							<th align="center">商品总价</th>
							<th align="center">移除</th>
						</tr>
					</thead>
					<c:forEach items="${cartItems}" var="cartItem">
						<tbody>
							<tr>
								<td align="center"><font size="2">${cartItem.goods.goodsName }</font></td>
								<td align="center"><img
									src=${cartItem.goods.goodsPicture == "" ? '/zhengjunming_user_commodity_trading_system/image/noimg.jpg' : cartItem.goods.goodsPicture}
									width="100" height="100"></td>
								<td align="center"><font size="2">${cartItem.goods.goodsDescription }</font></td>
								<c:if test="${cartItem.goodsAmount == 1 }">
									<td align="center"><font size="2">${cartItem.goodsAmount }&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/IncreaseGoodsCountServlet?id=${cartItem.goods.id }">+</a></font></td>
								</c:if>
								<c:if test="${cartItem.goodsAmount > 1 }">
									<td align="center"><font size="2"><a
											href="/zhengjunming_user_commodity_trading_system/ReduceGoodsCountServlet?id=${cartItem.goods.id }">-&nbsp;&nbsp;&nbsp;&nbsp;</a>${cartItem.goodsAmount }&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/IncreaseGoodsCountServlet?id=${cartItem.goods.id }">+</a></font></td>
								</c:if>
								<td align="center"><font size="2">￥${cartItem.goods.sellPrice }</font></td>
								<td align="center"><font size="2">￥${cartItem.totalPrice }</font></td>
								<td align="center"><a
									href="/zhengjunming_user_commodity_trading_system/DeleteCartItemServlet?id=${cartItem.goods.id }"><img
										src="/zhengjunming_user_commodity_trading_system/image/delete.png"
										width="10" height="20"></a></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
				<h3>
					<a
						href="/zhengjunming_user_commodity_trading_system/ClearCartServlet">清空购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="/zhengjunming_user_commodity_trading_system/PayServlet">结账</a>
				</h3>
			</center>
		</c:if>
	</c:if>

</body>
</html>