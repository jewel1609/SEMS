<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/member/myPage/saveAsExcel">로그인 내역 엑셀파일로 저장</a>	
	
	<table>
		<tr>
			<td>LGI_HTR_ID</td>
			<td>MBR_ID(FK)</td>
			<td>LGI_IP</td>
			<td>LGI_DT</td>
			<td>LGO_DT</td>
		</tr>
		<c:forEach var="${LoginHistoryListVO.loginHistoryList}" items="loginHistory">
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
		<form id="pagingForm">
			${ LoginHistoryListVO.paging.getPagingList("pageNo","[@]","이전","다음","pagingForm") }
		</form>
	</div>
	<div>
		<input type="search" name="search" />
	</div>


</body>
</html>