<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="product_head.jsp" %>

	<!-- 참고) https://getbootstrap.com/docs/4.6/components/modal/ -->
	<!-- 상품 이미지 모달 -->
	<div id="product_closeup_modal" class="modal fade" tabindex="-1" role="dialog" 
		 aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-xl">
  		
  		<!-- Modal Header -->
	    <div class="modal-header bg-white">
	        <h4 class="modal-title">상품 이미지</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	    </div>
	    <div class="modal-content" data-dismiss="modal">
    		<img src="${contextPath}/product_images/${product.image}" />
	    </div>
	    
	    <!-- Modal footer -->
	    <div class="modal-footer bg-white">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	    </div>
	  </div>
	</div>
	<!--// 상품 이미지 모달 -->

	<article>
		
		<!-- 타이틀 -->
		<h3 style="text-align:center" class="my-2 py-2">${page_title}</h3>
		<!--// 타이틀 -->
		
		<!-- 상품 등록폼 -->
		<div id="product_detail_body">
		
			<table class="table">
				<tr>
					<th class="col-2">상품분류</th>
					<td class="row">
						<div class="col-4">
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
						</div>
						<div class="col-8">
						</div>
					</td>
				</tr>
				<tr>
					<th class="col-2">상품명</th>
					<td class="row">
						<div class="col-12">
							${product.name}
						</div>						
					</td>
				</tr>
				<tr>
					<th class="col-2">원가[A]</th>
					<td class="row">
						<div style="width:105px">
							<div style="text-align:right">
								<!-- 1,234,567 원 -->
								<fmt:formatNumber pattern="#,###" value="${product.price1}" /> 원
							</div>
						</div>				
					</td>
				</tr>
				<tr>
					<th class="col-2">판매가[B]</th>
					<td class="row">
						<div style="width:105px">
							<div style="text-align:right">
								<!-- 1,234,567 원 -->
								<fmt:formatNumber pattern="#,###" value="${product.price2}" /> 원
							</div>
						</div>							
					</td>
				</tr>
				<tr>
					<th class="col-2">[B-A]</th>
					<td class="row">
						<div style="width:105px">
							<div style="text-align:right">
								<!-- 1,234,567 원 -->
								<fmt:formatNumber pattern="#,###" value="${product.price3}" /> 원
							</div>
						</div>							
					</td>
				</tr>				
				<tr>
					<th class="col-2">상세 설명</th>
					<td class="row">
						<div class="col-12 pb-3">
							${product.content}
							<%-- <textarea id="content" name="content" class="form-control" rows="7" readonly>${product.content}</textarea> --%>
						</div>
					</td>
				</tr>
				<tr>
					<th class="col-2">상품 이미지</th>
					<td class="row">
						<div class="col-12">
							<a href="#" data-toggle="modal" data-target="#product_closeup_modal">						
								<img src="${contextPath}/product_images/thumbnails/${product.thumbImage}" />
							</a>
					    </div>
					</td>
				</tr>
			</table>		
		</div>
		<!--// 상품 등록폼 -->
		
		<!-- 상품 수정/전체 상품 리스트 버튼 -->
		<div id="product_update_list_btns" class="row mb-4">
			<div class="col-5">
			</div>
			<div class="col-3 d-flex mx-3 justify-content-around">
				<button type="button" id="product_update_btn" class="btn btn-secondary"
					onclick="location.href='${contextPath}/admin/product/productUpdate?pseq=${product.pseq}'">상품 수정</button>
					
				<button type="button" id="product_list_btn" class="btn btn-secondary"
					onclick="location.href='${contextPath}/admin/product/productList?page=${page}'">전체 상품 리스트</button>
			</div>
			<div class="col-4">
			</div>
		</div>
		<!--// 상품 등록/취소 버튼 -->
		
	</article>

<%@ include file="../footer.jsp" %>