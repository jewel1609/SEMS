<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육참가 취소신청</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/> "></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#retractionBtn").click(function(){
			var retractionMessage = $("#retractionMessage").val();
			if ( retractionMessage == null || retractionMessage.length == 0 ) {
				alert("취소 사유를 작성해 주세요.");
				$("#retractionMessage").focus();
				return;
			}
			var retractionForm = $("#retractionForm");
			retractionForm.attr("method", "post");
			retractionForm.attr("action", "<c:url value="/education/doRetraction"/>");
			retractionForm.submit();
		});
	});
</script>
</head>
<body>

	<form id="retractionForm">
		<input type="hidden" name="educationId" value="${ educationId }">
		<textarea id="retractionMessage"name="retractionMessage"></textarea>
		<input type="button" id="retractionBtn" value="취소하기">
	</form>

</body>
</html>