<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/store/store.css">
<script type="text/javascript">
	function check() {
		var check = true;
		var reply = document.getElementsByName("reply").value;
		if (reply == "") {
			alert("回复不能为空！");
			check = false;
		}
		return check;
	}
</script>
<title>回复评价</title>
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
		<center>
			<div id="main">
				<div>
					<form
						action="/zhengjunming_user_commodity_trading_system/HandleReplyServlet"
						method="post" class="myform" onsubmit="return check()">
						<textarea rows="5" cols="50" name="reply" id="reply" class="reply"
							placeholder="*回复内容"></textarea>
						<br> <input type="hidden" name="id" value="${id }">
						<button type="submit" class="button" id="submit">回复</button>
					</form>
				</div>
			</div>
		</center>
	</c:if>
</body>
</html>