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

<link href="<c:url value="/css/slide_final.css" />" rel="stylesheet" />

<script src="<c:url value="/jquery/3.6.0/jquery.min.js" />"></script>

<script>
$(function() {
	
	// 슬라이드 왼쪽 버튼 클릭시
	$("body").on("click", "#left_btn" , function(){
		
		console.log("왼쪽 클릭");
		
		var first_id = $("img[id^=s]:first").attr("id");
		$("#slide_pnl").append("<img id='"+first_id+"' />");
		
		$("img[id^=s]:last").attr("src", $("img[id^=s]:first").attr("src") );
		$("img[id^=s]:last").attr("class", "slider_image" );
		
		console.log("가운데 있는 이미지 : "+$("img[id^=s]:eq(3)").attr("id"));
		var center_image_id = $("img[id^=s]:eq(3)").attr("id");
		
		$("img[id^=s]:first").remove();
	}); //
	
	// 슬라이드 오른쪽 버튼 클릭시
	$("body").on("click", "#right_btn" , function(){
		
		console.log("오른쪽 클릭");
		
		console.log("마지막 이미지 : " + $("img[id^=s]:last").attr("src"))
		
		// $("#slide_pnl").append("<img id='s"+cnt+"' />");
		// 임시 아이디 s0 추가 -> 추후 변경
		// 주의) append 처럼 가변값을 추가(+) 하면 추가 안됨.
		$("#slide_pnl").prepend("<img id='s0' />");
		
		$("img#s0").attr("src", $("img[id^=s]:last").attr("src") );
		$("img#s0").attr("class", "slider_image" );
		$("img#s0").attr("id", $("img[id^=s]:last").attr("id") ); // 아이디 변경
		
		console.log("마지막 이미지 : "+ $("img[id^=s]:last").html());
		$("img[id^=s]:last").remove();
		
		console.log("가운데 있는 이미지 : "+$("img[id^=s]:eq(2)").attr("id"));
		var center_image_id = $("img[id^=s]:eq(2)").attr("id");
	}); //
	
});
</script>
</head>
<body>

	<div id="slide_intro">
		
		<!-- 슬라이드 패널 -->
		<div id="slide_pnl">
		
	        <img id="s1" src="${contextPath}/img/slide/Desert.jpg" class="slider_image"/>
	        <img id="s2" src="${contextPath}/img/slide/Hydrangeas.jpg" class="slider_image"/>
	        <img id="s3" src="${contextPath}/img/slide/Jellyfish.jpg" class="slider_image"/>
	        <img id="s4" src="${contextPath}/img/slide/Koala.jpg" class="slider_image"/>
	        
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