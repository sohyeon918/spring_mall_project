<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<!-- contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  	<meta charset="UTF-8">
  	<title>Project Shop</title>
  
  	<!-- google material(icon) -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<!-- jquery -->
	<script src="<c:url value="/jquery/3.6.0/jquery.min.js" />"></script>
	
	<!-- bootstrap -->
	<link href="<c:url value="/bootstrap/5.1.3/css/bootstrap.min.css" />" rel="stylesheet" />
	<script src="<c:url value="/bootstrap/5.1.3/js/bootstrap.bundle.min.js" />"></script>

	<link href="<c:url value="/css/slide_main.css" />" rel="stylesheet" />  
	<link href="<c:url value="/css/shopping.css" />" rel="stylesheet">
	
	<!-- daum 우편번호 서비스 -->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	
	<script type="text/javascript" src="<c:url value="/js/member.js" />" charset="UTF-8"></script>
	<script type="text/javascript" src="<c:url value="/js/mypage.js" />" charset="UTF-8"></script> 
	
	<style>
	table#flag_tbl td {
		text-align:left;
	}
	</style>
</head>

<body>

	<!-- 플래그 변수 모니터링 패널 -->
	<div id="flag_box" class="offcanvas offcanvas-start">
	 	<div class="offcanvas-header">
			<h4 class="offcanvas-title">플래그 변수 현황</h4>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
		</div>
		<div class="offcanvas-body">
			
			<table id="flag_tbl" class="table table-striped">
				<thead>
					<tr>
						<th>플래그 변수명</th>
						<th>값</th>
					</tr>
				</thead>
				<tbody>	
					<tr>
						<td>회원 아이디 중복 점검</td>
						<td id="id_check_flag">false</td>
					</tr>
					<tr>
						<td>패쓰워드 점검</td>
						<td id="pw_check_flag">false</td>
					</tr>
					<tr>
						<td>회원 이름 점검</td>
						<td id="name_check_flag">false</td>
					</tr>
					<tr>
						<td>회원 이메일 점검</td>
						<td id="email_check_flag">false</td>
					</tr>
					<tr>
						<td>회원 연락처 점검</td>
						<td id="phone_check_flag">false</td>
					</tr>
				</tbody>
			</table> 
			
		</div>
	</div>
	<!--// 플래그 변수 모니터링 패널 -->

<div id="wrap">

<!--헤더파일 들어가는 곳 시작 -->
  <header>
    <!--로고 들어가는 곳 시작--->  
    <div id="logo">
      <!-- <a href="NonageServlet?command=index"> -->
      <a href="${contextPath}/">
        <img src="<c:url value="/img/logo.gif" />" width="180" height="100" alt="JavaMall">
      </a>  
    </div>
    <!--로고 들어가는 곳 끝-->     
    
    <nav id="catagory_menu">
     <ul>
       <!-- 로그아웃/로그인 상태 -->
       <c:choose>
	       <c:when test="${empty sessionScope.LOGIN_USER}">
		       <li>            
		       
		         <a href="${contextPath}/login/login_form" style="width:120px;">LOGIN(CUSTOMER</a>
		         
		         <!-- 관리자(admin) 메뉴 -->
		     	 <a href="${contextPath}/admin/home" style="width:100px;">| ADMIN)</a>
		     	 
			   </li>		       
			   
		       <li>/</li>
		       
		       <li>
		       	<a href="${contextPath}/member/contract">JOIN</a>
	       	   </li>
	       </c:when>	       
	       <c:otherwise>
		       <li style="color:#333;">
		        
		        <!-- 회원 정보 조회/수정/(탈퇴) 메뉴 버튼 -->
		         <a href="${contextPath}/member/member_info" style="width:120px;">
		         	${sessionScope.LOGIN_USER.name}(${sessionScope.LOGIN_USER.id})
		         </a>
		         
				 <!-- 관리자(admin) 메뉴 -->
		         <c:if test="${sessionScope.LOGIN_ROLE_USER.role eq 'admin'}">
			     	<a href="${contextPath}/admin/home" style="width:100px;">| ADMIN)</a>
			     </c:if>
			     		         
		       </li>
		       
		       <li><a href="${contextPath}/login/logout">LOGOUT</a></li>
	       </c:otherwise>       
       </c:choose>
       <li>/</li>
       <li>
         <a href="${contextPath}/cart/cartList">CART</a>
       </li><li>/</li>
       <li>
         <a href="${contextPath}/order/mypage">MY PAGE</a>
       </li><li>/</li>
       <li>
         <a href="NonageServlet?command=qna_list">Q&amp;A(1:1)</a>
       </li><li>/</li>
       
       <!-- 플래그 변수 확인창 버튼 -->
       <li>
		<a href="#" data-bs-toggle="offcanvas" data-bs-target="#flag_box">
		   플래그
		</a>
       </li>
     </ul>
    </nav>

    <nav id="top_menu">
      <ul>
        
        <%-- <li><a href="${contextPath}/product/category?kind=1">Heels</a></li>
        <li><a href="${contextPath}/product/category?kind=2">Boots</a></li>  
	  	<li><a href="${contextPath}/product/category?kind=3">Sandals</a></li> 
	  	<li><a href="${contextPath}/product/category?kind=4">Sneakers</a></li> 
	  	<li><a href="${contextPath}/product/category?kind=5">On Sale</a></li> --%> 
	  	
	  	<li><a href="${contextPath}/product/category?kind=1">Heels</a></li>
        <li><a href="${contextPath}/product/category?kind=2">Boots</a></li>  
	  	<li><a href="${contextPath}/product/category?kind=3">Sandals/Slipers</a></li> 
	  	<li><a href="${contextPath}/product/category?kind=4">Bloafer/Mule</a></li> 
	  	<li><a href="${contextPath}/product/category?kind=5">Slingback</a></li>
	  	<%-- <li><a href="${contextPath}/product/category?kind=6">On Sale</a></li> --%>
	  	
      </ul>
    </nav>
    <div class="clear"></div>
    <hr>
  </header>
  <!--헤더파일 들어가는 곳 끝 -->