<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#checkPasswordBtn").click(function() {
		if($("#password").val() == "") {
			alert("비밀번호를 입력하세요!");
			$("#password").focus();
			return;
		}
		
		//세번 틀렸을 시 자동 로그아웃 처리
		
		$("#checkPasswordForm").submit();
	});	
	
});

</script>
</head>
<body>
비밀번호 확인하기

<form id="checkPasswordForm" name="checkPasswordForm" method="post" action="/member/myPage/modify" >

	<input type="text" name="password" id="password" size="50" placeholder="비밀번호를 입력하세요" />
	<input type="button" id="checkPasswordBtn" value="확인" />

</form>

</body>
</html>