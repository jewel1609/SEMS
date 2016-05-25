<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 리스트 페이지</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<td>팀 아이디</td>
			<td>팀 이름</td>
			<td>팀 생성날짜</td>
			<td>교육 아이디</td>
		</tr>
		<c:forEach items="${ teamSearchListVO.teamList }" var="team">
			<tr>
				<td> ${ team.teamId }</td>
				<td><a href="teamDetail/${team.teamId}">${ team.teamName }</a></td>
				<td> ${ team.teamDate }</td>
				<td> ${ team.educationId }</td>
			</tr>
		</c:forEach>
		<tr >
			<td colspan="4" align="center">
				<form id="searchForm">
				${teamSearchListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm")}
			
				<!-- <select name="searchItems">
					<option value="teacher">강사명</option>
					<option value="study">교육명</option>
				</select>
				<input type="text" name="searchText" />
				<input type="submit" id="submit" value="검색" /> -->
				
				</form>
			</td>
		</tr>
		
	</table>
</body>
</html>