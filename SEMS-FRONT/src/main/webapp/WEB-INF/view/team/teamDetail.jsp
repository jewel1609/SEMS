<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>팀 리스트 번호</td>
			<td>팀 번호</td>
			<td>팀 구성원</td>
		</tr>
		
		<c:forEach items="${ teamsListsVO.teamsListsVO}" var="teamsList">
			<tr>
				<td>${ teamsList.teamListId }</td>
				<td>${ teamsList.teamId }</td>
				<td>${ teamsList.mbrId }</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>