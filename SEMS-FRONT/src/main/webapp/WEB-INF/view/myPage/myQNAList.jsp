<!-- @author 이기연-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="/resources/css/eduDetail.css" rel="stylesheet">
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육 문의 사항 리스트 보기</title>
</head>
<body>

	<a href="#"><input type="button" value="엑셀로 보내기"></a>
	<br/>
	<table border="1"> 
		<tr>
			<th>문의 번호</th>
			<th>교육 명</th>
			<th>문의 날짜</th>
			<th>문의 내용</th>
			<th>답변 여부</th>
		</tr>
		<c:forEach items="${qnaListVO.qnaVO}" var="qnaVO">
		<tr>
			<td>${qnaVO.replyId}</td>
			<td>${qnaVO.eduId}</td>
			<td>${qnaVO.createdDate}</td>
			<td><a href="<c:url value='/myPage/myQNADetail/${qnaVO.replyId}'/>" onclick="window.open(this.href, '문의 사항','');return false;" target="_blank">${qnaVO.description}</a></td>
			<td>${qnaVO.isAnswered}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5">${qnaListVO.paging.getPagingList("pageNo","[@]","이전","다음","pagingForm")}
			</td>
		</tr>
	</table>

</body>
</html>