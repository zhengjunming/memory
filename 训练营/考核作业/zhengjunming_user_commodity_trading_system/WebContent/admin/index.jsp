<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主页</title>
<link rel="stylesheet" type="text/css"
	href="/zhengjunming_user_commodity_trading_system/css/public/index.css">
<body>
	<c:if test="${not empty admin}">
		<h2>管理员页面</h2>
		<nav class="nav">
		<ul>
			<li class="current"><a
				href="/zhengjunming_user_commodity_trading_system/admin/index.jsp">首页</a></li>
			<li><a
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
		<hr width="100%">
		<p>欢迎管理员，在这里您可以对店主提交的审核进行处理以及管理店铺</p>
	</c:if>
	<c:if test="${empty admin }">
		<h1>
			必须登录后才能访问该页面，<a
				href="/zhengjunming_user_commodity_trading_system/admin/login.jsp">去登录</a>
		</h1>
	</c:if>

</body>
</html>
