<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- ///////////////////// 전체 회원(페이징) (시작) /////////////////////// -->

<c:if test="${empty searchVO.searchWord}">

<div class="w-100 my-4">
	<ul class="pagination justify-content-center">
	
		<!-- 첫 페이지 -->
		<li class="page-item">
			<a class="page-link pt-0 pb-2" 
				href="${contextPath}/admin/member/memberList?page=${pageVO.startPage}&limit=${pageVO.limit}"
				style="font-size:17pt">&laquo;</a>
		</li>
		
		
		<!-- 이전 페이지 -->
		<!-- 이전 페이지가 처음 페이지가 아니라면 링크 출력 -->
		<c:if test="${pageVO.currPage != pageVO.startPage}">
		
		<li class="page-item">
			<a class="page-link" 
			   href="${contextPath}/admin/member/memberList?page=${pageVO.currPage-1}&limit=${pageVO.limit}">
			   &lt;
		    </a>
		</li>
		
	  	<li class="page-item">
	  		<a class="page-link" 
	  		   href="${contextPath}/admin/member/memberList?page=${pageVO.currPage-1}&limit=${pageVO.limit}">
	  			${pageVO.currPage-1}
	  		</a>
  		</li>
  		
  		</c:if>
	  	
	  	
	  	<!-- 현재 페이지 -->
	  	<li class="page-item active">
	  		<a class="page-link" 
	  		   href="${contextPath}/admin/member/memberList?page=${pageVO.currPage}&limit=${pageVO.limit}">
	  		   ${pageVO.currPage}
			</a>
  		</li>
	  	
	  	<!-- 다음 페이지 -->
	  	<!-- 다음 페이지가 끝 페이지가 아니라면 링크 출력 -->
	  	<c:if test="${pageVO.currPage != pageVO.endPage}">
	  	
	  	<li class="page-item">
	  		<a class="page-link" 
	  		   href="${contextPath}/admin/member/memberList?page=${pageVO.currPage+1}&limit=${pageVO.limit}">
	  			${pageVO.currPage+1}
			</a>
  		</li>
  		
  		<li class="page-item">
  			<a class="page-link" 
  			   href="${contextPath}/admin/member/memberList?page=${pageVO.currPage+1}&limit=${pageVO.limit}">&gt;</a>
 			</li>
 			
  		</c:if>
	  	
	  	
	  	<!-- 끝 페이지 -->
	  	<li class="page-item">
	  		<a class="page-link pt-0 pb-2" 
	  		   href="${contextPath}/admin/member/memberList?page=${pageVO.endPage}&limit=${pageVO.limit}" 
	  		   style="font-size:17pt">
	  		   &raquo;
  		    </a>
  		</li>
	</ul>
</div>

</c:if>

<!-- ///////////////////// 전체 회원(페이징) (끝) /////////////////////// -->


<!-- ///////////////////// 검색 회원(페이징) (시작) /////////////////////// -->

<c:if test="${not empty searchVO.searchWord}">

<div class="w-100 my-4">
	<ul class="pagination justify-content-center">
	
		<!-- 첫 페이지 -->
		<li class="page-item">
			<a class="page-link pt-0 pb-2" 
				href="${contextPath}/admin/member/searchMemberList?page=${pageVO.startPage}&limit=${pageVO.limit}&search_field=${searchVO.searchFld}&search_word=${fn:replace(searchVO.searchWord, '%', '')}"
				style="font-size:17pt">&laquo;</a>
		</li>
		
		
		<!-- 이전 페이지 -->
		<!-- 이전 페이지가 처음 페이지가 아니라면 링크 출력 -->
		<c:if test="${pageVO.currPage != pageVO.startPage}">
		
		<li class="page-item">
			<a class="page-link" 
			   href="${contextPath}/admin/member/searchMemberList?page=${pageVO.currPage-1}&limit=${pageVO.limit}&search_field=${searchVO.searchFld}&search_word=${fn:replace(searchVO.searchWord, '%', '')}">
			   &lt;
		    </a>
		</li>
		
	  	<li class="page-item">
	  		<a class="page-link" 
	  		   href="${contextPath}/admin/member/searchMemberList?page=${pageVO.currPage-1}&limit=${pageVO.limit}&search_field=${searchVO.searchFld}&search_word=${fn:replace(searchVO.searchWord, '%', '')}">
	  			${pageVO.currPage-1}
	  		</a>
  		</li>
  		
  		</c:if>
	  	
	  	
	  	<!-- 현재 페이지 -->
	  	<li class="page-item active">
	  		<a class="page-link" 
	  		   href="${contextPath}/admin/member/searchMemberList?page=${pageVO.currPage}&limit=${pageVO.limit}&search_field=${searchVO.searchFld}&search_word=${fn:replace(searchVO.searchWord, '%', '')}">
	  		   ${pageVO.currPage}
			</a>
  		</li>
	  	
	  	<!-- 다음 페이지 -->
	  	<!-- 다음 페이지가 끝 페이지가 아니라면 링크 출력 -->
	  	<c:if test="${pageVO.currPage != pageVO.endPage}">
	  	
	  	<li class="page-item">
	  		<a class="page-link" 
	  		   href="${contextPath}/admin/member/searchMemberList?page=${pageVO.currPage+1}&limit=${pageVO.limit}&search_field=${searchVO.searchFld}&search_word=${fn:replace(searchVO.searchWord, '%', '')}">
	  			${pageVO.currPage+1}
			</a>
  		</li>
  		
  		<li class="page-item">
  			<a class="page-link" 
  			   href="${contextPath}/admin/member/searchMemberList?page=${pageVO.currPage+1}&limit=${pageVO.limit}&search_field=${searchVO.searchFld}&search_word=${fn:replace(searchVO.searchWord, '%', '')}">&gt;</a>
 			</li>
 			
  		</c:if>
	  	
	  	
	  	<!-- 끝 페이지 -->
	  	<li class="page-item">
	  		<a class="page-link pt-0 pb-2" 
	  		   href="${contextPath}/admin/member/searchMemberList?page=${pageVO.endPage}&limit=${pageVO.limit}&search_field=${searchVO.searchFld}&search_word=${fn:replace(searchVO.searchWord, '%', '')}" 
	  		   style="font-size:17pt">
	  		   &raquo;
  		    </a>
  		</li>
	</ul>
</div>

</c:if>

<!-- ///////////////////// 검색 회원(페이징) (끝) /////////////////////// -->