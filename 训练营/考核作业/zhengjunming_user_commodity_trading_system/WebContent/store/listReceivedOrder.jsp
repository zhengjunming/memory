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
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet">已收货订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListCancelOrderServlet">已取消订单</a></li>
			<li><a
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
				<p>2、暂时没有已收货的订单</p>
			</center>
		</c:if>
		<c:if test="${not empty orders }">
			<center>
				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">订单号</th>
							<th align="center">收货人</th>
							<th align="center">收货地址</th>
							<th align="center">收货人电话</th>
							<th align="center">订单总价格</th>
							<th align="center">买家留言</th>
							<th align="center">创建时间</th>
							<th align="center">查看订单详细信息</th>
						</tr>
					</thead>
					<c:forEach items="${orders}" var="order">
						<tbody>
							<tr>
								<td align="center"><font size="2">${order.orderNumber }</font></td>
								<td align="center"><font size="2">${order.receiver }</font></td>
								<td align="center"><font size="2">${order.address }</font></td>
								<td align="center"><font size="2">${order.phone }</font></td>
								<td align="center"><font size="2">￥${order.totalPrice }</font></td>
								<td align="center"><font size="2">${order.message }</font></td>
								<td align="center"><font size="2">${order.orderDate }</font></td>
								<td align="center"><font size="2">
										<form
											action="/zhengjunming_user_commodity_trading_system/GetOrderDetailsServlet"
											method="post">
											<input type="hidden" name="id" value="${order.id }">
											<input type="submit" value="查看订单详细信息">
										</form>
								</font></td>
							</tr>
						</tbody>
					</c:forEach>
					<c:if test="${totalPageNum == 1 }">
						<tbody>
							<tr>
								<td colspan="8" align="right">共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页</td>
							</tr>
						</tbody>
					</c:if>
					<c:if test="${totalPageNum > 1 }">
						<form
							action="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet"
							method="get" onsubmit="return check2()">
							<tbody>
								<tr>
									<td colspan="9" align="right"><c:if test="${page == 1 }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet?page=${page + 1 }">下一页</a>
										</c:if> <c:if test="${page == totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet?page=${page - 1 }">上一页</a>
										</c:if> <c:if test="${page > 1 && page < totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet?page=${page - 1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListReceivedOrderServlet?page=${page + 1 }">下一页</a>
										</c:if> <input type="text" id="page" size="2" name="page" value="">
										<input type="submit" name="go" value="跳转"></td>
								</tr>
							</tbody>
						</form>
					</c:if>
				</table>
			</center>
		</c:if>
	</c:if>
</body>
</html>