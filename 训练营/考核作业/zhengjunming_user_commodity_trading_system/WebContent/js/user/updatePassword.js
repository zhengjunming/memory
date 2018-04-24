/**
 * 用户修改密码信息校对
 */
function check() {
	var check = true;
	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;
	var repassword = document.getElementById("repassword").value;
	var regex = /^[0-9a-zA-Z]{6,16}$/;
	if (!regex.test(oldPassword) || !regex.test(newPassword)
			|| !regex.test(repassword)) {
		alert("密码格式不对");
		check = false;
	} else if (oldPassword == "" || newPassword == "" || repassword == "") {
		alert("信息不能为空");
		check = false;
	} else if (oldPassword.length < 6 || oldPassword.length > 16
			|| newPassword.length < 6 || newPassword.length > 16
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

// 实时监听用户的输入是否正确
function inprove(attr) {
	var flag = document.getElementById(attr);
	if (attr == "oldPassword") {
		var regex = /^[0-9a-zA-Z]{6,16}$/;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkOldPassword").innerText = "密码格式不对";
			document.getElementById("checkOldPassword").style.color = "red";
		} else {
			document.getElementById("checkOldPassword").innerText = "√";
			document.getElementById("checkOldPassword").style.color = "red";
		}
	} else if (attr == "newPassword") {
		var regex = /^[0-9a-zA-Z]{6,16}$/;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkNewPassword").innerText = "密码格式不对";
			document.getElementById("checkNewPassword").style.color = "red";
		} else {
			document.getElementById("checkNewPassword").innerText = "√";
			document.getElementById("checkNewPassword").style.color = "red";
		}
	} else {
		var regex = /^[0-9a-zA-Z]{6,16}$/;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkRepassword").innerText = "密码格式不对";
			document.getElementById("checkRepassword").style.color = "red";
		} else {
			document.getElementById("checkRepassword").innerText = "√";
			document.getElementById("checkRepassword").style.color = "red";
		}
	}
}

window.onload = function() {
	var password = document.getElementById("oldPassword");
	var newPassword = document.getElementById("newPassword");
	var repassword = document.getElementById("repassword");

	password.onkeyup = function() {
		inprove("oldPassword");
	}
	repassword.onkeyup = function() {
		inprove("repassword");
	}
	newPassword.onkeyup = function() {
		inprove("newPassword");
	}
}