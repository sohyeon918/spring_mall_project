<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
<%@ include file="sub_img.jsp"%>
<%@ include file="sub_menu.jsp"%>

<article>

	<h2>Cart List</h2>
	
	<form name="formm" method="post">
		
		<c:choose>
		
			<c:when test="${cartList.size() == 0}">
				<h5 style="padding-top:30px; text-align: center;">카트가 비었습니다.</h5>
			</c:when>
			
			<c:otherwise>
			
				<table id="cartList" class="table container">
					<tr>
						<th>상품코드</th>
						<th>상품명</th>
						<th>수 량</th>
						<th>가 격</th>
						<th>주문일</th>
						<th>&nbsp;</th>
						<!-- <th>수정/삭제</th> -->
					</tr>

					<c:forEach items="${cartList}" var="cartVO" varStatus="st">
					
						<input type="hidden" id="pseq_${cartVO.cseq}" name="pseq" value="${cartVO.pseq}" />
						
						<tr>
							<td class="pt-3 pb-0" style="width:70px">
								${cartVO.cseq}
							</td>
							<td class="pt-3 pb-0">
								<a href="${contextPath}/product/product_detail?pseq=${cartVO.pseq}">
									${cartVO.pname}
								</a>
							</td>
							<td style="width:80px" class="pt-2 pb-0">
								<%-- ${cartVO.quantity} --%>
								<!-- 주문 수량 변동 조정토록 조치 -->
								<div class="form-group">
									<input type="number" id="quantity_${cartVO.cseq}" name="quantity"
									       class="form-control form-control-sm" value="${cartVO.quantity}" min="1" 
									       style="text-align:right" />
								</div>       
							</td>
							<td style="text-align:right" class="pt-3 pb-0">
							     
								<fmt:formatNumber
									value="${cartVO.price2*cartVO.quantity}" type="currency" />
							</td>
							<td class="pt-3 pb-0">
								<fmt:formatDate value="${cartVO.indate}" type="date" />
							</td>
							<td class="pt-3 pb-0" style="width:100px">
								<div class="custom-control custom-checkbox mb-3">
									<input type="checkbox" id="cseq_${cartVO.cseq}" name="cseq"
									       class="custom-control-input" value="${cartVO.cseq}">
									       						
									<input type="hidden" name="cseq_idx" value="${cartVO.cseq}_${st.index}" />              
								</div>	       
							</td>
						</tr>
					</c:forEach>

					<tr>
						<th class="pt-4"><h5>총 액</h5></th>
						<th colspan="2" class="pt-4">
							<h5>
								<fmt:formatNumber value="${totalPrice}" type="currency" />
							</h5>
						</th>
						<th colspan="2" style="width:200px">
							<a href="#" class="btn btn-secondary my-2" onclick="go_cart_update()">
								수정
							</a>
							&nbsp;
							<a href="#" class="btn btn-secondary my-2" onclick="go_cart_delete()">
								삭제
							</a>
						</th>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>

		<div class="clear"></div>

		<div id="buttons" class="row col-10 float-end">
		
		 	<div class="col-2"></div>
          	<div class="col-4 pr-1">
				<input type="button" class="btn btn-secondary" value="쇼핑 계속하기" class="cancel"
					onclick="location.href='${contextPath}/'">
			</div>
         	<div class="col-3">
				<c:if test="${cartList.size() != 0}">
					<input type="button" class="btn btn-secondary" value="주문하기" class="submit"
						onclick="go_order_insert()">
				</c:if>
			</div>
			<div class="col-1"></div>	
		</div>
		
	</form>

</article>

<%@ include file="../footer.jsp"%>
