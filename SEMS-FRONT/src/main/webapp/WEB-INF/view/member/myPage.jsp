<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="/backend/resources/css/layout.css" />
<script type="text/javascript">


</script>
<title>회원 정보</title>
</head>
<body>
		
<a href="<c:url value="/member/myPage/checkPassword"/>">내 정보 수정</a>		

<a href="<c:url value="/member/myPage/resignMember" />">삭제</a>

</body>
</html>