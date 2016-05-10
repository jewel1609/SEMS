<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="2">교육 상세 조회</th>
		</tr>
		<tr>
			<td>강의 아이디</td>
			<td>${ education.educationId }</td>
		</tr>
		<tr>
			<td>교육 카테고리</td>
			<td>${ education.educationCategory }</td>
		</tr>
		<tr>
			<td>교육명</td>
			<td>${ education.educationTitle }</td>
		</tr>
		<tr>
			<td>강사명</td>
			<td>${ education.memberId }</td>
		</tr>
		<tr>
			<td>정원</td>
			<td>${ education.maxMember }</td>
		</tr>
		<tr>
			<td>강의장소</td>
			<td>${ education.educationLocation }</td>
		</tr>
		<tr>
			<td>커리큘렴 파일</td>
			<td>${ education.educationCurriculum }</td>
		</tr>
		<tr>
			<td>강의 소개</td>
			<td>${ education.educationIntroduce }</td>
		</tr>
		<tr>
			<td>강의 시작 날짜</td>
			<td>${ education.startDate }</td>
		</tr>
		<tr>
			<td>강의 종료 시간</td>
			<td>${ education.endDate }</td>
		</tr>
		<tr>
			<td>강의 형태</td>
			<td>${ education.educationType }</td>
		</tr>
		<tr>
			<td>비용</td>
			<td>${ education.cost }</td>
		</tr>
	</table>
		
	<c:if test="">
	<a href="">교육 참가 신청</a>
	</c:if>
	<c:if test="">
	<a href="">교육 참가 취소</a>
	</c:if>
</body>
</html>