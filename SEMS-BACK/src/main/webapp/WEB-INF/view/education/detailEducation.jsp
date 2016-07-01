<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.inputButton {
   border: none;
   border-radius: 5px;
   padding: 6px 12px;
   font-weight: bold;
   text-transform: uppercase;
   color: #FFFFFF;
   background-color: #E05149;
}

select, input {
   display: inline;
}
</style>
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#eduQna").click(function() {
			location.href = "<c:url value='/${educationId}/eduQna' />";
		});

		$("#eduReport").click(function() {
			location.href = "<c:url value='/${educationId}/eduReport' />";
		});

		$("#eduFile").click(function() {
			location.href = "<c:url value='/${educationId}/eduFile' />";
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table class="as">
	<tr>
		<th style="width: 12.5%">
			교육 분야
		</th>
		<th style="width: 12.5%">
			최대 인원	
		</th>
		<th style="width: 12.5%">
			강의 형태
		</th>
		<th style="width: 12.5%">
			강의 비용
		</th>
		<th style="width: 12.5%">
			시작 날짜
		</th>
		<th style="width: 12.5%">
			종료 날짜
		</th>
		<th style="width: 12.5%">
			강의 시작 시간
		</th>
		<th style="width: 12.5%">
			강의 종료 시간
		</th>
	</tr>
	<tr>
		<td>${education.educationCategory}</td>
		<td>${education.maxMember}</td>
		<td>${education.educationType}</td>
		<td>${education.cost}</td>
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
		<th>
			교육명
		</th>
		<th>
			커리큘럼
		</th>
	</tr>
	<tr>
		<td>${education.memberId}</td>
		<td>${education.educationLocation}</td>
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
		<td style="height: 300px;">${education.educationIntroduce}</td>
	</tr>
</table>
	<div style="text-align: left; display: inline;">
		<input type="button" id="eduFile" class="inputButton" value="강의자료게시판" />
		<input type="button" id="eduReport" class="inputButton" value="과제게시판" />
		<input type="button" id="eduQna" class="inputButton" value="QA 게시판" />
		<div style="float: right; display: inline;">
			<span style="margin: 5px;"> <a class="inputButton"
				href="<c:url value="/educationModify/${education.educationId}"/>">수정하기
			</a>
			</span> <a class="inputButton" href="<c:url value="/showEducationList"/>">목록
			</a>
		</div>
	</div>

</body>
</html>