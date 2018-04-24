<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/zhengjunming_user_commodity_trading_system/css/user/login.css">
<script type="text/javascript">
	function check() {
		var check = true;
		var username = document.getElementById("username").value;
		var mobileRegex = /^1[34578]\d{9}$/;
		if (username == "") {
			alert("信息不能为空");
			check = false;
		} else if (!(mobileRegex.test(username))) {
			alert("用户名格式不对");
			check = false;
		}
		return check;
	}
</script>
<title>找回密码主页</title>
</head>
<body>
<div id="main">
		<div class="content">
			<center>
				<h2 style="color: red;">${message }</h2>
			</center>
			<form action="/zhengjunming_user_commodity_trading_system/RetrieverPasswordServlet" method="post" class="myform" onsubmit="return check()">
				<h1>找回密码</h1>
				
				<input type="text" name="username" class="username" id="username" maxlength="30" placeholder="用户名：手机号码" value="${param.username }" /><span id="checkUsername"></span>
				<br>
				<input type="radio" name="choose" class="choose" value="email" checked="checked">通过邮箱找回
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="choose" class="choose" value="question">通过密保找回
				<br/>
				<button type="submit" class="button" id="submit">找回</button>
			</form>
		</div>
	</div>
</body>
</html>