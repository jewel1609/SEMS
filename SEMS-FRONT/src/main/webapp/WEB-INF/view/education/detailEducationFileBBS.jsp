<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
	cursor: pointer;
}
</style>
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet"/>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script> 
<script type="text/javascript">
	window.onload = function() {
		var backButton = document.getElementById("backButton");
		backButton.onclick = function() {
			location.href= "/education/fileBBS/${educationFileBBS.educationId}";
		};
	};
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>강의 자료</h2>
	<hr/>
	<table>
		<tr>
			<th>
				교육 번호
			</th>
			<td>
				${educationFileBBS.educationId}
			</td>
		</tr>
		<tr>
			<th>
				강의 자료 번호
			</th>
			<td>
				${educationFileBBS.articleId}
			</td>
		</tr>
		<tr>
			<th>
				강사 아이디
			</th>
			<td>
				${educationFileBBS.memberId}
			</td>
		</tr>
		<tr>
			<th>
				제목
			</th>
			<td>
				${educationFileBBS.title}
			</td>
		</tr>
		<tr>
			<th>
				내용
			</th>
			<td>
				${educationFileBBS.contents}
			</td>
		</tr>
		<tr>
			<th>
				작성일
			</th>
			<td>
				${educationFileBBS.createDate}
			</td>
		</tr>
		<tr>
			<th>
				조회수
			</th>
			<td>
				${educationFileBBS.hits}
			</td>
		</tr>
	</table>
	<c:forEach items="${fileList}" var="file">
		<a href="/downloadEducationFile/${file.fileId}" >
			${file.fileName} <br/>
		</a>
	</c:forEach>	
	<input id="backButton" class="inputButton" type="button" value="목록으로"/>
</body>
</html>