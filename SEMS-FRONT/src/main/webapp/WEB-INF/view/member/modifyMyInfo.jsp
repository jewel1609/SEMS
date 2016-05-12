<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">


$(document).ready(function() {
	
	$("#modifyBtn").click(function(){
		var form = $("#memberInfoForm");
		form.attr("action", "<c:url value="/member/myPage/doModifyAction" />");
		form.submit();
	});
	
	
	$('#years, #months, #days').change(function () {
        $("#birthDate").val();
        
        var target = document.getElementById("years");
        var date = "";
        var year = target.options[target.selectedIndex].text;
        date += year + "-";
        
        target = document.getElementById("months");
        var month = target.options[target.selectedIndex].text;
        if ( month.length == 1) {
           month = "0" + month;
        }
        date += month + "-";
        
        target = document.getElementById("days");
        var day = target.options[target.selectedIndex].text;
        if ( day.length == 1) {
           day = "0" + day;
        }
        date += day;
        
        $("#birthDate").val(date);
     });
	
	
});

</script>
<script type="text/javascript">
$(function () {
   for (i = new Date().getFullYear() ; i > 1900; i--) {
      $('#years').append($('<option />').val(i).html(i));
   }

   for (i = 1; i < 13; i++) {
      $('#months').append($('<option />').val(i).html(i));
   }
   updateNumberOfDays();
   
   /* 기존 생년월일을 짤라서 select box 에 표시 */
   var birthDate;
   <c:if test="${member.birthDate ne null}">
      birthDate = "${member.birthDate}";
   </c:if>
   var yyyy;
   var mm;
   var dd;
   
   if (birthDate != null && birthDate != "" ) {
      var splitBirthDate = birthDate.split("-");
      yyyy = splitBirthDate[0];
      mm = splitBirthDate[1];
      dd = splitBirthDate[2];
      
      try{
         yyyy = parseInt(yyyy);
         mm = parseInt(mm);
         dd = parseInt(dd);
      } catch (NumberFormatException) {
         yyyy = 2016;
         mm = 1;
         dd = 1;
      }
   }
   else {
      yyyy = 2016;
      mm = 1;
      dd = 1;
   }
   
   $("#years").val(yyyy).attr("selected", "selected");
   $("#months").val(mm).attr("selected", "selected");
   $("#days").val(dd).attr("selected", "selected");
   
   if ( mm < 10 ) {
      mm = "0" + mm;
   }
   
   if ( dd < 10 ) {
      dd = "0" + dd;
   }
   
   $("#birthDate").val(yyyy + "-" + mm + "-" + dd);
   
   $('#years, #months').change(function () {
      updateNumberOfDays();
   });
});

function updateNumberOfDays() {
   $('#days').html('');
   month = $('#months').val();
   year = $('#years').val();
   days = daysInMonth(month, year);

   for (i = 1; i < days + 1 ; i++) {
      $('#days').append($('<option />').val(i).html(i));
   }
}

function daysInMonth(month, year) {
   return new Date(year, month, 0).getDate();
}
</script>
</head>
<body>

<form:form commandName="memberInfoForm" method="post">

	아이디 : ${member.id}  <br/>
	비밀번호 : <input type="text" name="password" id="password" />
	<br />
	이름 : <input type="text" name="name" id="name" value="${member.name}" placeholder="이름을 입력하세요." tabindex="1"/>
	<br/>
	이메일 : <input type="text" name="email" id="email"  value="${member.email}" placeholder="이메일을 입력하세요. " tabindex="2" /> 
	<br />
	
	대학교 : ${member.universityName}  <br />
	전공 : ${member.majorName} <br /> 
	
	생년월일 : 
	<select id="years" name="years" tabindex="5"></select>&nbsp;년
	<select id="months" name="months" tabindex="6"></select>&nbsp;월
    <select id="days" name="days" tabindex="7"></select>&nbsp;일
	<input type="hidden" id="birthDate" name="birthDate" value="${ member.birthDate }" />
	
	<!-- 
	 <input type="text" name="birthDate" id="birthDate"  value="${member.birthDate}" placeholder="생년월일을 입력하세요." /> 	
	 -->
	 
	<br />
	전화번호 : <input type="text" name="phoneNumber" id="phoneNumber"  value="${member.phoneNumber}" placeholder="전화번호를 입력하세요." tabindex="8" /> 
	<br />
	회원구분 : ${memberTypeCodeName} <br />
	졸업구분 : 
	<c:forEach items="${graduationTypeList}" var="graduationTypeCodeName">
				<c:if test="${graduationTypeCodeName eq selectedGraduationTypeCodeName}">
				<input type="radio" class="graduationType" name="graduationType" value="${graduationTypeCodeName}" checked="checked"/>${graduationTypeCodeName}
				</c:if>
				<c:if test="${graduationTypeCodeName ne selectedGraduationTypeCodeName}">
				<input type="radio" class="graduationType" name="graduationType" value="${graduationTypeCodeName}"/>${graduationTypeCodeName}
				</c:if>
	</c:forEach>
	<br/>
	최종학력 : 
	<c:forEach items="${highestEducationLevelCodeNameList}" var="helCodeName">
				<c:if test="${helCodeName eq selectedHighestEducationLevelCodeName}">
				<input type="radio" class="helCodeName" name="helCodeName" value="${helCodeName}" checked="checked"/>${helCodeName}
				</c:if>
				<c:if test="${helCodeName ne selectedHighestEducationLevelCodeName}">
				<input type="radio" class="helCodeName" name="helCodeName" value="${helCodeName}"/>${helCodeName}
				</c:if>
	</c:forEach>
	
	
	
	<input type="hidden" name="id" id="id" value="${member.id}"/> <br/><br/>
	<input type="button" id="modifyBtn" value="수정 완료" />
	
</form:form>
</body>
</html>