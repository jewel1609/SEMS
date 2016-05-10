<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>졸업 구분 코드 설정 페이지</title>
</head>
<body>

	<form method="post" action="/insertGrdtCd">
	
		<table>
			<th>Code Id</th>
			<th>Code Name</th>
			
			<c:forEach items="${grtdTpList}" var="graduation">
				<tr>
					<td>${graduation.cdId}</td>
					<td><input type="text" class="cdNm" name="cdNm" value="${graduation.cdNm}"/></td>
					<td><input type="button" value="수정"/></td>
					<td><input type="button" value="삭제"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="text" id="cdId" name="cdId" value=""/></td>
				<td><input type="text" id="cdNm" name="cdNm" value=""/></td>
			</tr>
		</table>
		<input type="button" value="추가" />
		<input type="reset" value="취소" />
	</form>
	
	
</body>
</html>