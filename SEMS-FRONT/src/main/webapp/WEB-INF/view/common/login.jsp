<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery.min.js" ></script>
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
			
			$.post("/login", $("#loginForm").serialize(), function(data) {
				if(data == "OK") {
					alert("로그인이 완료되었습니다. 페이지를 새로고침합니다.");
					location.href="/main";
				} else if (data == "NO") {
					alert("로그인이 실패했습니다. 아이디 혹은 비밀번호를 확인해 주세요.");
					$("#id").focus();
				} else if (data == "OVER") {
					alert("로그인이 지속 실패하여, 계정이 잠겼습니다. 운영자에게 문의하세요!");
					$("#id").focus();
				} else if (data == "CNGPW") {
					alert("비밀번호를 설정한지 30일이 지났습니다. 비밀번호를 변경해주세요!");
					location.href="/changePassword";
					$("#id").focus();
				} else if (data == "RSN") {
					alert("탈퇴한 회원입니다. 60일 뒤에 같은 아이디로 가입 가능합니다.");
					$("#id").focus();
				}
			});
		});
		
		$(".registButton").click(function() {
			location.href = "/register/policy";
		});
	});
</script>
<div class="login">
	<div class="wrapper">
		<form id="loginForm" name="loginForm">
			<input type="text" name="id" id="id" placeholder="ID" />
			<input type="password" name="password" id="password" placeholder="Password" />
			<span class="button loginButton" style="cursor: pointer;">Login</span>
			<span class="button registButton" style="cursor: pointer;">Sign Up</span>
		</form>
	</div>
</div>
