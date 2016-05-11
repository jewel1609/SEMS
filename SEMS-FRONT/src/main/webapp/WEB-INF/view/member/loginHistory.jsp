<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery.min.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchBtn").click(function(){
			if($("#searchKeyWord").val() == "") {
				alert("검색어를 입력해 주세요.");
				return
			}
			movePage('0');
		});
		
	});
	

</script>
<title>로그인 히스토리 확인 페이지</title>
</head>
<body>
	<table style="margin-left: 30%;">
		<tr>
			<td>번호</td>
			<td>사용자 아이디</td>
			<td>IP주소</td>
			<td>로그인시간</td>
			<td>로그아웃시간</td>
		</tr>
		<c:forEach items="${loginHistoryListVO.loginHistoryList}"
			var="loginHistory">
			<tr>
				<td>${loginHistory.lgiHtrId}</td>
				<td>${loginHistory.id}</td>
				<td>${loginHistory.lgiIp}</td>
				<td>${loginHistory.lgiDt}</td>
				<td>${loginHistory.lgoDt}</td>
			</tr>
		</c:forEach>
	</table>
	<div align="center">
		<form id="pagingForm">${ LoginHistoryListVO.paging.getPagingList("pageNo","[@]","이전","다음","pagingForm") }
		</form>
	</div>
	<div align="center" style="margin-top: 50%;">
		<form id="searchForm">
			<select name="searchType">
				<c:if test="${loginHistoryVO.searchType eq '1'}">
					<option value="1" selected="selected">통합검색</option>
					<option value="2">IP</option>
					<option value="3">시간</option>
				</c:if>
				<c:if test="${loginHistoryVO.searchType eq '2'}">
					<option value="1">통합검색</option>
					<option value="2" selected="selected">IP</option>
					<option value="3">시간</option>
				</c:if>
				<c:if test="${loginHistoryVO.searchType eq '3'}">
					<option value="1">통합검색</option>
					<option value="2">IP</option>
					<option value="3" selected="selected">시간</option>
				</c:if>
			</select><input type="search" name="searchKeyWord"	id="searchKeyWord" value="${loginHistoryVO.searchKeyWord }"
				placeholder="#search" /> <input type="button" name="searchBtn"
				id="searchBtn" value="검색" />
		</form>
		<a href="/member/myPage/saveAsExcel">로그인 내역 엑셀파일로 저장</a>
	</div>
</body>
</html>