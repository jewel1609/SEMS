<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#searchBtn").click(function() {
			
			var beginDate = $("#beginDate").val();
			var closeDate = $("#closeDate").val();
				
			if (beginDate == "" || closeDate == "") {
				// 검색 기간 입력 되지 않은 경우
				if (beginDate == "") {
					alert("검색시작일을 지정해주세요.");
					$("#beginDate").focus();
					return;
				}
				
				if (closeDate == "") {
					alert("검색 마지막일을 지정해주세요.");
					$("#closeDate").focus();
					return;
				}
			} 
			else{
				// 검색 기간 입력 되었지만
				// 검색 시작일이 더 클 경우
				if(beginDate > closeDate){
					alert("검색 기간이 잘못 설정되었습니다.");
					return;
				}
			}
		
			movePage('0');
			
		});
		
		$("#searchInitBtn").click(function() {
			
			location.href="<c:url value='/member/loginHistoryInit' />";
			
		});
	});
</script>
<title>로그인 히스토리 확인 페이지</title>
</head>
<body>
	<table align="center" border="1">
		<tr>
			<th>번호</th>
			<th>사용자 아이디</th>
			<th>IP주소</th>
			<th>로그인시간</th>
			<th>로그아웃시간</th>
			<th>IP 확인 요청</th>
		</tr>
		<c:forEach items="${loginHistoryListVO.loginHistoryList}"
			var="loginHistory">
			<tr align="center">
				<td>${loginHistory.lgiHtrId}</td>
				<td>${loginHistory.id}</td>
				<td>${loginHistory.lgiIp}</td>
				<td>${loginHistory.lgiDt}</td>
				<td>${loginHistory.lgoDt}</td>
				<td><a href="<c:url value="/member/doRequestIpHistory/${loginHistory.lgiHtrId}"/>">요청</a></td>
			</tr>
		</c:forEach>
	</table>
	<div align="center">
		<form id="pagingForm">
		<div align="center">
			${loginHistoryListVO.paging.getPagingList("pageNo","[@]","이전","다음","pagingForm")}
		</div>
		<div align="center">
			<input type="date" name="beginDate" id="beginDate" value="${loginHistorySearchVO.beginDate}" />
			<input type="date" name="closeDate" id="closeDate" value="${loginHistorySearchVO.closeDate}" /> 
			<input type="button" id="searchBtn" name="searchBtn" value="검색" />
			<input type="button" id="searchInitBtn" name="searchInitBtn" value="초기화" />
			
		</div>
		</form>
	</div>

	<a href="<c:url value="/member/myPage/saveAsExcel"/>">엑셀 파일로 저장
	
</body>
</html>