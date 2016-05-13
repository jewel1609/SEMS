<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#applyEdu").click(function(){
			$.post("<c:url value='/doApplyEducation'/>"
					, { "educationId" : $("#eduId").val(),
						"educationType" : $("#eduType").val() }
					, function(data){
						if (data == "OK") {
							alert("신청완료!");
							location.href="<c:url value='/educationList'/>";
							document.getElementById("#applyEdu").disabled=true;
						}
						else if( data == "FAIL"){
							alert("주간/야간 교육은 각 하나씩 신청할 수 있습니다. 이미 신청하신 교육타입(주간/야간)입니다.");
							location.href="<c:url value='/educationList'/>";
						}
					});
		});
		
		$("#cancleEdu").click(function(){
			var educationId = $("#eduId").val();
			location.href = "<c:url value='/doCancelEducation/"+ educationId +"'/>";
		});
		
		$("#goList").click(function(){
			location.href="<c:url value='/educationList'/>";
		});
		
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
</script>
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<th colspan="2">교육 상세 조회</th>
		</tr>
		<tr>
			<td>강의 아이디</td>
			<td>${ education.educationId }</td>
		</tr>
		<tr>
			<td>교육 카테고리</td>
			<td>${ education.educationCategory }</td>
		</tr>
		<tr>
			<td>교육명</td>
			<td>${ education.educationTitle }</td>
		</tr>
		<tr>
			<td>강사명</td>
			<td>${ education.memberId }</td>
		</tr>
		<tr>
			<td>정원</td>
			<td>${ education.maxMember }</td>
		</tr>
		<tr>
			<td>강의장소</td>
			<td>${ education.educationLocation }</td>
		</tr>
		<tr>
			<td>커리큘렴 파일</td>
			<td>${ education.educationCurriculum }</td>
		</tr>
		<tr>
			<td>강의 소개</td>
			<td>${ education.educationIntroduce }</td>
		</tr>
		<tr>
			<td>강의 시작 날짜</td>
			
			<td>${ education.startDate }</td>
		</tr>
		<tr>
			<td>강의 종료 시간</td>
			<td>${ education.endDate }</td>
		</tr>
		<tr>
			<td>강의 형태</td>
			<td>${ education.typeName }</td>
		</tr>
		<tr>
			<td>유료/무료</td>
			<td>${ education.costName }</td>
		</tr>
	</table>
	<br/>
	<input type="button"  value="검색 목록" id="goList" name="goList" />
	<br/>
	<br/>
	<h3>문의사항</h3>
	<c:if test="${ eduReplyListVO.qnaList.size() gt 0 }">
	<table border="1">
	<c:forEach items="${eduReplyListVO.qnaList}" var="qna">
	<tr>
		<td colspan="2">작성자</td>
		<td colspan="2">${ qna.mbrId }</td>
		<td>내용</td>
		<td colspan="2">${ qna.description }</td>
		
	</tr>
	<tr>
		<td>좋아요</td>
		<td>${ qna.likeCnt }
		<td>싫어요</td>
		<td>${ qna.dislikeCnt }</td>
		<td>날짜</td>
		<td>${ qna.createdDate } </td>
	</tr>
	</c:forEach>
	 <tr>
		<td colspan="6"  align="center">
			<form id="pagingForm">
					<c:if test="${ eduReplyListVO ne null }">
						${eduReplyListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "pagingForm")}
					</c:if>
			</form>
		</td>
	</tr>
	</table>
	</c:if>
	<br/>
	<form:form commandName="qnaVO" method="post" action="/doWriteComment">
	<input type="hidden" name="educationId" value="${ education.educationId }">	
	<textarea id="description" name="description" cols="51" rows="5" placeholder="내용을 입력하세요." >${qnaVO.description}</textarea><br/>
	<form:errors path="description"/><br/>
	<input type="submit" value="댓글쓰기"/>
	</form:form>
	<br/>
	<input type="hidden" value="${ education.educationId }" id="eduId" />
	<input type="hidden" value="${ education.educationType }" id="eduType" />
	
	<c:if test="${ isApply }">
	<input type="button" id="applyEdu" name="applyEdu" value="교육 참가 신청" />
	</c:if>
	
	<c:if test="${ !isApply }">
	<input type="button" id="cancleEdu" name="cancleEdu" value="교육 참가 취소" />
	</c:if>
	<br/>
	
	<c:forEach items="${fileList}" var="fileVO">
	파일다운로드: <a href="/downloadFile/${ education.educationId }">${fileVO.fileName}</a>
	</c:forEach>
</body>
</html>