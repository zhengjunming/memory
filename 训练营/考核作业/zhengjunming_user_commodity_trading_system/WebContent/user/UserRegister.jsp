<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/zhengjunming_user_commodity_trading_system/css/user/register.css">
<title>会员注册</title>
<script type="text/javascript" src="/zhengjunming_user_commodity_trading_system/js/user/register.js"></script>
</head>
<body>
	<div id="main">
		<div class="content">
			<center>
				<h2 style="color: red;">${message }</h2>
			</center>
			<form action="/zhengjunming_user_commodity_trading_system/UserRegisterServlet" method="post" class="myform" onsubmit="return check()">
				<h1>会员注册</h1>
				
				<input type="text" name="username" class="username" id="username" maxlength="30" placeholder="用户名：手机号码" value="${param.username }"/><span id="checkUsername"></span>
				
				<input type="password" name="password" class="password" id="password" maxlength="16" placeholder="密码:6到16位的数字或字母" value="${param.password }"/><span id="checkPassword"></span> 
				
				<input type="password" name="repassword" class="repassword" id="repassword" maxlength="16" placeholder="请确认密码" value="${param.repassword }" onchange="checkPassword()" /><span id="checkRepassword"></span>

				<input type="text" name="email" class="email" id="email" maxlength="30" placeholder="邮箱" value="${param.email }"/><span id="checkEmail"></span>
				
				<input type="text" name="question" class="question" id="question" maxlength="30" placeholder="密保问题" value="${param.question }"/><span id="checkQuestion"></span>
	
				<input type="text" name="answer" class="answer" id="answer" maxlength="30" placeholder="密保答案" value="${param.answer }"/><span id="checkAnswer"></span>
				
				<div class="checkcontent">
					<input type="text" name="checkcode" class="checkcode" id="checkcode" maxlength="16" placeholder="验证码" /> 
					<img src="/zhengjunming_user_commodity_trading_system/CheckcodeServlet" id="myimg" onclick="change();" style="cursor: pointer;">
				</div>
				<br/>
				<button type="submit" class="button" id="submit">注册</button>
				<input type="reset" class="reset" id="reset" />
			</form>
		</div>
	</div>
</body>
</html>