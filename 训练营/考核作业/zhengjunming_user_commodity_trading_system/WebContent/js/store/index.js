/**
 * 
 */
function check2() {
	var page = document.getElementById("page").value;
	if (page == "") {
		return false;
	}
}
function check() {
	var check = true;
	var key = document.getElementById("key").value;
	if (key == "") {
		check =  false;
	}
	return check;
}