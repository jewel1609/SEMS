<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
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
			
			var mbrId = "<c:out value="${ oneQNABBSByAtcId.mbrId }"/>";
			var sessionId = "<c:out value="${ sessionId }"/>";
			
			if( mbrId == sessionId ) {
				alert("질문자가 답변을 등록하실 수 없습니다.");
				return;
			}
			
			var form = $("#replyWriteForm");
			form.attr("action", "<c:url value="/doWriteReply"/>");
			form.submit();
			alert("답변이 등록되었습니다.");
		});
		
		$(".adoptReplyBtn").click(function() {
			
			if ( confirm("답변을 채택하시겠습니까?") == true ) {
				alert("답변 채택 완료");
				return;
			}
			
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
	<c:if test="${ qnaReplyListVO.qnaReplyList.size() gt 0 }">
	<c:forEach items="${qnaReplyListVO.qnaReplyList}" var="qnaReplyList">
	<div>
		<span>
			작성자 : ${qnaReplyList.mbrId}
			
		</span><br/>
		<span> 
			날짜 : ${qnaReplyList.createdDate}
			<c:if test="${ oneQNABBSByAtcId.mbrId eq sessionId && qnaReplyList.mbrId ne sessionId }">
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" class="adoptReplyBtn" value="답변 채택" />
			</c:if> 
		</span><br/>
		<span>
			내용	: ${qnaReplyList.description} 
			
		</span><br/>
		<input type="button" id="likeBtn" value="추천"/> ${qnaReplyList.likeCnt}
		<input type="button" id="dislikeBtn" value="반대"/> ${qnaReplyList.dislikeCnt}
		
		점수 : ${qnaReplyList.qnaReplyPoint}
	</div><br/>
	</c:forEach>
	<form id="pagingForm">
	${qnaReplyListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm") }
	</form>
	
	</c:if>
	
	
	<form:form id="replyWriteForm" commandName="eduBBSReplyVO" method="post">
	<input type="hidden" name="atcId" value="${oneQNABBSByAtcId.atcId}"/>
	<textarea id="description" name="description" cols="40" rows="3" placeholder="내용을 입력하세요." >${eduBBSreplyVO.description}</textarea> 
	<br />
		<form:errors path="description" />
	<br />
	<input type="button" id="replyBtn" value="답변 쓰기"/>
	</form:form>
	<input type="button" id="listBtn" value="질문 리스트로"/>
	
</body>
</html>