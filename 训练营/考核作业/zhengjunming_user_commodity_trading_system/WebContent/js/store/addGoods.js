/**
 * 上架商品信息判断
 */

function check() {
	var check = true;
	var goodsName = document.getElementById("goodsName").value;
	var marketPrice = document.getElementById("marketPrice").value;
	var sellPrice = document.getElementById("sellPrice").value;
	var quantity = document.getElementById("quantity").value;
	var regex1 = /^[1-9][0-9]*$/;
	var regex2 = /^[1-9]d*.d*|0.d*[1-9]d*$/;
	if (goodsName == "" || marketPrice == "" || sellPrice == ""
			|| quantity == "") {
		alert("必填信息不能为空！");
		check = false;
	} else if (!(regex1.test(quantity))) {
		alert("库存量必须为正整数");
		check = false;
	} else if (!(regex1.test(marketPrice)) && !(regex2.test(marketPrice))) {
		alert("市场价格必须为正浮点数或正整数");
		check = false;
	} else if (!(regex1.test(sellPrice)) && !(regex2.test(sellPrice))) {
		alert("销售价格必须为正浮点数或正整数");
		check = false;
	} 
	return check;
}
