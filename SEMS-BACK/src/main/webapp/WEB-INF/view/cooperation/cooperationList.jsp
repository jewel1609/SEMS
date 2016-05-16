<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>협력사 리스트</title>
</head>
<body>
	
	<table border="1" style="text-align: center; border-collapse: collapse;">
		<tr>
			<th>COO_ID</th>
			<th>협약사 명</th>
			<th>협약사 소재지</th>
			<th>사업자 번호</th>
			<th>대표 명</th>
			<th>담당자 전화번호</th>
			<th>회사 전화번호</th>
			<th>담당자 이메일</th>
			<th>협력사/협약사 선택지</th>
		</tr>
		<c:forEach items="${cooperationListVO.cooperationList }" var="cooperation">
		<tr>
			<td>${cooperation.cooperationId }</td>
			<td><a href="<c:url value='/cooDetail/${cooperation.cooperationId }' />">${cooperation.cooperationTitle }</a></td>
			<td>${cooperation.cooperationLocation }</td>
			<td>${cooperation.cooperationNumber }</td>
			<td>${cooperation.representativeName }</td>
			<td>${cooperation.managerPhoneNumber }</td>
			<td>${cooperation.cooperationPhoneNumber }</td>
			<td>${cooperation.managerEmail }</td>
			<td>${cooperation.cooperationType }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="9">
			<form id="pagingForm">
				${cooperationListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
			<span style="float:right;"><a href="<c:url value='/registerCoo' />">글쓰기</a></span>
			</form>
			</td>
		</tr>
	</table>
	
</body>
</html>