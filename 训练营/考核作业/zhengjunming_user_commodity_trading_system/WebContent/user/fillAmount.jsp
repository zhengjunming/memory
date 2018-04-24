<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/user/user.css">
<style type="text/css">
.button {
	background-color: white;
	height: 32px;
	width: 5%;
	font-size: 20px;
	margin: 4px auto;
}

.goodsName, .goodsAmount {
	width: 15%;
}

.goodsName, .goodsAmount {
	height: 25px;
	font-size: 20px;
	margin: 10px auto;
}

.myform {
	margin-left: 0%;
}
</style>
<script type="text/javascript">
	function check() {
		var check = true;
		var goodsAmount = document.getElementById("goodsAmount").value;
		var regex = /^[1-9][0-9]*$/;
		if (goodsAmount == "") {
			alert("信息不能为空");
			check = false;
		} else if (!(regex.test(goodsAmount))) {
			alert("数量必须为正整数");
			check = false;
		}
		return check;
	}
</script>

<title>填写商品数量</title>
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
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/updatePassword.jsp">修改密码</a></li>
			<li class="current"><a>购买商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<h2></h2>
		<div id="main">
			<div>
				<center>
					<h2 style="color: red;">${message }</h2>
				</center>
				<center>
					<p>请在下方填写您想购买的商品数量</p>
					<c:remove var="message" scope="session" />
					<form
						action="/zhengjunming_user_commodity_trading_system/BuyGoodsServlet"
						method="post" class="myform" onsubmit="return check()">
						<input type="text" value="${param.goodsName }" id="goodsName"
							name="goodsName" class="goodsName" readOnly="true"><br>
						<input type="hidden" value="${param.id }" name="id"><br>
						<input type="text" name="goodsAmount" id="goodsAmount"
							class="goodsAmount" placeholder="商品数量" value=""><br>
						<br>
						<button type="submit" class="button" id="submit">提交</button>
					</form>
				</center>
			</div>
		</div>
	</c:if>
</body>
</html>