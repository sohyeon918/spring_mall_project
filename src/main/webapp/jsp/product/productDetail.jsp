<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.jsp"%> 
<%@ include file="sub_menu.html" %>
       
  <article>
  
    <h1>Item</h1>
    
    <div id="itemdetail">
    
      <form  method="post" name="formm">    
      
        <fieldset>
        
          <legend> Item detail Info</legend>  
          
          <a href="${contextPath}/product/product_detail?pseq=${productVO.pseq}">         
            <span style="float: left;"> 
              <img  src="${contextPath}/product_images/${productVO.image}"  />
            </span>
          </a>  
          
          <br><br>
          
          <div id="item_right_pnl">
           
	          <div class="row mb-2 mt-3">
	          	<h4>${productVO.name}</h4>
	          </div>
	          
	          <div class="row mb-2">
	          	<div class="col-3">	
	          		<label>가 격 :</label>
	          	</div>	  
	          	<div class="col-8 mt-1">
	          		<fmt:formatNumber maxFractionDigits="3" value="${productVO.price2}" /> 원
	          	</div>	  
	          </div>	
	          
	          <div class="row mb-2">
	          	<div class="col-3">						
		          <label class="form-label">수 량 : </label>
		        </div>
		        <div class="col-6">  
		          <input type="number" class="form-control" name="quantity" size="2"  value="1" min="1">
		        </div>  
	          </div>
	          
	          <input  type="hidden" name="pseq"  value="${productVO.pseq}"><br>
          </div>
          
        </fieldset>
        
        <div id="product_content" class="row p-2 m-2">
        	${productVO.content}
        </div>
        
        <div class="clear"></div>
        
        <div id="buttons" class="row col-10 float-end">
        
          <div class="col-1"></div>
          <div class="col-3">
          	<input type="button" value="카트에 담기"  class="btn btn-secondary"  onclick="go_cart()">
          </div>
          <div class="col-3">
          	<input type="button" value="즉시 구매"   class="btn btn-secondary ps-3 ms-4"   onclick="go_order()">
          </div>
          <div class="col-2">
          	<input type="reset"  value="취소"      class="btn btn-secondary ps-3">
          </div>
          <div class="col-3"></div>
          
        </div>
        
      </form>
        
    </div>
  </article>
  
<%@ include file="../footer.jsp" %>    