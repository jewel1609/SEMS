<!-- @author 이기연-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="/resources/css/eduDetail.css" rel="stylesheet">
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육 문의 사항 리스트 보기</title>
</head>
<body>
	
	<table border="1"> 
		<tr>
			<th>문의 번호</th>
			<th>교육 이름</th>
			<th>문의 날짜</th>
		</tr>
		<c:forEach items="${qnaList.qnaVO}" var="qnaVO">
		<tr>
			<td><a href="<c:url value='/myPage/myQNADetail/{replyId}'/>" onclick="window.open(this.href, '문의 사항','');return false;" target="_blank">${qnaVO.replyId}</a></td>
			<td>${qnaVO.eduId}</td>
			<td>${qnaVO.createdDate}</td>
		</tr>
		</c:forEach>
		
	</table>

</body>
</html>