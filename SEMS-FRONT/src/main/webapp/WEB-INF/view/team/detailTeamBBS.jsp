<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	
	</table>


</body>
</html>