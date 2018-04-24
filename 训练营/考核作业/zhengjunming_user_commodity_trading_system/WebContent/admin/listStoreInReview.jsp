<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看店家申请信息</title>
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/public/index.css">
</head>
<body>
	<c:if test="${not empty admin}">
		<h2>管理员页面</h2>
		<nav class="nav">
		<ul>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/admin/index.jsp">首页</a></li>
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/ProcessingRequestServlet">查看店家申请信息</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ManageShopServlet">管理店铺</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListCloseStoreServlet">查看被强制关闭的店铺</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/ListBeRejectStoreServlet">查看被拒绝开店的店铺</a></li>
			<li><a
				href="/zhengjunming_user_commodity_trading_system/AdminDropOutServlet">退出</a></li>
		</ul>
		</nav>
		<h2></h2>
		<c:if test="${empty stores }">
			<h3>没有任何店家申请消息</h3>
		</c:if>
		<center>
			<c:if test="${not empty stores }">
				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">店主姓名</th>
							<th align="center">店主电话</th>
							<th align="center">店主邮箱</th>
							<th align="center">店铺名</th>
							<th align="center">店铺描述</th>
							<th align="center">申请时间</th>
							<th align="center">操作</th>
						</tr>
					</thead>
					<c:forEach items="${stores }" var="store">
						<tbody>
							<tr>
								<td align="center"><font size="2">${store.storeOwnerName }</font></td>
								<td align="center"><font size="2">${store.phone }</font></td>
								<td align="center"><font size="2">${store.email }</font></td>
								<td align="center"><font size="2">${store.storeName }</font></td>
								<td align="center"><font size="2">${store.storeDescription }</font></td>
								<td align="center"><font size="2">${store.createTime }</font></td>
								<td align="center"><font size="2">
										<form
											action="/zhengjunming_user_commodity_trading_system/AgreeOpenServlet"
											method="post">
											<input type="hidden" name="email" value="${store.email }">
											<input type="submit" name="submit" value="同意">
										</form>
										<form
											action="/zhengjunming_user_commodity_trading_system/RejectOpenServlet"
											method="post">
											<input type="hidden" name="email" value="${store.email }">
											<input type="submit" name="submit" value="拒绝">
										</form>
								</font></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</c:if>
		</center>
	</c:if>
	<c:if test="${empty admin }">
		<h1>
			必须登录后才能访问该页面，<a
				href="/zhengjunming_user_commodity_trading_system/admin/login.jsp">去登录</a>
		</h1>
	</c:if>
</body>
</html>