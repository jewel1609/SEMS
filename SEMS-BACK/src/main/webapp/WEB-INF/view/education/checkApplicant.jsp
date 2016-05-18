<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
$(document).ready( function() {

	$("#searchInitBtn").click(function() {
		location.href="<c:url value='/checkEduApplicant' />";
	});
	$("#searchBtn").click( function() {
		
		if( $("#search option:selected").val() == "educationName"){
			if ($("#searchKeyword").val() == ""){
				alert("검색어를 입력하세요!");
				return;
			}
		}
		movePage('0');
	});
});
</script>
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
			<c:if test="${ eduHistory.state eq 'EDU_JN_C' }">
				<tr>
					<td>${eduHistory.educationTitle }</td>
					<td>${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
				</tr>
			</c:if>		
				
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ eduHistoryListVO ne null }">
							${eduHistoryListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
					<div style="text-align: right;">
						<select id="search" name="search">
							<option id="educationName" value="educationName">교육명</option>
						</select>
						
						<input type="text" id="searchKeyword" name="searchKeyword" value="${ eduHistorySearchVO.searchKeyword }"/>
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>	
	</table>
	


</body>
</html>