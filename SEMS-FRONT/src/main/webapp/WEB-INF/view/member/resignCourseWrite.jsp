<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".resignButton").click(function() {
			if( $("#courseDropReason").val() == "" ) {
				alert("사유를 입력하세요!");
				$("#courseDropReason").focus();
				return;
			}
			
/* 		$("#dropForm").attr("action", "<c:url value="/dropCourseApply/${educationHistory.educationId}"/>");
		$("#dropForm").attr("method", "POST");
		$("#dropForm").submit(); */
		
		$.post("<c:url value='/dropCourseApply/${educationHistory.educationId}'/>", $("#dropForm").serialize(), function(data) {
			if(data == "OK") {
				alert("수강포기 신청이 완료되었습니다.");
				window.close();
			}
		});
		
		});
		
		$(".login .loginButton, .login .registButton").keypress(function (e) {
            if (e.keyCode === 13) {
                $(this).click();
            }
        });
	});
</script>
<body>


	강의포기 신청서  <br />
	memberId : ${educationHistory.memberId }	<br />
	startDate : ${educationHistory.startDate }	<br />
	endDate : ${educationHistory.endDate }	<br />
	educationTitle : ${educationHistory.educationTitle }	<br />
	<form id="dropForm" name="dropForm">
		cmnt : <input type="text" id="courseDropReason" name="courseDropReason" /> 
		<input type="hidden" id="educationId" name="educationId" />
		<span class="button resignButton" style="cursor: pointer;">포기신청</span>
	</form>

</body>
</html>