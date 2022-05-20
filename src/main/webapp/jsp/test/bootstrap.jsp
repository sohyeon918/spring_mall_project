<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- <script src="${contextPath}/jquery/3.6.0/jquery.min.js"></script> --%>
<%-- <script src="<c:url value="/jquery/3.6.0/jquery.min.js" />"></script> --%>

<link href="${contextPath}/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextPath}/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>

<!-- 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
 -->
 
<title>demo</title>
</head>
<body>

	 <div class="container">
	 	<button type="button" class="btn btn-primary">Primary</button>
	 </div>
	 
	 <div class="container mt-3">
	  <h3>Modal Example</h3>
	  <p>Click on the button to open the modal.</p>
	  
	  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
	    Open modal
	  </button>
	</div>
	
	<!-- The Modal -->
	<div class="modal" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">Modal Heading</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        Modal body..
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
	      </div>
	
	    </div>
	  </div>
	</div>
	 
</body>
</html>