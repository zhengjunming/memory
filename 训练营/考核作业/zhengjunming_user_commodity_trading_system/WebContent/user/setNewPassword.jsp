<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/zhengjunming_user_commodity_trading_system/css/user/user.css">
<script type="text/javascript">
function check() {
	var check = true;
	var newPassword = document.getElementById("newPassword").value;
	var repassword = document.getElementById("repassword").value;
	if (newPassword == "" || repassword == "") {
		alert("信息不能为空");
		check = false;
	} else if (newPassword.length < 6 || newPassword.length > 16
			|| repassword.length < 6 || repassword.length > 16) {
		alert("密码长度不能小于6位或者大于16位");
		check = false;
	}
	if (newPassword != repassword) {
		alert("两次新密码不一致");
		check = false;
	}

	return check;
}

</script>
<title>找回密码</title>
</head>
<body>
<div id="main">
		<div>
			<center>
				<h2 style="color: red;">${message }</h2>
			</center>
			<center>
			<form action="/zhengjunming_user_commodity_trading_system/SetNewPasswordServlet"
				method="post" class="myform" onsubmit="return check()">
				<h1>找回密码</h1>
				<input type="password" name="newPassword" id="newPassword" class="newPassword" maxlength="30" value="" placeholder="新密码" /><span id="checkNewPassword"></span><br> 
				
				<input type="password" name="repassword" id="repassword" class="repassword" maxlength="30" value="" placeholder="重复输入新密码" /><span id="checkRepassword"></span><br>

				<button type="submit" class="button" id="submit">提交</button>
			</form>
			</center>
		</div>
	</div>
</body>
</html>