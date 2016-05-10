<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<!-- 
3-3. 내 정보 수정은 이름, 비밀번호, 이메일, 최종학력, 졸업구분, 나이, 전화번호만 수정 가능하다. 
3-4. 비밀번호를 제외한 수정가능한 모든 항목은 Textbox로 출력한다. 
3-5. 비밀번호는 보안을 위해 비워둔다. 
3-6. 사용자가 비밀번호를 입력하지 않고 "수정 완료" 버튼을 클릭했을 경우 비밀 번호의 수정을 원치 않은 것으로 갈음하여, 비밀번호 갱신을 하지 않는다. 
3-7. 비밀번호는 회원 가입시의 비밀번호 정책을 동일하게 사용해야 한다. 
3-8. 비밀번호를 새로 입력한 뒤 "수정 완료" 버튼을 클릭했을 경우, SALT를 새로 발급해 SHA-256 암호화 하여 비밀번호를 갱신한다. 


 -->
<body>

<form:form commandName="memberVO" method="post" action="/member/myPage/doModifyAction">

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
	memberType : ${member.memberType} <br />
	
	<input type="submit"  value="수정 완료" />
	
</form:form>
</body>
</html>