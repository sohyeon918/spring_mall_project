<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
<%@ include file="sub_img.jsp"%>
<%@ include file="sub_menu.jsp"%>

<article>
	
	<h2>My Page(${title})</h2>
	
	<form name="formm" method="post">
	
		<table id="cartList" class="table">
			<tr>
				<th>주문일자</th>
				<th>주문번호</th>
				<th>상품명</th>
				<th>결제 금액</th>
				<th>주문 상세</th>
			</tr>
			
			<c:forEach items="${orderList}" var="orderVO">
				<tr>
					<td>
						<fmt:formatDate value="${orderVO.indate}" type="date" />
					</td>
					<td>${orderVO.oseq}</td>
					<td>${orderVO.pname}</td>
					<td>
						<fmt:formatNumber value="${orderVO.price2}" type="currency" />
					</td>
					<td>
						<a href="${contextPath}/order/orderDetail?oseq=${orderVO.oseq}">
							조회 
						</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>

		<div class="clear"></div>
		<div id="buttons" style="float: right">
			<input type="button" class="btn btn-secondary" value="쇼핑 계속하기"
					onclick="location.href='${contextPath}'">
		</div>
		
	</form>
	
</article>

<%@ include file="../footer.jsp"%>
