<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
	
		$("#write").click(function() {
			location.href="<c:url value='/team/teamBBS/write'/>";
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀별 커뮤니티 게시판 </title>
</head>
<body>
	
	<form name="searchForm" id="searchForm" >
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
			<c:forEach items="${ TeamBBSListVO.teamBBSList }" var="teamBBSVO">
				<tr>
					<td><a href="<c:url value='/team/teamBBS/detail/${teamBBSVO.teamBBSId}'/>">${ teamBBSVO.title }</a></td>
					<td>${teamBBSVO.memberId}</td>
					<td>${teamBBSVO.createdDate}</td>
					<td>${teamBBSVO.modifiedDate}</td>
					<td>첨부파일 수 (미완)</td>
					<td>댓글 수 (미완)</td>
					<td>${teamBBSVO.hits}</td>
					<td>${teamBBSVO.likeCount}</td>
					<td>${teamBBSVO.disLikeCount}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center">
					<c:if test="${ TeamBBSListVO ne null }">
					${TeamBBSListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
					</c:if>
				</td>
			</tr>  
		</table>
	</form>
	<input type="button" id="write" value="글쓰기">
	<div class="clear"></div>
	
</body>
</html>