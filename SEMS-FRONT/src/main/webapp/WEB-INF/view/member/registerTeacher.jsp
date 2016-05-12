<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>

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
	$(document).ready(function () {
		
		var isCheckedId = false;
		var isCheckedEmail = false;
		
		$("#id").blur(function () {
			if($("#id").val()=="") {
				$("#messageById").text("");
				return;
			}
			$.post("/checkValidationById", { "id" : $("#id").val() }, function(data) {
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageById").text("사용할 수 있는 아이디 입니다.").css("color", "green");
					isCheckedId = true;
				} else if (data == "EXIST") {
					$("#messageById").text("이미 사용중이거나 탈퇴한 회원입니다!").css("color", "red");
					isCheckedId = false;
				} else if (data == "NO") {
					$("#messageById").text("영문, 숫자 조합의 5글자 이상이어야 합니다!").css("color", "red");
					isCheckedId = false;
				}
			});
		});
		
		$("#id").focus(function () {
			$("#messageById").text("");
		});
		
		$("#password").blur(function () {
			$.post("/checkValidationByPassword", { "password" : $("#password").val() }, function(data) {
				if($("#password").val()=="") {
					$("#messageByPassword").text("");
					return;
				}
				
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageByPassword").text("안전한 비밀번호 입니다.").css("color", "green");
				} else if (data == "NO") {
					$("#messageByPassword").text("영문, 숫자, 특수문자 조합의 10~16 글자이어야 합니다!").css("color", "red");
				}
			});
			
			$("#repeatPassword").val("");
		});
		
		$("#password").focus(function () {
			$("#messageByPassword").text("");
		});
		
		$("#repeatPassword").blur(function () {
			$.post("/checkValidationByRepeatPassword", { "password" : $("#password").val(), "repeatPassword" : $("#repeatPassword").val() }, function(data) {
				if($("#repeatPassword").val()=="") {
					$("#messageByRepeatPassword").text("");
					return;
				}
				
				if (!data) {
					alert("통신 실패");
				} else if (data == "OK") {
					$("#messageByRepeatPassword").text("일치합니다.").css("color", "green");
				} else if (data == "NO") {
					$("#messageByRepeatPassword").text("비밀번호가 일치하지 않습니다.").css("color", "red");
				}
			});
		});
		
		$("#repeatPassword").focus(function () {
			$("#messageByRepeatPassword").text("");
		});
		
		$("#email").blur ( function () {
			if($("#email").val()=="") {
				$("#messageByEmail").text("");
				return;
			}
			$.post("/checkExistionByEmail", { "email" : $("#email").val() }, function(data) {
				if (!data) {
					alert("통신 실패");
				} else if (data == "NO") {
					$("#messageByEmail").text("사용할 수 있는 이메일 입니다.").css("color", "green");
					isCheckedEmail = true;
				} else if (data == "EXIST") {
					$("#messageByEmail").text("이미 사용중이거나 탈퇴한 회원입니다!").css("color", "red");
					isCheckedEmail = false;
				}
			});
		});
		
		$("#email").focus(function () {
			$("#messageByEmail").text("");
		});
		
		$("#registerButton").click( function () {
			
			if(isCheckedId == false) {
				alert("아이디를 확인해주세요.");
				return;
			}
			
			if(isCheckedEmail == false) {
				alert("이메일을 확인해주세요.");
				return;
			}
			
			var form = $("#registerForm");
			form.submit();
		});
		
		$('#years, #months, #days').change(function () {
			$("#birthDate").val();
			
			var target = document.getElementById("years");
			var date = "";
			var year = target.options[target.selectedIndex].text;
			date += year + "-";
			
			target = document.getElementById("months");
			var month = target.options[target.selectedIndex].text;
			if ( month.length == 1) {
				month = "0" + month;
			}
			date += month + "-";
			
			target = document.getElementById("days");
			var day = target.options[target.selectedIndex].text;
			if ( day.length == 1) {
				day = "0" + day;
			}
			date += day;
			
			$("#birthDate").val(date);
		});
		
	});
</script>

<script type="text/javascript">
	$(function () {
		for (i = new Date().getFullYear() ; i > 1900; i--) {
			$('#years').append($('<option />').val(i).html(i));
		}

		for (i = 1; i < 13; i++) {
			$('#months').append($('<option />').val(i).html(i));
		}
		updateNumberOfDays();
		
		var birthDate;
		<c:if test="${member.birthDate ne null}">
			birthDate = "${member.birthDate}";
		</c:if>
		var yyyy;
		var mm;
		var dd;
		
		if (birthDate != null && birthDate != "" ) {
			var splitBirthDate = birthDate.split("-");
			yyyy = splitBirthDate[0];
			mm = splitBirthDate[1];
			dd = splitBirthDate[2];
			
			try{
				yyyy = parseInt(yyyy);
				mm = parseInt(mm);
				dd = parseInt(dd);
			} catch (NumberFormatException) {
				yyyy = 2016;
				mm = 1;
				dd = 1;
			}
		}
		else {
			yyyy = 2016;
			mm = 1;
			dd = 1;
		}
		
		$("#years").val(yyyy).attr("selected", "selected");
		$("#months").val(mm).attr("selected", "selected");
		$("#days").val(dd).attr("selected", "selected");
		
		if ( mm < 10 ) {
			mm = "0" + mm;
		}
		
		if ( dd < 10 ) {
			dd = "0" + dd;
		}
		
		$("#birthDate").val(yyyy + "-" + mm + "-" + dd);
		
		$('#years, #months').change(function () {
			updateNumberOfDays();
		});
	});

	function updateNumberOfDays() {
		$('#days').html('');
		month = $('#months').val();
		year = $('#years').val();
		days = daysInMonth(month, year);

		for (i = 1; i < days + 1 ; i++) {
			$('#days').append($('<option />').val(i).html(i));
		}
	}

	function daysInMonth(month, year) {
		return new Date(year, month, 0).getDate();
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form id="registerForm" commandName="memberVO" method="post" action="/doRegisterAction">
		아이디 : <input type="text" id="id" name="id" value="${ member.id }" tabindex="1"/>
		<br/><span id="messageById"></span>
		<form:errors path="id" /><br/>
		
		비밀번호 : <input type="password" id="password" name="password" value="${ member.password }" tabindex="2" />
		<br/><span id="messageByPassword"></span>
		<form:errors path="password" /><br/>
		
		비밀번호 재확인: <input type="password" id="repeatPassword" tabindex="3" />
		<br/><span id="messageByRepeatPassword"></span><br/>
		
		이름 : <input type="text" id="name" name="name" value="${ member.name }" tabindex="4" />
		<br/><form:errors path="name" /><br/>
		
		이메일 : <input type="email" id="email" name="email" value="${ member.email }" tabindex="5" />
		<br/><span id="messageByEmail"></span>
		<form:errors path="email"/><br/>
		
		생년월일 : 
		<select id="years" name="years" tabindex="6"></select>&nbsp;년
		<select id="months" name="months" tabindex="7"></select>&nbsp;월
		<select id="days" name="days" tabindex="8"></select>&nbsp;일
		<input type="hidden" id="birthDate" name="birthDate" value="${ member.birthDate }" />
		<br/><form:errors path="birthDate" /><br/>
		
		전화 번호 : <input type="text" name="phoneNumber" value="${ member.phoneNumber }" tabindex="9"/>
		<br/><form:errors path="phoneNumber" /><br/>
		
		<input type="hidden" name="memberType" value="TR" />
		<input id="registerButton" class="inputButton" type="button" value="가입"/>
	</form:form>

</body>
</html>