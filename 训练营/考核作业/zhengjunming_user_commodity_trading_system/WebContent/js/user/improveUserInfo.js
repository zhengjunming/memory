/**
 * 
 */

function check() {
	var check = true;
	var email = document.getElementById("email").value;
	var question = document.getElementById("question").value;
	var answer = document.getElementById("answer").value;
	var phone = document.getElementById("phone").value;
	var emailRegex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var mobileRegex = /^1[34578]\d{9}$/;
	if (email == "" || question == "" || answer == "") {
		alert("必填信息不能为空");
		check = false;
	} else if (!(emailRegex.test(email))) {
		alert("邮箱格式不对");
		check = false;
	} else if (phone != "") {
		if (!(mobileRegex.test(phone))) {
			alert("手机格式不对");
			check = false;
		}
	}
	return check;
}
