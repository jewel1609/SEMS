<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value='/resources/css/eduDetail.css'/>" rel="stylesheet"/> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>강의 자료 게시판</h2>
	<hr/>
	
	<table>
		<tr>
			<th>
				강의 자료 번호
			</th>
			<th>
				강사명
			</th>
			<th>
				제목
			</th>
			<th>
				내용
			</th>
			<th>
				작성일
			</th>
			<th>
				수정일
			</th>
			<th>
				조회수
			</th>
		</tr>
		<c:forEach items="${educationFileBBSList}" var="educationFileBBS">
		<tr>
			<td>
				${educationFileBBS.articleId}
			</td>
			<td>
				${educationFileBBS.memberId}
			</td>
			<td>
				${educationFileBBS.title}
			</td>
			<td>
				${educationFileBBS.contents}
			</td>
			<td>
				${educationFileBBS.createDate}
			</td>
			<td>
				${educationFileBBS.modifyDate}
			</td>
			<td>
				${educationFileBBS.hits}
			</td>
		</tr>
		</c:forEach>
	</table>
	<hr/>
	<c:if test="${sessionScope._MEMBER_.id eq teacherId}">
		<form action="<c:url value='/education/writeFileBBS' />" method="post">
			<input type="hidden" name="educationId" value="${educationId}"/>
			<input type="submit" value="글쓰기"/>
		</form>
	</c:if>
</body>
</html>