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
	
} // window.onload
