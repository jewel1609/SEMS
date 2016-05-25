<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	table {
		width:1000px;
		height:80px;
	}
	td {
		text-align:center;
		}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 게시판 상세보기</title>
</head>
<body>

	<table border="1">
		<tr>
			<th>제목</th>
			<th>글</th>
			<th>글번호</th>
			<th>수정한 날짜</th>
			<th>글쓴이</th>
			<th>좋아요</th>
			<th>싫어요</th>
			<th>공지여부</th>
			<th>작성한 날짜</th>
			<th>조회수</th>
			<th>팀번호</th>
		</tr>
		<tr>
			<td>${ bbsVO.title }</td>
			<td>${ bbsVO.descript }</td>
			<td>${ bbsVO.teamBBSId }</td>
			<td>
				<c:if test="${ bbsVO.modifiedDate eq null}">수정 이력 없음</c:if>
				<c:if test="${ bbsVO.modifiedDate ne null}">${ bbsVO.modifiedDate }</c:if>
			</td>
			<td>${ bbsVO.memberId }</td>
			<td>${ bbsVO.likeCount }</td>
			<td>${ bbsVO.disLikeCount }</td>
			<td>${ bbsVO.isNotice }</td>
			<td>${ bbsVO.createdDate }</td>
			<td>${ bbsVO.hits }</td>
			<td>${ bbsVO.teamId }</td>
		</tr>
	</table>
	<br/>
	<br/>
	<br/>	
	<table border="1">
		<tr>
			<th>댓글ID</th>
			<th>팀게시글ID</th>
			<th>댓글쓴이</th>
			<th>글</th>
			<th>댓글그룹ID</th>
			<th>상위댓글ID</th>
			<th>DEPTH</th>
			<th>좋아요</th>
			<th>싫어요</th>
			<th>순번</th>
		</tr>
		<c:forEach items="${ bbsRplVO }" var="bbsRpl">
		<tr>
			<td>${ bbsRpl.replyId }</td>
			<td>${ bbsRpl.teamBBSId }</td>
			<td>${ bbsRpl.mbrId }</td>
			<td>${ bbsRpl.descript }</td>
			<td>${ bbsRpl.groupId }</td>
			<td>${ bbsRpl.parentReplyId }</td>
			<td>${ bbsRpl.depth }</td>
			<td>${ bbsRpl.likeCnt }</td>
			<td>${ bbsRpl.disLikeCount }</td>
			<td>${ bbsRpl.orderNo }</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>