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

			if (beginDate != "" || closeDate != "") {
				var beginDate = $("#beginDate").val();
				beginDate = $.trim(beginDate);
				if (beginDate == "") {
					alert("검색시작일을 지정해주세요.");
					$("#beginDate").focus();
					return;
				}
				var closeDate = $("#closeDate").val();
				closeDate = $.trim(closeDate);
				if (closeDate == "") {
					alert("검색 마지막일을 지정해주세요.");
					$("#closeDate").focus();
					return;
				}
			}

			var beginDate = $("#beginDate").val();
			beginDate = $.trim(beginDate).replace("/", " ");
			var closeDate = $("#closeDate").val();
			closeDate = $.trim(beginDate).replace("/", " ");

			if (beginDate > closeDate) {
				alert("검색 설정 날짜가 잘못 되었습니다. 다시 셋팅해주세요");
				$("#beginDate").focus;
				return;
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
			<td>IP확인요청하기</td>
		</tr>
		<c:forEach items="${loginHistoryListVO.loginHistoryList}"
			var="loginHistory">
			<tr>
				<td>${loginHistory.lgiHtrId}</td>
				<td>${loginHistory.id}</td>
				<td>${loginHistory.lgiIp}</td>
				<td>${loginHistory.lgiDt}</td>
				<td>${loginHistory.lgoDt}</td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
	<div align="center">
		<form id="pagingForm">${loginHistoryListVO.paging.getPagingList("pageNo","[@]","이전","다음","pagingForm")}
		</form>
	</div>

	<form:form commandName="loginHistorySearchVO" method="post" id="loginHistorySearchForm">
		<div align="center" style="margin-top: 150px;">
			<input type="date" name="beginDate" id="beginDate" value="${loginHistorySearchVO.beginDate}" />
			<form:errors path="beginDate" />
			<input type="date" name="closeDate" id="closeDate" value="${loginHistorySearchVO.closeDate}" />
			<form:errors path="closeDate" />
			<input type="submit" name="searchBtn" name="searchBtn" value="검색" />
		</div>
	</form:form>
	<a href="<c:url value="/member/myPage/saveAsExcel"/>">로그인 내역 엑셀파일로
		저장</a>
</body>
</html>