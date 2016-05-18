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
				if ($("#compCheckedBox").val() == '1') {
					if ($.trim($('#companyName').val()) == '') {
						alert("소속업체를 입력하시오.");
						$('#companyName').focus();
						return false;
					}
				}				
				if ($("#preCheckedBox").val() == '1') {
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
		$("#generalBusinessNumber").hide();
		$("#preCheckedBox").val('0');
		</c:if>
		$(".pre").click(function() {
			var isChecked = $(this).prop("checked");
			if (isChecked) {
				$("#companyMember").hide();
				$("#generalBusinessNumber").show();
				$("#compCheckedBox").val('0');
				$("#companyName").val('');
			}
		});
		<c:if test="${not empty teacherVO.businessNumber }">
		$("#companyMember").hide();
		$("#compCheckedBox").val('0');
		</c:if>
		$(".noPre").click(function() {
			var isChecked = $(this).prop("checked");
			if (isChecked) {
				$("#generalBusinessNumber").hide();
				$("#companyMember").show();
				$("#preCheckedBox").val('0');
				$("#businessNumber").val('');
			}
		});
		$("#addEduHis").click(function(){
			var contents = '';
			
			contents +='<tr>';
			contents +='<td><input type="date" id="eduStartDate" class="onlyText" name="eduStartDate"></td>';
			contents +='<td><input type="date" id="eduEndDate" class="onlyText"	name="eduEndDate"></td>';
			contents +='<td><input type="text" id="educationName" class="onlyText" name="educationName" maxlength="50"></td>';
			contents +='<td><input type="text" id="educationLocation" class="onlyText"	name="educationLocation" maxlength="50"></td>';
			contents +='<td><input type="button" class="removeEduHisTr" value="삭제"/></td>';
			contents +='</tr>';

			$("#EduHisTable").append(contents);
		});
		
		$("#addBook").click(function(){
			var contents = '';
			
			contents +='<tr>';
			contents +='<td><input type="text" id="bookName" class="onlyText" name="bookName" maxlength="50"></td>';
			contents +='<td><input type="text" id="bookCompany" class="onlyText" name="bookCompany" maxlength="20"></td>';
			contents +='<td><input type="button" class="removeBookHisTr" value="삭제"/></td>';
			contents +='</tr>';

			$("#BookTable").append(contents);
		});
		
		$("#addProHis").click(function(){
			var contents = '';
		
			contents +='<tr>';
			contents +='<td><input type="date" id="startDate" class="onlyText" name="startDate"></td>';
			contents +='<td><input type="date" id="endDate" class="onlyText" name="endDate"></td>';
			contents +='<td><input type="text" id="projectName" class="onlyText" name="projectName" maxlength="50"></td>';
			contents +='<td><input type="text" id="projectLocation" class="onlyText" name="projectLocation" maxlength="50"></td>';
			contents +='<td><input type="button" class="removeProHisTr" value="삭제"/></td>';
			contents +='</tr>';

			$("#ProHisTable").append(contents);
		});
		
		$(".removeHisTr").click(function(){
			if (confirm("정말 삭제하시겠습니까?") == true) {
				
				var id = $(this).parent().children().eq(0).val();
				var type = $(this).parent().children().eq(1).val();
				location.href="/backend/deleteTeacherBookEduProHistory/"+id+"/"+type;
			}else{
				return;
			}			
		});

	});
