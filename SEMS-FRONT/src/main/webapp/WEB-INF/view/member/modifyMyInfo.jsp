<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
});

</script>
</head>
<body>

<form:form commandName="member" method="post" action="/member/myPage/doModifyAction">

	memberId : ${member.id}  <br/>
	password : <input type="text" name="password" id="password" />
	<br />
	<input type="text" name="name" id="name" value="${member.name}" placeholder="이름을 입력하세요." />
	<br/>
	<input type="text" name="email" id="email"  value="${member.email}" placeholder="이메일을 입력하세요. " /> 
	<br />
	<input type="text" name="highestEducationLevel" id="highestEducationLevel"  value="${member.highestEducationLevel}" placeholder="최종학력을 입력하세요." />
	<br />
	universityName : ${member.universityName}  <br />
	majorName : ${member.majorName} <br /> 
	<input type="text" name="graduationType" id="graduationType"  value="${member.graduationType}" placeholder="졸업 구분을 입력하세요."/>
	<br />
	<input type="text" name="birthDate" id="birthDate"  value="${member.birthDate}" placeholder="생년월일을 입력하세요." /> 
	<br />
	<input type="text" name="phoneNumber" id="phoneNumber"  value="${member.phoneNumber}" placeholder="전화번호를 입력하세요." /> 
	<br />
	memberType : ${member.memberType} <br />
	
	<input type="hidden" name="id" id="id" value="${member.id}"/>
	<input type="submit"  value="수정 완료" />
	
</form:form>
</body>
</html>