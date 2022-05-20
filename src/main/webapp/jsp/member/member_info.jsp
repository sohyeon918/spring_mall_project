<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="../header.jsp" %>
<%@ include file="sub_menu_member.jsp" %>

<style>
article#view_box {
    float:left;
    margin-left:50px;	
	width:680px;
	/* background-color:cyan; */
	font-size:13pt;
	line-height:20pt;
}	

form fieldset legend.view_title {
	font-size: 17pt; 
	border-bottom: 1px dotted #ccc; 
	width: 680px; 
	padding: 10px 0; 
	margin:20px 0;
}
</style>

<article id="view_box"  class="container">

    <h2 class="my-3">회원 정보</h2>
    
    <%-- ${LOGIN_USER} --%>
    
	  <form id="view">
	  
	    <fieldset>
	    
	      <legend class="view_title">Basic Info</legend>
	      
	      <!-- 아이디 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label">User ID</label>
	      	</div>
	      	<div class="col-8 mt-1">
	      		${LOGIN_USER.id}
	      	</div>	
	      </div>
	      
	      <!-- 패쓰워드 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label">Password</label>
	      	</div>
	      	<div class="col-8 mt-1">
	      		<%-- ${LOGIN_USER.pwd} --%>
	      		**********
			</div>	
	      </div>
	     	      
	      <!-- 회원 이름 -->
	      <div class="row">
	      	<div class="col-4">
	      		<label class="form-label">Name</label>
	      	</div>
	      	<div class="col-8 mt-1">
	      		${LOGIN_USER.name}
	      	</div>	
	      </div>
	      
	      <!-- 회원 이메일 -->
	      <div class="row">
	      	<div class="col-4">
	      		<label class="form-label">E-Mail</label>
	      	</div>
	      	<div class="col-8 mt-1">
	      		${LOGIN_USER.email}
	      	</div>	
	      </div>
	      
	      <!-- 회원 연락처 : 휴대폰 -->
	      <div class="row">
	      	<div class="col-4">
	      		<label class="form-label w-100">Phone Number</label>
	      	</div>
	      	<div class="col-8 mt-1">
	      		${LOGIN_USER.phone}
	      	</div>	
	      </div>
	      
	    </fieldset>
	    
	    <fieldset>
	    
	      <legend class="view_title">Optional</legend>
	      
	      <!-- 회원 우편번호 : 검색 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label">Zip Code</label>
	      	</div>
	      	<div class="col-2 mt-1">
	      		${LOGIN_USER.zipNum}
	      	</div>	
	      	<div class="col-6">
			</div>
	      </div>
	
		  <!-- 회원 주소 : 기본 주소(도로명) -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label w-100">Address(Basic)</label>
	      	</div>
	      	<div class="col-8 mt-1">
	      		${LOGIN_USER.address1}
	      	</div>	
	      </div>
	      
	      <!-- 회원 주소 : 상세주소 -->
	      <div class="row">
	      	<div class="col-4">
	      		<label class="form-label w-100">Address(Detail)</label>
	      	</div>
	      	<div class="col-8 mt-1">
	      		${LOGIN_USER.address2}
	      	</div>	
	      </div>
	      
	    </fieldset>
	   
	  </form>
    
</article>    

<!-- footer -->
<%@ include file="../footer.jsp" %>