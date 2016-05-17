<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육 이력</title>
</head>
<body>

<h3>현재 참가 중 교육</h3>
<table border="1">
<tr>
		<th>교육 명</th>
		<th>비용</th>
		<th>신청 날짜</th>
		<th>코멘트</th>
		<th>피드백</th>
		<th>교육 시작일</th>
		<th>교육 종료일</th>
	</tr>
	<c:forEach items="${ joinEducationList }" var="joinEducationVO">
		<tr>
			<td>${ joinEducationVO.educationTitle }</td>
			<td>${ joinEducationVO.cost }</td>
			<td>${ joinEducationVO.educationHistoryDate }</td>
			<td>${ joinEducationVO.cmnt }</td>
			<td>${ joinEducationVO.fdbk }</td>
			<td>${ joinEducationVO.startDate }</td>
			<td>${ joinEducationVO.endDate }</td>
		</tr>
	</c:forEach>	
</table>
<br/><br/>
<h3>교육 히스토리</h3>
<table border="1">
	<tr>
		<th>교육 명</th>
		<th>비용</th>
		<th>신청 날짜</th>
		<th>신청 상태</th>
		<th>코멘트</th>
		<th>피드백</th>
		<th>교육 시작일</th>
		<th>교육 종료일</th>
	</tr>
	<c:forEach items="${ educationHistoryListVO.educationHistoryList }" var="educationHistoryVO">
		<tr>
			<td>${ educationHistoryVO.educationTitle }</td>
			<td>${ educationHistoryVO.cost }</td>
			<td>${ educationHistoryVO.educationHistoryDate }</td>
			<td>
				<c:if test="${ educationHistoryVO.state eq 'GVUP_APLY' }">
					참가 포기
				</c:if>
				<c:if test="${ educationHistoryVO.state eq 'JOIN_APLY' }">
					참가 신청
				</c:if>
				<c:if test="${ educationHistoryVO.state eq 'JOIN_CMPL' }">
					참가 완료
				</c:if>
				<c:if test="${ educationHistoryVO.state eq 'CNCL_APLY' }">
					참가 취소
				</c:if>
			</td>
			<td>${ educationHistoryVO.cmnt }</td>
			<td>${ educationHistoryVO.fdbk }</td>
			<td>${ educationHistoryVO.startDate }</td>
			<td>${ educationHistoryVO.endDate }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="10">
			<form id="pagingForm">
				${ educationHistoryListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
			</form>
		</td>
	</tr>
	<tr>
		<td>
			<span><a href="<c:url value="/member/myPage/educationHistory/exportExel"/>" >다운로드</a></span>
		</td>
	</tr>
</table>

</body>
</html>