/**
 * 
 */
function go_order_save() {
	var count = 0;
	if (document.frm.result.length == undefined) {
		if (document.frm.result.checked == true) {
			count++;
		}
	} else {
		for (var i = 0; i < document.frm.result.length; i++) {
			if (document.frm.result[i].checked == true) {
				count++;
			}
		}
	}
	if (count == 0) {
		alert("주문처리할 항목을 선택해 주세요.");
	} else {
		document.frm.action = "/project/admin/order/orderSave";
		document.frm.submit();
	}
}