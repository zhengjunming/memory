/**
 * 
 */
function check() {
	var receiver = document.getElementById("receiver").value;
	var address = document.getElementById("address").value;
	var phone = document.getElementById("phone").value;
	if (receiver == "" || address == "" || phone == "") {
		alert("必填信息不能为空");
		return false;
	}
	return true;
}