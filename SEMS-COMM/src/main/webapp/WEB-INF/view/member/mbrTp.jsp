<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head> 
<body>
회원정보페이지
<table border="2">
		<tr>
			<th>코드 아이디</th>
			<th>회원 등급</th>
		</tr>

		<c:forEach items="${mbrTpVOList}" var="mbrTp">
			<tr>
				<td>${mbrTp.cdId}</td>
				<td>${mbrTp.cdNm}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>