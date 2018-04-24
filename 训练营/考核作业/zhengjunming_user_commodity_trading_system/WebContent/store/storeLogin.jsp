<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/zhengjunming_user_commodity_trading_system/css/store/storeLogin.css">
<title>店家登录</title>
<script type="text/javascript" src="/zhengjunming_user_commodity_trading_system/js/store/storeLogin.js"></script>
</head>
<body>
	<div id="main">
		<div class="content">
			<center>
				<h2 style="color: red;">${message }</h2>
			</center>
			<form action="/zhengjunming_user_commodity_trading_system/StoreLoginServlet" method="post" class="myform" onsubmit="return check()">
				<h1>店家登录</h1>

				<input type="text" name="username" class="username" id="username" maxlength="30" placeholder="用户名：手机号码" value="${param.username }" /><span id="checkUsername"></span>
			
				<input type="password" name="password" class="password" id="password" maxlength="16" placeholder="密码:6到16位的数字或字母" value="${param.password }"/><span id="checkPassword"></span>
			
				<div class="checkcontent">
					<input type="text" name="checkcode" class="checkcode" id="checkcode" maxlength="16" placeholder="验证码" /> 
					
					<img src="/zhengjunming_user_commodity_trading_system/CheckcodeServlet" id="myimg" onclick="change();" style="cursor: pointer;"> 
				</div>
				<br/>
				
				<button type="submit" class="button" id="submit">登录</button>
			</form>
		</div>
	</div>
</body>
</html>