<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/store/store.css">
<script type="text/javascript"
	src="/zhengjunming_user_commodity_trading_system/js/store/modifyOrderInfo.js"></script>
<title>修改订单信息</title>
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
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListNoShippingOrderServlet">未发货订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListShippedOrderServlet">已发货订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet">已收货订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListCancelOrderServlet">已取消订单</a></li>
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/ListOrderForModifyServlet">修改订单信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet">返回主页面</a></li>
		</ul>
		</nav>
		<h2></h2>
		<div id="main">
			<div>
				<center>
					<h3 style="color: red;">${message }</h3>
					<form
						action="/zhengjunming_user_commodity_trading_system/HandleOrderInfoServlet"
						method="post" class="myform" onsubmit="return check()">

						订单编号：<input type="text" name="orderNumber" class="orderNumber"
							id="orderNumber" maxlength="30" placeholder="*订单编号"
							disabled="true" value="${orderInfo.orderNumber }" /><br>

						收货人：<input type="text" name="receiver" class="receiver"
							id="receiver" maxlength="30" placeholder="*收货人"
							value="${orderInfo.receiver }" /><br> 收货地址：<input
							type="text" name="address" class="address" id="address"
							placeholder="*收货地址" value="${orderInfo.address }"><br>

						收货人电话：<input type="text" name="phone" class="phone" id="phone"
							maxlength="16" placeholder="*收货人电话" value="${orderInfo.phone }"><br>

						订单总价：<input type="text" name="totalPrice" class="totalPrice"
							id="totalPrice" maxlength="30" placeholder="*订单总价"
							disabled="true" value="${orderInfo.totalPrice }" /><br>

						买家留言：<br>
						<textarea rows="5" cols="50" name="message" class="message"
							id="message" placeholder="买家留言" disabled="true">${orderInfo.message }</textarea>
						<br>

						<button type="submit" class="button" id="submit">提交</button>

					</form>
				</center>
			</div>
		</div>
	</c:if>
</body>
</html>