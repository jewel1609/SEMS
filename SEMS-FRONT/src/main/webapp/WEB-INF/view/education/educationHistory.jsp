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

<table>
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
			<td>${ educationHistoryVO.state }</td>
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