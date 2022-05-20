<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.javateam.project.domain.*" %>

<script>
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
						
			$("#member_modal").css({"opacity":"1"});

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
			
			$("#member_modal").css({"opacity":"0"});
			
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
		
		// 관리자 모드 - 개별 회원 정보 수정
		var emails = document.querySelectorAll("input[type='text'][id^=email]");
		
		console.log("emails update");
		
		for (var emailUpdate of emails) {
			
			console.log("email update");
			
			emailUpdate.onclick = function(e) {
				
				alert("이메일 클릭");
				
				var id = e.currentTarget.id;
				console.log("focus id : " + id);
				
				document.getElementById(id).style.background = "yellow"; // 입력 활성화 상태
				
				// emailUpdate.readOnly = false; // 편집 가능 상태
			}
			
			/*
			emailUpdate.onblur = function(e) {
				
			
				var emailId = e.currentTarget.id;
				console.log("blur id : " + emailId);
				
				if (confirm('정말 정보 변경하시겠습니까?')) {
					
					var userId = emailId.substring("email_".length)
					
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
								
							},
							error : function(xhr, status, error) {
								
								console.error("xhr : " + xhr);
								console.error("status : " + status);
								console.error("error : " + error);
							}
							
						}); // ajax (이메일 중복 점검)
						
					} else {
						console.log("폼점검 실패");
						
						// alert("이메일 폼점검 실패");
					} //
					
					// 전송
					
				} // confirm 
				else {
					
					console.log("전송 취소");
					return false;
				} //
				// confirm ~~ else 
				
			} // blur
			*/
			
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
				
				if (confirm('정말 정보 변경하시겠습니까?')) {
					
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
		
	} //
</script>