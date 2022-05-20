/**
 * 회원 정보 수정
 */

window.onload = function() {
	
	// 상세 주소(부가 항목)
	var join_zipNum = document.querySelector("form#update input#zipNum");
	var join_address1 = document.querySelector("form#update input#address1");
	var join_address2 = document.querySelector("form#update input#address2");
	

	// 주소 초기화 시작
	address_init_btn.onclick = function() {
		
		console.log("주소 초기화");
		
		join_zipNum.value = "";
		join_address1.value = "";
		join_address2.value = "";
		
	} //	
	// 주소 초기화 끝
	
};	