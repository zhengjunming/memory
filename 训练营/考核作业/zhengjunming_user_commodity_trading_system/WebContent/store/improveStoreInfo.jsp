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
	src="/zhengjunming_user_commodity_trading_system/js/store/improveStoreInfo.js"></script>
<title>完善店铺信息</title>
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
				href="/zhengjunming_user_commodity_trading_system/ListGoodsForImproveServlet">修改商品信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/lookGoodsStatus.jsp">查看商品状态</a></li>
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/store/improveStoreInfo.jsp">完善/修改店铺信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/listOrderIndex.jsp">处理订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/modifyPassword.jsp">修改密码</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/StoreDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<h2></h2>
		<div id="main">
			<div>
				<center>
					<p style="color: red;">${message }</p>
					<h3>完善店铺信息，带*为必填</h3>
					<form
						action="/zhengjunming_user_commodity_trading_system/ImproveStoreInfoServlet"
						method="post" class="myform" onsubmit="return check()">

						<font size="5">用&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名：</font><input
							type="text" name="username" class="username" id="username"
							maxlength="30" placeholder="*用户名：手机号码" disabled="true"
							value="${store.storeUsername }" /><br> <font size="5">*邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</font><input
							type="text" name="email" class="email" id="email" maxlength="32"
							placeholder="*邮箱" value="${store.email }"><span
							id="checkEmail"></span><br> <font size="5">*真实姓名：</font><input
							type="text" name="realname" class="realname" id="realname"
							maxlength="20" placeholder="*真实姓名"
							value="${store.storeOwnerName }" /><span id="checkRealname"></span><br>

						<font size="5">*手机号码：</font><input type="text" name="phone"
							class="phone" id="phone" maxlength="16" placeholder="*手机号码"
							value="${store.phone }" /><span id="checkPhone"></span><br>

						<font size="5">*店铺名称：</font><input type="text" name="storeName"
							class="storeName" id="storeName" maxlength="32"
							placeholder="*店铺名字" value="${store.storeName }"><span
							id="checkStoreName"></span><br> <font size="5">店铺描述：</font>
						<br>
						<textarea rows="1" cols="255" name="storeDescription"
							class="storeDescription" id="storeDescription" placeholder="店铺描述">${store.storeDescription }</textarea>
						<span id="checkStoreDescription"></span><br>

						<button type="submit" class="button" id="submit">提交</button>
					</form>
				</center>
			</div>
		</div>
	</c:if>
</body>
</html>