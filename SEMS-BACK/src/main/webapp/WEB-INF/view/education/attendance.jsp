<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>출결 조회</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchBtn").click(function(){
			movePage('0');
		});
		
		$("#searchType").change(function(){
			var searchType = $(this).val();
			if ( searchType == '1' ) {
				$("#trainee").show();
				$("#trainee").siblings().hide();
			}
			else if ( searchType == '2' ) {
				$("#education").show();
				$("#education").siblings().hide();
			}
			else if ( searchType == '3') {
				$("#team").show();
				$("#team").siblings().hide();
			}
		});
	});
</script>
</head>
<body>
	<table>
		<tr>
			<td>날짜</td>
			<td>이름</td>
			<td>교육 이름</td>
			<td>출석 시간</td>
			<td>퇴근 시간</td>
		</tr>
		<c:forEach items="${ attendanceListVO.attendanceList }" var="attendanceVO">
			<tr>
				<td>${ attendanceVO.attendanceTime }</td>
				<td>${ attendanceVO.memberName }</td>
				<td>${ attendanceVO.educationTitle }</td>
				<td>${ attendanceVO.attendanceTime }</td>
				<td>${ attendanceVO.attendanceTime }</td>
			</tr>
		</c:forEach>
	</table>
	<form id="searchForm">
		${attendanceListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
		<select id="searchType" name="searchType">
			<option value="1" selected>교육생</option>
			<option value="2">교육</option>
			<option value="3">팀</option>
		</select>
		<div style="display: inline;">
			<select id="trainee" name="memberId">
				<c:if test="${ eduTrainees eq null or eduTrainees.size() eq 0 }">
				<option disabled="disabled">선택할 교육생이 없습니다.</option>
				</c:if>
				<c:if test="${ eduTrainees ne null or eduTrainees.size() gt 0 }">
				<c:forEach items="${ eduTrainees }" var="trainee">
				<option value="${ trainee.id }">${ trainee.name }</option>
				</c:forEach>
				</c:if>
			</select>
			<select id="education" name="educationId" style="display: none;">
				<c:if test="${ educations eq null or educations.size() eq 0 }">
				<option disabled="disabled">선택할 교육이 없습니다.</option>
				</c:if>
				<c:if test="${ educations ne null or educations.size() gt 0 }">
				<c:forEach items="${ educations }" var="education">
				<option value="${ education.educationId }">${ education.educationTitle }</option>
				</c:forEach>
				</c:if>
			</select>
			<select id="team" name="teamId" style="display: none;">
				<c:if test="${ eduTeams eq null or eduTeams.size() eq 0 }">
				<option disabled="disabled">선택할 팀이 없습니다.</option>
				</c:if>
				<c:if test="${ eduTeams ne null or eduTeams.size() gt 0 }">
				<c:forEach items="${ eduTeams }" var="team">
				<option value="${ team.id }">${ team.name }</option>
				</c:forEach>
				</c:if>
			</select>
		</div>
		<input type="button" id="searchBtn" value="검색">
	</form>
</body>
</html>