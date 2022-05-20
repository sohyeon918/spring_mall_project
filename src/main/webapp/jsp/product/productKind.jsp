<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.jsp"%> 
<%@ include file="sub_menu.html" %>       

  <article>
    
    <h2>Item</h2>     
    
    <c:forEach items="${productKindList}"  var="productVO" varStatus="st">
    
      <!-- <div id="item"> -->
      <div id="productItem${st.index}">
        
        <a href="${contextPath}/product/product_detail?pseq=${productVO.pseq}">
          <img src="${contextPath}/product_images/${productVO.image}" />
          <%-- <h6>${productVO.name}</h6> --%>
        </a>
        
        <!-- 2022.5.17 : 상품명 오버플로우 방지 ==> 일부분만 출력  -->        
        <div style="text-indent:10px; width:95%; overflow:hidden; white-space: nowrap; text-overflow:ellipsis">
        	<a href="${contextPath}/product/product_detail?pseq=${productVO.pseq}">
          		${productVO.name}
          	</a>	
        </div>
        
        <fmt:formatNumber maxFractionDigits="3" value="${productVO.price2}" /> 원  
        
      </div>
      
    </c:forEach>    
    
    <div class="clear"></div>
    
  </article>
  
<%@ include file="../footer.jsp" %>    