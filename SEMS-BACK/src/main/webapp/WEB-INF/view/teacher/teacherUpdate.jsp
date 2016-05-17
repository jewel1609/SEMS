<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher 수정 페이지</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		$("#annual").keyup(function(event) {
			reg = /[^0-9]/gi;
	        v = $(this).val();
	        if (reg.test(v)) {
	        	alert("숫자만 입력 하시오.");
	            $(this).val(v.replace(reg, ''));
	            $(this).focus();
	        }
		});

		$("#update").click(function() {
			if (confirm("정말 등록하시겠습니까?") == true) {
				if ($("#checkCheckedBox").val() == '1') {
					if ($.trim($('#companyName').val()) == '') {
						alert("소속업체를 입력하시오.");
						$('#companyName').focus();
						return false;
					}
				}				
				if ($("#checkCheckedBox").val() == '0') {
					if ($.trim($('#businessNumber').val()) == '') {
						alert("사업자번호를 입력하시오.");
						$('#businessNumber').focus();
						return false;
					}
				}
				if ($.trim($('#annual').val()) == '') {
					alert("연차를 입력하시오.");
					$('#annual').focus();
					return false;
				}
				if ($.trim($('#eduStartDate').val()) == '') {
					alert("시작 날짜를 입력하시오.");
					$('#eduStartDate').focus();
					return false;
				}
				if ($.trim($('#eduEndDate').val()) == '') {
					alert("종강 날짜를 입력하시오.");
					$('#eduEndDate').focus();
					return false;
				}
				if ($.trim($('#educationName').val()) == '') {
					alert("강의명을 입력하시오.");
					$('#educationName').focus();
					return false;
				}
				if ($.trim($('#educationLocation').val()) == '') {
					alert("강의장을 입력하시오.");
					$('#educationLocation').focus();
					return false;
				}
				if ($.trim($('#startDate').val()) == '') {
					alert("프로젝트 시작 날짜를 입력하시오.");
					$('#startDate').focus();
					return false;
				}
				if ($.trim($('#endDate').val()) == '') {
					alert("프로젝트 종료 날짜을 입력하시오.");
					$('#endDate').focus();
					return false;
				}
				if ($.trim($('#projectName').val()) == '') {
					alert("프로젝트명을 입력하시오.");
					$('#projectName').focus();
					return false;
				}
				if ($.trim($('#projectLocation').val()) == '') {
					alert("프로젝트 회사를 입력하시오.");
					$('#projectLocation').focus();
					return false;
				}
				if ($.trim($('#bookName').val()) == '') {
					alert("책이름을 입력하시오.");
					$('#bookName').focus();
					return false;
				}
				if ($.trim($('#bookCompany').val()) == '') {
					alert("출판사를 입력하시오.");
					$('#bookCompany').focus();
					return false;
				}
			} else {
				return;
			}
		});
		<c:if test="${not empty teacherVO.companyName }">
		$("#businessNumber").hide();
		</c:if>
		$(".pre").click(function() {
			var isChecked = $(this).prop("checked");
			if (isChecked) {
				$("#businessNumber").show();
				$("#checkCheckedBox").val('0');
				$("#companyName").val('');
			} else {
				$("#businessNumber").hide();
			}
		});

	});
</script>
</head>
<body>

	<div
		style="width: 30%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form commandName="educationVO" method="post"
			action="/backend/doTeacherModifyAction">

	     강사명 : ${teacherVO.name }
			<br />
		<c:if test="${not empty teacherVO.companyName }">
	     소속업체 :  <input type="text" class="onlyText" id="companyName"
				name="companyName" value="${teacherVO.companyName }" maxlength="20"/>
				<input type="checkbox" name="pre"
					class="tip pre" title="프리랜서" value="true" />
				<input type="hidden" name="checkCheckedBox" id="checkCheckedBox" value="1" />
			<br />
		</c:if>
		<c:if test="${not empty teacherVO.businessNumber }">
	     사업자 번호 : <input type="text" class="onlyText" id="businessNumber"
				name="businessNumber" value="${teacherVO.businessNumber }" />
			<br />
		</c:if>
	     연차 : <input type="text" class="onlyText"
				id="annual" name="annual"
				value="${teacherVO.annual }" maxlength="20"/>년
			<br />
			<form:errors path="annual" />
			<br />
	   	강의 이력  : 
			<table border="1">
				<tr>
					<th>시작날짜</th>
					<th>종료날짜</th>
					<th>교육명</th>
					<th>교육장소</th>
				</tr>
				<c:forEach items="${educationHistoryVO}" var="list">
				
				</c:forEach>
				<tr>
					<td>
						<input type="date" id="eduStartDate"
							name="eduStartDate"
							value="${list.startDate }" maxlength="100">
					</td>
					<td>
						<input type="date" id="eduEndDate"
							name="eduEndDate"
							value="${list.endDate }" maxlength="100">
					</td>
					<td>
						<input type="text" id="educationName" class="onlyText"
							name="educationName"
							value="${list.educationName }" maxlength="100">
					</td>
					<td>
						<input type="text" id="educationLocation" class="onlyText"
							name="educationLocation"
							value="${list.educationLocation }" maxlength="100">
					</td>
				</tr>
			</table>			
			<br />
	     집필 서적  :  
	    	<table border="1">
				<tr>
					<th>책이름</th>
					<th>출판사</th>
				</tr>
				<c:forEach items="${teacherBookVO}" var="list">
				
				</c:forEach>
				<tr>
					<td>
						<input type="text" id="bookName" class="onlyText"
							name="bookName"
							value="${list.bookName }" maxlength="100">
					</td>
					<td>
						<input type="text" id="bookCompany" class="onlyText"
							name="bookCompany"
							value="${list.bookCompany }" maxlength="100">
					</td>
				</tr>
			</table>
			<br />
	     프로젝트 이력  :  
	     	<table border="1">
				<tr>
					<th>시작날짜</th>
					<th>종료날짜</th>
					<th>프로젝트명</th>
					<th>회사이름</th>
				</tr>
				<c:forEach items="${projectHistoryVO}" var="list">
				<tr>
					<td>
						<input type="date" id="startDate"
							name="startDate"
							value="${list.startDate }" maxlength="100">
					</td>
					<td>
						<input type="date" id="endDate"
							name="endDate"
							value="${list.endDate }" maxlength="100">
					</td>
					<td>
						<input type="text" id="projectName"
							name="projectName"
							value="${list.projectName }" maxlength="100">
					</td>
					<td>
						<input type="text" id="projectLocation"
							name="projectLocation"
							value="${list.projectLocation }" maxlength="100">
					</td>
				</tr>
				</c:forEach>
			</table>			
			<br />

			<input type="hidden" name="id"	value="${teacherVO.id }" />
			
			<input type="submit" id="update" value="등록하기" />
		</form:form>
	</div>
</body>
</html>