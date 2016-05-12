<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Education 수정 페이지 </title>
<script language="javascript">


</script>
</head>
<body>
 

 
 <div style="width:30%; height:100%; border:thin; border-style:double; border-radius: 5px; padding:5px;">
		<form:form commandName="educationVO" method="post"
	     							 action="/backend/doEducationModifyAction" enctype="multipart/form-data" >
	     	
	     educationCategory : 
	       <select id="educationCategory" name="educationCategory" >
	         <c:forEach items="${categoryList}" var="category">
		         <option value="${category.cdNm}">${category.cdNm}</option>
       		 </c:forEach>
     	 </select> <br/>
	     <form:errors path="educationCategory" /><br/>
	     educationTitle : <input type="text" id="educationTitle" name="educationTitle" value="${educationVO.educationTitle }"/>   <br/>
	     <form:errors path="educationTitle" /><br/>
	     memberId :  <input type="text" id="memberId" name="memberId" value="${educationVO.memberId }"/>   <br/>
	     <form:errors path="memberId" /><br/>
	     maxMember : <input type="text" id="maxMember" name="maxMember" value="${educationVO.maxMember }"/>   <br/>
	     <br/>
	     educationLocation : <input type="text" id="educationLocation" name="educationLocation" value="${educationVO.educationLocation }"/> <br/>
	     <form:errors path="educationLocation" /><br/>
	   	educationCurriculum : 
	   	<input type="text" id="educationCurriculum" name="educationCurriculum" value="${educationVO.educationCurriculum }">
	   	  <input type="file" name="file" id="educationCurriculum" tabindex="2" style="height: 45px">
	      <form:errors path="educationCurriculum" /><br/>  
	     <br/>
	     educationIntroduce :  
	    	
	 	   <input type="text" id="educationIntroduce" name="educationIntroduce" value="${educationVO.educationIntroduce }"/><br/>  
	     <form:errors path="educationIntroduce" /><br/>
	     startDate :  <input type="date" id="startDate" name="startDate" value="${educationVO.startDate }"/>   <br/>
	     <form:errors path="startDate" /><br/>
	     endDate :  <input type="date" id="endDate" name="endDate" value="${educationVO.endDate }"/>   <br/>
	     <form:errors path="endDate" /><br/>
	    
	     startTime : <input type="time" id="startTime" name="startTime" value="${educationVO.startTime }"/>   <br/>
	     <form:errors path="startTime" /><br/>
	     endTime :  <input type="time" id="endTime" name="endTime" value="${educationVO.endTime }"/>   <br/>
	     <form:errors path="endTime" /><br/>
	     educationType : 
	   	<c:forEach items="${typeList}" var="type">
         	<input type="radio" class="educationType" name="educationType" value="${type.cdNm}"/> ${type.cdNm}
      	   </c:forEach>
	      <form:errors path="cost" /><br/>
	    
	     <form:errors path="educationType" /><br/>
	     cost : 
	      <c:forEach items="${costList}" var="cost">
         	<input type="radio" class="cost" name="cost" value="${cost.cdNm}"/> ${cost.cdNm}
      	   </c:forEach>
	      <form:errors path="cost" /><br/>
	    	<input type="hidden" name="educationId" value="${educationVO.educationId }"/>
	      <input type="submit" value="등록하기" />
   		</form:form>
	</div>
</body>
</html>