<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form:form commandName="educationReportVO" method="post"
			action="/education/doReportWriteAction" enctype="multipart/form-data">
		
		<input type="hidden" id="educationId" name="educationId" value="${ educationReportVO.educationId }" />
		
		과제 명 : <input type="text" id="title" name="title" placeholder="과제 명" value="${educationReportVO.title}"/>
		<br/>
		<form:errors path="title" style="color:red;" />
		<br/>
		
		과제 내용 : <textarea id="contents" name="contents" >${educationReportVO.contents}</textarea>
		<br/><br/>		
		
		시작 일 : <input type="datetime-local" id="startDate" name="startDate" value="${educationReportVO.startDate}" />
		<br/>
		<form:errors path="startDate" style="color:red;" />
		<br/>
		
		종료 일 : <input type="datetime-local" id="endDate" name="endDate" value="${educationReportVO.endDate}"/>
		<br/>
		<form:errors path="endDate" style="color:red;" />
		<br/>
		
				
		<input type="file" name="file" id="file" />
		<br/><br/>	
		
		<input type="submit" id="submit" name="submit" />
				</form:form>
	
	
	
</body>
</html>