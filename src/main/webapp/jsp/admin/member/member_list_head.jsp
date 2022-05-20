<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.javateam.project.domain.*" %>

<!-- daum 우편번호 서비스 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script src="${contextPath}/js/member.js" charset="UTF-8"></script>

<script>
	// 툴팁(tooltip:가이드) 제어
	$(function() {
		
		// 이메일/연락처/우편번호/기본주소 
		$("[data-toggle='tooltip']").tooltip();
	});

	// 모달 박스(modal) 제어
	$(function() {
		
		$("[id^='ckbox_']").click(function(e){
		
			console.log("체크박스 체크");
			
			var member_id = e.currentTarget.id.substring('ckbox_'.length);
			console.log("member_id : " + member_id);
			
			// 다른 체크 박스 비활성화
			var chboxes = document.querySelectorAll("input[id^=ckbox_]");
			for (var chbox of chboxes) {
				chbox.checked = false;
			}
			
			// 자신의 체크박스는 활성화
			document.querySelector("input[id=ckbox_"+member_id+"]").checked = true;
						
			// $("#member_modal").css({"opacity":"1"});
			
			// 팝업(모달)을 좌표 회복
			$("#member_modal").css({"left":"500px"});
			$("#member_modal").css({"top":"150px"});

			// 개별 회원 정보 가져오기
			
			$.ajax({
				url : "${contextPath}/member/member_view",
				type : "get",
				data : { id : member_id },
				dataType: "text",
				success : function(data) {
					
					console.log("data : " + data);
					
					var json = JSON.parse(data);
					console.log("id : " + json.id);
					
					// HTML에 대입/출력
					$("#member_modal_header_title").html(json.id + "의 회원 정보");
					
					$("#member_modal #user_id").html(json.id);
					$("#member_modal #user_pwd").html(json.pwd);
					$("#member_modal #user_name").html(json.name);
					$("#member_modal #user_email").html(json.email);
					$("#member_modal #user_phone").html(json.phone);
					$("#member_modal #user_zipNum").html(json.zipNum);
					$("#member_modal #user_address1").html(json.address1);
					$("#member_modal #user_address2").html(json.address2);
					$("#member_modal #user_indate").html(json.indate);
									
				},
				error : function(xhr, status, error) {
					
					console.error("xhr : " + xhr);
					console.error("status : " + status);
					console.error("error : " + error);
				}
				
			}); // ajax
			
		});
		
		// modal 팝업창 닫기
		$("#member_modal_close_btn").click(function() {
			
			// $("#member_modal").css({"opacity":"0"});
			
			// 팝업(모달)을 좌표 viewport 외곽에 위치
			$("#member_modal").css({"left":"-500px"});
			$("#member_modal").css({"top":"-150px"});
			
			// 체크 박스 비활성화
			console.log("member_id : " + $("#member_modal #user_id").html());
			// document.querySelector("input[id=ckbox_"+$("#member_modal #user_id").html()+"]").checked = false;
			
			var chboxes = document.querySelectorAll("input[id^=ckbox_]");
			for (var chbox of chboxes) {
				chbox.checked = false;
			}
			
		}); //
		
	});
	

	////////////////////////////////////////////////////////////////

	// java List => javascript Array
	function getRoleFromMembers(num) {
		
		var i = 0;
		var arr = new Array(); // javascript 배열
		
		<%
		    List<MemberRoleVO> memberList = (List<MemberRoleVO>)request.getAttribute("members");
		
			for (int i=0; i<memberList.size(); i++) {
				
				MemberRoleVO roleVO = memberList.get(i);
		%>
				arr.push("<%=roleVO.getRole() %>"); // javascript 배열에 요소 추가
		<%
			}
		%>
		
		// java List => javascript Array 변환 완료
		
		console.log("arr : " + arr[num]);
		
		return arr[num];
	}
	
	////////////////////////////////////////////////////////////////

	// java List => javascript Array
	// 기존 이메일/연락처/주소정보(우편번호, 기본주소, 상세주소) => JS 배열 (ex. 최대 10개)
	// ex) 이메일들 배열
	function getDefaultMemberFldsFromMembers(fld) {
		
		var i = 0;
		var arr = new Array(); // javascript 배열
		
		<%
		    List<MemberRoleVO> memberList2 = (List<MemberRoleVO>)request.getAttribute("members");
		
			for (int i=0; i<memberList2.size(); i++) {
				
				MemberRoleVO roleVO = memberList.get(i);
		%>
		 		// javascript 배열에 요소 추가
		 		
				if (fld == '이메일') {
					arr.push("<%=roleVO.getEmail() %>");
				} else if (fld == '연락처') {
					arr.push("<%=roleVO.getPhone() %>");
				} else if (fld == '우편번호') {
					arr.push("<%=roleVO.getZipNum() %>");
				} else if (fld == '기본주소') {
					arr.push("<%=roleVO.getAddress1() %>");
				} else if (fld == '상세주소') {
					arr.push("<%=roleVO.getAddress2() %>");
				}
		<%
			}
		%>
		
		// java List => javascript Array 변환 완료
		console.log("arr : " + arr);
		
		return arr;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	window.onload = function() {
		
		// var limit = "${pageVO.limit}";
		var limit = "${members.size()}";
		
		console.log("--limit :" +limit)
		
		// 회원 롤(role) 제어
		// 초기 상태(값) 반영
		//console.log("선택된 항목(option)의 index2 : " + selectBox.selectedIndex);
		//console.log("선택된 항목의 텍스트 : " + selectBox.options[selectBox.selectedIndex].text);
		//console.log("선택된 항목의 값 : " + selectBox.options[selectBox.selectedIndex].value);
		
		// select box(role) id => "role0"
		var selectBoxId;
		var selectBox;
		
		var role;
		
		for (var i=0; i<limit; i++) {
			
			selectBoxId = "role" + i;
			selectBox = document.getElementById(selectBoxId);
			
			// role = "${members[0].role}"; // (X)
			role = getRoleFromMembers(i); // java List => javascript Array
			
			console.log("개별 회원 role : " + role);
			
			for (var j=0; j<selectBox.options.length; j++) {
				
				if (selectBox.options[j].value == role) {
					selectedIdx = j;					
				}
				
			} // for
			
			console.log(j + "의 selectedIdx : " + selectedIdx);
			
			selectBox.selectedIndex = selectedIdx; // role => select에 반영
			
		} // for
		
		
		// 관리자가 변경시 변경 사항 반영 : ajax
		var roleSelectBoxes = document.querySelectorAll("select[id^=role]");
		
		for (var roleSelectBox of roleSelectBoxes) {
		
			roleSelectBox.onchange = function(e) {
				
				console.log("이벤트 발생");
				console.log("roleSelectBoxes onchange event : " + e.currentTarget.id);
				
				var tempId = e.currentTarget.id;
				console.log("target title : " + document.getElementById(tempId).title);
				// console.log("target name : " + document.getElementById(id).name);
				
				console.log("선택된 값 : " + document.getElementById(tempId).value);
				
				// 회원 아이디
				var memberId =  document.getElementById(tempId).title;
				// 회원 롤(role)
				var memberRole = document.getElementById(tempId).value;
				
				
				// AJAX : 회원 id, 회원 role => update => success => 전체 현황 reload
				$.ajax({
					url : "${contextPath}/member/update_role",
					type : "get",
					data : { 
						id : memberId,
						role : memberRole
					},
					dataType: "text",
					success : function(data) {
						
						console.log("data : " + data);
						
						/*
						if (data.trim() == '회원 롤 정보가 정상적으로 수정되었음') {
							
							location.reload(); // 페이지 갱신
						} 
						*/
						
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
		
		////////////////////////////////////////////////////////////////////////
		
		// 관리자 모드 - 개별 회원 정보 수정(이메일)
		var emails = document.querySelectorAll("input[type='text'][id^=email]");
		
		// 기존 이메일들 저장 변수(임시 배열) : 2022.5.4
		var default_emails = getDefaultMemberFldsFromMembers("이메일");
		
		console.log("emails update");
		
		// 배열 확인
		default_emails.forEach(function (item, index, array) {
		  console.log(item, index)
		});
		
		console.log("default emails[0] : " + default_emails[0]);
		
		for (var emailUpdate of emails) {
			
			console.log("email update");
			
			emailUpdate.onclick = function(e) {
				
				console.log("이메일 클릭");
				
				var id = e.currentTarget.id;
				console.log("focus id : " + id);
				
				document.getElementById(id).style.background = "#FFFCCC"; // 입력 활성화 상태
				
				// 기존 이메일 저장 : 2022.5.4
				var count_email_id = "count_"+id.substring("email_".length);
				var count_email = document.getElementById(count_email_id).value;
				
				var default_email = default_emails[count_email];
				console.log("기존 이메일 : " + default_email);
				
				// emailUpdate.readOnly = false; // 편집 가능 상태
			}
			
		} // for
		
		
		// var email_update_btns = document.querySelectorAll("button[id^=email_update_btn_]");
		var email_update_btns = document.querySelectorAll("[id^=email_update_btn_]");
		
		for (var email_update_btn of email_update_btns) {

			console.log("버튼들");
			
			// 버튼 클릭시
			email_update_btn.onclick = function(e) {
				
				console.log("클릭");
				
				alert("이메일 수정");
				
				var btnId = e.currentTarget.id;
				console.log("btn id : " + btnId);
				
				if (confirm('정말 정보를 변경하시겠습니까?')) {
					
					var userId = btnId.substring("email_update_btn_".length);
					var emailId = "email_"+userId;
					
					console.log("사용자 아이디 : " + userId);
					console.log("이메일 아이디 : " + emailId);
					
					var emailUpdate = document.getElementById(emailId);
					
					console.log("이메일 값(test) : " + emailUpdate.value);
					// 폼점검
					const email_valid = new RegExp(/^[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/).test(emailUpdate.value);
					
					if (email_valid == true) {
						
						console.log("폼점검 성공");
						
						// ajax (이메일 중복 점검)
						$.ajax({
							url : "${contextPath}/member/email_check_update",
							type : "get",
							data : { 
								id : userId,
								email : emailUpdate.value 
							},
							dataType: "text",
							success : function(data) {
								
								console.log("data : " + data);
								
								alert(data);
								
								if (data.trim() == '사용 가능한 이메일') {
									
									console.log("update 전송");
									
									// 개별 회원 이메일 수정
									// ajax (이메일 수정)
									$.ajax({
										url : "${contextPath}/member/email_update",
										type : "get",
										data : { 
											id : userId,
											email : emailUpdate.value 
										},
										dataType: "text",
										success : function(data) {
											
											console.log("data : " + data);
											
											alert(data); // 이메일 수정 성공/실패 메시징
											
										},
										error : function(xhr, status, error) {
											 
											console.error("xhr : " + xhr);
											console.error("status : " + status);
											console.error("error : " + error);
										}
										
									}); // ajax (이메일 수정)  
									
								} // '사용 가능한 이메일'
								
								else { // 중복일 경우
									
									console.log("중복");
									
									// 실패시 : "중복 이메일 존재"
									if (data.trim() == '중복 이메일 존재') {
										
										// 원래 화면으로 복구 (화면 리로드(리프레쉬))
										// location.reload();
										
										// 원래 이메일로 복구(초기화) : 2022.5.4
										emailUpdate.value = default_email;
										console.log("기존 이메일 : " + default_email);
									}
								} //
								
							},
							error : function(xhr, status, error) {
								
								console.error("xhr : " + xhr);
								console.error("status : " + status);
								console.error("error : " + error);
							}
							
						}); // ajax (이메일 중복 점검)
						
					} else {
						console.log("폼점검 실패");
						
						alert("이메일 폼점검 실패");
						
						// 원래 이메일로 복구(초기화) : 2022.5.4
						emailUpdate.value = default_email;
						console.log("기존 이메일 : " + default_email);
					} //
					
					// 전송
					
				} // confirm 
				else {
					
					console.log("전송 취소");
					return false;
				} //
				// confirm ~~ else 
				
			} // onclick
		} //
		
		//////////////////////////////////////////////////////////////////
		
		// 관리자 모드 - 개별 회원 정보 수정(연락처)		
		var phones = document.querySelectorAll("input[type='text'][id^=phone]");
		
		// 기존 연락처 저장 변수(임시) : 2022.5.4
		// var default_phone;
		var default_phones = getDefaultMemberFldsFromMembers("연락처");
		
		console.log("phones update ------------------------");
		
		// 배열 확인
		default_phones.forEach(function (item, index, array) {
		  console.log(item, index)
		});
		
		console.log("default phones[0] : " + default_phones[0]);
		
		for (var phoneUpdate of phones) {
			
			console.log("phone update");
			
			phoneUpdate.onclick = function(e) {
				
				console.log("연락처 클릭 : " + count_phone);
				
				console.log("default phones[0]-2 : " + default_phones[0]);
				
				var id = e.currentTarget.id;
				console.log("focus id : " + id);
				
				document.getElementById(id).style.background = "#FFFCCC"; // 입력 활성화 상태
				
				// 기존 연락처 저장 : 2022.5.4
				var count_phone_id = "count_"+id.substring("phone_".length);
				var count_phone = document.getElementById(count_phone_id).value;
				
				console.log("count_phone : " + count_phone);
				
				var default_phone = default_phones[count_phone];
				console.log("기존 연락처 : " + default_phone);
				
				// phoneUpdate.readOnly = false; // 편집 가능 상태
			}
			
		} // for
		
		
		var phone_update_btns = document.querySelectorAll("[id^=phone_update_btn_]");
		
		for (var phone_update_btn of phone_update_btns) {

			console.log("버튼들");
			
			// 버튼 클릭시
			phone_update_btn.onclick = function(e) {
				
				console.log("클릭");
				
				alert("연락처 수정");
				
				var btnId = e.currentTarget.id;
				console.log("btn id : " + btnId);
				
				if (confirm('정말 정보를 변경하시겠습니까?')) {
					
					var userId = btnId.substring("phone_update_btn_".length);
					var phoneId = "phone_"+userId;
					
					console.log("사용자 아이디 : " + userId);
					console.log("연락처 아이디 : " + phoneId);
					
					var phoneUpdate = document.getElementById(phoneId);
					
					console.log("연락처 값(test) : " + phoneUpdate.value);
					// 폼점검
					const phone_valid = new RegExp(/^01\d{1}-\d{3,4}-\d{4}$/).test(phoneUpdate.value);
					
					if (phone_valid == true) {
						
						console.log("폼점검 성공");
						
						// ajax (연락처 중복 점검)
						$.ajax({
							url : "${contextPath}/member/phone_check_update",
							type : "get",
							data : { 
								id : userId,
								phone : phoneUpdate.value 
							},
							dataType: "text",
							success : function(data) {
								
								console.log("data : " + data);
								
								alert(data);
								
								if (data.trim() == '사용 가능한 연락처') {
									
									console.log("update 전송");
									
									// 개별 회원 연락처 수정
									// ajax (연락처 수정)
									$.ajax({
										url : "${contextPath}/member/phone_update",
										type : "get",
										data : { 
											id : userId,
											phone : phoneUpdate.value 
										},
										dataType: "text",
										success : function(data) {
											
											console.log("data : " + data);
											
											alert(data); // 연락처 수정 성공/실패 메시징
											
										},
										error : function(xhr, status, error) {
											 
											console.error("xhr : " + xhr);
											console.error("status : " + status);
											console.error("error : " + error);
										}
										
									}); // ajax (연락처 수정)  
									
								} // '사용 가능한 연락처'
								
								else { // 중복일 경우
									
									console.log("중복");
									
									// 실패시 : "중복 연락처 존재"
									if (data.trim() == '중복 연락처 존재') {
										
										// 원래 화면으로 복구 (화면 리로드(리프레쉬))
										// location.reload();
										
										// 원래 연락처로 복구(초기화) : 2022.5.4
										phoneUpdate.value = default_phone;
										console.log("기존 연락처 : " + default_phone);
									}
								} //
								
							},
							error : function(xhr, status, error) {
								
								console.error("xhr : " + xhr);
								console.error("status : " + status);
								console.error("error : " + error);
							}
							
						}); // ajax (연락처 중복 점검)
						
					} else {
						console.log("폼점검 실패");
						
						alert("연락처 폼점검 실패");
						
						// 원래 연락처로 복구(초기화) : 2022.5.4
						phoneUpdate.value = default_phone;
						console.log("기존 연락처 : " + default_phone);
					} //
					
					// 전송
					
				} // confirm 
				else {
					
					console.log("전송 취소");
					return false;
				} //
				// confirm ~~ else 
				
			} // onclick
		} //
		
		////////////////////////////////////////////////////////////////////////
		
		// 관리자 모드 - 개별 회원 정보 수정(우편번호/기본주소)
		var zipNums = document.querySelectorAll("div[id^=zipNum]");
		var address1s = document.querySelectorAll("div[id^=address1]");
		
		// 기존 우편번호/기본주소 저장 변수(임시) : 2022.5.4
		var default_zipNums = getDefaultMemberFldsFromMembers("우편번호");
		var default_address1s = getDefaultMemberFldsFromMembers("기본주소");
		var default_address2s = getDefaultMemberFldsFromMembers("상세주소");
		
		console.log("zipNum/address1 update ------------------------");
		
		// 배열 확인
		default_zipNums.forEach(function (item, index, array) {
		  console.log(item, index)
		});
		
		console.log("default zipNums[0] : " + default_zipNums[0]);
		
		// 우편번호 필드
		for (var zipNumUpdate of zipNums) {
			
			console.log("zipNum update");
			
			zipNumUpdate.onclick = function(e) {
				
				var id = e.currentTarget.id;
				console.log("focus id : " + id);
				
				console.log("우편번호 클릭 : ");
				
				console.log("default zipNums[0]-2 : " + default_zipNums[0]);
				
				// 기존 우편번호 저장
				var count_zipNum_id = "count_"+id.substring("zipNum_".length);
				var count_zipNum = document.getElementById(count_zipNum_id).value;
				var userId = id.substring("zipNum_".length);
				
				console.log("count_zipNum : " + count_zipNum);
				
				var default_zipNum = default_zipNums[count_zipNum];
				console.log("기존 우편번호 : " + default_zipNum);
				
				// 주소 검색 팝업 출력
				getPostcodeAddress(userId);
				
			}; // zipNumUpdate.onclick
			
		} // for	
		
		// 기본주소 필드
		for (var address1Update of address1s) {
			
			console.log("address1 update");
			
			address1Update.onclick = function(e) {
				
				var id = e.currentTarget.id;
				console.log("focus id : " + id);
				
				console.log("기본주소 클릭 : ");
				
				console.log("default address1s[0]-2 : " + default_address1s[0]);
				
				// 기존 기본주소 저장
				var count_address1_id = "count_"+id.substring("address1_".length);
				var count_address1 = document.getElementById(count_address1_id).value;
				var userId = id.substring("address1_".length);
				
				console.log("count_address1 : " + count_address1);
				
				var default_address1 = default_zipNums[count_address1];
				console.log("기존 기본주소 : " + default_address1);
				
				// 주소 검색 팝업 출력
				getPostcodeAddress(userId);
				
			}; // address1Update.onclick
			
		} // for	
		
		// 우편번호/기본주소 이벤트 처리
		var zip_address1_update_btns = document.querySelectorAll("[id^=zip_address1_update_btn_]");
		
		for (var zip_address1_update_btn of zip_address1_update_btns) {

			console.log("버튼들");
			
			// 버튼 클릭시
			zip_address1_update_btn.onclick = function(e) {
				
				console.log("클릭");
				
				alert("우편번호/기본주소 수정");
				
				var btnId = e.currentTarget.id;
				console.log("btn id : " + btnId);
				
				if (confirm('정말 정보를 변경하시겠습니까?')) {
					
					var userId = btnId.substring("zip_address1_update_btn_".length);
					var zipNumId = "zipNum_"+userId;
					var address1Id = "address1_"+userId;
					var address2Id = "address2_"+userId;
					
					console.log("사용자 아이디 : " + userId);
					console.log("우편번호 아이디 : " + zipNumId);
					console.log("기본주소 아이디 : " + address1Id);
					console.log("상세주소 아이디 : " + address2Id);
					
					var zipNumUpdate = document.getElementById(zipNumId);
					var address1Update = document.getElementById(address1Id);
					var address2Update = document.getElementById(address2Id);
					
					console.log("우편번호 값(test) : " + zipNumUpdate.innerText);
					console.log("기본주소 값(test) : " + address1Update.innerText);
					console.log("상세주소 값(test) : " + address2Update.innerText);
					
					// 전송전 상세주소 입력 여부 점검
					// 상세주소가 입력되었을 때
					
					var address2Val = address2Update.innerText;
					
					if (address2Val.trim() != '') { 
						
						console.log("폼점검(유효주소) 성공 : 상세주소 입력");
						
						$.ajax({
							url : "${contextPath}/member/zip_address_update",
							type : "get",
							data : { 
								id : userId,
								zipNum : zipNumUpdate.innerText,
								address1 : address1Update.innerText,
								address2 : address2Val
							},
							dataType: "text",
							success : function(data) {
								
								console.log("data : " + data);
								
								alert(data);
								
							},
							error : function(xhr, status, error) {
								
								console.error("xhr : " + xhr);
								console.error("status : " + status);
								console.error("error : " + error);
							}
							
						}); // 전송
					
					// 상세주소가 입력되지 않았을 때	
					} else {
						
						console.log("폼점검(상세주소) 실패");
						
						alert("상세주소를 입력하십시오.");
						
						// 기존 정보 복원
						var idx = document.getElementById("count_"+userId).value
						default_address2 = default_address2s[idx];
						
						address2Update.innerHTML = default_address2;
						console.log("기존 상세주소 : " + default_address2);
						
						address2Update.focus();
					} //
					
					// 전송
					
				} // confirm 
				else {
					
					console.log("전송 취소");
					
					var idx = document.getElementById("count_"+userId).value
					default_address2 = default_address2s[idx];
					
					address2Update.innerHTML = default_address2;
					console.log("기존 상세주소 : " + default_address2);
					
					return false;
				} //
				// confirm ~~ else 
				
			} // onclick
			
		} //
		
		
		// 상세주소 이벤트 처리
		var address2_update_btns = document.querySelectorAll("[id^=address2_update_btn_]");
		
		for (var address2_update_btn of address2_update_btns) {

			console.log("상세 주소 버튼들");
			
			// 버튼 클릭시
			address2_update_btn.onclick = function(e) {
				
				console.log("클릭");
				
				alert("상세주소 수정");
				
				var btnId = e.currentTarget.id;
				console.log("btn id : " + btnId);
				
				if (confirm('정말 정보를 변경하시겠습니까?')) {
					
					var userId = btnId.substring("address2_update_btn_".length);
					var zipNumId = "zipNum_"+userId;
					var address1Id = "address1_"+userId;
					var address2Id = "address2_"+userId;
					
					console.log("사용자 아이디 : " + userId);
					console.log("우편번호 아이디 : " + zipNumId);
					console.log("기본주소 아이디 : " + address1Id);
					console.log("상세주소 아이디 : " + address2Id);
					
					var zipNumUpdate = document.getElementById(zipNumId);
					var address1Update = document.getElementById(address1Id);
					var address2Update = document.getElementById(address2Id);
					
					console.log("우편번호 값(test) : " + zipNumUpdate.innerText);
					console.log("기본주소 값(test) : " + address1Update.innerText);
					console.log("상세주소 값(test) : " + address2Update.innerText);
					
					// 전송전 우편번호/기본주소/상세주소 입력 여부 점검
					// 상세주소가 입력되었을 때
					
					var address2Val = address2Update.innerText;
					
					if (zipNumUpdate.innerText.trim() == '' || 
						address1Update.innerText.trim() == '') 
					{
						// 주소 검색 유도
						alert("우편번호/기본주소를 검색하십시오");
						zipNumUpdate.focus();
						
					} else if (zipNumUpdate.innerText.trim() != '' &&
							   address1Update.innerText.trim() != '' && 
							   address2Val.trim() != '') { 
						
						console.log("폼점검(유효주소) 성공 : 상세주소 입력");
						
						if (confirm('우편번호/기본주소/상세주소 모두를 수정하시겠습니까?')) {
						
							$.ajax({
								url : "${contextPath}/member/zip_address_update",
								type : "get",
								data : { 
									id : userId,
									zipNum : zipNumUpdate.innerText,
									address1 : address1Update.innerText,
									address2 : address2Val
								},
								dataType: "text",
								success : function(data) {
									
									console.log("data : " + data);
									
									alert(data);
	
								},
								error : function(xhr, status, error) {
									
									console.error("xhr : " + xhr);
									console.error("status : " + status);
									console.error("error : " + error);
								}
								
							}); // 전송
						
						} else {
							
							alert("상세주소만 수정하겠습니다");
							
							$.ajax({
								url : "${contextPath}/member/address2_update",
								type : "get",
								data : { 
									id : userId,
									address2 : address2Val
								},
								dataType: "text",
								success : function(data) {
									
									console.log("data : " + data);
									
									alert(data);
								},
								error : function(xhr, status, error) {
									
									console.error("xhr : " + xhr);
									console.error("status : " + status);
									console.error("error : " + error);
								}
								
							}); // 전송
						}
						
					// 상세주소가 입력되지 않았을 때	
					} else {
						
						console.log("폼점검(상세주소) 실패");
						
						alert("상세주소를 입력하십시오.");
						
						// 기존 정보 복원
						var idx = document.getElementById("count_"+userId).value
						default_address2 = default_address2s[idx];
						
						address2Update.innerHTML = default_address2;
						console.log("기존 상세주소 : " + default_address2);
						
						address2Update.focus();
					} //
					
					// 전송
					
				} // confirm 
				else {
					
					console.log("전송 취소");
					
					var idx = document.getElementById("count_"+userId).value
					default_address2 = default_address2s[idx];
					
					address2Update.innerHTML = default_address2;
					console.log("기존 상세주소 : " + default_address2);
					
					return false;
				} //
				// confirm ~~ else 
				
			} // onclick
			
		} //
		
		////////////////////////////////////////////////////////////////////////
																   
		var member_inactive_btns = document.querySelectorAll("[id^=member_inactive_btn_]");
		
		for (var member_inactive_btn of member_inactive_btns) {

			console.log("관리자 메뉴 개별 회원정보 비활성화(삭제) 버튼들");
			
			// 버튼 클릭시
			member_inactive_btn.onclick = function(e) {
				
				var btnId = e.currentTarget.id;
				var userId = btnId.substring("member_inactive_btn_".length);
				
				console.log("삭제할 ID : " + userId);
				
				if (confirm("정말 "+ userId + " 회원 정보를 삭제하시겠습니까?")) {
					
					$.ajax({
						url : "${contextPath}/member/member_inactive_admin",
						type : "get",
						data : { 
							id : userId
						},
						dataType: "text",
						success : function(data) {
							
							console.log("data : " + data);
							
							alert(data);
							
							// 전체 화면 갱신 
							location.reload();
						},
						error : function(xhr, status, error) {
							
							console.error("xhr : " + xhr);
							console.error("status : " + status);
							console.error("error : " + error);
						}
						
					}); // 전송
					
				} else {
					
					alert(userId + "에 대한 회원정보 삭제를 취소하였습니다");
				} //
			}
			
		} // for	
		
	} // window.onload
</script>