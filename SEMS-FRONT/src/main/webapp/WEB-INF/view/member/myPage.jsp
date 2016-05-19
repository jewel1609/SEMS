<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">


</script>
<title>회원 정보</title>
</head>
<body>

	<table border="1" style="border-collapse: collapse; text-align: center;">
		<c:forEach items="${menuList }" var="menuList" >
			<tr>
				<td><a href="<c:url value='/member/${menuList.url }' />">${menuList.codeName }</a></td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>