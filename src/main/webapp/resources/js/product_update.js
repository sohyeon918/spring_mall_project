/**
 * 상품 등록 
 */
window.onload = function() {
	
	var price1 = document.getElementById("price1");
	var price2 = document.getElementById("price2");
	var price3 = document.getElementById("price3");
	
	price2.onblur = function(e) {
		
		console.log("B-A 자동 계산");
		
		console.log("price1.value : " + price1.value);
		console.log("price2.value : " + price2.value);
		
		if (price1.value == '' || price2.value == '') {
			
			alert("상품 원가/판매가를 입력하십시오");
			price1.focus();
			
		} else {
			
			if (price1.value < 0 || price2.value < 0) {
			
				alert("상품 원가/판매가는 0원 이상이어야 합니다");
				price1.focus();
				
			} else {
			
				price3.value = price2.value - price1.value;
				console.log("price3.value : " + price3.value);
			}
			
		} //		
		
	} // price2.onblur
	
	// 상품 종류(분류) 셀렉트 박스(select) 초기화
	// 초기 상태
	// var kind = "${product.kind}"; // 주의사항) EL 변수 인식 (X)
	// 대체 방안) 임시 히든 필드에 값을 저장하여 그 값을 조회(얻어옴) 
	var kind = document.getElementById("kind_hidden").value;
	
	console.log("kind : " + kind);
	
	var selectBox = document.getElementById("kind");
	
	var selectedIdx = 0;
	
	for (var i=0; i<selectBox.options.length; i++) {
		
		if (selectBox.options[i].value == kind) {
			selectedIdx = i;
		}
		
	} //
	
	console.log("초기 값 : " + selectedIdx);
	
	// 선택된 index => select "selected"
	selectBox.selectedIndex = selectedIdx;
	
} // window.onload
