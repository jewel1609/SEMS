<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#questionBtn").click(function() {
			
			location.href = "<c:url value='/eduBoard/QNAWrite' />";
		});
			
	});

</script>
<title>강의게시판 - 마이게시판 - 질문/답변 게시판</title>
</head>
<body>
	
	<table border="1">
		<tr>
			<td>질문 번호</td>
			<td>질문 제목</td>
			<td>작성자</td>
			<td>작성 시간</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${educationQNAList}" var="qnaBBS">
		<tr>
			<td>${qnaBBS.atcId}</td>
			<td>
				<a href="<c:url value='/eduBoard/QNADetail/${qnaBBS.atcId}'/>">
					${qnaBBS.title}
				</a>
			</td>
			<td>${qnaBBS.mbrId}</td>
			<td>${qnaBBS.createDate}</td>
			<td>${qnaBBS.hits}</td>
		</tr>
		</c:forEach>
	</table>
	
	<input type="button" id="questionBtn" value="질문하기"/>
	
</body>
</html>