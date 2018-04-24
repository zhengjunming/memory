/**
 * 管理员登录信息校对
 */
function change() {
	document.getElementById("myimg").src = "/zhengjunming_user_commodity_trading_system/CheckcodeServlet?"
			+ new Date().getTime();
}

function check() {
	var check = true;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var checkcode = document.getElementById("checkcode").value;
	if (username == "" || password == "" || checkcode == "") {
		alert("信息不能为空");
		check = false;
	}
	return check;
}

function inprove(attr) {
	var flag = document.getElementById(attr);

	var regex = /^[0-9a-zA-Z]{6,16}$/g;
	if (!(flag.value.match(regex))) {
		document.getElementById("checkPassword").innerText = "密码格式不对";
		document.getElementById("checkPassword").style.color = "red";
	} else {
		document.getElementById("checkPassword").innerText = "√";
		document.getElementById("checkPassword").style.color = "red";
	}

}
window.onload = function() {
	var password = document.getElementById("password");
	password.onkeyup = function() {
		inprove("password");
	}
}