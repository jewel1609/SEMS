<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	
	$(document).ready(function () {
		
		$("#updateBtn").click(function () {
			
			location.href = "<c:url value="/memberUpdate/"/>"+${memberVO.id}
			
		});
		
		
	});
	
</script>
<title></title>
</head>


<body>

<span id="updateBtn">수정</span>

</body>
</html>