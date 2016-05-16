<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
}
</style>
<script type="text/javascript">

	$(document).ready( function () {
		$("#closeButton").click(function)
	});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="position: relative; text-align: center;">
		정말로 비밀번호를 변경하시겠습니까?
		<br/>
		비밀번호는 회원의 메일로 변경된 값이 전송 되어집니다.
		<br/>
		<br/>
		<input id="sendPasswordButton" class="inputButton" type="button" value="Yes"/>
		<input id="closeButton" class="inputButton" type="button" value="No"/>
	</div>
</body>
</html>