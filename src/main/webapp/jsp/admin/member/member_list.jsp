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
		    
		    <!-- 관리자 메인 메뉴 -->
		    <nav>
		    	<ul>
			    	<li><a href="${contextPath}/admin/product/productList">상품 리스트</a></li>
			    	<li><a href="${contextPath}/admin/order/orderList">주문 리스트</a></li>
			    	<li><a href="${contextPath}/admin/member/memberList">회원 리스트</a></li>
			    	<li><a href="#">Q&amp;A리스트</a></li>
			    	<li><a href="${contextPath}/login/logout">로그아웃</a></li>
		    	</ul>
		    </nav>
		    <!--// 관리자 메인 메뉴 -->
		         
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
			<div style="width:1300px; overflow-x:auto" class="pt-5"> 
				
				<div id="member_grid_title" style="width:3400px">
					<!-- <div style="float:left;width:100px">메뉴</div> -->
					<div class="member_list_grid_title" style="width:100px">&nbsp;</div>
					<div class="member_list_grid_title" style="width:80px">번호</div>
					<div class="member_list_grid_title" style="width:100px">아이디</div>
					<div class="member_list_grid_title" style="width:100px">패쓰워드</div>
					<div class="member_list_grid_title" style="width:400px">이메일</div>
					<div class="member_list_grid_title" style="width:100px">&nbsp;</div>
					
					<!-- <div class="member_list_grid_title" style="width:250px">연락처</div> -->
					<div class="member_list_grid_title" style="width:400px">연락처</div>
					<div class="member_list_grid_title" style="width:100px">&nbsp;</div>
					
					<div class="member_list_grid_title" style="width:120px">우편번호</div>
					<div class="member_list_grid_title" style="width:400px">기본주소</div>
					<div class="member_list_grid_title" style="width:100px">&nbsp;</div>
					
					<div class="member_list_grid_title" style="width:400px">상세주소</div>
					<div class="member_list_grid_title" style="width:100px">&nbsp;</div>
					
					<div class="member_list_grid_title" style="width:200px">회원상태</div>
					<div class="member_list_grid_title" style="width:350px">가입일</div>
					<div class="member_list_grid_title" style="width:200px">롤(role)</div>
					<div class="member_list_grid_title" style="width:100px">관리자메뉴</div>
				</div>
					
				<br><hr style="width:3400px"><br>
					
				<!-- 본문 시작  -->
				<div id="grid_body" style="width:3400px;">
				
					<c:forEach var="member" items="${members}" varStatus="st">
					
						<!-- 한줄 시작 -->
						<div style="width:3400px;height:70px;">
						
							<%-- <div id="count_${member.id}" style="width:0">${st.index}</div> --%>
							<input type="hidden" id="count_${member.id}" value="${st.index}" />
						
					   		<!-- checkbox -->
							<div style="float:left;width:100px;height:70px;">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input" id="ckbox_${member.id}">
									<label class="custom-control-label" for="ckbox_${member.id}">
										<span class="material-icons"> <!-- 사람 모양 아이콘 -->
											perm_identity
										</span>
									</label>
								</div>							
							</div>
							
							<!-- 글번호(페이지별) -->
							<div style="float:left;width:80px;height:70px;">
								${pageVO.limit * (pageVO.currPage-1) + st.count}
							</div>
							<!--// 글번호(페이지별) -->
						
							<!-- 회원 아이디 -->
							<div style="float:left;width:100px;height:70px;">
								${member.id}
							</div>
							<!--// 회원 아이디 -->
							
							<div style="float:left;width:100px;height:70px;">
								**********
							</div>
							
							<!-- 이메일 -->
							<div style="float:left;width:400px;height:70px;">
							
								<input type="text" onclick="alert('click text')" class="form-control"
					    		       id="email_${member.id}" value="${member.email}" />
								
							</div>
							
							<div style="float:left;width:100px;height:70px;">
								<a href="#" data-toggle="tooltip" id="email_update_btn_${member.id}" class="btn" title="이메일 수정">
			    					<span class="material-icons">
										check_circle_outline
									</span>
								</a>
							</div>
							<!--// 이메일 -->
							
							<!-- 연락처 -->
							<div style="float:left;width:400px;height:70px;">
								
								<%-- ${member.phone} --%>
								<input type="text" class="form-control"
					    		       id="phone_${member.id}" value="${member.phone}" />
								
							</div>
							
							<div style="float:left;width:100px;height:70px;">
								<a href="#" data-toggle="tooltip" id="phone_update_btn_${member.id}" class="btn" title="이메일 수정">
			    					<span class="material-icons">
										check_circle_outline
									</span>
								</a>
							</div>
							<!--// 연락처 -->
							
							<!-- 우편번호 -->
							<div style="float:left;width:120px;height:70px;">
								<%-- ${member.zipNum} --%>
								<%-- <input type="text" id="zipNum_${member.id}" class="form-control"
									   onclick="alert('zipNum_${member.id}')" value="${member.zipNum}" readonly /> --%>
								<div id="zipNum_${member.id}" class="p-2"><%-- onclick="getPostcodeAddress('${member.id}')" --%>
									${empty member.zipNum ? "&nbsp;" : member.zipNum}
								</div>	   
									   
							</div>
							<!--// 우편번호  -->
							
							<!-- 기본주소 -->
							<div style="float:left;width:400px;height:70px;">
								<div id="address1_${member.id}" class="p-2">
									${empty member.address1 ? "&nbsp;" : member.address1}
								</div>
							</div>	
							<!--// 기본주소  -->
							
							<div style="float:left;width:100px;height:70px;">
								<a href="#" data-toggle="tooltip" id="zip_address1_update_btn_${member.id}" class="btn" title="우편번호 기본주소 수정">
			    					<span class="material-icons">
										check_circle_outline
									</span>
								</a>
							</div>
							
							<!-- 상세주소 -->
							<div style="float:left;width:400px;height:70px;">
								<div id="address2_${member.id}" class="p-2" contenteditable="false"
								     onclick="this.setAttribute('contenteditable','true');">
									${member.address2}
								</div>  
							</div>	
							<!--// 상세주소 -->
							
							<div style="float:left;width:100px;height:70px;">
								<a href="#" data-toggle="tooltip" id="address2_update_btn_${member.id}" class="btn" title='상세주소 수정'>
			    					<span class="material-icons">
										check_circle_outline
									</span>
								</a>
							</div>
							
							<!-- 회원 상태  -->
							<div style="float:left;width:200px;height:70px;">
								${member.useyn == 'y' ? "회원" : "탈퇴"}
							</div>
							<!--// 회원 상태  -->
							
							<!-- 가입일 -->
							<div style="float:left;width:350px;height:70px;">
								<fmt:formatDate value="${member.indate}" pattern="yyyy년  M월 d일 HH:mm:ss"/>
							</div>
							<!--// 가입일 -->

							<!-- 회원 롤(Role) -->	
							<div style="float:left;width:200px;height:70px;">
								<select id="role${st.index}" title="${member.id}" name="role${st.index}" class="form-control">
									<option value="user">일반회원</option>
									<option value="admin">관리자</option>
									<option value="guest">게스트</option>
								</select>	
							</div>	
							<!--// 회원 롤(Role) -->
							
							<!-- 관리자 메뉴 -->
							<div style="float:left;width:100px;height:70px;" class="ml-2">
								
								<div class="btn-group btn-group-sm mt-1">
								  <!-- <button type="button" class="btn btn-secondary py-1" onclick="alert('수정')">수정</button> -->
								  <button type="button" id="member_inactive_btn_${member.id}" class="btn btn-light py-1">삭제</button>
								</div>
								
							</div>	
							<!--// 관리자 메뉴 -->
							
						</div>
						
						<!-- 한줄 끝 -->
						
					</c:forEach>
				
				</div> 
				<!--  본문 끝  -->
						
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