<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/store/store.css">
<title>订单详细信息</title>
</head>
<body>
	<c:if test="${empty store }">
		<center>
			<h1>您还未登录，不能访问此页面</h1>
			<h3>
				可前往<a
					href="/zhengjunming_user_commodity_trading_system/store/storeLogin.jsp">登录</a>或者<a
					href="/zhengjunming_user_commodity_trading_system/store/openStore.jsp">开店</a>
			</h3>
		</center>
	</c:if>
	<c:if test="${not empty store }">
		<h2>您好：${store.storeUsername }店家</h2>
		<nav class="nav">
		<ul>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/listOrderIndex.jsp">处理订单首页</a></li>
			<c:if test="${orderStatus == shipped }">
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListNoShippingOrderServlet">未发货订单</a></li>
				<li class="current"><a
					href="/zhengjunming_user_commodity_trading_system/ListShippedOrderServlet">已发货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet">已收货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListCancelOrderServlet">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == cancel }">
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListNoShippingOrderServlet">未发货订单</a></li>
				<li class="current"><a
					href="/zhengjunming_user_commodity_trading_system/ListShippedOrderServlet">已发货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet">已收货订单</a></li>
				<li class="current"><a
					href="/zhengjunming_user_commodity_trading_system/ListCancelOrderServlet">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == underReview }">
				<li class="current"><a
					href="/zhengjunming_user_commodity_trading_system/ListNoShippingOrderServlet">未发货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListShippedOrderServlet">已发货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet">已收货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListCancelOrderServlet">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == received }">
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListNoShippingOrderServlet">未发货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListShippedOrderServlet">已发货订单</a></li>
				<li class="current"><a
					href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet">已收货订单</a></li>
				<li><a
					href="/zhengjunming_user_commodity_trading_system/ListCancelOrderServlet">已取消订单</a></li>
			</c:if>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListOrderForModifyServlet">修改订单信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet">返回主页面</a></li>
		</ul>
		</nav>
		<center>
			<h2>订单详细信息</h2>
			<table id="newspaper-a">
				<thead>
					<tr>
						<th align="center">商品名称</th>
						<th align="center">商品价格</th>
						<th align="center">商品数量</th>
					</tr>
				</thead>
				<c:forEach items="${orderItems}" var="orderItem">
					<tbody>
						<tr>
							<td align="center"><font size="2">${orderItem.goodsName }</font></td>
							<td align="center"><font size="2">￥${orderItem.goodsPrice }</font></td>
							<td align="center"><font size="2">${orderItem.goodsAmount }</font></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</center>
	</c:if>
</body>
</html>