<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/store/store.css">
<title>查看商品状态</title>
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
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/store/childIndex.jsp">首页</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListHasShelvedGoodsServlet">已上架商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListHasUnderShelvedGoodsServlet">已下架商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListHasOutGoodsServlet">断货商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet">返回主页面</a></li>
		</ul>
		</nav>
		<h2></h2>
		<p>您可以查询你店铺商品的状态</p>
	</c:if>
</body>
</html>