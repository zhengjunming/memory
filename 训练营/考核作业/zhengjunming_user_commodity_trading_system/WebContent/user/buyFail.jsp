<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购买失败</title>
</head>
<body>
	<center>
		<h1>您购买失败，失败原因如下</h1>
		<c:forEach items="${messages }" var="message">
			<h2>${message }</h2>
		</c:forEach>
		<h3>
			赶紧回<a
				href="/zhengjunming_user_commodity_trading_system/ListCartServlet">我的购物车</a>修改一下数量吧
		</h3>
	</center>

</body>
</html>