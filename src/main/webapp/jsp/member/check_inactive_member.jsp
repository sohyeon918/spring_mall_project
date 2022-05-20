<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.jsp"%> 
<%@ include file="sub_menu.jsp" %>

<style>
/* 탈퇴 회원 점검 박스 */
#check_inactive_member_box {
	width:500px;
/* 	background:cyan; */
	font-size:14pt;
}	

/* 버튼들 */
#check_inactive_member_buttons {
	width:500px;
}
</style>

<!--   <script>
  // 에러 메시징 
  var msg = "${login_err_msg}";
  
  console.log("msg : " + msg);
  
  if (msg != '') {
	  alert(msg);
  }
  </script>		 -->
  
  <script>
  function choose_default_member() {
	  
	  console.log("기존 정보(탈퇴 전 정보 활용 점검");
	  
	  if (confirm("기존 정보(탈퇴 전 정보)를 다시 사용하시겠습니까?")) {
		  
		  console.log("기존 정보 재사용");
		  // return false;
		  
	  } else { // 신규 가입
		 
		  console.log("신규 가입");
		  history.back();
	  	  return false;	
	  } //
  }
  
  </script>
       
  <article>
  
    <h1>Check Inactive Member</h1>
  
    <form method="post" onsubmit="return choose_default_member()"
    	  action="${contextPath}/member/check_inactive_member_proc">
  
        <!-- 탈퇴 회원 점검 박스 -->
        <div id="_box" class="my-5">
        
        	<div class="row">
	        	<div class="col-4 pt-2 my-1">
	        		<label class="form-label">User ID</label>
	        	</div>
	        	<div class="col-8 my-1">
	        		 <input id="id" name="id" type="text" class="form-control" 
	        		 		required pattern="[a-zA-Z]{1}\w{7,19}" maxlength="20"
	        		 		title="아이디는 영문으로 시작하면서 영문/숫자를 조합하여 8~20자로 작성해야 합니다.">
	        	</div>
        	</div>
        	
        	<div class="row">
        		<div class="col-4 pt-2 my-1">
        			<label class="form-label">Password</label> 
        		</div>
        		<div class="col-8 my-1">
        			<input id="pwd" name="pwd" type="text" class="form-control" 
        				  required pattern="(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,20}"
        				  title="패쓰워드는 특수문자/영문/숫자를 포함하여 8~20로 작성해야 합니다." maxlength="20">
        		</div>
        		
        	</div>
        	
        	<div class="row">
        		<div class="col-4 pt-2 my-1">
        			<label class="form-label">E-Mail</label> 
        		</div>
        		<div class="col-8 my-1">
        			<input type="text" id="email" name="email" class="form-control"
		      		       required pattern="[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}"
		      		       title="올바른 이메일 형식이 아닙니다. ex) abcd@abcd.com">
        		</div>
        		
        	</div>
        	
        	<div class="row">
        		<div class="col-4 pt-2 my-1">
        			<label class="form-label">Phone Number</label> 
        		</div>
        		<div class="col-8 my-1">
        			<input type="text" id="phone" name="phone" class="form-control"
		      		       required pattern="01\d{1}-\d{3,4}-\d{4}"
		      		       title="연락처(휴대폰)는 010-1234-5678 형식으로 입력하십시오"
		      		       placeholder="ex) 010-1234-5678">
        		</div>
        		
        	</div>
        	
        </div>
        <!--// 탈퇴 회원 점검 박스 -->
        
        <div class="clear"></div>
        
        <div id="_buttons" class="row my-5">
        
            <div class="col-2"></div>     
            
            <div class="col-6 ps-1">
            	<input type="submit" value="탈퇴 회원 여부 점검" class="btn btn-outline-secondary">
            </div>	
        
        	<div class="col-4 ps-1">
                <input type="button" value="회원가입" class="btn btn-outline-secondary"
	                 onclick="history.back()">
	        </div>         
            
        </div>
        
    </form>  
  </article>
  
<%@ include file="../footer.jsp" %>      