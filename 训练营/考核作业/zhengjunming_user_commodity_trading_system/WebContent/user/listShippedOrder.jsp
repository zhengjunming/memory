<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/user/listMyOrder.css">
<script type="text/javascript"
	src="/zhengjunming_user_commodity_trading_system/js/user/index.js"></script>
<title>已发货订单页面</title>
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
				href="/zhengjunming_user_commodity_trading_system/user/listMyOrder.jsp">处理订单首页</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListNoShippingOrderServlet">待发货</a></li>
			<li class="current"><a
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
		<c:if test="${empty orders }">
			<center>
				<p>没有相应的记录，可能原因为：</p>
				<p>1、搜索页数没有在页数范围之内</p>
				<p>2、您暂时没有待收货的订单</p>
			</center>
		</c:if>
		<c:if test="${not empty orders }">
			<center>
				<p>您共有${count }条购物订单待收货</p>
			</center>
			<c:forEach items="${orders }" var="order">
				<center>
					<p>订单号：${order.orderNumber  }，下单时间：${order.orderDate }</p>
					<form
						action="/zhengjunming_user_commodity_trading_system/UserCancelOrderServlet"
						method="post">
						<input type="hidden" name="id" value="${order.id }"> <input
							type="submit" value="取消订单">
					</form>
					<form
						action="/zhengjunming_user_commodity_trading_system/ConfirmReceiptServlet"
						method="post">
						<input type="hidden" name="id" value="${order.id }"> <input
							type="submit" value="确认收货">
					</form>
					<br>
					<table id="newspaper-a">
						<thead>
							<tr>
								<th align="center">商品名称</th>
								<th align="center">商品价格</th>
								<th align="center">商品数量</th>
								<th align="center">价格小计</th>
							</tr>
						</thead>

						<c:forEach items="${order.orderItems }" var="orderItem">
							<tbody>
								<tr>
									<td align="center" width="20%">${orderItem.goodsName }</td>
									<td align="center" width="20%">￥${orderItem.goodsPrice }</td>
									<td align="center" width="20%">${orderItem.goodsAmount }</td>
									<td align="center" width="20%">￥${orderItem.totalPrice }</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</center>
			</c:forEach>
			<center>
				<table>
					<c:if test="${totalPageNum == 1 }">
						<tr>
							<td width="100%" colspan="10" align="right" bgcolor="#F5F5DC">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页</td>
						</tr>
					</c:if>
					<c:if test="${totalPageNum > 1 }">
						<form
							action="/zhengjunming_user_commodity_trading_system/UserListShippedOrderServlet"
							method="get" onsubmit="return check2()">
							<tr>
								<td width="100%" colspan="10" align="right" bgcolor="#F5F5DC">
									<c:if test="${page == 1 }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/UserListShippedOrderServlet?page=${page + 1 }">下一页</a>
									</c:if> <c:if test="${page == totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/UserListShippedOrderServlet?page=${page - 1 }">上一页</a>
									</c:if> <c:if test="${page > 1 && page < totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/UserListShippedOrderServlet?page=${page - 1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/UserListShippedOrderServlet?page=${page + 1 }">下一页</a>
									</c:if> <input type="text" id="page" size="2" name="page" value="">
									<input type="submit" name="go" value="跳转">
								</td>
							</tr>
						</form>
					</c:if>
				</table>
			</center>
		</c:if>
	</c:if>
</body>
</html>