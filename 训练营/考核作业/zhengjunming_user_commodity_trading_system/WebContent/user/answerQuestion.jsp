<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
* {
	margin: 0;
	padding: 0px;
}
.button {
	background-color: white;
	height: 32px;
	width: 30%;
	font-size: 20px;
	margin: 4px auto;
}

.question, .answer {
	width: 60%;
}

.question, .answer {
	height: 25px;
	font-size: 20px;
	margin: 10px auto;
}

.myform {
	margin-left: 25%;
}
body  {
	background:
		url("/zhengjunming_user_commodity_trading_system/image/background.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}
.content {
	width: 500px;
	background-color: rgba(255, 255, 255, 0.6);
	margin: 10% auto;
	padding: 5px 20px;
}
</style>
<script type="text/javascript">
	function check() {
		var answer = document.getElementById("answer").value;
		if (answer == "") {
			alert("回答不能为空");
			return false;
		}
		return true;
	}
</script>
<title>回答密保</title>
</head>
<body>
<div id="main">
		<div class="content">
			<center>
				<h2 style="color: red;">${message }</h2>
			</center>
			<form action="/zhengjunming_user_commodity_trading_system/CheckAnswerServlet" method="post" class="myform" onsubmit="return check()">
				<h1>回答密码问题</h1>
				<input type="text" name="question" class="question" id="question" maxlength="30" placeholder="密保问题" value="${user.securityQuestion }" readOnly="true"/><span id="checkQuestion"></span>
				<br>
				<input type="text" name="answer" class="answer" id="answer" maxlength="30" placeholder="密保答案" value=""/><span id="checkAnswer"></span>
				<br/>
				<button type="submit" class="button" id="submit">提交</button>
			</form>
		</div>
	</div>
</body>
</html>