<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀별 게시판 상세 페이지</title>
</head>
<body>
	
	<table>
		<tr> 
			<td>게시판 아이디</td>
			<td>멤버 아이디</td>
			<td>게시글 제목</td>
			<td>게시글 내용</td>
			<td>조회수</td>
			<td>작성일</td>
			<td>수정일</td>
			<td>공지여부 Y/N</td>
		</tr>
		<tr>
			<td>${teamBBS.teamBBSId }</td>
			<td>${teamBBS.memberId }</td>
			<td>${teamBBS.title }</td>
			<td>${teamBBS.descript }</td>
			<td>${teamBBS.hits }</td>
			<td>${teamBBS.createdDate }</td>
			<td>${teamBBS.modifiedDate }</td>
			<td>${teamBBS.isNotice}</td>
		</tr>	
		<tr>
		<td><a href="<c:url value='/team/teamBBS/like/${teamBBSVO.teamBBSId}'/>">좋아요</a></td>
		<td><a href="<c:url value='/team/teamBBS/dislike/${teamBBSVO.teamBBSId}'/>">싫어요</a></td>
		</tr>
	</table>


</body>
</html>