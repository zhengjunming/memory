<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/zhengjunming_user_commodity_trading_system/js/user/index.js"></script>
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/user/user.css">
<title>搜索结果页面</title>
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
			<li class="current"><a href="">${store.storeName }店家首页</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListCartServlet">我的购物车</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/listMyOrder.jsp">我的订单</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ImproveUserInforServlet">完善个人信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/user/updatePassword.jsp">修改密码</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/UserDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<h2></h2>
		<c:if test="${empty goodses }">
			<center>
				<p>没有相应的记录，可能原因为：</p>
				<p>1、搜索页数没有在页数范围之内</p>
				<p>2、没有该商品</p>
			</center>
		</c:if>
		<center>
			<c:if test="${not empty goodses }">
				<table align=center>
					<tr>
						<td>搜索店内商品</td>
						<td>
							<form
								action="/zhengjunming_user_commodity_trading_system/UserSearchGoodsInStoreServlet"
								method="get" onsubmit="return check()">
								<input type="text" id="key" name="key" size="20%"> <input
									type="hidden" id="id" name="id" value="${id }"> <input
									type="submit" name="query" value="搜索">
							</form>
						</td>
					</tr>
				</table>
				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">商品名称</th>
							<th align="center">商品类别</th>
							<th align="center">商品描述</th>
							<th align="center">市场价格</th>
							<th align="center">促销价格</th>
							<th align="center">折扣率</th>
							<th align="center">销售量</th>
							<th align="center">库存量</th>
							<th align="center">商品图片</th>
							<th align="center">操作</th>
						</tr>
					</thead>
					<c:forEach items="${goodses}" var="goods">
						<tbody>
							<tr>
								<td align="center"><font size="2">${goods.goodsName }</font></td>
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
								<td align="center"><font size="2">
										<form
											action="/zhengjunming_user_commodity_trading_system/user/fillAmount.jsp"
											method="post">
											<input type="hidden" name="goodsName"
												value="${goods.goodsName }"> <input type="hidden"
												name="id" value="${goods.id }"> <input type="submit"
												value="立即购买">
										</form>
										<form
											action="/zhengjunming_user_commodity_trading_system/AddGoodsToCartServlet"
											method="post">
											<input type="hidden" name="id" value="${goods.id }">
											<input type="submit" value="加入购物车">
										</form>
								</font></td>
							</tr>
						</tbody>
					</c:forEach>
					<c:if test="${totalPageNum == 1 }">
						<tbody>
							<tr>
								<td colspan="10" align="right">共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页</td>
							</tr>
						</tbody>
					</c:if>
					<c:if test="${totalPageNum > 1 }">
						<form
							action="/zhengjunming_user_commodity_trading_system/UserSearchGoodsInStoreServlet"
							method="get" onsubmit="return check2()">
							<tbody>
								<tr>
									<td colspan="10" align="right"><c:if test="${page == 1 }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/UserSearchGoodsInStoreServlet?page=${page + 1 }&id=${id }&key=${key }">下一页</a>
										</c:if> <c:if test="${page == totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/UserSearchGoodsInStoreServlet?page=${page - 1 }&id=${id }&key=${key }">上一页</a>
										</c:if> <c:if test="${page > 1 && page < totalPageNum }">
								共${count }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${page }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/UserSearchGoodsInStoreServlet?page=${page - 1 }&id=${id }&key=${key }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="/zhengjunming_user_commodity_trading_system/UserSearchGoodsInStoreServlet?page=${page + 1 }&id=${id }&key=${key }">下一页</a>
										</c:if> <input type="text" id="page" size="2" name="page" value="">
										<input type="hidden" id="id" name="id" value="${id }">
										<input type="hidden" name="key" value="${key }"> <input
										type="submit" name="go" value="跳转"></td>
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