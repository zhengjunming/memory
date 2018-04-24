<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/user/user.css">
<script type="text/javascript">
	function check() {
		var check = true;
		var evaluate = document.getElementsByName("evaluate").value;
		if (evaluate == "") {
			alert("评价不能为空！");
			check = false;
		}
		return check;
	}
</script>
<title>订单评价</title>
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
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserListShippedOrderServlet">待收货</a></li>
			<li class="current"><a
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
		<center>
			<div id="main">
				<div>
					<center>
						<h2 style="color: red;">${message }</h2>
					</center>
					<form
						action="/zhengjunming_user_commodity_trading_system/HandleEvaluateServlet"
						method="post" class="myform" enctype="multipart/form-data"
						onsubmit="return check()">
						<textarea rows="5" cols="50" name="evaluate" id="evaluate"
							class="evaluate" placeholder="*评价内容"></textarea>
						<br> <input type="hidden" name="id" value="${id }"> <font
							size="5">图片：</font><input type="file" name="file" class="file"
							id="file" /><br>
						<p>支持.jpg，.gif，.png，.jpeg格式的图片</p>
						<button type="submit" class="button" id="submit">评价</button>
					</form>
				</div>
			</div>
		</center>
	</c:if>
</body>
</html>