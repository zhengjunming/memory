/**
 * 
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
	var mobileRegex = /^1[34578]\d{9}$/;
	if (username == "" || password == "" || checkcode == "") {
		alert("信息不能为空");
		check = false;
	} else if (password.length < 6 || password.length > 16) {
		alert("密码长度不能小于6位或者大于16位");
		check = false;
	} else if (!(mobileRegex.test(username))) {
		alert("手机格式不对");
		check = false;
	}
	return check;
}

function inprove(attr) {
	var flag = document.getElementById(attr);

	if (attr == "username") {
		var mobileRegex = /^1[34578]\d{9}$/;
		if (!(flag.value.match(mobileRegex))) {
			document.getElementById("checkUsername").innerText = "手机格式不对";
			document.getElementById("checkUsername").style.color = "red";
		} else {
			document.getElementById("checkUsername").innerText = "√";
			document.getElementById("checkUsername").style.color = "red";
		}
	} else {
		var regex = /^[0-9a-zA-Z]{6,16}$/g;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkPassword").innerText = "密码格式不对";
			document.getElementById("checkPassword").style.color = "red";
		} else {
			document.getElementById("checkPassword").innerText = "√";
			document.getElementById("checkPassword").style.color = "red";
		}
	}
}
window.onload = function() {

	var username = document.getElementById("username");
	var password = document.getElementById("password");
	username.onkeyup = function() {
		inprove("username");
	}
	password.onkeyup = function() {
		inprove("password");
	}
}