</script>
</head>
<body>

	<div
		style="width: 40%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form commandName="teacherVO" method="post"
			action="/backend/doTeacherInfoModifyAction">
		강사 수정
		<br />
		<br />
	     강사명 : ${teacherVO.name }
			<br />
			<br />
		<div
		style="width: 90%; height: 40%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<div id="companyMember">
	     소속업체 :  <input type="text" class="onlyText" id="companyName"
				name="companyName" value="${teacherVO.companyName }" maxlength="30"/>
				<input type="checkbox" name="pre"
					class="tip pre" title="프리랜서" value="true" />
				<input type="hidden" name="compCheckedBox" id="compCheckedBox" value="1" />
			<br />
			<br />
		</div>
		<div id="generalBusinessNumber">
	     개인 사업자 번호 : <input type="text" class="onlyText" id="businessNumber"
				name="businessNumber" value="${teacherVO.businessNumber }" maxlength="30" />
					<input type="checkbox" name="noPre"
						class="tip noPre" title="사업체소속" value="true" />
					<input type="hidden" name="preCheckedBox" id="preCheckedBox" value="1" />
		</div>
			<br />
			<br />
	     연차 : <input type="text" class="onlyText"
				id="annual" name="annual"
				value="${teacherVO.annual }" maxlength="20"/>년
			<br />
			<form:errors path="annual" />
			<br />
			<input type="hidden" name="memberId" value="${teacherVO.memberId }" />
			
			
		</div>
		<br />
	   	강의 이력(<input type="button" id="addEduHis" value="추가"/>)  : 
			<table border="1" id="EduHisTable">
				<tr>
					<th>시작날짜</th>
					<th>종료날짜</th>
					<th>교육명</th>
					<th>교육장소</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${educationHistoryVO}" var="list" varStatus="status">
				<tr>
					<td>
						<input type="date" id="eduStartDate" class="onlyText"
							name="educationHistoryList[${status.index}].startDate"
							value="${list.startDate }" >
					</td>
					<td>
						<input type="date" id="eduEndDate" class="onlyText"
							name="educationHistoryList[${status.index}].eduEndDate"
							value="${list.endDate }" >
					</td>
					<td>
						<input type="text" id="educationName" class="onlyText"
							name="educationHistoryList[${status.index}].educationName"
							value="${list.educationName }" maxlength="50">
					</td>
					<td>
						<input type="text" id="educationLocation" class="onlyText"
							name="educationHistoryList[${status.index}].educationLocation"
							value="${list.educationLocation }" maxlength="50">
					</td>
					<td>
						<input type="hidden" id="eduHisId" 
							name="educationHistoryList[${status.index}].id"
							value="${list.id }" >
						<input type="hidden" id="type" name="type" value="edu" >
						<input type="button" class="removeHisTr" value="삭제"/>
					</td>
				</tr>
				</c:forEach>
			</table>			
			<br />
	     집필 서적(<input type="button" id="addBook" value="추가"/>)  :  
	    	<table border="1" id="BookTable">
				<tr>
					<th>책이름</th>
					<th>출판사</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${teacherBookVO}" var="list" varStatus="status">
				
				<tr>
					<td>
						<input type="text" id="bookName" class="onlyText"
							name="teacherBookList[${status.index}].bookName"
							value="${list.bookName }" maxlength="50">
					</td>
					<td>
						<input type="text" id="bookCompany" class="onlyText"
							name="teacherBookList[${status.index}].bookCompany"
							value="${list.bookCompany }" maxlength="20">
					</td>
					<td>
						<input type="hidden" id="bookHisId" 
							name="teacherBookList[${status.index}].id"
							value="${list.id }" >
						<input type="hidden" id="type" name="type" value="book" >
						<input type="button" class="removeHisTr" value="삭제"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			<br />
	     프로젝트 이력(<input type="button" id="addProHis" value="추가"/>)  :  
	     	<table border="1" id="ProHisTable">
				<tr>
					<th>시작날짜</th>
					<th>종료날짜</th>
					<th>프로젝트명</th>
					<th>회사이름</th>
					<th>삭제</th>
				</tr>
				<c:forEach items="${projectHistoryVO}" var="list" varStatus="status">
				
				<tr>
					<td>
						<input type="date" id="startDate" class="onlyText"
							name="projectHistoryList[${status.index}].startDate" 
							value="${list.startDate }" >
					</td>
					<td>
						<input type="date" id="endDate" class="onlyText"
							name="projectHistoryList[${status.index}].endDate" 
							value="${list.endDate }" >
					</td>
					<td>
						<input type="text" id="projectName" class="onlyText"
							name="projectHistoryList[${status.index}].projectName" 
							value="${list.projectName }" maxlength="50">
					</td>
					<td>
						<input type="text" id="projectLocation" class="onlyText"
							name="projectHistoryList[${status.index}].projectLocation" 
							value="${list.projectLocation }" maxlength="50">
					</td>
					<td>
						<input type="hidden" id="proHisId" 
							name="projectHistoryList[${status.index}].id"
							value="${list.id }" >
						<input type="hidden" id="type" name="type" value="pro" >
						<input type="button" class="removeHisTr" value="삭제"/>
					</td>
				</tr>
				</c:forEach>
			</table>			
			<br />
			<input type="submit" id="update" value="수정하기" />
		</form:form>	
			
	</div>
</body>
</html>