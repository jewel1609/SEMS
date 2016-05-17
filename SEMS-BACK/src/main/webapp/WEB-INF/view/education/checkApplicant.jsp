<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<td>교육명</td>
			<td>학생아이디</td>
			<td>신청자아이피</td>
			<td>상태변경일</td>
		</tr>
		<c:forEach items="${ eduHistoryListVO.educationHistoryList }" var="eduHistory">
			<c:if test="${ eduHistory.state eq 'JOIN_CMPL' }">
				<tr>
					<td>${eduHistory.educationTitle }</td>
					<td>${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
				</tr>
			</c:if>		
				
		</c:forEach>
		<tr>
			<td>
				${eduHistoryListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "")}
			</td>
		</tr>
	</table>
	


</body>
</html>