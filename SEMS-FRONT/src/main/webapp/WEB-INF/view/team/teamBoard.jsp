<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">

	$("#write").click(function() {
		location.href="<c:url value='/team/teamBBS/write'/>";
	});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀별 커뮤니티 게시판 </title>
</head>
<body>

	<table>
		<tr>
			<td>제목	</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>수정일</td>
			<td>첨부파일의 수</td>
			<td>댓글 수</td>
			<td>조회 수</td>
			<td>추천 수</td>
			<td>반대 수</td>
		</tr>
		<tr>
		
		</tr>
	</table>
	<input type="button" id="write" value="글쓰기">
	<div class="clear"></div>
	
</body>
</html>