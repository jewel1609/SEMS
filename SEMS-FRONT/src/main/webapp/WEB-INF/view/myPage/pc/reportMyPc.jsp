<!-- 이기연 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		var isCheckedEct = false;
		var isCheckedReportedCategory = false;
		var isCheckedReportedComment = false;
		
		if(isCheckedEct == false) {
			$("#ectText").hide();
		}

		if ($("#reportedCategory").val() == "ect") {
	 		isCheckedEct = true;
	 		$("#ectText").show();
		}
		
		$("#reportPcBtn").click(function() {
 		 	if( $("#reportedCategory").val() == null || $("#reportedCategory").val() == "default") {
				alert("고장난 항목을 고르세요.")
				$("#reportedCategory").focus();
				return;
			}
				
			if( $("#reportedComment").val() == "") {
				alert("내용을 입력하세요.")
				$("#reportedComment").focus();
				return;
			}
			
			alert("신고버튼 눌렀습니다.");
			window.open("about:blank", "_self").close();
			
		});
		
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>PC ${pcId}번 고장 신고</h1>

<form:form id="reportPc" commandName="reportedPcVO" method="post"
actoin="/myPc/reportPage/report/">
<table border="1">
	<tr>
		<th>고장난 항목</th>
		<th>고장이 난 부분(상세기록)</th>
	</tr>
	
	<tr>
		<td>
			<select id="reportedCategory">
				<option value="default">선택</option>
				<option value="computer">컴퓨터</option>
				<option value="keyboard">키보드</option>
				<option value="mouse">마우스</option>
				<option value="desk">책상</option>
				<option value="ect">기타</option>
				<input type="text" id="ectText" /> 
		</td>
		<td>
			<textarea id="reportedComment" rows="20" cols="50"></textarea>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="button" id="reportPcBtn" value="신고하기" /> 
		</td>
	</tr>
</table>
</form:form>
</body>
</html>