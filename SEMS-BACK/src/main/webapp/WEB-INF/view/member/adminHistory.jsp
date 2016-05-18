<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function (){
		
		$("#searchBtn").click(function(){
			if ( $("#startDate") || $("#endDate") ) {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				
				if (startDate == "" || endDate == "") {
					
						if ( startDate == "" ) {
							alert("검색 시작일을 입력해주세요.");
							return;
						}
						if ( endDate == "" ) {
							alert("검색 종료일을 입력해주세요.");
							return;
						}
					}
				else if ( startDate > endDate ) {
					alert("검색일을 잘못 입력하셨습니다.");
					return;
				}
			}
				else {
					alert("검색 조건을 입력을 입력하세요.");
				}
				movePage('0');
		});
		
		$("#searchInitBtn").click(function() {
			
			location.href="<c:url value='/member/adminHistoryInit' />";
			
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>

<div id="admin">
	<h3>admin</h3>
	<form name="searchForm" id="searchForm">
	<table id="admin" border="1">
		<tr>
			<th>회원 ID</th>
			<th>로그인 IP</th>
			<th>로그인 시간</th>
			<th>로그아웃 시간</th>
			<th>회원 종류</th>
		</tr>
		<c:forEach items="${ loginHistoryListVO.loginHistoryList }" var="history">
			<tr>
				<td>${ history.id }</td>
			<td>${ history.lgiIp }</td>
			<td>${ history.lgiDt }</td>
			<td>${ history.lgoDt }</td>
			<td>${ history.memberType }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<div style="text-align:center;">
					<c:if test="${ loginHistoryListVO ne null }">
						${ loginHistoryListVO.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm") }
					</c:if>
				</div>
				<div style="text-align:right;">
					<input type="date" id="startDate" name="startDate" value="${ loginHistorySearchVO.startDate }">
					<input type="date" id="endDate" name="endDate" value="${ loginHistorySearchVO.endDate }">
					
					<input type="button" id="searchBtn" value="검색" />
					<input type="button" id="searchInitBtn" value="검색 초기화" />
				</div>
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>