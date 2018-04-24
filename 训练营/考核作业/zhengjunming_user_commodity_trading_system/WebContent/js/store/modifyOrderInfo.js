/**
 * 修改订单信息
 */

function check() {
	var check = true;
	var receiver = document.getElementById("receiver").value;
	var address = document.getElementById("address").value;
	var phone = document.getElementById("phone").value;
	var mobileRegex = /^1[34578]\d{9}$/;
	if (receiver == "" || address == "" || phone == "") {
		alert("信息不能为空");
		check = false;
	} else if (!(mobileRegex.test(phone))) {
		alert("手机号码格式不对");
		check = false;
	}
	return check;
}