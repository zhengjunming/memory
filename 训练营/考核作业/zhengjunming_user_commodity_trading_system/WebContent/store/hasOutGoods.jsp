<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/store/store.css">
<title>断货商品</title>
</head>
<body>
	<h2>您好：${store.storeUsername }店家</h2>
	<nav class="nav">
	<ul>
		<li><a
			href="/zhengjunming_user_commodity_trading_system/store/childIndex.jsp">首页</a></li>
		<li><a
			href="/zhengjunming_user_commodity_trading_system/ListHasShelvedGoodsServlet">已上架商品</a></li>
		<li><a
			href="/zhengjunming_user_commodity_trading_system/ListHasUnderShelvedGoodsServlet">已下架商品</a></li>
		<li class="current"><a
			href="/zhengjunming_user_commodity_trading_system/ListHasOutGoodsServlet">断货商品</a></li>
		<li><a
			href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet">返回主页面</a></li>
	</ul>
	</nav>
	<h2></h2>
	<center>
		<c:if test="${empty goodses }">
			<center>
				<p>没有相应的记录，可能原因为：</p>
				<p>1、搜索页数没有在页数范围之内</p>
				<p>2、暂时没有已断货的商品</p>
			</center>
		</c:if>
		<c:if test="${not empty goodses }">
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
							<td align="center"><font size="2">${goods.goodsDiscount }</font></td>
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
						action="/zhengjunming_user_commodity_trading_system/ListHasOutGoodsServlet"
						method="get" onsubmit="return check()">
						<tbody>
							<tr>
								<td colspan="8" align="right">
									<c:if test="${page == 1 }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListHasOutGoodsServlet?page=${page + 1 }">下一页</a>
									</c:if> <c:if test="${page == totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListHasOutGoodsServlet?page=${page - 1 }">上一页</a>
									</c:if> <c:if test="${page > 1 && page < totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListHasOutGoodsServlet?page=${page - 1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
											href="/zhengjunming_user_commodity_trading_system/ListHasOutGoodsServlet?page=${page + 1 }">下一页</a>
									</c:if> <input type="text" id="page" size="2" name="page" value="">
									<input type="submit" name="go" value="跳转">
								</td>
							</tr>
						</tbody>
					</form>
				</c:if>
			</table>
		</c:if>
	</center>
</body>
</html>