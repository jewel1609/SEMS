<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#listBtn").click(function() {
			location.href="<c:url value='/eduBoard/QNAList'/>";
		});
		
		$("#replyBtn").click(function() {
			
			if( $("#description").val() == "" ) {
				alert("내용을 작성하세요.");
				return;
			}
			
			var form = $("#replyWriteForm");
			form.attr("action", "<c:url value="/doWriteReply"/>");
			form.submit();
			alert("댓글이 등록되었습니다.");
		});
		
		
	});
</script>
<title>QNA 상세페이지</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>질문 번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자</td>
		</tr>
		<tr>
			<td>${oneQNABBSByAtcId.atcId}</td>
			<td>${oneQNABBSByAtcId.title}</td>
			<td>${oneQNABBSByAtcId.contents}</td>
			<td>${oneQNABBSByAtcId.mbrId}</td>
		</tr>
	</table>
	
	
	<br/><br/>
	
	<form:form id="replyWriteForm" commandName="eduBBSReplyVO" method="post">
	<input type="hidden" name="atcId" value="${oneQNABBSByAtcId.atcId}"/>
	<textarea id="description" name="description" cols="40" rows="3" placeholder="내용을 입력하세요." >${eduBBSreplyVO.description}</textarea> 
	<br />
		<form:errors path="description" />
	<br />
	<input type="button" id="replyBtn" value="댓글 쓰기"/>
	</form:form>
	<input type="button" id="listBtn" value="질문 리스트로"/>
	
</body>
</html>