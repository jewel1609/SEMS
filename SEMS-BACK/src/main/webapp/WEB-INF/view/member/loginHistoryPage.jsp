<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/resources/css/loginHistoryPage.css'/>" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>

<ul class="tab">
	<li><a href="#", class="tablinks" onclick="openGroup(event, 'admin')">admin</a></li>
	<li><a href="#", class="tablinks" onclick="openGroup(event, 'member')">member</a></li>
</ul>

<div id="admin" class="tebcontent">
	<h3>admin</h3>
	<%-- <table id="admin" border="1">
		<tr>
			<td>번호</td>
			<td>관리자 아이디</td>
			<td>IP 주소</td>
			<td>로그인 시간</td>
			<td>로그아웃 시간</td>
			<td>IP확인 요청하기</td>
		</tr>
		<c:forEach items="${ loginHistoryListVO.loginHistoryList }" var="loginHistory">
			<tr>
				<td>${ loginHistory.lgiHtrId }</td>
				<td>${ loginHistory.id }</td>
				<td>${ loginHistory.lgiIp }</td>
				<td>${ loginHistory.lgiDt }</td>
				<td>${ loginHistory.lgoDt }</td>
			</tr>
		</c:forEach>
	</table> --%>
</div>
<div id="member" class="tebcontent">
	<h3>member</h3>
	<table id="member" border="1">
		<tr>
			<td>번호</td>
			<td>사용자 아이디</td>
			<td>IP 주소</td>
			<td>로그인 시간</td>
			<td>로그아웃 시간</td>
			<td>IP확인 요청하기</td>
		</tr>
		<c:forEach items="${ loginHistoryListVO.loginHistoryList }" var="loginHistory">
			<tr>
				<td>${ loginHistory.lgiHtrId }</td>
				<td>${ loginHistory.id }</td>
				<td>${ loginHistory.lgiIp }</td>
				<td>${ loginHistory.lgiDt }</td>
				<td>${ loginHistory.lgoDt }</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>