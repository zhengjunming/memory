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
	src="/zhengjunming_user_commodity_trading_system/js/store/addGoods.js"></script>
<title>上架商品</title>
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
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/store/addGoods.jsp">上架商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListNotUnderShelvedGoodsServlet">下架商品</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListGoodsForImproveServlet">修改商品信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/lookGoodsStatus.jsp">查看商品状态</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/improveStoreInfo.jsp">完善/修改店铺信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/listOrderIndex.jsp">处理订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/modifyPassword.jsp">修改密码</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/StoreDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<center>
			<h2>请填写商品的相关信息，带*号为必填项</h2>
		</center>
		<div id="main">
			<div>
				<center>
					<h3 style="color: red;">${message }</h3>
					<form
						action="/zhengjunming_user_commodity_trading_system/AddGoodsServlet"
						method="post" class="myform" enctype="multipart/form-data"
						onsubmit="return check()">

						<input type="text" name="goodsName" class="goodsName"
							id="goodsName" maxlength="30" placeholder="*商品名称"
							value="${param.goodsName }" /><br>

						<textarea rows="5" cols="50" name="goodsDescription"
							class="goodsDescription" id="goodsDescription" placeholder="商品描述"
							value="${param.goodsDescription }" /></textarea>
						<br> <input type="text" name="marketPrice"
							class="marketPrice" id="marketPrice" maxlength="30"
							placeholder="*市场价格：非负整数或小数" value="${param.marketPrice }" /><br>
						<input type="text" name="sellPrice" class="sellPrice"
							id="sellPrice" maxlength="30" placeholder="*销售价格：非负整数或小数"
							value="${param.sellPrice }"><br> <input
							type="text" name="quantity" class="quantity" id="quantity"
							maxlength="30" placeholder="*库存量：非负整数" value="${param.quantity }" /><br>

						<font size="5">图片：</font><input type="file" name="file"
							class="file" id="file" /><br>
						<p>支持.jpg，.gif，.png，.jpeg格式的图片</p>
						<button type="submit" class="button" id="submit">添加</button>

					</form>
				</center>
			</div>
		</div>
	</c:if>
</body>
</html>