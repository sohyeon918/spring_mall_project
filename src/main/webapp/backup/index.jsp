<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="header.jsp" %>

	  <!--메인 이미지 들어가는 곳 시작 --->
	  <div class="clear"></div>
	  
	  <div id="main_img">
	    <img src="<%=request.getContextPath() %>/img/main_img.jpg" >    
	  </div>
	  <!--메인 이미지 들어가는 곳 끝--->
	
	  <div class="clear"></div>   
	
	  <div id="front">   
	    <h2>New Item</h2>     
	    <div id="bestProduct">         
	      <c:forEach items="${newProductList}"  var="productVO">
	        <div id="item">
	          <a href="#">
	            <img src="<%=request.getContextPath() %>/product_images/${productVO.image}" />
	            <h3>${productVO.name} </h3>    
	            <p><fmt:formatNumber maxFractionDigits="3" value="${productVO.price2}" />원</p>
	          </a>    
	        </div>
	      </c:forEach>      
	    </div>
	    
	   <div class="clear"></div>
	
	<!-- ######################################################### -->
	
 	   <h2> Best Item</h2>     
      	<div id="bestProduct">    
			<c:forEach items="${bestProductList}"  var="productVO">
				<div id="item">
					<a href="#">
						<img src="<%=request.getContextPath() %>/product_images/${productVO.image}" />
					</a>
					<h3>${productVO.name}</h3>    
            		<p><fmt:formatNumber maxFractionDigits="3" value="${productVO.price2}" /> 원</p>
				</div>	
			</c:forEach>
		</div>
		
	    <div class="clear"></div>
  		
 	</div>
 
<!-- footer -->
<%@ include file="footer.jsp" %>
