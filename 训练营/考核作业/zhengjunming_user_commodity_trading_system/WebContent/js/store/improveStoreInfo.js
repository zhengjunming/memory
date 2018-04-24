/**
 * 完善店铺信息
 */
function check() {
	var check = true;
	var realname = document.getElementById("realname").value;
	var email = document.getElementById("email").value;
	var phone = document.getElementById("phone").value;
	var storeName = document.getElementById("storeName").value;
	var emailRegex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var mobileRegex = /^1[34578]\d{9}$/;
	if (email == "" || realname == "" || phone == "" || storeName == "") {
		alert("必填信息不能为空");
		check = false;
	}else if (!(mobileRegex.test(phone))) {
		alert("手机号码格式不对");
		check = false;
	} else if (!(emailRegex.test(email))) {
		alert("邮箱格式不对");
		check = false;
	}
	return check;
}