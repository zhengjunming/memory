<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/zhengjunming_user_commodity_trading_system/css/store/openStore.css">
<script type="text/javascript" src="/zhengjunming_user_commodity_trading_system/js/store/openStore.js"></script>
<title>开店页面</title>
</head>
<body>
	<div id="main">
		<div class="content">
			<center>
				<h2 style="color: red;">${message }</h2>
			</center>
			<form
				action="/zhengjunming_user_commodity_trading_system/StoreRegisterServlet"
				method="post" class="myform" onsubmit="return check()">
				<h1>开店需填写以下信息（带*为必填）</h1>

				<input type="text" name="username" class="username" id="username" maxlength="30" placeholder="*用户名：手机号码" value="${param.username }"/><span id="checkUsername"></span>

				<input type="password" name="password" class="password" id="password" maxlength="16" placeholder="*密码:6到16位的数字或字母" value="${param.password }"/><span id="checkPassword"></span> 
				
				<input type="password" name="repassword" class="repassword" id="repassword" maxlength="16" placeholder="*请确认密码" value="${param.repassword }" onchange="checkPassword()" /><span id="checkRepassword"></span> 
				
				<input type="text" name="email" class="email" id="email" maxlength="32" placeholder="*邮箱" value="${param.email }"><span id="checkEmail"></span>
				
				<input type="text" name="realname" class="realname" id="realname" maxlength="20" placeholder="*您的真实姓名" value="${param.realname }"/><span id="checkRealname"></span>
				
				<input type="text" name="phone" class="phone" id="phone" maxlength="16" placeholder="*您的手机号码" value="${param.phone }"/><span id="checkPhone"></span>
				
				<input type="text" name="storeName" class="storeName" id="storeName" maxlength="32" placeholder="*请为您的店铺起名" value="${param.storeName }"><span id="checkStoreName"></span>
				
				<textarea rows="6" cols="40" name="storeDescription" class="storeDescription" id="storeDescription" placeholder="描述您的店铺" value="${param.storeDescription }"></textarea><span id="checkStoreDescription"></span>
				
				<div class="checkcontent">
					<input type="text" name="checkcode" class="checkcode" id="checkcode" maxlength="16" placeholder="*验证码" /> 
					
					<img src="/zhengjunming_user_commodity_trading_system/CheckcodeServlet" id="myimg" onclick="change();" style="cursor: pointer;">
				</div>
				<br />
				<button type="submit" class="button" id="submit">注册</button>
				<button type="reset" class="reset" id="reset">重置</button>
			</form>
		</div>
	</div>
</body>
</html>