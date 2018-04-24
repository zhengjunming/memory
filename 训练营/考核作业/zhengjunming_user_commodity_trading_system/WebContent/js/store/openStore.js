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
	var repassword = document.getElementById("repassword").value;
	var checkcode = document.getElementById("checkcode").value;
	var realname = document.getElementById("realname").value;
	var email = document.getElementById("email").value;
	var phone = document.getElementById("phone").value;
	var storeName = document.getElementById("storeName").value;
	var emailRegex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var mobileRegex = /^1[34578]\d{9}$/;
	if (username == "" || password == "" || repassword == "" || checkcode == ""
			|| email == "" || realname == "" || phone == "" || storeName == "") {
		alert("必填信息不能为空");
		check = false;
	} else if (!(mobileRegex.test(username))) {
		alert("用户名格式不对");
		check = false;
	} else if (password != repassword) {
		alert("两次密码不一致");
		check = false;
	} else if (password.length < 6 || password.length > 16
			|| repassword.length < 6 || repassword.length > 16) {
		alert("密码长度不能小于6位或者大于16位");
		check = false;
	} else if (!(mobileRegex.test(phone))) {
		alert("手机号码格式不对");
		check = false;
	} else if (!(emailRegex.test(email))) {
		alert("邮箱格式不对");
		check = false;
	}
	return check;
}
// 实时监听用户的输入是否正确
function inprove(attr) {
	var flag = document.getElementById(attr);

	if (attr == "password") {
		var regex = /^[0-9a-zA-Z]{6,16}$/;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkPassword").innerText = "密码格式不对";
			document.getElementById("checkPassword").style.color = "red";
		} else {
			document.getElementById("checkPassword").innerText = "√";
			document.getElementById("checkPassword").style.color = "red";
		}
	} else if (attr == "repassword") {
		var regex = /^[0-9a-zA-Z]{6,16}$/;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkRepassword").innerText = "密码格式不对";
			document.getElementById("checkRepassword").style.color = "red";
		} else {
			document.getElementById("checkRepassword").innerText = "√";
			document.getElementById("checkRepassword").style.color = "red";
		}
	} else if (attr == "username") {
		var mobileRegex = /^1[34578]\d{9}$/;
		if (!(flag.value.match(mobileRegex))) {
			document.getElementById("checkUsername").innerText = "用户名格式不对";
			document.getElementById("checkUsername").style.color = "red";
		} else {
			document.getElementById("checkUsername").innerText = "√";
			document.getElementById("checkUsername").style.color = "red";
		}
	} else if (attr == "phone") {
		var mobileRegex = /^1[34578]\d{9}$/;
		if (!(flag.value.match(mobileRegex))) {
			document.getElementById("checkPhone").innerText = "手机格式不对";
			document.getElementById("checkPhone").style.color = "red";
		} else {
			document.getElementById("checkPhone").innerText = "√";
			document.getElementById("checkPhone").style.color = "red";
		}
	} else if (attr == "email") {
		var emailRegex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if (!(flag.value.match(emailRegex))) {
			document.getElementById("checkEmail").innerText = "邮箱格式不对";
			document.getElementById("checkEmail").style.color = "red";
		} else {
			document.getElementById("checkEmail").innerText = "√";
			document.getElementById("checkEmail").style.color = "red";
		}
	}
}

window.onload = function() {
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var repassword = document.getElementById("repassword");
	var phone = document.getElementById("phone");
	var email = document.getElementById("email");
	username.onkeyup = function() {
		inprove("username");
	}
	password.onkeyup = function() {
		inprove("password");
	}
	repassword.onkeyup = function() {
		inprove("repassword");
	}
	phone.onkeyup = function() {
		inprove("phone");
	}
	email.onkeyup = function() {
		inprove("email");
	}
}

// 比较两次的密码
function checkPassword() {
	var password = document.getElementById("password").value;
	var repassword = document.getElementById("repassword").value;
	if (password != repassword) {
		alert("两次密码不一致，请重新输入");
		return false;
	}
}