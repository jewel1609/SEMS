<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<title>Insert title here</title>
</head>
<body>
<form name="searchForm" id="searchForm">
	<table border="1">
		<tr>
			<th>회원 ID</th>
			<th>로그인 IP</th>
			<th>로그인 시간</th>
			<th>로그아웃 시간</th>
			<th>회원 종류</th>
		</tr>
		<c:forEach items="${ loginHistoryListVO.loginHistoryList }" var="history">
		<tr>
			<td>${ history.id }</td>
			<td>${ history.lgiIp }</td>
			<td>${ history.lgiDt }</td>
			<td>${ history.lgoDt }</td>
			<td>${ history.memberType }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<c:if test="${ loginHistoryListVO ne null }">
					${loginHistoryListVO.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
				</c:if> 
			</td>
		</tr>
	</table>
</form>
</body>
</html>