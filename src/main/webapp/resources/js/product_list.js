/**
 * 전체 상품 리스트
 */
window.onload = function() {
	
	// select 박스 초기 설정(상품 사용 여부, 베스트 상품)
	
	// 상품 사용 여부 초기화
	var useynSelectBoxId;
	var useynSelectBox;
	var pseq;
	
	var useynSelectBoxes = document.querySelectorAll("select[id^=useyn]");
	
	console.log("한 페이지의 요소 갯수 : " + useynSelectBoxes.length);
	
	var useynBoxesLen = useynSelectBoxes.length;
	var selectedIdx;
	
	for (var i=0; i<useynBoxesLen; i++) {
		
		useynSelectBox = useynSelectBoxes[i];
		pseq = useynSelectBox.id.substring('useyn_'.length);
		
		console.log("select id : " + useynSelectBox.id);
		console.log("pseq : " + pseq);
		
		// 초기값 설정
		var pseqVal = document.getElementById("hidden_useyn_"+pseq).value;
		console.log("pseq init val : " + pseqVal);
		
		for (var j=0; j<useynSelectBox.options.length; j++) {
			
			if (useynSelectBox.options[j].value == pseqVal) {
				selectedIdx = j;
			}
		} // for
		
		useynSelectBox.selectedIndex = selectedIdx;
		
	} // for
	
	/////////////////////////////////////////////////////////////////////////////
	
	// 베스트 상품 초기화
	var bestynSelectBoxId;
	var bestynSelectBox;
	var pseq;

	var bestynSelectBoxes = document.querySelectorAll("select[id^=bestyn]");

	console.log("한 페이지의 요소 갯수 : " + bestynSelectBoxes.length);

	var bestynBoxesLen = bestynSelectBoxes.length;
	var selectedIdx;

	for (var i=0; i<bestynBoxesLen; i++) {
		
		bestynSelectBox = bestynSelectBoxes[i];
		pseq = bestynSelectBox.id.substring('bestyn_'.length);
		
		console.log("select id : " + bestynSelectBox.id);
		console.log("pseq : " + pseq);
		
		// 초기값 설정
		var pseqVal = document.getElementById("hidden_bestyn_"+pseq).value;
		console.log("pseq init val : " + pseqVal);
		
		for (var j=0; j<bestynSelectBox.options.length; j++) {
			
			if (bestynSelectBox.options[j].value == pseqVal) {
				selectedIdx = j;
			}
		} // for
		
		bestynSelectBox.selectedIndex = selectedIdx;
		
	} // for
	
	/////////////////////////////////////////////////////////////////////////////
	
	// 관리자가 상품 사용 여부 변경시 변경 사항 반영 : ajax
	var useynSelectBoxes = document.querySelectorAll("select[id^=useyn]");

	for (var useynSelectBox of useynSelectBoxes) {

		useynSelectBox.onchange = function(e) {
			
			console.log("이벤트 발생");
			console.log("useynSelectBoxes onchange event : " + e.currentTarget.id);
			
			var tempId = e.currentTarget.id;
			
			// 상품 아이디
			var pseq = tempId.substring("useyn_".length);
			// 상품 사용 여부
			var useyn = document.getElementById(tempId).value;
			
			console.log("상품 아이디 : " + pseq);
			console.log("선택된 값 : " + useyn);
			
			// AJAX : 상품 사용(가) 여부 => update => success
			$.ajax({
				url : "/project/admin/product/updateUseYN",
				type : "get",
				data : { 
					pseq : pseq,
					useyn : useyn 	
				},
				dataType: "text",
				success : function(data) {
					
					console.log("data : " + data);
					alert(data.trim()); //  메시징
					
				},
				error : function(xhr, status, error) {
					
					console.error("xhr : " + xhr);
					console.error("status : " + status);
					console.error("error : " + error);
				}
				
			}); // ajax
		} //

	} // for
	
	//////////////////////////////////////////////////////////////////
	
	// 관리자가 상품 베스트 상품 여부 변경시 변경 사항 반영 : ajax
	var bestynSelectBoxes = document.querySelectorAll("select[id^=bestyn]");

	for (var bestynSelectBox of bestynSelectBoxes) {

		bestynSelectBox.onchange = function(e) {
			
			console.log("이벤트 발생");
			console.log("bestynSelectBoxes onchange event : " + e.currentTarget.id);
			
			var tempId = e.currentTarget.id;
			
			// 상품 아이디
			var pseq = tempId.substring("bestyn_".length);
			// 상품 사용 여부
			var bestyn = document.getElementById(tempId).value;
			
			console.log("상품 아이디 : " + pseq);
			console.log("선택된 값 : " + bestyn);
			
			// AJAX : 상품 사용(가) 여부 => update => success
			$.ajax({
				url : "/project/admin/product/updateBestYN",
				type : "get",
				data : { 
					pseq : pseq,
					bestyn : bestyn 	
				},
				dataType: "text",
				success : function(data) {
					
					console.log("data : " + data);
					alert(data.trim()); //  메시징
					
				},
				error : function(xhr, status, error) {
					
					console.error("xhr : " + xhr);
					console.error("status : " + status);
					console.error("error : " + error);
				}
				
			}); // ajax
		} //

	} // for

	//////////////////////////////////////////////////////////////////
	
	// 상품 검색 폼점검
	var search_product_btn = document.getElementById("search_product_btn");
	var search_field = document.getElementById("search_field");
	var search_word = document.getElementById("search_word");
	var search_product_frm = document.getElementById("search_product_frm");
	
	search_product_btn.onclick = function(e) {
		
		alert("상품 검색");
		
		// 전체 상품 검색 : 검색어 미입력
		if (search_field.value == 'all' && search_word.value.trim() == '') {
			
			alert("검색어를 입력하십시오.");
			search_word.focus(); // 검색어 입력 대기
		
		// 전체 상품 검색 : 검색어 입력 전송	
		} else if (search_field.value == 'all' && search_word.value.trim() != '') {
			
			alert("전체 상품 내에서 검색하겠습니다.");
			search_product_frm.submit();
			
		// 상품 분류 검색 : 검색어 미입력시는 상품 분류 내의 모든 상품 검색	
		} else if (search_field.value != 'all' && search_word.value.trim() == '') {
		
			alert("상품 분류로 검색하겠습니다.");
			search_product_frm.submit();
			
		// 상품 분류 검색 : 검색어 입력 전송	
		} else if (search_field.value != 'all' && search_word.value.trim() != '') {
			
			search_product_frm.submit();
		}
		
	} //
	
} // window.onload