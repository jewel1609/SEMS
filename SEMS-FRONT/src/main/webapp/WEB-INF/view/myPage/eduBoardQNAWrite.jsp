<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#cancel").click(function() {
			
			location.href = "<c:url value='/eduBoard/QNAList' />";
		});
			
		$("#qnaWriteBtn").click(function() {
			var form = $("#qnaWriteForm");
			form.attr("action", "<c:url value="/eduBoard/doQNAWrite" />");
			form.submit();
			alert("질문이 등록되었습니다.");
		});
		
	});

</script>
<title>질문/답변 질문하기</title>
</head>
<body>
	
	<form:form id="qnaWriteForm" commandName="eduBBS" method="post">
		제목  <br/><input type="text" id="title" name="title" size="38"/> <br/><br/>
		내용  <br/><textarea id="contents" name="contents" cols="40" rows="10" ></textarea> <br/>
	 
	
		<input type="button" id="qnaWriteBtn" value="질문하기" /> 
		<input type="button" id="cancel" value="취소" />
	</form:form>
</body>
</html>