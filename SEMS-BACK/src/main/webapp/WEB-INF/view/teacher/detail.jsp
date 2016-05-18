<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강사 상세정보</title>
</head>
<body>

	<span>${ teacherInfo.memberId}</span><br/>
	<span>${ teacherInfo.name }</span><br/>
	<span>${ teacherInfo.companyName }</span><br/>
	<span>${ teacherInfo.businessNumber }</span><br/>
	<span>${ teacherInfo.annual }</span><br/>
	<br/><br/>
	<c:forEach items="${ teacherEducationHistory }" var="educationHistory">
	 	<span>${ educationHistory.id }</span><br/>
		<span>${ educationHistory.startDate }</span><br/>
		<span>${ educationHistory.endDate }</span><br/>
		<span>${ educationHistory.educationName }</span><br/>
		<span>${ educationHistory.educationLocation }</span><br/>
	</c:forEach>
	<br/><br/>
	<c:forEach items="${ teacherProjectHistory }" var="projectHistory">
		<span>${ projectHistory.id }</span><br/>
		<span>${ projectHistory.startDate }</span><br/>
		<span>${ projectHistory.endDate }</span><br/>
		<span>${ projectHistory.projectName }</span><br/>
		<span>${ projectHistory.projectLocation }</span><br/>
	</c:forEach>
	<br/><br/>
	<c:forEach items="${ educationHistory }" var="educationHistory">
		<span>${ educationHistory.educationId }</span><br/>
		<span>${ educationHistory.educationCategory }</span><br/>
		<span>${ educationHistory.educationTitle }</span><br/>
		<span>${ educationHistory.memberId }</span><br/>
		<span>${ educationHistory.maxMember }</span><br/>
		<span>${ educationHistory.educationLocation }</span><br/>
		<span>${ educationHistory.educationCurriculum }</span><br/>
		<span>${ educationHistory.educationIntroduce }</span><br/>
		<span>${ educationHistory.startDate }</span><br/>
		<span>${ educationHistory.endDate }</span><br/>
		<span>${ educationHistory.startTime }</span><br/>
		<span>${ educationHistory.endTime }</span><br/>
		<span>${ educationHistory.educationType }</span><br/>
		<span>${ educationHistory.cost }</span><br/>
	</c:forEach>
	<br/><br/>
	<c:forEach items="${ teacherBook }" var="book">
		<span>${ book.id }</span><br/>
		<span>${ book.bookName }</span><br/>
		<span>${ book.bookCompany }</span><br/>
	</c:forEach>

</body>
</html>