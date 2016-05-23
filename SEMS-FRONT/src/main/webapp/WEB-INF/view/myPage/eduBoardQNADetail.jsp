<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
			
		
		
	});
</script>
<title>QNA 상세페이지</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>질문 번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자</td>
		</tr>
		<tr>
			<td>${oneQNABBSByAtcId.atcId}</td>
			<td>${oneQNABBSByAtcId.title}</td>
			<td>${oneQNABBSByAtcId.contents}</td>
			<td>${oneQNABBSByAtcId.mbrId}</td>
		</tr>
	</table>
</body>
</html>