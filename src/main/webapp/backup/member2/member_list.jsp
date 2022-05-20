<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko-kr">
<head>
	<meta charset="UTF-8">
	<title>JavaMall Admin</title>
	
	<!-- google material(icon) -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<!-- jquery -->
	<script src="<c:url value="/jquery/3.6.0/jquery.min.js" />"></script>
	
	<!-- bootstrap 5 -->
	<%-- <link href="<c:url value="/bootstrap/5.1.3/css/bootstrap.min.css" />" rel="stylesheet" />
	<script src="<c:url value="/bootstrap/5.1.3/js/bootstrap.bundle.min.js" />"></script> --%>
	
	<!-- bootstrap 4 -->
	<link href="<c:url value="/bootstrap4/dist/css/bootstrap.min.css" />" rel="stylesheet" />
	<script src="<c:url value="/bootstrap4/dist/js/bootstrap.bundle.min.js" />"></script>
	
	<!-- CSS -->
	<link rel="stylesheet" href="${contextPath}/css/member_list.css" />
	
	<!-- javascript -->
	<%@ include file="member_list_head.jsp" %>
	
</head>
<body>

	<%@ include file="member_popup.jsp" %>
		
	<div id="wrap">
	
		<header>
		 	
		 	<!--로고 들어가는 곳 시작--->  
		    <div id="logo">
		      <a href="${contextPath}/">
		        <img src="<c:url value="/img/logo.gif" />" width="180" height="100" alt="JavaMall">
		      </a>  
		    </div>
		    <!--로고 들어가는 곳 끝-->
		    
		    <nav>
		    	<ul>
			    	<li><a href="#">상품 리스트</a></li>
			    	<li><a href="#">주문 리스트</a></li>
			    	<li><a href="${contextPath}/admin/member/memberList">회원 리스트</a></li>
			    	<li><a href="#">Q&amp;A리스트</a></li>
			    	<li><a href="#">로그아웃</a></li>
			    	<!-- <li><a href="#">로그인</a></li> -->
		    	</ul>
		    </nav>
		         
		</header>
		
		<!-- 페이지 관련 인자들 -->
		<div>
			<table class="table table-striped w-100">
				 <thead>
			      <tr>
			        <th>인자</th>
			        <td>현재 페이지</td>
			        <td>처음 페이지</td>
			        <td>마지막 페이지</td>
			        <td>페이지당 게시글 수</td>
			        <td>총 페이지수</td>
			        <td>검색 VO</td>
			        <td>검색어(원본)</td>
			        <td>검색어(수정)</td>
			    </thead>
			    <tbody>
			      <tr>
			        <th>값</th>
			        <td>${pageVO.currPage}</td>
			        <td>${pageVO.startPage}</td>
			        <td>${pageVO.endPage}</td>
			        <td>${pageVO.limit}</td>
			        <td>${pageVO.maxPage}</td>
			        <td>${searchVO}</td>
			        <td>${searchVO.searchWord}</td>
			        <td>${fn:replace(searchVO.searchWord, '%', '')}</td>
			      </tr>
			    </tbody>
			</table>
		</div>
		
		<!-- 현황 본문 -->
		<article>
		
			<!-- 회원 정보 리스트 -->
			<div class="table-responsive pt-5">
				
				<table class="table table-striped table-hover" style="width:2300px;">
					<thead>
						<tr>
							<th></th>
							<th style="width:100px"></th>
							<th style="width:80px">번호</th>
							<th style="width:100px">아이디</th>
							<th style="width:100px">패쓰워드</th>
							<th style="width:500px">이메일</th>
							<th style="width:100px">&nbsp;</th>
							<th style="width:250px">연락처</th>
							<th style="width:120px">우편번호</th>
							<th style="width:400px">기본주소</th>
							<th style="width:400px">상세주소</th>
							<th style="width:150px">회원상태</th>
							<th style="width:350px">가입일</th>
							<th style="width:250px">롤(role)</th>
							<th style="width:200px">관리자메뉴</th>
						</tr>
					</thead>
					
					<tbody>
					
						<c:forEach var="member" items="${members}" varStatus="st">
						
						<!-- 게시글 번호 -->
						<c:set var="article_num" value="${pageVO.limit * (pageVO.currPage-1) + st.count}" />
						
						<tr>
							<td>
								<a href="#"  class="btn btn-secondary py-1" onclick="alert('click')">버튼</a>    
								<a href="#"  class="btn" title="이메일 수정" onclick="alert('클릭')">
			    					<span class="material-icons">
										check_circle_outline
									</span>
								</a>								
							</td> 
							
							
						    <!-- checkbox -->
							<td>
								<%-- <div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input" id="ckbox_${member.id}">
									<label class="custom-control-label" for="ckbox_${member.id}">
										<span class="material-icons"> <!-- 사람 모양 아이콘 -->
											perm_identity
										</span>
									</label>
								</div>	 --%>							
							</td>
							
							<td>
			    		       1<%-- ${article_num} --%>
							</td>
							
							<td>abcd<%-- ${member.id} --%>
								
							</td>
							
							<td><a href="#"  class="btn" title="이메일 수정" onclick="alert('클릭')">
			    					<span class="material-icons">
										check_circle_outline
									</span>
								</a>**********</td>
							
							<td>
							
						    	<!-- <div style="float:left;"> --> <!-- id="email_update_btn_${member.id}" -->
									<%-- <a href="#" id="email_update_btn_${member.id}" onclick="alert('클릭')" 
									   class="btn" title="이메일 수정">
				    					<span class="material-icons">
											check_circle_outline
										</span>
									</a> --%>
								<!-- </div> -->	   
					    		
				    			<!-- <div style="float:left;">  class="form-control bg-light" -->
									<%-- <input type="text" onclick="alert('click text')"
						    		       id="email_${member.id}" style="width:300px" value="${member.email}" /> --%>
								<!-- </div>	 -->
								
								
									<!--  <a href="#" onclick="alert('클릭')" 
									    class="btn" title="이메일 수정">
				    					<span class="material-icons">
											check_circle_outline
										</span>
									</a> -->
							</td>
								
							<td>${member.phone}</td>
							<td>${member.zipNum}</td>
							<td>${member.address1}</td>
							<td>${member.address2}</td>
							<td>${member.useyn == 'y' ? "회원" : "탈퇴"}</td>
							
							<td><fmt:formatDate value="${member.indate}" pattern="yyyy년  M월 d일 HH:mm:ss"/></td>

							<td>
								<select id="role${st.index}" title="${member.id}" name="role${st.index}" class="form-control">
									<option value="user">일반회원</option>
									<option value="admin">관리자</option>
									<option value="guest">게스트</option>
								</select>	
							</td>
							
							<td>
								<div class="btn-group btn-group-sm mt-1">
								  <button type="button" class="btn btn-secondary py-1" onclick="alert('수정')">수정</button>
								  <button type="button" class="btn btn-light py-1" onclick="alert('삭제')">삭제</button>
								</div>
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
					
				</table>
				
			</div>
			
			<!-- 페이징 -->
			<%@ include file="member_list_paging.jsp" %>
			
			<!-- 검색 -->
			<form action="${contextPath}/admin/member/searchMemberList">
			
			<div class="w-100 row mt-4 mb-3">
			
				<div class="col-3"></div>
				
				<div class="col-2 justify-content-center">
					<select name="search_field" class="form-control">
						<option value="id">아이디</option>
				  		<option value="name" selected>이름</option>
						<option value="address1">기본주소</option>
						<option value="address2">상세주소</option>					  
					</select>
				</div>
				
				<div class="col-3">
					<input type="text" name="search_word" class="form-control">
				</div>
				
				<div class="col-1">
					<button class="btn btn-secondary pt-2 pb-0"><!-- 검색 -->
						<span class="material-icons">
							search
						</span>
					</button>
				</div>
				
				<div class="col-3">
					<a class="btn btn-secondary pt-2 pb-2" href="${contextPath}/admin/member/memberList">전체 회원 리스트</a>
				</div>
				
				<!-- hidden field -->
				<input type="hidden" name="page" value="1" />
				
				<input type="hidden" name="limit" value="${empty searchVO.limit ? pageVO.limit : searchVO.limit}" />
				
			</div>
			
			</form>
		
		</article>
		
<%@ include file="../footer.jsp" %>