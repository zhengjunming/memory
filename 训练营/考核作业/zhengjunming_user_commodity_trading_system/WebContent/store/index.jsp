<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/public/index.css">
<script type="text/javascript"
	src="/zhengjunming_user_commodity_trading_system/js/store/index.js"></script>
<title>店家主页</title>
</head>
<body>
	<c:if test="${store.storeStatus == storeStatus1 }">
		<center>
			<h1>
				您的信息还在审核中，请耐心等待 <a
					href="/zhengjunming_user_commodity_trading_system/store/invalidate.jsp">退出</a>
			</h1>
		</center>
	</c:if>
	<c:if test="${store.storeStatus == storeStatus2 }">
		<center>
			<h1>
				很对不起，您的开店请求已被拒绝！ <a
					href="/zhengjunming_user_commodity_trading_system/store/invalidate.jsp">退出</a>
			</h1>
		</center>
	</c:if>
	<c:if test="${store.storeStatus == storeStatus3 }">
		<h2>您好：${store.storeUsername }店家</h2>
		<nav class="nav">
		<ul>
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet">首页</a></li>
			<li><a
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
			<h2></h2>
			<c:if test="${empty goodses }">
				<center>
					<p>没有相应的记录，可能原因为：</p>
					<p>1、搜索页数没有在页数范围之内</p>
					<p>2、您的店铺还没有商品</p>
					<p>3、搜索的商品不存在</p>
				</center>
			</c:if>
			<c:if test="${not empty goodses }">
				<table align=center>
					<tr>
						<td>搜索店铺商品</td>
						<td>
							<form
								action="/zhengjunming_user_commodity_trading_system/StoreSearchServlet"
								method="get" onsubmit="return check()">
								<input type="text" id="key" name="key" size="20%"> <input
									type="submit" name="query" value="搜索">
							</form>
						</td>
					</tr>
				</table>
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
							<th align="center">商品状态</th>
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
								<td align="center"><font size="2">${goods.goodsStatus }</font>
								<td align="center"><img
									src=${goods.goodsPicture == "" ? '/zhengjunming_user_commodity_trading_system/image/noimg.jpg' : goods.goodsPicture}
									width="100" height="100"></td>
							</tr>
						</tbody>
					</c:forEach>
					<c:if test="${totalPageNum == 1 }">
						<tbody>
							<tr>
								<td colspan="9" align="right">共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页</td>
							</tr>
						</tbody>
					</c:if>
					<c:if test="${totalPageNum > 1 }">
						<form
							action="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet"
							method="get" onsubmit="return check2()">
							<tbody>
								<tr>
									<td colspan="9" align="right"><c:if test="${page == 1 }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet?page=${page + 1 }">下一页</a>
										</c:if> <c:if test="${page == totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet?page=${page - 1 }">上一页</a>
										</c:if> <c:if test="${page > 1 && page < totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet?page=${page - 1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/ListAllStoreGoodsServlet?page=${page + 1 }">下一页</a>
										</c:if> <input type="text" id="page" size="2" name="page" value="">
										<input type="submit" name="go" value="跳转"></td>
								</tr>
							</tbody>
						</form>
					</c:if>
				</table>
			</c:if>
		</center>
	</c:if>
</body>
</html>