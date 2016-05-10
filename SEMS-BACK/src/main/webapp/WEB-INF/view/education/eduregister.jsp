<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Education 등록 페이지 </title>
</head>
<body>
 
 테스터 입니다. 
 
 <div style="width:30%; height:100%; border:thin; border-style:double; border-radius: 5px; padding:5px;">
		<form:form commandName="educationVO" method="post"
	     							 action="/backend/doWriteAction">
	     	
	     educationCategory : <input type="text" id="educationCategory" name="educationCategory" value="${educationVO.educationCategory }"/>
	     <form:errors path="educationCategory" /><br/>
	     educationTitle : <input type="text" id="educationTitle" name="educationTitle" value="${educationVO.educationTitle }"/>   
	     <form:errors path="educationTitle" /><br/>
	     memberId :  <input type="text" id="memberId" name="memberId" value="${educationVO.memberId }"/>   
	     <form:errors path="memberId" /><br/>
	     maxMember : <input type="text" id="maxMember" name="maxMember" value="${educationVO.maxMember }"/>   
	     <br/>
	     educationLocation : <input type="text" id="educationLocation" name="educationLocation" value="${educationVO.educationLocation }"/> 
	     <form:errors path="educationLocation" /><br/>
	     educationCurriculum :  <input type="text" id="educationCurriculum" name="educationCurriculum" value="${educationVO.educationCurriculum }"/>   
	     <br/>
	     educationIntroduce :  <input type="text" id="educationIntroduce" name="educationIntroduce" value="${educationVO.educationIntroduce }"/>
	     <form:errors path="educationIntroduce" /><br/>
	     startDate :  <input type="text" id="startDate" name="startDate" value="${educationVO.startDate }"/>   
	     <form:errors path="startDate" /><br/>
	     endDate :  <input type="text" id="endDate" name="endDate" value="${educationVO.endDate }"/>   
	     <form:errors path="endDate" /><br/>
	     startTime : <input type="text" id="startTime" name="startTime" value="${educationVO.startTime }"/>   
	     <form:errors path="startTime" /><br/>
	     endTime :  <input type="text" id="endTime" name="endTime" value="${educationVO.endTime }"/>   
	     <form:errors path="endTime" /><br/>
	     educationType : <input type="text" id="educationType" name="educationType" value="${educationVO.educationType }"/>   
	     <form:errors path="educationType" /><br/>
	     cost : <input type="text" id="cost" name="cost" value="${educationVO.cost }"/>   
	     <form:errors path="cost" /><br/>
	      <input type="submit" value="등록하기" />
   		</form:form>
	</div>
</body>
</html>