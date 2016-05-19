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
		<th>educationHistoryId</th>
		<th>educationId</th>
		<th>memberId</th>
		<th>educationHistoryDate</th>
		<th>state</th>
		<th>ip</th>
		<th>comment</th>
		<th>feedback</th>
		<th>startDate</th>
		<th>endDate</th>
		<th>DROP</th>
	</tr>
	<c:forEach items="${ educationHistoryListVO.educationHistoryList }" var="educationHistoryVO">
		<tr>
			<td>${ educationHistoryVO.educationHistoryId }</td>
			<td>${ educationHistoryVO.educationId }</td>
			<td>${ educationHistoryVO.memberId }</td>
			<td>${ educationHistoryVO.educationHistoryDate }</td>
			<td>${ educationHistoryVO.state }</td>
			<td>${ educationHistoryVO.ip }</td>
			<td>${ educationHistoryVO.cmnt }</td>
			<td>${ educationHistoryVO.fdbk }</td>
			<td>${ educationHistoryVO.startDate }</td>
			<td>${ educationHistoryVO.endDate }</td>
			<td><a href="<c:url value='/resignCourse/${educationHistoryVO.educationId}'/>" target="_blank" 
			onclick="window.open(this.href, 'popupName', 'width=800, height=500, left=50, top=50, statusbar=0, scrollbars=1'); return false;" 
			onkeypress="this.onclick(); return false;" >DROP</a>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="10">
			<form id="pagingForm">
				${ educationHistoryListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
			</form>
		</td>
	</tr>
</table>

</body>
</html>