<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- header -->
<%@ include file="../header.jsp" %>
<%@ include file="sub_menu_member.jsp" %>

<script type="text/javascript" src="<c:url value="/js/member_update.js" />" charset="UTF-8"></script>

<style>
article#update_box {
    float:left;
    margin-left:50px;
    margin-top:30px;	
	width:680px;
/* 	background-color:cyan; */
	font-size:13pt;
	line-height:20pt;
}	

form fieldset legend.update_title {
	font-size: 17pt; 
	border-bottom: 1px dotted #ccc; 
	width: 680px; 
	padding: 10px 0; 
/* 	background-color:yellow; */
	margin:20px 0;
}
</style>

<!-- 회원 정보 수정 성공/실패 메시징 -->
<script>
var msg = "${update_success_msg}";

if (msg.trim() != '') {
	alert(msg);
}
</script>

<article id="update_box"  class="container">
	  
	  <h2>Update Us</h2>
	  
	  <!-- 에러 메시지 출력 -->
	  <!-- 
	  <div style="background:yellow; width:100%; height:200px; overflow:auto; font-size:9pt">
		
		<spring:hasBindErrors name="memberDTO">
			<%-- ${errors} --%>
			${errors.getFieldError("pwd").defaultMessage}<br>
			${errors.getFieldError("pwdCheck").defaultMessage}<br>
			${errors.getFieldError("email").defaultMessage}<br>
			${errors.getFieldError("phone").defaultMessage}<br>
		</spring:hasBindErrors>
		
		${pwd_err_msg}<br>
		${address_err_msg}<br>
		${email_err_msg}<br>
		${phone_err_msg}
	  </div>
	   -->
	  
	  <form id="update" action="${contextPath}/member/update_proc" method="post" name="formm">
	  
	    <fieldset>
	    
	      <legend class="update_title">Basic Info</legend>
	      
	      <!-- 아이디 -->
	      <div class="row my-3">
	      	<div class="col-4">
	      		<label class="form-label">User ID</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="id" name="id" class="form-control bg-light" readonly
	      		       value="${LOGIN_USER.id}">
	      	</div>	
	      </div>
	      
	      <!-- 신규(변경) 패쓰워드 -->
	      <div class="row my-3">
	      	<div class="col-4">
	      		<label class="form-label">Password</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="pwd" name="pwd" class="form-control" value=" " maxlength="20"> 
			</div>	
	      </div>
	      
	      <!-- 신규(변경) 패쓰워드 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="pwd_err_msg">
				<spring:hasBindErrors name="memberDTO">
					${errors.getFieldError("pwd").defaultMessage}
				</spring:hasBindErrors>
			</div>    
	      </div>
	      
	      <!-- 패쓰워드(재입력) -->
	      <div class="row mb-3">
	      	<div class="col-4">
	      		<label class="form-label w-100">Retype-Password</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="pwdCheck" name="pwdCheck" class="form-control" value=" " maxlength="20">
	      	</div>	
	      </div>
	      
	      <!-- 패쓰워드(재입력) 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="pwd_err_msg">
				<spring:hasBindErrors name="memberDTO">
					${errors.getFieldError("pwdCheck").defaultMessage}
				</spring:hasBindErrors>
				${pwd_err_msg}		
			</div>    
	      </div>
	      
	      <!-- 회원 이름 -->
	      <div class="row mb-3">
	      	<div class="col-4">
	      		<label class="form-label">Name</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="name" name="name" class="form-control bg-light"
	      		       readonly value="${LOGIN_USER.name}">
	      	</div>	
	      </div>
	      
	      <!-- 회원 이메일 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label">E-Mail</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="email" name="email" class="form-control" value="${LOGIN_USER.email}">
	      	</div>	
	      </div>
	      
	      <!-- 회원 이메일 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="email_err_msg">
				<spring:hasBindErrors name="memberDTO">
					${errors.getFieldError("email").defaultMessage}
				</spring:hasBindErrors>	
				${email_err_msg}
			</div>    
	      </div>
	      
	      <!-- 회원 연락처 : 휴대폰 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label w-100">Phone Number</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="phone" name="phone" class="form-control"
	      			   value="${LOGIN_USER.phone}">
	      	</div>	
	      </div>
	      
	      <!-- 회원 연락처 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="phone_err_msg">
				<spring:hasBindErrors name="memberDTO">
					${errors.getFieldError("phone").defaultMessage}
				</spring:hasBindErrors>
				${phone_err_msg}
			</div>    
	      </div>
	      
	    </fieldset>
	    
	    <fieldset>
	    
	      <legend class="update_title">Optional</legend>
	      
	      <!-- 회원 우편번호 : 검색 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label">Zip Code</label>
	      	</div>
	      	<div class="col-2">
	      		<input type="text" id="zipNum" name="zipNum" class="form-control bg-light" 
	      		       readonly value="${LOGIN_USER.zipNum}">
	      	</div>	
	      	<div class="col-6">
	      		<input type="button" value="주소 찾기" class="btn btn-outline-secondary"  onClick="getPostcodeAddress()">
	      		<input type="button" id="address_init_btn" value="주소 초기화" class="btn btn-outline-secondary ms-1">
			</div>
	      </div>
	
		  <!-- 회원 주소 : 기본 주소(도로명) -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label w-100">Address(Basic)</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="address1" name="address1" class="form-control bg-light" readonly
	      		       value="${LOGIN_USER.address1}">
	      	</div>	
	      </div>
	      
	      <!-- 회원 주소 : 상세주소 -->
	      <div class="row">
	      	<div class="col-4">
	      		<label class="form-label w-100">Address(Detail)</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="address2" name="address2" class="form-control" 
	      			   value="${LOGIN_USER.address2}">
	      	</div>	
	      </div>
	      
	      <!-- 회원 주소 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="address_err_msg">${address_err_msg}</div>   
	      </div>
	      
	    </fieldset>
	   
	    <div id="buttons" class="row mb-3">
	    	<div class="col-2"></div>
	    	<div class="col-5 ps-5 pe-3">
	      	<input type="submit" id="update_submit" class="btn btn-outline-secondary px-5" value="회원정보 수정">
	      </div>
	      <div class="col-4"> 
	      	<input type="reset"  class="btn btn-outline-secondary px-5" value="취소">
	     	</div>
	     	<div class="col-1">
	     	</div>
	    </div>
	
	  </form>
	
	</article>

<!-- footer -->
<%@ include file="../footer.jsp" %>