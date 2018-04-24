<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/store/store.css">
<title>店家修改密码</title>
<script type="text/javascript"
	src="/zhengjunming_user_commodity_trading_system/js/store/modifyPassword.js"></script>
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
				href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet">首页</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/addGoods.jsp">上架商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListNotUnderShelvedGoodsServlet">下架商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system//ListGoodsForImproveServlet">修改商品信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/lookGoodsStatus.jsp">查看商品状态</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/improveStoreInfo.jsp">完善/修改店铺信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/listOrderIndex.jsp">处理订单</a></li>
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/store/modifyPassword.jsp">修改密码</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/StoreDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<div id="main">
			<div class="content">
				<center>
					<h2 style="color: red;">${message }</h2>
				</center>
				<center>
					<form
						action="/zhengjunming_user_commodity_trading_system/StoreModifyPasswordServlet"
						method="post" class="myform" onsubmit="return check()">
						<h1>修改密码</h1>

						<input type="password" name="oldPassword" id="oldPassword"
							class="oldPassword" maxlength="30" value="" placeholder="旧密码" /><span
							id="checkOldPassword"></span><br> <input type="password"
							name="newPassword" id="newPassword" class="newPassword"
							maxlength="30" value="" placeholder="新密码" /><span
							id="checkNewPassword"></span><br> <input type="password"
							name="repassword" id="repassword" class="repassword"
							maxlength="30" value="" placeholder="重复输入新密码" /><span
							id="checkRepassword"></span><br>

						<button type="submit" class="button" id="submit">修改</button>

					</form>
				</center>
			</div>
		</div>
	</c:if>
</body>
</html>