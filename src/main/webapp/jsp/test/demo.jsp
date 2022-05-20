<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>demo</title>

<script src="${contextPath}/jquery/3.6.0/jquery.min.js"></script>
<script src="./jquery/3.6.0/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value="/jquery/3.6.0/jquery.min.js" />"></script>

<script>
$(function(){
	$("body").css("background", "blue");
});
</script>
</head>
<body>
demo2
</body>
</html>