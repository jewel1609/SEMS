<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">

   $(document).ready(function() {
	 
	   $("#searchInitBtn").click(function() {
			location.href="<c:url value='/${educationId}/eduFile' />";
		});
	   
	   $("#searchBtn").click( function() {
			
			if( $("#searchType option:selected").val() == "1"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			
			if( $("#searchType option:selected").val() == "2"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			movePage('0');
		});
   });
</script>
<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border-top: 1px solid #bcbcbc;
    border-bottom: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<table>
		<tr>
			<td>eduFileId</td>
			<td>educationId</td>
			<td>isDelete</td>
			<td>memberId</td>
			<td>title</td>
			<td>contents</td>
			<td>createDate</td>
			<td>modifyDate</td>
			<td>hits</td>
		</tr>
		<c:forEach items="${ eduFileListVO.eduFileList }" var="eduFile">
				<tr>
					<td>${eduFile.eduFileId }</td>
					<td>${eduFile.educationId }</td>
					<td>${eduFile.isDelete }</td>
					<td>${eduFile.memberId }</td>
					<td>${eduFile.title }</td>
					<td>${eduFile.contents }</td>
					<td>${eduFile.modifyDate }</td>
					<td>${eduFile.hits }</td>
				</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ eduFileListVO ne null }">
							${eduFileListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
					<div style="text-align: right;">
						<select id="search" name="search">
							<option id="title" value="2">제목</option>
							<option id="id" value="1">아이디</option>
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword" value="${ eduFileSearchVO.searchKeyword }"/>
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>	
	</table>
	
	
</body>
</html>