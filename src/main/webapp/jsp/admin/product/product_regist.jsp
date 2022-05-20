<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="product_head.jsp" %>

	<article>
		
		<!-- 타이틀 -->
		<h3 style="text-align:center" class="my-2 py-2">${page_title}</h3>
		<!--// 타이틀 -->
		
		<form id="product_regist_frm" 
			  method="post" 
			  action="${contextPath}/admin/product/productRegistProc"
			  enctype="multipart/form-data">
		
		<!-- 상품 등록폼 -->
		<div id="product_regist_body">
		
			<table class="table">
				<tr>
					<th class="col-2">상품분류</th>
					<td class="row">
						<div class="col-4">
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
					<th class="col-2">상품명</th>
					<td class="row">
						<div class="col-12">
							<input type="text" id="name" name="name" class="form-control"
								   required
							       pattern="[- _0-9a-zA-Z가-힣]{2,33}" /> <!-- varchar2(100 byte) -->
						</div>						
					</td>
				</tr>
				<tr>
					<th class="col-2">원가[A]</th>
					<td class="row">
						<div class="col-12">
							<input type="number" id="price1" name="price1" class="form-control" 
								   required pattern="\d{1,7}" min="0" /> <!-- number(7,0) -->
						</div>						
					</td>
				</tr>
				<tr>
					<th class="col-2">판매가[B]</th>
					<td class="row">
						<div class="col-12">
							<input type="number" id="price2" name="price2" class="form-control" 
							       required pattern="\d{1,7}" min="0"/>
						</div>						
					</td>
				</tr>
				<tr>
					<th class="col-2">[B-A]</th>
					<td class="row">
						<div class="col-12">
							<input type="number" id="price3" name="price3" class="form-control bg-white" 
							       required pattern="\d{1,7}" min="0" readonly />
						</div>						
					</td>
				</tr>				
				<tr>
					<th class="col-2">상세 설명</th>
					<td class="row">
						<div class="col-12">
							<textarea id="content" name="content" 
							          required maxlength="2000" class="form-control" rows="7"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<th class="col-2">상품 이미지</th>
					<td class="row">
						<div class="col-12">
							<div class="custom-file mb-3">
					      		<input type="file" class="custom-file-input" required id="image" name="image">
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
		
		<!--// 상품 등록폼 -->
		
		<!-- 상품 등록/취소 버튼 -->
		<div id="product_regist_btns" class="row mb-4">
			<div class="col-5">
			</div>
			<div class="col-3 d-flex mx-3 justify-content-around">
				<input type="submit" id="product_regist_submit" class="btn btn-secondary" value="상품 등록" />
				<input type="reset" id="product_regist_reset" class="btn btn-secondary" value="등록 취소" />
			</div>
			<div class="col-4">
			</div>
		</div>
		<!--// 상품 등록/취소 버튼 -->
		
		</form>
		
	</article>
	
	
<%@ include file="../footer.jsp" %>	