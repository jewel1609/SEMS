<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
</style>
<title>강사 상세정보</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$("#deleteBtn").click(function(){
			if (confirm("정말로 삭제하시겠습니까?")) {
				location.href = "<c:url value='/teacherDelete/${ teacherInfo.memberId}'/>";
			}
		});
		
		$("#updateBtn").click(function(){
			location.href = "<c:url value='/teacherModify/${ teacherInfo.memberId}'/>";
		});
	});
</script>
</head>
<body>
	<span>강사 정보</span>
	<table border=1 style="width: 100%">
		<tr>
			<th style="text-align: center;">강사 아이디</th>
			<th style="text-align: center;">강사 명</th>
			<th style="text-align: center;">소속 업체</th>
			<th style="text-align: center;">사업자 번호</th>
			<th style="text-align: center;">연차</th>
			<th style="text-align: center;">평점</th>
		</tr>
		<tr>
			<td>${ teacherInfo.memberId }</td>
			<td>${ teacherInfo.name }</td>
			<td>${ teacherInfo.companyName }</td>
			<td>${ teacherInfo.businessNumber }</td>
			<td>${ teacherInfo.annual }</td>
			<td>${ teacherEducationGrade }</td>
		</tr>
	</table>
	<br/><br/>
	<span>강의 이력</span>
	<table border=1 style="width: 100%">
		<tr>
			<th style="text-align: center;">강의 아이디</th>
			<th style="text-align: center;">강의 명</th>
			<th style="text-align: center;">강의 시작날짜</th>
			<th style="text-align: center;">강의 종료날짜</th>			
			<th style="text-align: center;">기관명</th>
		</tr>
		<c:forEach items="${ teacherEducationHistory }" var="educationHistory">
			<tr>
				<td>${ educationHistory.id }</td>
				<td>${ educationHistory.educationName }</td>
				<td>${ educationHistory.startDate }</td>
				<td>${ educationHistory.endDate }</td>
				<td>${ educationHistory.educationLocation }</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<span>프로젝트 이력</span>
	<table border=1 style="width: 100%">
		<tr>
			<th style="text-align: center;">프로젝트 아이디</th>
			<th style="text-align: center;">프로젝트 명</th>	
			<th style="text-align: center;">프로젝트 시작날짜</th>
			<th style="text-align: center;">프로젝트 종료날짜</th>					
			<th style="text-align: center;">프로젝트 장소</th>
		</tr>
		<c:forEach items="${ teacherProjectHistory }" var="projectHistory">
			<tr>
				<td>${ projectHistory.id }</td>
				<td>${ projectHistory.projectName }</td>
				<td>${ projectHistory.startDate }</td>
				<td>${ projectHistory.endDate }</td>
				<td>${ projectHistory.projectLocation }</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<table border=1 style="width: 100%">
		<tr>
			<th style="text-align: center;">책 아이디</th>
			<th style="text-align: center;">책 이름</th>	
			<th style="text-align: center;">출판사</th>
		</tr>
		<c:forEach items="${ teacherBook }" var="book">
			<tr>
				<td>${ book.id }</td>
				<td>${ book.bookName }</td>
				<td>${ book.bookCompany }</td>
			</tr>
		</c:forEach>
	</table>
	<div style="float: left;">
		<input type="button" style="float: left; cursor: pointer;" id="deleteBtn" class="inputButton" value="삭제" />
		<input type="button" style="float: right; cursor: pointer;" id="updateBtn" class="inputButton" value="수정" />
	</div>	
</body>
</html>