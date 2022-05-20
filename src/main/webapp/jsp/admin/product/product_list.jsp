<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="product_head.jsp" %>
		
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
			        
			        <!-- 2022.5.17 -->
			        <td>${searchProductVO}</td>
			        <td>${searchProductVO.searchWord}</td>
			        <td>${fn:replace(searchProductVO.searchWord, '%', '')}</td>
			        
			      </tr>
			    </tbody>
			</table>
		</div>
		
		<!-- 상품 현황 본문 -->
		<article id="product_list_section">
			
			<!-- 상품 현황 패널 -->
			<div id="product_list_pnl" style="width:1300px;" class="my-3 pb-3">
			
				<form id="search_product_frm" 
					  action="${contextPath}/admin/product/searchProductList">
			
				<!-- 검색/버튼들(전체목록, 상품 등록) -->
				<div id="product_list_head" class="row my-5">
				
					<div class="col-1">
						<!-- 대분류
						<select name="search_field" class="form-control">
							<option value="all">전체(상품명)</option>
							<option value="category">상품 분류</option>
							<option value="sales">세일 상품</option>
							<option value="best">베스트상품</option>
						</select>
						-->
					</div>
					
					<!-- 검색 구분 -->
					<div class="col-2 justify-content-center">
						<!-- <select id="search_field" name="search_field" class="form-control">
							<option value="all">전체(상품명)</option>
					  		<option value="1">Heels</option>
							<option value="2">Boots</option>
							<option value="3">Sandals</option>					  
							<option value="4">Slipers</option>
							<option value="5">Snikers</option>
							<option value="6">Sales</option>
						</select> -->
						
						<!-- 2022.5.17 -->
						<select id="search_field" name="search_field" class="form-control">
							<option value="all">전체(상품명)</option>
					  		<option value="1">Heels</option>
							<option value="2">Boots</option>
							<option value="3">Sandals/Slipers</option>					  
							<option value="4">Bloafer/Mule</option>
							<option value="5">Slingback</option>
							<option value="6">Sales</option>
						</select>
					</div>
					<!--// 검색 구분 -->
					
					<!-- 검색어 -->
					<div class="col-3">
						<input type="text" id="search_word" name="search_word" class="form-control">
					</div>
					<!--// 검색어 -->
					
					<!-- 검색 버튼 -->
					<div class="col-1">
						<button id="search_product_btn" type="button" 
								class="btn btn-secondary pt-2 pb-0">
							<span class="material-icons">
								search
							</span>
						</button>
					</div>
					<!--// 검색 버튼 -->
					
					<!-- 전체 상품 리스트 버튼 -->
					<div class="col-2 d-flex flex-row-reverse">
						<a class="btn btn-secondary pt-2 pb-2" href="${contextPath}/admin/product/productList">전체 상품 리스트</a>
					</div>
					<!--// 전체 상품 리스트 버튼 -->
					
					<!-- 상품 등록 버튼 -->
					<div class="col-2">
						<a class="btn btn-secondary pt-2 pb-2" 
						   href="${contextPath}/admin/product/productRegist">상품 등록</a>
					</div>
					<!--// 상품 등록 버튼 -->
					
					<div class="col-1">
					</div>
					
				</div>
				<!--// 검색/버튼들(전체목록, 상품 등록) -->
				
				</form>
			
				<!-- 상품 현황(리스트) -->
				<div id="product_list_body">
					<!-- 상품 현황(리스트) -->
					
					<table class="table">
						<thead>
							<th>번호</th>
							<th>상품ID</th>
							<th>분류</th>
							<th>상품이미지</th>
							<th>상품명</th>
							<th>원가(원)</th>
							<th>판매가(원)</th>
							<th>마진</th>
							<th>사용유무</th>
							<th>베스트상품 유무</th>
							<th>등록일</th>							
						</thead>
						<tbody>
							
							<c:forEach var="product" items="${products}" varStatus="st">
								<tr>
									<!-- 게시글 번호 -->
									<td>${st.count}</td>
									<!--// 게시글 번호 -->
									
									<!-- 상품 아이디 -->
									<td>${product.pseq}</td>
									<!--// 상품 아이디  -->
									
									<!-- 상품 분류 -->
									 <td>
											<%-- ${product.kind == "1" ? "Heels" :
											  product.kind == "2" ? "Boots" :
											  product.kind == "3" ? "Sandals" :
											  product.kind == "4" ? "Slipers" :
											  product.kind == "5" ? "Snikers" :
											  "Sales"} --%>
											  
											<!-- 2022.5.17 -->  
											${product.kind == "1" ? "Heels" :
											  product.kind == "2" ? "Boots" :
											  product.kind == "3" ? "Sandals/Slipers" :
											  product.kind == "4" ? "Bloafer/Mule" :
											  product.kind == "5" ? "Slingback" :
											  "Sales"}
									</td>
									<!-- 상품 분류 -->
									
									<!-- 상품 이미지 -->
									<td>
										<a href="${contextPath}/admin/product/productDetail?pseq=${product.pseq}&page=${pageVO.currPage}">
											<img src="${contextPath}/product_images/thumbnails/${product.thumbImage}" 
											    width="95" height="100">
										</a>    
									</td>
									<!--// 상품 이미지 -->
									
									<!-- 상품명 -->
									<td>
										<a href="${contextPath}/admin/product/productDetail?pseq=${product.pseq}&page=${pageVO.currPage}">
											${product.name}
										</a>
									</td>
									<!--// 상품명 -->
									
									<!-- 원가 -->
									<td>
										<fmt:formatNumber pattern="#,###" value="${product.price1}" />
									</td>
									<!--// 원가 -->
									
									<!-- 판매가 -->
									<td>
										<fmt:formatNumber pattern="#,###" value="${product.price2}" />
									</td>
									<!--// 판매가 -->
									
									<!-- 마진 -->
									<td>
										<fmt:formatNumber pattern="#,###" value="${product.price3}" />
									</td>
									<!--// 마진 -->
									
									<!-- 사용 여부 -->
									<td>
										<%-- ${product.useyn == 'y' ? '사용' : '미사용'} --%>
										<input type="hidden" id="hidden_useyn_${product.pseq}" 
											   name="hidden_useyn_${product.pseq}" 
											   value="${product.useyn}" disabled />
										
										<select id="useyn_${product.pseq}" name="useyn_${product.pseq}" class="form-control">
											<option value="y">사용</option>
											<option value="n">미사용</option>
										</select>
									</td>
									<!--// 사용 여부 -->
									
									<!-- 베스트 상품 여부 -->
									<td>
										<%-- ${product.bestyn == 'y' ? "베스트" : "일반"} --%>
										<input type="hidden" id="hidden_bestyn_${product.pseq}" 
											   name="hidden_bestyn_${product.pseq}" 
											   value="${product.bestyn}" disabled />
										
										<select id="bestyn_${product.pseq}" name="bestyn_${product.pseq}" class="form-control">
											<option value="y">베스트상품</option>
											<option value="n">일반상품</option>
										</select>
									</td>
									<!--// 베스트 상품 여부 -->
									
									<!-- 등록/변경일 -->
									<td>
										<fmt:formatDate value="${product.indate}" 
												pattern="yyyy년  M월 d일 HH:mm:ss"/>
									</td>
									<!--// 등록/변경일 -->									
									
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					
				</div>
				<!--// 상품 현황(리스트) -->		
					
			</div>
			<!--// 상품 현황 패널 -->
			
			<!-- 페이징(외장화)-->
			<%@ include file="product_list_paging.jsp" %>
			<!--// 페이징(외장화) -->
			
						
		</article>
		<!--// 상품 현황 본문 -->
		
<!-- 	</div>
	// 전체 레이아웃
	
</body>
</html> -->

<%@ include file="../footer.jsp" %>