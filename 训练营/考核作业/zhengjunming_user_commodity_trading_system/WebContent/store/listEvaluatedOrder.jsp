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
	src="/zhengjunming_user_commodity_trading_system/js/store/index.js"></script>
<title>已收货订单</title>
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
				href="/zhengjunming_user_commodity_trading_system/ListEvaluateOrderServlet">用户已评价订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListOrderForModifyServlet">修改订单信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet">返回主页面</a></li>
		</ul>
		</nav>
		<h2></h2>
		<c:if test="${empty orders }">
			<center>
				<p>没有相应的记录，可能原因为：</p>
				<p>1、搜索页数没有在页数范围之内</p>
				<p>2、暂时没有已评价的订单</p>
			</center>
		</c:if>
		<c:if test="${not empty orders }">
			<center>
				<c:forEach items="${orders}" var="order">
					<p>订单号：${order.orderNumber }&nbsp;&nbsp;&nbsp;&nbsp;收货人：${order.receiver }</p>
					<p>
						ta买了：
						<c:forEach items="${order.orderItems}" var="orderItem">
						${orderItem.goodsName }&nbsp;&nbsp;&nbsp;&nbsp;
					</c:forEach>
					</p>
					<p>买家评价：${order.evaluation }</p>
					<c:if test="${not empty order.picture }">
						<img src="${order.picture }" width="100" height="100">
					</c:if>
					<br>
					<c:if test="${not empty order.reply }">
						<p>已回复内容：${order.reply }</p>
					</c:if>
					<c:if test="${empty order.reply }">
						<p>
							<a
								href="/zhengjunming_user_commodity_trading_system/HelpStoreGetIdServlet?id=${order.id }">回复ta</a>
						</p>
					</c:if>
					<br>
					<br>
				</c:forEach>
				<c:if test="${totalPageNum == 1 }">
					<tr>
						<td colspan="10" align="right">
							共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页</td>
					</tr>
				</c:if>
				<c:if test="${totalPageNum > 1 }">
					<form
						action="/zhengjunming_user_commodity_trading_system/ListEvaluateOrderServlet"
						method="get" onsubmit="return check2()">
						<tr>
							<td width="100%" colspan="9" align="right" bgcolor="#F5F5DC">
								<c:if test="${page == 1 }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="/zhengjunming_user_commodity_trading_system/ListEvaluateOrderServlet?page=${page + 1 }">下一页</a>
								</c:if> <c:if test="${page == totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="/zhengjunming_user_commodity_trading_system/ListEvaluateOrderServlet?page=${page - 1 }">上一页</a>
								</c:if> <c:if test="${page > 1 && page < totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="/zhengjunming_user_commodity_trading_system/ListEvaluateOrderServlet?page=${page - 1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="/zhengjunming_user_commodity_trading_system/ListEvaluateOrderServlet?page=${page + 1 }">下一页</a>
								</c:if> <input type="text" id="page" size="2" name="page" value="">
								<input type="submit" name="go" value="跳转">
							</td>
						</tr>
					</form>
				</c:if>
			</center>
		</c:if>
	</c:if>
</body>
</html>