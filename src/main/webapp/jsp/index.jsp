<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="header.jsp" %>

<script>
$(function() {
	
	// 신상품 갤러리 슬라이드 메뉴
	
	// 슬라이드 왼쪽 버튼 클릭시
	$("body").on("click", "#new_left_btn" , function(){
		
		console.log("왼쪽 클릭");

		// 현재 왼쪽 첫자리 패널 아이디
		var first_id = $("#newProduct div[id^=newItem]:first").attr("id");
		
	    console.log("현재 첫요소 : " + $("#newProduct div[id^=newItem]:first").html());
		
		var addItem = "<div id=\"newItem"+first_id+"\">"+$("#newProduct div[id="+first_id+"]").html()+"</div>";
	
		$("#newProduct").append(addItem);
		
		// 처음 패널 내용 제거
		$("div[id="+first_id+"]").remove();
	}); //
	
	// 슬라이드 오른쪽 버튼 클릭시
	$("body").on("click", "#new_right_btn" , function(){
		
		console.log("오른쪽 클릭");
		
		// 현재 오른쪽 끝자리 패널 아이디
		var last_id = $("#newProduct div[id^=newItem]:last").attr("id");
		
	    console.log("현재 오른쪽 끝요소 : " + $("#newProduct div[id^=newItem]:last").html());
		
		var addItem = "<div id=\"newItem"+last_id+"\">"+$("#newProduct div[id="+last_id+"]").html()+"</div>";
	
		$("#newProduct").prepend(addItem);
		
		// 끝 패널 내용 제거
		$("div[id="+last_id+"]").remove();
	}); //
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	// 베스트 갤러리 슬라이드 메뉴
	
	// 슬라이드 왼쪽 버튼 클릭시
	$("body").on("click", "#best_left_btn" , function(){
		
		console.log("왼쪽 클릭");

		// 현재 왼쪽 첫자리 패널 아이디
		var first_id = $("#bestProduct div[id^=bestItem]:first").attr("id");
		
	    console.log("현재 첫요소 : " + $("#bestProduct div[id^=bestItem]:first").html());
		
		var addItem = "<div id=\"bestItem"+first_id+"\">"+$("#bestProduct div[id="+first_id+"]").html()+"</div>";
	
		$("#bestProduct").append(addItem);
		
		// 처음 패널 내용 제거
		$("div[id="+first_id+"]").remove();
	}); //
	
	// 슬라이드 오른쪽 버튼 클릭시
	$("body").on("click", "#best_right_btn" , function(){
		
		console.log("오른쪽 클릭");
		
		// 현재 오른쪽 끝자리 패널 아이디
		var last_id = $("#bestProduct div[id^=bestItem]:last").attr("id");
		
	    console.log("현재 오른쪽 끝요소 : " + $("#bestProduct div[id^=bestItem]:last").html());
		
		var addItem = "<div id=\"bestItem"+last_id+"\">"+$("#bestProduct div[id="+last_id+"]").html()+"</div>";
	
		$("#bestProduct").prepend(addItem);
		
		// 끝 패널 내용 제거
		$("div[id="+last_id+"]").remove();
	}); //
	
});
</script>

	  <!--메인 이미지 들어가는 곳 시작 --->

	  <!-- Carousel -->
	  <div id="main_img" class="carousel slide" data-bs-ride="carousel">
		
		  <!-- Indicators/dots -->
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#main_img" data-bs-slide-to="0" class="active"></button>
		    <button type="button" data-bs-target="#main_img" data-bs-slide-to="1"></button>
		    <button type="button" data-bs-target="#main_img" data-bs-slide-to="2"></button>
		    <button type="button" data-bs-target="#main_img" data-bs-slide-to="3"></button>
		  </div>
		  
		  <!-- The slideshow/carousel -->
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="${contextPath}/img/main/main1.png" class="d-block" style="width:100%">
		    </div>
		    <div class="carousel-item">
		      <img src="${contextPath}/img/main/main2.png" class="d-block" style="width:100%">
		    </div>
		    <div class="carousel-item">
		      <img src="${contextPath}/img/main/main3.png" class="d-block" style="width:100%">
		    </div>
		    <div class="carousel-item">
		      <img src="${contextPath}/img/main/main4.png" class="d-block" style="width:100%">
		    </div>
		  </div>
		  
		  <!-- Left and right controls/icons -->
		  <button class="carousel-control-prev" type="button" data-bs-target="#main_img" data-bs-slide="prev">
		    <!-- <span class="carousel-control-prev-icon"></span> -->
		  	<span class="material-icons" style="color:purple; font-size:6em; font-weight:bold">arrow_back_ios</span>
		  </button>
		  
		  <button class="carousel-control-next" type="button" data-bs-target="#main_img" data-bs-slide="next">
		    <!-- <span class="carousel-control-next-icon"></span> -->
		    <span class="material-icons" style="color:purple; font-size:6em; font-weight:bold">arrow_forward_ios</span>
		  </button>
		  
	  </div>
	    
	  <!--메인 이미지 들어가는 곳 끝--->
	
	  <!-- ##################################################### --> 

	  <div id="front">   
	  
	    <h2>New Item</h2>     
	    
	    <div id="newProduct">     
	      
	      <c:forEach var="i" begin="0" end="3">
	      
				<div id="newItem${i}">
					
					<c:choose>
					
						<%-- 상품 있을 경우 --%>						
						<c:when test="${not empty newProductList[i].name}">
							<a href="${contextPath}/product/product_detail?pseq=${newProductList[i].pseq}">
								<img src="<%=request.getContextPath() %>/product_images/${newProductList[i].image}" />
							</a>
							<h6>${newProductList[i].name}</h6>    
							<p><fmt:formatNumber maxFractionDigits="3" value="${newProductList[i].price2}" /> 원</p>
						</c:when>
						
						<%-- 상품 없을 경우 --%>
						<c:otherwise>
							<img src="<%=request.getContextPath() %>/img/blank_tranparent.gif">
							<h6>&nbsp;</h6>    
							<p>&nbsp;</p>
						</c:otherwise>
						
					</c:choose>
					
				</div>
				
			</c:forEach>   
	      
	    </div> 
	    
	    <!-- 신상품 갤러리 슬라이드 메뉴 -->
		<div id="new_slide_menu">
		
			<!-- 왼쪽으로 진행 -->
			<div id="new_left_btn_pnl">
				<span id="new_left_btn" class="material-icons">
		            keyboard_arrow_left
			    </span>
		    </div>
		    
		    <!-- 가운데 공(blank) 패널 -->
		    <div id="new_blank_panel">&nbsp;</div>
		    
		    <!-- 오른쪽으로 진행 -->
			<div id="new_right_btn_pnl">
				<span id="new_right_btn" class="material-icons">
		            keyboard_arrow_right
			    </span>
		    </div>
	    
	    </div>
	    <!--// 신상품 갤러리 슬라이드 메뉴 -->
	
	   <!-- ######################################################### -->
	
	   <br>
	   	
 	   <h2>Best Item</h2>
 	        
      	<div id="bestProduct">    
      	
      		<c:forEach var="i" begin="0" end="3">
      		   	
				<div id="bestItem${i}">
					
					<!-- 상품 있을 경우 -->
					<c:if test="${not empty bestProductList[i].name}">
						<a href="${contextPath}/product/product_detail?pseq=${bestProductList[i].pseq}">
							<img src="<%=request.getContextPath() %>/product_images/${bestProductList[i].image}" />
						</a>
						<h6>${bestProductList[i].name}</h6>    
						<p><fmt:formatNumber maxFractionDigits="3" value="${bestProductList[i].price2}" /> 원</p>
					</c:if>
					
					<!-- 상품 없을 경우 -->
					<c:if test="${empty bestProductList[i].name}">
						<img src="<%=request.getContextPath() %>/img/blank_tranparent.gif">
						<h6>&nbsp;</h6>    
						<p>&nbsp;</p>
					</c:if>
					
				</div>
				
			</c:forEach>   
      		   
		</div>
		
		<!-- 베스트 상품 갤러리 슬라이드 메뉴 -->
		<div id="best_slide_menu">
		
			<!-- 왼쪽으로 진행 -->
			<div id="best_left_btn_pnl">
				<span id="best_left_btn" class="material-icons">
		            keyboard_arrow_left
			    </span>
		    </div>
		    
		    <!-- 가운데 공(blank) 패널 -->
		    <div id="best_blank_panel">&nbsp;</div>
		    
		    <!-- 오른쪽으로 진행 -->
			<div id="best_right_btn_pnl">
				<span id="best_right_btn" class="material-icons">
		            keyboard_arrow_right
			    </span>
		    </div>
	    
	    </div>
	    <!--// 베스트 상품 갤러리 슬라이드 메뉴 -->
		
 	</div>
 
<!-- footer -->
<%@ include file="footer.jsp" %>
