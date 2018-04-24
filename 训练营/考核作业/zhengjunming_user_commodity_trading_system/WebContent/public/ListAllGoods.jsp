<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/public/listGoods.css">
<title>商城主页</title>
<script type="text/javascript"
	src="/zhengjunming_user_commodity_trading_system/js/public/listGoods.js"></script>
</head>
<body>
	<header>
	<div class="container" id="head">
		<ul class="ulheader">
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/UserLogin.jsp">会员登录</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/UserRegister.jsp">免费注册</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/store/openStore.jsp">我要开店</a></li>
		</ul>
	</div>
	</header>
	<br>
	<br>
	<br>
	<center>
		<h1>商品总览</h1>
	</center>
	<center>
		<form
			action="/zhengjunming_user_commodity_trading_system/PublicSearchServlet"
			method="get" onsubmit="return check()">
			<input type="text" id="query" name="key" size="20%"
				placeholder="输入关键字"><br> <input type="radio"
				name="choose" class="choose" value="goods" checked="checked">商品
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
				type="radio" name="choose" class="choose" value="store">店铺<br>
			<input type="submit" name="query" value="搜索">
		</form>
	</center>
	<c:if test="${empty goodses }">
		<center>
			<p>没有相应的记录，可能原因为：</p>
			<p>1、搜索页数没有在页数范围之内</p>
			<p>2、没有该商品</p>
		</center>
	</c:if>

	<c:if test="${not empty goodses }">
		<center>
			<table id="newspaper-a">
				<thead>
					<tr>
						<th align="center">商品名称</th>
						<th align="center">商品描述</th>
						<th align="center">市场价格</th>
						<th align="center">促销价格</th>
						<th align="center">折扣率</th>
						<th align="center">销售量</th>
						<th align="center">库存量</th>
						<th align="center">商品图片</th>
					</tr>
				</thead>
				<c:forEach items="${goodses}" var="goods">
					<tbody>
						<tr>
							<td align="center"><font size="2">${goods.goodsName }</font></td>
							<td align="center"><font size="2">${goods.goodsDescription }</font></td>
							<td align="center"><font size="2">￥${goods.marketPrice }</font></td>
							<td align="center"><font size="2">￥${goods.sellPrice }</font></td>
							<td align="center"><font size="2">${goods.goodsDiscount }折</font></td>
							<td align="center"><font size="2">${goods.sellCount }</font></td>
							<td align="center"><font size="2">${goods.quantity }</font></td>
							<td align="center"><img
								src=${goods.goodsPicture == "" ? '/zhengjunming_user_commodity_trading_system/image/noimg.jpg' : goods.goodsPicture}
								width="100" height="100"></td>
						</tr>
					</tbody>
				</c:forEach>
				<c:if test="${totalPageNum == 1 }">
					<tbody>
						<tr>
							<td colspan="8" align="right">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页</td>
						</tr>
					</tbody>
				</c:if>
				<c:if test="${totalPageNum > 1 }">
					<form
						action="/zhengjunming_user_commodity_trading_system/ListAllGoodsServlet"
						method="get" onsubmit="return check2()">
						<tbody>
							<tr>
								<td colspan="8" align="right">
									<c:if test="${page == 1 }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListAllGoodsServlet?page=${page + 1 }">下一页</a>
									</c:if> <c:if test="${page == totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListAllGoodsServlet?page=${page - 1 }">上一页</a>
									</c:if> <c:if test="${page > 1 && page < totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListAllGoodsServlet?page=${page - 1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListAllGoodsServlet?page=${page + 1 }">下一页</a>
									</c:if> <input type="text" id="page" size="2" name="page" value="">
									<input type="submit" name="go" value="跳转">
								</td>
							</tr>
						</tbody>
					</form>
				</c:if>
			</table>
		</center>
	</c:if>
</body>
</html>