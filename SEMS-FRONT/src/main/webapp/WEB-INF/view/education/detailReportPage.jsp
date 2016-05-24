<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div style="width: 50%; heght: 100%; border: thin; border-style: double; border-radius: 5px;">
		${ educationReportVO.title } <br />
		<hr>
		강사 명 : ${ educationReportVO.memberId } <br />
		<hr>
		${ educationReportVO.contents } <br />
		<hr>
		시작 일 : ${ educationReportVO.startDate } <br /> 
		종료 일 : ${ educationReportVO.endDate } <br />
		<hr>
		
		<c:if test="${reportFile.size() gt 0 }"> 
			첨부파일 <br/>
			<c:forEach items="${reportFile}" var="file">
				<a href="/downloadFile/${educationReportVO.articleId }" >
					${file.fileName }
				</a>
			</c:forEach>
			<hr>
		</c:if>
		
		<c:if test="${loginMember.memberType eq 'TR' }">
			<a href="<c:url value='/education/modifyReport/${educationReportVO.articleId }' />">수정</a> /
			<a href="<c:url value='/education/deleteReport/${educationReportVO.articleId }' />">삭제</a> / 
		</c:if>
		<a href="<c:url value='/education/reportList/${educationReportVO.educationId }' />">목록</a>
	</div>

</body>
</html>