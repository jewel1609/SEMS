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

<table>
	<tr>
		<th>
			교육 분야
		</th>
		<th>
			최대 인원	
		</th>
		<th>
			강의 형태
		</th>
		<th>
			강의 비용
		</th>
	</tr>
	<tr>
		<td>${education.educationCategory}</td>
		<td>${education.maxMember}</td>
		<td>${education.educationType}</td>
		<td>${education.cost}</td>
	</tr>
</table>

<table>	
	<tr>
		<th style="width: 25%">
			시작 날짜
		</th>
		<th style="width: 25%">
			종료 날짜
		</th>
		<th style="width: 25%">
			강의 시작 시간
		</th>
		<th style="width: 25%">
			강의 종료 시간
		</th>
	</tr>
	<tr>
		<td>${education.startDate}</td>
		<td>${education.endDate}</td>
		<td>${education.startTime}</td>
		<td>${education.endTime}</td>
	</tr>
</table>	
<table>	
	<tr>
		<th>
			강사명
		</th>
		<th>
			강의실
		</th>
	</tr>
	<tr>
		<td>${education.memberId}</td>
		<td>${education.educationLocation}</td>
	</tr>
</table>
<table>
	<tr>
		<th>
			교육명
		</th>
		<th>
			커리큘럼
		</th>
	</tr>
	<tr>
		<td>${education.educationTitle}</td>
		<td>${education.educationCurriculum}</td>
	</tr>
</table>

<table>
	<tr>
		<th>
			강의 소개
		</th>
	</tr>
	<tr>
		<td>${education.educationIntroduce}</td>
	</tr>
</table>

</body>
</html>