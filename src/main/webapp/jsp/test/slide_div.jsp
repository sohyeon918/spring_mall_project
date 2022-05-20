<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>slide</title>

<!-- google material(icon) -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link href="<c:url value="/css/slide_div.css" />" rel="stylesheet" />

<script src="<c:url value="/jquery/3.6.0/jquery.min.js" />"></script>

<script>
$(function() {
	
	// 슬라이드 왼쪽 버튼 클릭시
	$("body").on("click", "#left_btn" , function(){
		
		console.log("왼쪽 클릭");

		// 현재 왼쪽 첫자리 패널 아이디
		var first_id = $("#slide_pnl div[id^=item]:first").attr("id");
		
	    console.log("현재 첫요소 : " + $("#slide_pnl div[id^=item]:first").html());
		
		var addItem = "<div id=\"item"+first_id+"\">"+$("div[id="+first_id+"]").html()+"</div>";
	
		$("#slide_pnl").append(addItem);
		
		// 처음 패널 내용 제거
		$("#slide_pnl div[id="+first_id+"]").remove();
	}); //
	
	// 슬라이드 오른쪽 버튼 클릭시
	$("body").on("click", "#right_btn" , function(){
		
		console.log("오른쪽 클릭");
		
		// 현재 오른쪽 끝자리 패널 아이디
		var last_id = $("#slide_pnl div[id^=item]:last").attr("id");
		
	    console.log("현재 오른쪽 끝요소 : " + $("#slide_pnl div[id^=item]:last").html());
		
		var addItem = "<div id=\"item"+last_id+"\">"+$("div[id="+last_id+"]").html()+"</div>";
	
		$("#slide_pnl").prepend(addItem);
		
		// 끝 패널 내용 제거
		$("#slide_pnl div[id="+last_id+"]").remove();
	}); //
	
});
</script>
</head>
<body>

	<div id="slide_intro">
		
		<!-- 슬라이드 패널 -->
		<div id="slide_pnl">
			
			<div id="item1">			
	        	<img id="s1" src="${contextPath}/img/slide/Desert.jpg" class="slider_image"/>
	        	<h3>사막</h3>
	        	<p>1000</p>
	        </div>
	        
	        <div id="item2">
	        	<img id="s2" src="${contextPath}/img/slide/Hydrangeas.jpg" class="slider_image"/>
	        	<h3>하이드라</h3>
	        	<p>2000</p>
	        </div>
	        
	        <div id="item3">	
	        	<img id="s3" src="${contextPath}/img/slide/Jellyfish.jpg" class="slider_image"/>
	        	<h3>해파리</h3>
	        	<p>3000</p>
	        </div>
	        
	        <div id="item4">	
	        	<img id="s4" src="${contextPath}/img/slide/Koala.jpg" class="slider_image"/>
	        	<h3>코알라</h3>
	        	<p>4000</p>
	        </div>	
	        
		</div>
		
		<!-- 슬라이드 메뉴 -->
		<div id="slide_menu">
		
			<!-- 왼쪽으로 진행 -->
			<div id="left_btn_pnl">
				<span id="left_btn" class="material-icons">
		            keyboard_arrow_left
			    </span>
		    </div>
		    
		    <!-- 가운데 공(blank) 패널 -->
		    <div id="blank_panel">&nbsp;</div>
		    
		    <!-- 오른쪽으로 진행 -->
			<div id="right_btn_pnl">
				<span id="right_btn" class="material-icons">
		            keyboard_arrow_right
			    </span>
		    </div>
	    
	    </div>
	    
	</div>

</body>
</html>