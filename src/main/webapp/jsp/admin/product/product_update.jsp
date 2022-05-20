<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="product_head.jsp" %>

	<div class="mx-auto w-100 bg-white my-3">
		개별 상품 정보 : ${product}<br>
		개별 상품 정보(세션) : ${sessionScope.DEFAULT_PRODUCT_INFO}<br>
	</div>

	<article>
		
		<!-- 타이틀 -->
		<h3 style="text-align:center" class="my-2 py-2">${page_title}</h3>
		<!--// 타이틀 -->
		
		<form id="product_update_frm" 
			  method="post" 
			  action="${contextPath}/admin/product/productUpdateProc"
			  enctype="multipart/form-data">
		
		<!-- 상품 수정폼 -->
		<div id="product_update_body">
		
			<table class="table">
				<tr>
					<th class="col-2">상품분류</th>
					<td class="row">
						<div class="col-4">
							<%-- ${product.kind == "1" ? "Heels" :
							  product.kind == "2" ? "Boots" :
							  product.kind == "3" ? "Sandals/Slipers" :
							  product.kind == "4" ? "Bloafer/Mule" :
							  product.kind == "5" ? "Slingback" :
							  "Sales"} --%>
							
							<!-- 제품 종류 임시 저장 → js 활용 -->
							<input type="hidden" id="kind_hidden" value="${product.kind}" disabled />
							  
							<!-- <select id="kind" name="kind" class="form-control">
						  		<option value="1">Heels</option>
								<option value="2">Boots</option>
								<option value="3">Sandals</option>					  
								<option value="4">Slipers</option>
								<option value="5">Snikers</option>
								<option value="6">Sales</option>
							</select> -->
							
							<!-- 2022.5.17 -->
							<select id="kind" name="kind" class="form-control">
						  		<option value="1">Heels</option>
								<option value="2">Boots</option>
								<option value="3">Sandals/Slipers</option>					  
								<option value="4">Bloafer/Mule</option>
								<option value="5">Slingback</option>
								<option value="6">Sales</option>
							</select>
						</div>
						<div class="col-8">
						</div>
					</td>
				</tr>
				<tr>
					<th class="col-2">상품 아이디</th>
					<td class="row">
						<div class="col-4">
							<input type="hidden" id="pseq" name="pseq" value="${product.pseq}" />
							${product.pseq}
						</div>
						<div class="col-8">
						</div>
					</td>
				</tr>
				<tr>
					<th class="col-2">상품명</th>
					<td class="row">
						<div class="col-12">
							<input type="text" id="name" name="name" class="form-control"
							       required pattern="[- _0-9a-zA-Z가-힣]{2,33}" value="${product.name}" />
						</div>						
					</td>
				</tr>
				<tr>
					<th class="col-2">원가[A]</th>
					<td class="row">
						<div class="col-12">
							<input type="number" id="price1" name="price1" class="form-control" 
								   required pattern="\d{1,7}" min="0" value="${product.price1}" /> <!-- number(7,0) -->
						</div>						
					</td>
				</tr>
				<tr>
					<th class="col-2">판매가[B]</th>
					<td class="row">
						<div class="col-12">
							<input type="number" id="price2" name="price2" class="form-control" 
							       required pattern="\d{1,7}" min="0" value="${product.price2}"/>
						</div>						
					</td>
				</tr>
				<tr>
					<th class="col-2">[B-A]</th>
					<td class="row">
						<div class="col-12">
							<input type="number" id="price3" name="price3" class="form-control bg-white" 
							       required pattern="\d{1,7}" min="0" readonly value="${product.price3}" />
						</div>						
					</td>
				</tr>				
				<tr>
					<th class="col-2">상세 설명</th>
					<td class="row">
						<div class="col-12">
							<textarea id="content" name="content" style="resize:none"
							          required maxlength="2000" class="form-control" rows="7">${product.content}</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<th class="col-2">상품 이미지(기존)</th>
					<td class="row">
						<div class="col-12">
							<img src="${contextPath}/product_images/thumbnails/${product.thumbImage}" />
					    </div>
					</td>
				</tr>
				<tr>
					<th class="col-2">상품 이미지 수정</th>
					<td class="row">
						<div class="col-12">
							<div class="custom-file mb-3">
					      		<input type="file" class="custom-file-input" id="image" name="image">
						    	<label class="custom-file-label" for="image">상품 이미지를 업로드 하십시오</label>
						    </div>
					    </div>
					</td>
				</tr>
			</table>		
		</div>

		<!-- 파일 필드 업로드 파일명 자동 입력  -->		
		<script>
		$(".custom-file-input").on("change", function() {
		  var fileName = $(this).val().split("\\").pop();
		  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
		});
		</script>
		
		<!-- 전송 히든 필드 : useyn, bestyn -->
		<input type="hidden" name="useyn" value="${product.useyn}" />
		<input type="hidden" name="bestyn" value="${product.bestyn}" />
		
		<!--// 상품 수정폼 -->
		
		<!-- 상품 수정/취소 버튼 -->
		<div id="product_update_btns" class="row mb-4">
			<div class="col-5">
			</div>
			<div class="col-3 d-flex mx-3 justify-content-around">
				<input type="submit" id="product_update_submit" class="btn btn-secondary" value="상품 수정" />
				<input type="reset" id="product_update_reset" class="btn btn-secondary" value="수정 취소" />
			</div>
			<div class="col-4">
			</div>
		</div>
		<!--// 상품 수정/취소 버튼 -->
		
		</form>
		
	</article>
	
	
<%@ include file="../footer.jsp" %>	