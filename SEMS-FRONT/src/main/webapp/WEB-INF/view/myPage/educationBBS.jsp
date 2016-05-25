<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>

	<div>
		<span>강의 자료 <a href="<c:url value="/education/fileBBS/${educationId}" />">더보기</a></span>
		<table>
			<tr></tr>
			
			<c:forEach items="${educationItems}" var="item">
			<tr>
				<td>${item.articleId}</td>
				<td>${item.title}</td>
				<td>${item.createDate}</td>
			</tr>
			</c:forEach>
		
		</table>
	</div>
	
	<div>
		<span>질문/답변 <a href="<c:url value="/eduBoard/QNAList/${educationId}" />">더보기</a></span>
	</div>
	
	<div>
		<span>과제 <a href="<c:url value="/" />">더보기</a></span>
	</div>
	
	<div>
		<span>시험 <a href="<c:url value="/" />">더보기</a></span>
	</div>
	
	<div>
		<span>강의 평가 <a href="<c:url value="/" />">더보기</a></span>
	</div>

</div>

</body>
</html>