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
			
			$.post("<c:url value='/member/doResign'/>", $("#loginForm").serialize(), function(data) {
				if(data == "OK") {
					alert("아이디를 삭제합니다.");
					location.href="/main";
				} else if (data == "NO") {
					alert("로그인이 실패했습니다. 아이디 혹은 비밀번호를 확인해 주세요.");
					$("#password").focus();
				} else if (data == "OVER") {
					alert("로그인이 지속 실패하여, 계정이 잠겼습니다. 운영자에게 문의하세요!");
					$("#password").focus();
				} else if (data == "CNGPW") {
					alert("비밀번호를 설정한지 30일이 지났습니다. 비밀번호를 변경해주세요!");
					location.href="/changePassword";
					$("#password").focus();
				} else if (data == "RSN") {
					alert("탈퇴한 회원입니다. 60일 뒤에 같은 아이디로 가입 가능합니다.");
					$("#password").focus();
				}
			});
		});
		
		$(".register .registButton").click(function() {
			location.href = "/comm/register";
		});
	});
</script>
<div class="login">
	<div class="wrapper">
		탈퇴하시려면 비밀번호를 입력하세요. <br />
		<form id="loginForm" name="loginForm">
			ID : ${id}<br />
			<input type="hidden" name="resignCode" value="${resignCode}" />
			<input type="hidden" name="id" id="id" value="${id}" />
			<input type="password" name="password" id="password" placeholder="Password" />
			<span class="button loginButton" style="cursor: pointer;">Login</span>
		</form>
	</div>
</div>
