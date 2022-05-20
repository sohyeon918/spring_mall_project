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
<style>
#div1 {
	background-color:yellow;
	width:500px;
	height:100px;
	margin:auto;
}	

.btn_custom1 {
	width:100px;
	margin:0 20px;
	background-color:orange;
}
</style>
</head>
<body>
	 <!-- 
	 <div id="div1" class="row mx-auto">
	 	<div class="col-2">
	 	</div>
	 	<div class="col-3 ms-2 bg-warning">
	 		<button id="btn1" type="button" class="btn btn-primary">Basic1</button>
	 	</div>
	 	<div class="col-3 bg-danger">
	 		<button id="btn2" type="button" class="btn btn-info">Basic2</button>
	 	</div>
	 	<div class="col-3 bg-info">
	 		<button id="btn3" type="button" class="btn btn-success">Basic3</button>
	 	</div>
	 	<div class="col-1">
	 	</div>
	 </div>
	  -->
	  
	 <div id="div1">
		<button id="btn1" type="button" class="btn btn-primary btn_custom1">Basic1</button>
 		<button id="btn2" type="button" class="btn btn-info btn_custom1">Basic2</button>
 		<button id="btn3" type="button" class="btn btn-success btn_custom1">Basic3</button>
	 </div> 
	  
</body>
</html>