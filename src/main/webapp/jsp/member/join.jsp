<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  
<%@ include file="../header.jsp" %>   
<%@ include file="sub_img.jsp"%> 
<%@ include file="sub_menu.jsp" %>

<script type="text/javascript" src="<c:url value="/js/member_join.js" />" charset="UTF-8"></script>

<style>
article#join_box {
    float:left;
    margin-left:50px;	
	width:680px;
/* 	background-color:cyan; */
	font-size:13pt;
	line-height:20pt;
}	

form fieldset legend.join_title {
	font-size: 17pt; 
	border-bottom: 1px dotted #ccc; 
	width: 680px; 
	padding: 10px 0; 
/* 	background-color:yellow; */
	margin:20px 0;
}
</style>

	<article id="join_box"  class="container">
	  
	  <h2>Join Us</h2>
	  
	  <!-- <form id="join" action="NonageServlet?command=join" method="post" name="formm"> -->
	  <form id="join" action="${contextPath}/member/join_proc" method="post" name="formm">
	  
	    <fieldset>
	    
	      <legend class="join_title">Basic Info</legend>
	      
	      <!-- 아이디 -->
	      <div class="row my-3">
	      	<div class="col-4">
	      		<label class="form-label">User ID</label>
	      	</div>
	      	<div class="col-8">
	      		<!-- 아이디 중복 점검 -->
	      		<input type="text" id="id" name="id" class="form-control" 
	      			   required	
	      		       pattern="[a-zA-Z]{1}\w{7,19}"
	      		       title="아이디는 영문/숫자 조합으로 8~20사이로 입력하십시오">
	      	</div>	
	      </div>
	      
	      <!-- 패쓰워드 -->
	      <div class="row my-3">
	      	<div class="col-4">
	      		<label class="form-label">Password</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="pwd" name="pwd" class="form-control" 
	      			   required pattern="(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,20}"
	      			   title="패쓰워드는 영문/숫자 및 특수문자 최소 1자의 조합으로 8~20사이로 입력하십시오">
			</div>	
	      </div>
	      
	      <!-- 패쓰워드(재입력) -->
	      <div class="row my-3">
	      	<div class="col-4">
	      		<label class="form-label w-100">Retype-Password</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="pwdCheck" name="pwdCheck" class="form-control"
	      			   required pattern="(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,20}"
	      			   title="패쓰워드는 영문/숫자 및 특수문자 최소 1자의 조합으로 8~20사이로 입력하십시오">
	      	</div>	
	      </div>
	      
	      <!-- 패쓰워드 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="pwd_err_msg"></div>    
	      </div>
	      
	      <!-- 회원 이름 -->
	      <div class="row my-1">
	      	<div class="col-4">
	      		<label class="form-label">Name</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="name" name="name" class="form-control"
	      		       required pattern="[가-힣]{2,13}"
	      		       title="이름은 한글로 13자까지만 허용합니다">
	      	</div>	
	      </div>
	      
	      <!-- 회원 이름 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="name_err_msg"></div>    
	      </div>
	      
	      <!-- 회원 이메일 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label">E-Mail</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="email" name="email" class="form-control"
	      		       required pattern="[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}"
	      		       title="올바른 이메일 형식이 아닙니다. ex) abcd@abcd.com">
	      	</div>	
	      </div>
	      
	      <!-- 회원 이메일 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="email_err_msg"></div>    
	      </div>
	      
	      <!-- 
	      <div class="my-2">
	      
	      	<div class="col-4 bg-info">
	      		<label class="form-label">E-Mail</label>
	      	</div>
	      	
	      	
	      	<div class="col-8 float-end">
	      	
	      		<div class="row">
	      		
	       		<input type="text" name="email1" class="form-control me-1" style="margin-left:20px; width:115px; height:40px">
	       		@
	       		<select name="email2" class="form-select ms-2 me-1 py-0"  style="width:150px;height:40px">
				  <option>naver.com</option>
				  <option>daum.net</option>
				</select>
				
				<input type="text" name="email3" class="form-control ms-2 me-0" style="width:128px; height:40px">
			</div>
			
	      	</div>	
	      	
	      </div>
	      
	      <br><br>
	       -->
	      
	      <!-- 회원 연락처 : 휴대폰 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label w-100">Phone Number</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="phone" name="phone" class="form-control"
	      		       required pattern="01\d{1}-\d{3,4}-\d{4}"
	      		       title="연락처(휴대폰)는 010-1234-5678 형식으로 입력하십시오"
	      		       placeholder="ex) 010-1234-5678">
	      	</div>	
	      </div>
	      
	      <!-- 회원 연락처 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="phone_err_msg"></div>    
	      </div>
	      
	    </fieldset>
	    
	    <fieldset>
	    
	      <legend class="join_title">Optional</legend>
	      
	      <!-- 회원 우편번호 : 검색 -->
	      <div class="row my-2">
	      	<div class="col-4">
	      		<label class="form-label">Zip Code</label>
	      	</div>
	      	<div class="col-2">
	      		<input type="text" id="zipNum" name="zipNum" class="form-control bg-light" readonly>
	      	</div>	
	      	<div class="col-6">
	      		<!-- <input type="button" value="주소 찾기" class="btn btn-outline-secondary" onclick="post_zip()"> -->
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
	      		<input type="text" id="address1" name="address1" class="form-control bg-light" readonly>
	      	</div>	
	      </div>
	      
	      <!-- 회원 주소 : 상세주소 -->
	      <div class="row">
	      	<div class="col-4">
	      		<label class="form-label w-100">Address(Detail)</label>
	      	</div>
	      	<div class="col-8">
	      		<input type="text" id="address2" name="address2" class="form-control">
	      	</div>	
	      </div>
	      
	      <!-- 회원 주소 에러 메시지 -->
	      <div class="row text-danger">
	      	<div class="col-4"></div>
			<div class="col-8" id="address_err_msg"></div>    
	      </div>
	      
	    </fieldset>
	   
	    <!-- <div class="clear"></div> -->
	   
	    <!-- <div id="buttons">
	      <input type="button"    value="회원가입"   class="submit" onclick="go_save()"> 
	      <input type="reset"      value="취소"     class="cancel">
	    </div> -->
	    
	    <div id="buttons" class="row mb-3">
	    	<div class="col-3"></div>
	    	<div class="col-4 ps-5 pe-3">
	      	<!-- <input type="button" class="btn btn-outline-secondary px-5" value="회원가입"  onclick="go_save()"> -->
	      	<input type="button" id="join_submit" class="btn btn-outline-secondary px-5" value="회원가입">
	      </div>
	      <div class="col-4"> 
	      	<input type="reset"  class="btn btn-outline-secondary px-5" value="취소">
	     	</div>
	     	<div class="col-1">
	     	</div>
	    </div>
	
	  </form>
	
	</article>
  
<%@ include file="../footer.jsp" %>
  