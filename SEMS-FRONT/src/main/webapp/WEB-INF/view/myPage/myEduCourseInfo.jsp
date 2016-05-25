<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 게시판</title>
</head>
<body>


<div>
	<span>현재 수강 목록</span>
		<table>
			<tr>
				<th>educationId</th>
				<th>educationTitle</th>
				<th>memberId</th>
				<th>status</th>
				<th>startDate</th>
				<th>endDate</th>
		
			</tr>
			<c:forEach items="${ educationListVO.educationList }" var="educationVO">
				<tr>
					<td>${ educationVO.educationId }</td>
					<td>${ educationVO.educationTitle }</td>
					<td>${ educationVO.memberId }</td>
					<td>${ educationVO.status }</td>
					<td>${ educationVO.startDate }</td>
					<td>${ educationVO.endDate }</td>
					<td><a href="<c:url value='/resignCourse/${educationVO.educationId}'/>" target="_blank" 
					onclick="window.open(this.href, 'popupName', 'width=800, height=500, left=50, top=50, statusbar=0, scrollbars=1'); return false;" 
					onkeypress="this.onclick(); return false;" >DROP</a>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10">
					<form id="pagingForm">
						${ educationListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
					</form>
				</td>
			</tr>
		</table>
</div>

<jsp:include page="/WEB-INF/view/myPage/myPreEduCourseInfo.jsp"></jsp:include>

</body>
</html>