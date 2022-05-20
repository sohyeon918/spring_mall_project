////////////////////////////////////////////////////////////////////////////////

// 회원 가입(join) 폼 점검(form validation)

var id_check_flag = false; // 아이디 중복 점검 플래그 변수
var pw_check_flag = false; // 패쓰워드 점검 플래그 변수
var name_check_flag = false; // 이름 점검 플래그 변수
var email_check_flag = false; // 이메일 점검 플래그 변수 
var phone_check_flag = false; // 연락처 점검 플래그 변수


window.onload = function() {
	
	// 필수 항목
	var join_id = document.querySelector("form#join input#id");
	var join_pwdCheck = document.querySelector("form#join input#pwdCheck");
	var join_name = document.querySelector("form#join input#name");
	var join_email = document.querySelector("form#join input#email");
	var join_phone = document.querySelector("form#join input#phone");
	
	// 상세 주소(부가 항목)
	var join_zipNum = document.querySelector("form#join input#zipNum");
	var join_address1 = document.querySelector("form#join input#address1");
	var join_address2 = document.querySelector("form#join input#address2");
	
	// 아이디 중복 점검 시작
	join_id.onblur = function() {
		
		console.log("아이디 값 : " + join_id.value);
		
		// 폼 점검(form validation)
		// console.log("아이디 유효성 점검 여부 : " + join_id.value.match(/^[a-zA-Z]{1}\w{7,19}$/));
		console.log("아이디 유효성 점검 여부2 : " + new RegExp(/^[a-zA-Z]{1}\w{7,19}$/).test(join_id.value));
		
		let join_id_valid = new RegExp(/^[a-zA-Z]{1}\w{7,19}$/).test(join_id.value);
		
		// ajax 전송 여부
		if (join_id_valid == true) {
			
			// ajax
			console.log("ajax 전송");
			
			$.ajax({
				url : "../member/id_check",
				type : "get",
				data : { id : $("form#join input#id").val() },
				dataType: "text",
				success : function(data) {
					
					console.log("data : " + data);
					
					if (data.trim() == '회원 존재') {
						
						// console.log("아이디 플래그 상태 : " + $("#id_check_flag").html());
						
						alert("해당 아이디의 회원이 이미 존재합니다.");
						
						id_check_flag = false;
						$("#id_check_flag").html("false"); // 플래그 모니터링에 반영
						
						// 추가) 탈퇴한 회원이 재가입 시도했을 때
						if (confirm("탈퇴한 회원이시면 재가입을 하시겠습니까?")) {
							
							location.href = "../member/checkInactiveMember";
						}
						
						// console.log("아이디 플래그 상태2 : " + $("#id_check_flag").html());
						
					} else {
						
						alert("사용할 수 있는 아이디입니다.");
						
						id_check_flag = true;
						$("#id_check_flag").html("true"); // 플래그 모니터링에 반영
					} //
					
				},
				error : function(xhr, status, error) {
					
					console.error("xhr : " + xhr);
					console.error("status : " + status);
					console.error("error : " + error);
				}
				
			}); // ajax
			
		} else {
			
			// 필드 초기화 + 재입력
			console.log("재입력");
			
			id_check_flag = false;
			$("#id_check_flag").html("false"); // 플래그 모니터링에 반영
			join_id.value = "";
			// join_id.focus();
		} //
		
	} //
	// 아이디 중복 점검 끝	
	
	// 패쓰워드 점검 시작
	join_pwdCheck.onblur = function() {
		
		console.log("패쓰워드 점검");
		
		var pwd = document.getElementById("pwd");
		var pwdCheck = document.getElementById("pwdCheck");
		
		console.log("pwd : " + pwd.value);
		console.log("pwdCheck : " + pwdCheck.value);
		
		// 패쓰워드 폼점검(form validation)
		var regex_pwd = new RegExp(/^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,20}$/);
		
		console.log("패쓰워드1 정규식 점검 : " + regex_pwd.test(pwd.value))
		console.log("패쓰워드2 정규식 점검 : " + regex_pwd.test(pwdCheck.value))
		
		if (regex_pwd.test(pwd.value) == false || regex_pwd.test(pwdCheck.value) == false) {
			
			console.log("패쓰워드 폼점검 : 규정 불일치");
			
			console.log("에러 메시지 : " + pwd.title);
			
			// 에러 메시징
			document.getElementById("pwd_err_msg").innerHTML = pwd.title;
			
			// 패쓰워드 플래그 : false
			pw_check_flag = false;
			document.getElementById("pw_check_flag").innerHTML = "false";
		
		} else { // 폼점검 통과 
			
			if (pwd.value == pwdCheck.value) {
				
				console.log("패쓰워드 일치");
				
				// 에러 메시지 초기화
				document.getElementById("pwd_err_msg").innerHTML = "";
				
				// 패쓰워드 플래그 : true
				pw_check_flag = true;
				document.getElementById("pw_check_flag").innerHTML = "true";
				
			} else {
				
				console.log("패쓰워드 불일치");
				
				// 에러 메시징
				document.getElementById("pwd_err_msg").innerHTML = "패쓰워드가 일치하지 않습니다";
				
				// 패쓰워드 플래그 : false
				pw_check_flag = false;
				document.getElementById("pw_check_flag").innerHTML = "false";
			}
		
		} //
		
	} //
	// 패쓰워드 점검 끝
	
	// 이름 점검 시작
	join_name.onblur = function() {
		
		console.log("회원 이름 폼점검 : " + join_name.value);
		
		var regex_name = new RegExp(/^[가-힣]{2,13}$/);
		
		if (regex_name.test(join_name.value)) {
			
			console.log("이름 필드 유효성 점검 통과")
			
			name_check_flag = true;
			document.getElementById("name_check_flag").innerHTML = "true";
			
			document.getElementById("name_err_msg").innerHTML = "";
			
		} else {
			
			console.log("이름 필드 유효성 점검 오류")
			
			name_check_flag = false;
			document.getElementById("name_check_flag").innerHTML = "false";
			
			document.getElementById("name_err_msg").innerHTML = join_name.title;
		} //
		
		///// 전체 플래그 상태 //////
		
		console.log("아이디 필드 점검 플래그 : " + id_check_flag);
		console.log("패쓰워드 필드 점검 플래그 : " + pw_check_flag);
		console.log("이름 필드 점검 플래그 : " + name_check_flag);
		
	} //
	// 이름 점검 끝
	
	// 이메일 점검 시작
	join_email.onblur = function() {
		
		console.log("이메일 값 : " + join_email.value);
		document.getElementById("email_err_msg").innerHTML = ""; // 에러 메시지 초기화
		
		const join_email_valid = new RegExp(/^[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/).test(join_email.value);
		
		// ajax 전송 여부
		if (join_email_valid == true) {
			
			console.log("이메일 유효성 점검 통과");
			
			$.ajax({
				url : "../member/email_check",
				type : "get",
				data : { email : $("form#join input#email").val() },
				dataType: "text",
				success : function(data) {
					
					console.log("data : " + data);
					
					if (data.trim() == '중복 이메일 존재') {
						
						// console.log("이메일 플래그 상태 : " + $("#email_check_flag").html());
						
						alert("해당 이메일은 이미 사용하고 있습니다.");
						
						email_check_flag = false;
						$("#email_check_flag").html("false"); // 플래그 모니터링에 반영
						
						// console.log("이메일 플래그 상태2 : " + $("#email_check_flag").html());
						
					} else {
						
						alert("사용할 수 있는 이메일입니다.");
						
						email_check_flag = true;
						$("#email_check_flag").html("true"); // 플래그 모니터링에 반영
						
						document.getElementById("email_err_msg").innerHTML = "";
					} //
					
				},
				error : function(xhr, status, error) {
					
					console.error("xhr : " + xhr);
					console.error("status : " + status);
					console.error("error : " + error);
				}
				
			}); // ajax
			
		} else {
			
			// 필드 초기화 + 재입력
			console.log("재입력");
			
			// 에러 메시징
			document.getElementById("email_err_msg").innerHTML = join_email.title;
			
			email_check_flag = false;
			$("#email_check_flag").html("false"); // 플래그 모니터링에 반영
			
			join_email.value = "";
			// join_email.focus();
		} // if
		
	}
	// 이메일 점검 끝
	
	// 연락처 점검 시작
	join_phone.onblur = function() {
		
		console.log("연락처 값 : " + join_phone.value);
		document.getElementById("phone_err_msg").innerHTML = ""; // 에러 메시지 초기화
		
		const join_phone_valid = new RegExp(/^01\d{1}-\d{3,4}-\d{4}$/).test(join_phone.value);
		
		// ajax 전송 여부
		if (join_phone_valid == true) {
			
			console.log("연락처 유효성 점검 통과");
			
			$.ajax({
				url : "../member/phone_check",
				type : "get",
				data : { phone : $("form#join input#phone").val() },
				dataType: "text",
				success : function(data) {
					
					console.log("data : " + data);
					
					if (data.trim() == '중복 연락처(휴대폰) 존재') {
						
						// console.log("연락처 플래그 상태 : " + $("#phone_check_flag").html());
						
						// alert("해당 연락처는 이미 사용하고 있습니다.");
						$("#phone_err_msg").html("해당 연락처는 이미 사용하고 있습니다.");
						
						phone_check_flag = false;
						$("#phone_check_flag").html("false"); // 플래그 모니터링에 반영
						
						// console.log("연락처 플래그 상태2 : " + $("#phone_check_flag").html());
						
					} else {
						
						// alert("사용할 수 있는 연락처입니다.");
						$("#phone_err_msg").html("사용할 수 있는 연락처입니다.");
						
						phone_check_flag = true;
						$("#phone_check_flag").html("true"); // 플래그 모니터링에 반영
						
						document.getElementById("phone_err_msg").innerHTML = "";
					} //
					
				},
				error : function(xhr, status, error) {
					
					console.error("xhr : " + xhr);
					console.error("status : " + status);
					console.error("error : " + error);
				}
				
			}); // ajax
			
		} else {
			
			// 필드 초기화 + 재입력
			console.log("재입력");
			
			// 에러 메시징
			document.getElementById("phone_err_msg").innerHTML = join_phone.title;
			
			phone_check_flag = false;
			document.getElementById("phone_check_flag").innerHTML = false; // 플래그 모니터링에 반영
			
			join_phone.value = "";
			join_phone.focus();
		} // if
		
	}
	// 연락처 점검 끝
	
	// 주소 점검 시작
	// 전송 가능 상태
	// case-1) 모든 필드(우편번호, 기본주소, 상세수조) 공란(blank) 상태
	// case-2) 모든 필드(우편번호, 기본주소, 상세수조) 입력(full) 상태
	join_address2.onblur = function() {
		
		console.log("주소 폼점검 : " + join_address2.value);
		
		if ((join_zipNum.value == '' && join_address1.value == '' && join_address2.value.trim() == '') ||
			(join_zipNum.value != '' && join_address1.value != '' && join_address2.value.trim() != '')) 	
		{	
			console.log("상세 주소 필드 유효성 점검 통과")
			
			document.getElementById("address_err_msg").innerHTML = "";
			
		} else {
			
			console.log("상세 주소 필드 유효성 점검 오류")
			
			// document.getElementById("address_err_msg").innerHTML = "상세 주소를 입력하십시오";
			
			if (join_zipNum.value != '' && join_address1.value != '' && join_address2.value.trim() == '') {
				
				document.getElementById("address_err_msg").innerHTML = "상세 주소를 입력하십시오";
				
			} else if (join_zipNum.value == '' && join_address1.value == '' && join_address2.value.trim() != '') {
				
				document.getElementById("address_err_msg").innerHTML = "주소 찾기 버튼으로 주소를 검색하십시오";
				
			} //
			
			document.getElementById("address2").innerHTML = "";
			
		} //
		
		///// 전체 플래그 상태 //////
		
		console.log("아이디 필드 점검 플래그 : " + id_check_flag);
		console.log("패쓰워드 필드 점검 플래그 : " + pw_check_flag);
		console.log("이름 필드 점검 플래그 : " + name_check_flag);
		console.log("이메일 필드 점검 플래그 : " + email_check_flag);
		console.log("연락처(휴대폰) 필드 점검 플래그 : " + phone_check_flag);
		
	} //
	 
	
	// 주소 초기화 시작
	address_init_btn.onclick = function() {
		
		console.log("주소 초기화");
		
		join_zipNum.value = "";
		join_address1.value = "";
		join_address2.value = "";
		
	} //	
	// 주소 초기화 끝
	
	///////////////////////////////////////////////////////////////
	
	console.log("아이디 필드 점검 플래그 : " + id_check_flag);
	console.log("패쓰워드 필드 점검 플래그 : " + pw_check_flag);
	console.log("이름 필드 점검 플래그 : " + name_check_flag);
	console.log("이메일 필드 점검 플래그 : " + email_check_flag);
	console.log("연락처(휴대폰) 필드 점검 플래그 : " + phone_check_flag);
	
	///////////////////////////////////////////////////////////////
	
	
	// 회원 정보 전송(가입) 시작
	join_submit.onclick = function() {
		
		console.log("회원 정보 전송(가입)");
		
		console.log("아이디 필드 점검 플래그 : " + id_check_flag);
		console.log("패쓰워드 필드 점검 플래그 : " + pw_check_flag);
		console.log("이름 필드 점검 플래그 : " + name_check_flag);
		console.log("이메일 필드 점검 플래그 : " + email_check_flag);
		console.log("연락처(휴대폰) 필드 점검 플래그 : " + phone_check_flag);
		
		// 필수 항목 점검
		if (id_check_flag == true && // 아이디 중복 점검 플래그 변수
		    pw_check_flag == true && // 패쓰워드 점검 플래그 변수
		    name_check_flag == true && // 이름 점검 플래그 변수
		    email_check_flag == true && // 이메일 점검 플래그 변수 
		    phone_check_flag == true) // 연락처 점검 플래그 변수
		{	
			console.log("폼점검 완료");
			
			// 부가 항목 점검 : ex) 주소
			// 전송 가능 상태
			// case-1) 모든 필드(우편번호, 기본주소, 상세수조) 공란(blank) 상태
			// case-2) 모든 필드(우편번호, 기본주소, 상세수조) 입력(full) 상태
			
//			var zipNum = document.getElementById("zipNum").value;
//			var address1 = document.getElementById("address1").value;
//			var address2 = document.getElementById("address2").value;
			
			if ((join_zipNum.value == '' && join_address1.value == '' && join_address2.value.trim() == '') ||
				(join_zipNum.value != '' && join_address1.value != '' && join_address2.value.trim() != '')) 	
			{	
				// 회원 정보 전송(가입)
				console.log("회원 정보 전송(가입)");
				
				document.getElementById("join").submit();
				
				
			} else {
				
				// 회원 정보 미전송
				console.log("폼점검 최종 실패 & 전송 실패");
				
				if (join_zipNum.value != '' && join_address1.value != '' && join_address2.value.trim() == '') {
					
					document.getElementById("address_err_msg").innerHTML = "상세 주소를 입력하십시오";
					
				} else if (join_zipNum.value == '' && join_address1.value == '' && join_address2.value.trim() != '') {
					
					document.getElementById("address_err_msg").innerHTML = "주소 찾기 버튼으로 주소를 검색하십시오";
					
				} //
				
				document.getElementById("address2").innerHTML = "";
				
			} // 
			
		
		} else {
			
			console.log("폼점검 미완료");
		}
	} //
	
	
	// 회원 정보 전송(가입) 끝
	
} // window.onload 끝