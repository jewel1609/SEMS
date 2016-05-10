<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$(".login .loginButton").click(function() {
			if( $("#id").val() == "" ) {
				alert("ID를 입력하세요!");
				$("#id").focus();
				return;
			}
			if( $("#password").val() == "" ) {
				alert("Password를 입력하세요!");
				$("#password").focus();
				return;
			}
			
			$.post("/HuCloud/member/login", $("#loginForm").serialize(), function(data) {
				if(data == "OK") {
					alert("로그인이 완료되었습니다. 페이지를 새로고침합니다.");
					location.href="/SEMS-FRONT/첫 페이지";
				} else if (data == "NO") {
					alert("로그인이 실패했습니다. 아이디 혹은 비밀번호를 확인해 주세요.");
					$("#id").focus();
				} else if (data == "OVER") {
					alert("로그인이 지속 실패하여, 계정이 잠겼습니다. 운영자에게 문의하세요!");
				} else if (data == "CNGPW") {
					alert("비밀번호를 설정한지 30일이 지났습니다. 비밀번호를 변경해주세요!");
					location.href="/SEMS-FRONT/changePassword";
				}
			});
			
		});
	});
</script>
</body>
</html>