<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<!-- modal -->
	<div id="member_modal">
	    
	    <!-- modal header -->
		<div id="member_modal_header">
			
			<!-- bullet -->
		    <div id="member_modal_header_icon">
				<span class="material-icons">
		        	web_asset
		        </span>		        
	        </div>
			
			<!-- title -->
	        <div id="member_modal_header_title">
			</div>
			
			<!-- closing button icon -->
			<div id="member_modal_header_close">
			    <a href="#" id="member_modal_close_btn">
					<span class="material-icons" style="color:#333">
			        	clear
			        </span>
		        </a>
			</div>
		
		</div>
		
		<!-- modal content -->
		<div id="member_modal_body">
		 	  
		  <!-- 아이디 -->
		  <div class="row">
			<div class="col-4">
				<label>User ID</label>
			</div>
			<div id="user_id" class="col-8 mt-1">
			</div>	
		  </div>
		  
		  <!-- 패쓰워드 -->
		  <div class="row">
			<div class="col-4">
				<label>Password</label>
			</div>
			<div id="user_pwd" class="col-8 mt-1">
				**********
			</div>	
		  </div>
				  
		  <!-- 회원 이름 -->
		  <div class="row">
			<div class="col-4">
				<label>Name</label>
			</div>
			<div id="user_name" class="col-8 mt-1">
			</div>	
		  </div>
		  
		  <!-- 회원 이메일 -->
		  <div class="row">
			<div class="col-4">
				<label>E-Mail</label>
			</div>
			<div id="user_email" class="col-8 mt-1">
			</div>	
		  </div>
		  
		  <!-- 회원 연락처 : 휴대폰 -->
		  <div class="row">
			<div class="col-4">
				<label >Phone Number</label>
			</div>
			<div id="user_phone" class="col-8 mt-1">
			</div>	
		  </div>
		  
		  <!-- 회원 우편번호 : 검색 -->
		  <div class="row">
			<div class="col-4">
				<label>Zip Code</label>
			</div>
			<div id="user_zipNum" class="col-8 mt-1">
			</div>	
		  </div>
		
		  <!-- 회원 주소 -->
		  <div class="row">
			<div class="col-4">
				<label>Address</label>
			</div>
			<div class="col-8">
				<div id="user_address1">
				</div>
				<div id="user_address2">
				</div>
			</div>	
		  </div>
		  
		</div>
		
		<!-- modal footer -->
		<div id="member_modal_footer">
		</div>
		
	</div>