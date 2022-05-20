<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="order_head.jsp"%>

<article>

	<form name="frm" class="form-inline" method="post" action="${contextPath}/admin/orderList">
	
		<table class="table table-borderless d-flex flex-row-reverse pr-5">
			<tr>
				<td class="pt-3"><b>주문자 이름</b></td> 
				<td>
					<input type="text" class="form-control" name="key">
				</td> 
				<td>
					<input class="btn btn-secondary" type="submit" value="검색">
				</td>
			</tr>
		</table>
		
		<br>
		
		<table id="orderList" class="table">
			<tr>
				<th>주문번호(처리여부)</th>
				<th>주문자</th>
				<th>상품명</th>
				<th>수량</th>
				<th>우편번호</th>
				<th>배송지</th>
				<th>전화</th>
				<th>주문일</th>
			</tr>
			
			<c:forEach items="${orderList}" var="orderVO">
				<tr>
					<td>
						<c:choose>
							<c:when test="${orderVO.result=='1'}">
							
								<div class="row" style="width:180px;">
								
									<div class="col-3 mt-0">
										<span style="font-weight: bold; color: blue">${orderVO.odseq}</span>
									</div>
									
									<div class="col-8">
										<div class="custom-control custom-checkbox">
											<input type="checkbox" id="result_${orderVO.odseq}" name="result"
											       class="custom-control-input" value="${orderVO.odseq}">
										 	<label class="custom-control-label mt-0" for="result_${orderVO.odseq}">미처리</label>       
										</div>
									</div>
								
								</div>
								
					        </c:when>
							<c:otherwise>
								
								<div class="custom-control custom-checkbox mb-3" style="width:180px;">
									<input type="checkbox" class="custom-control-input"
									       checked="checked" disabled="disabled">
								 	<label class="custom-control-label">${orderVO.odseq} 처리완료</label>       
								</div>
																
					        </c:otherwise>
						</c:choose>
					</td>
					
					<td>${orderVO.mname}</td>
					<td>${orderVO.pname}</td>
					<td>${orderVO.quantity}</td>
					<td>${orderVO.zipNum}</td>
					<td>
						${orderVO.address1}&nbsp;
						${orderVO.address2} 
					</td>
					<td>${orderVO.phone}</td>
					
					<td><fmt:formatDate value="${orderVO.indate}" /></td>
				</tr>
			</c:forEach>
		</table>
		<input type="button" class="btn btn-secondary"
			   value="주문처리(입금확인)" onClick="go_order_save()">
			
	</form>
	
</article>

<%@ include file="../footer.jsp"%>
