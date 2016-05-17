<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Education 등록 페이지</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="<c:url value="/resources/js/jquery.timepicker.min.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.timepicker.min.css"/>">
<script type="text/javascript">
	$(document).ready(function() {
		
		var ua = navigator.userAgent.toLowerCase();
		if (ua.indexOf('chrome') == -1 ) // Internet Explorer일 경우
	    {
			console.log('ie');
			$("#startDate").datepicker({ dateFormat: "yy-mm-dd" });
			$("#endDate").datepicker({ dateFormat: "yy-mm-dd" });
			/*
			http://timepicker.co/ 참조
			*/
			$("#startTime").timepicker({
				 timeFormat: 'HH:mm',
			        // year, month, day and seconds are not important
			        //minTime: new Date(0, 0, 0, 8, 0, 0),
			        //maxTime: new Date(0, 0, 0, 15, 0, 0),
			        // time entries start being generated at 6AM but the plugin 
			        // shows only those within the [minTime, maxTime] interval
			        startHour: 6,
			        // the value of the first item in the dropdown, when the input
			        // field is empty. This overrides the startHour and startMinute 
			        // options
			        startTime: new Date(0, 0, 0, 8, 20, 0),
			        // items in the dropdown are separated by at interval minutes
			        interval: 10
			});
			$("#endTime").timepicker({
				 timeFormat: 'HH:mm',
			        // year, month, day and seconds are not important
			        //minTime: new Date(0, 0, 0, 8, 0, 0),
			       // maxTime: new Date(0, 0, 0, 15, 0, 0),
			        // time entries start being generated at 6AM but the plugin 
			        // shows only those within the [minTime, maxTime] interval
			        startHour: 6,
			        // the value of the first item in the dropdown, when the input
			        // field is empty. This overrides the startHour and startMinute 
			        // options
			        startTime: new Date(0, 0, 0, 8, 20, 0),
			        // items in the dropdown are separated by at interval minutes
			        interval: 10
			});
	    }
	    else  // Internet Explorer가 아닐경우
	    {
	    }

		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		$(".onlyTextWithBracket").keyup(function(event) {
			regexp = /[@\#$%<>&\=_\’]/gi;

			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		$("#maxMember").keyup(function(event) {
			reg = /[^0-9]/gi;
	        v = $(this).val();
	        if (reg.test(v)) {
	        	alert("숫자만 입력 하시오.");
	            $(this).val(v.replace(reg, ''));
	            $(this).focus();
	            return;
	        }
	        if ( v == 0 ) {
	        	alert("0이상의 숫자만 입력 하시오.");
	            $(this).val('');
	            $(this).focus();
	            return;
	        }
	        if ( v > 2147483647 ) {
	        	alert("너무 큰 숫자의 입력입니다.");
	            $(this).focus();
	            return;
	        }
		});

		$("#write").click(function() {
			if (confirm("정말 등록하시겠습니까?") == true) {
				if ($.trim($('#educationCategory').val()) == '') {
					alert("카테고리를 입력하시오.");
					$('#educationCategory').focus();
					return false;
				}
				if ($.trim($('#educationTitle').val()) == '') {
					alert("강의명을 입력하시오.");
					$('#educationTitle').focus();
					return false;
				}
				if ($.trim($('#memberId').val()) == '') {
					alert("강사명을 입력하시오.");
					$('#memberId').focus();
					return false;
				}
				if ($.trim($('#maxMember').val()) == '') {
					alert("제한 인원수를 입력하시오.");
					$('#maxMember').focus();
					return false;
				}
				if ( $.trim($('#maxMember').val()) == 0 ) {
		        	alert("0이상의 숫자만 입력 하시오.");
		        	$('#maxMember').val('');
		        	$('#maxMember').focus();
		            return false;
		        }
		        if ( $.trim($('#maxMember').val()) > 2147483647 ) {
		        	alert("너무 큰 숫자의 입력입니다.");
		        	$('#maxMember').focus();
		            return false;
		        }
				if ($.trim($('#educationLocation').val()) == '') {
					alert("강의실 명을 입력하시오.");
					$('#educationLocation').focus();
					return false;
				}
				if ($.trim($('#educationCurriculum').val()) == '') {
					alert("커리큘럼을 입력하시오.");
					$('#educationCurriculum').focus();
					return false;
				}
				if ($.trim($('#file').val()) == '') {
					alert("파일을 선택하시오.");
					$('#file').focus();
					return false;
				}
				if ($('#file').val().indexOf('.xlsx') == -1) {
					alert("엑셀 파일만 등록할 수 있습니다.");
					$('#file').focus();
					return false;
				}
				if ($.trim($('#educationIntroduce').val()) == '') {
					alert("강의소개를 입력하시오.");
					$('#educationIntroduce').focus();
					return false;
				}
				if ($.trim($('#startDate').val()) == '') {
					alert("시작 날짜를 입력하시오.");
					$('#startDate').focus();
					return false;
				}
				if ($.trim($('#endDate').val()) == '') {
					alert("종료 날짜을 입력하시오.");
					$('#endDate').focus();
					return false;
				}
				if ($.trim($('#startTime').val()) == '') {
					alert("시작 시간을 입력하시오.");
					$('#startTime').focus();
					return false;
				}
				if ($.trim($('#endTime').val()) == '') {
					alert("종료 시간을 입력하시오.");
					$('#endTime').focus();
					return false;
				}
				if ($.trim($('.educationType').val()) == '') {
					alert("타입을 선택하시오.");
					$('.educationType').focus();
					return false;
				}
				if ($.trim($('.cost').val()) == '') {
					alert("비용을 선택하시오.");
					$('.cost').focus();
					return false;
				}
			} else {
				return;
			}
		});

	});
</script>
</head>
<body>

	테스터 입니다.

	<div
		style="width: 30%; height: 100%; border: thin; border-style: double; border-radius: 5px; padding: 5px;">
		<form:form commandName="educationVO" method="post"
			action="/backend/doWriteAction" enctype="multipart/form-data">
	     	
	     교육 카테고리 : 
	       <select id="educationCategory" name="educationCategory">
				<c:forEach items="${categoryList}" var="category">
					<c:if test="${educationVO.educationCategory eq category.cdId }">
						<option value="${category.cdId}" selected="selected">${category.cdNm}</option>
					</c:if>
					<c:if test="${educationVO.educationCategory ne category.cdId }">
						<option value="${category.cdId}" selected="selected">${category.cdNm}</option>
					</c:if>
				</c:forEach>
			</select>
			<br />
			<form:errors path="educationCategory" />
			<br />
	     교육명 : <input type="text" class="onlyTextWithBracket"
				id="educationTitle" name="educationTitle"
				value="${educationVO.educationTitle }" maxlength="20"/>
			<br />
			<form:errors path="educationTitle" />
			<br />
	     강사명 :  <input type="text" class="onlyText" id="memberId"
				name="memberId" value="${educationVO.memberId }"  maxlength="20"/>
			<br />
			<form:errors path="memberId" />
			<br />
	     정원 : <input type="text" class="onlyText" id="maxMember"
				name="maxMember" value="${educationVO.maxMember }" min="1" max="2147483647" />
			<br />
			<br />
	     강의실 : <input type="text" class="onlyText"
				id="educationLocation" name="educationLocation"
				value="${educationVO.educationLocation }" maxlength="20"/>
			<br />
			<form:errors path="educationLocation" />
			<br />
	   	커리큘럼 : 
	   	<input type="text" name="educationCurriculum" class="onlyText"
				id="educationCurriculum" value="${educationVO.educationCurriculum }" maxlength="100">
			<input type="file" name="file" id="file" tabindex="2"
				style="height: 45px">
			<form:errors path="educationCurriculum" />
			<br />
			<br />
	     교육 소개 :  
	    	
	 	   <input type="text" style="height: 100px" id="educationIntroduce"
				class="onlyTextWithBracket" name="educationIntroduce"
				value="${educationVO.educationIntroduce }" />
			<br />
			<form:errors path="educationIntroduce" />
			<br />
	     개강일 :  <input type="date" id="startDate" name="startDate"
				value="${educationVO.startDate }" />
			<br />
			<form:errors path="startDate" />
			<br />
	     종강일 :  <input type="date" id="endDate" name="endDate"
				value="${educationVO.endDate }" />
			<br />
			<form:errors path="endDate" />
			<br />
	    
	     강의 시간(시작) : <input type="time" id="startTime" name="startTime"
				value="${educationVO.startTime }" />
			<br />
			<form:errors path="startTime" />
			<br />
	     강의 시간(종료) :  <input type="time" id="endTime" name="endTime"
				value="${educationVO.endTime }" />
			<br />
			<form:errors path="endTime" />
			<br />
	     주간/야간 : 
	   	<c:forEach items="${typeList}" var="type">
				<c:if test="${educationVO.educationType eq type.cdId }">
					<input type="radio" class="educationType" name="educationType"
						checked="checked" value="${type.cdId}" /> ${type.cdNm}
	   			</c:if>
				<c:if test="${educationVO.educationType ne type.cdId }">
					<input type="radio" class="educationType" name="educationType"
						value="${type.cdId}" /> ${type.cdNm}
	   			</c:if>
			</c:forEach>
			<form:errors path="educationType" />
			<br />
	     비용 : 
	      <c:forEach items="${costList}" var="cost">
				<c:if test="${educationVO.cost eq cost.cdId }">
					<input type="radio" class="cost" name="cost" value="${cost.cdId}"
						checked="checked" /> ${cost.cdNm}
	      		</c:if>
				<c:if test="${educationVO.cost ne cost.cdId }">
					<input type="radio" class="cost" name="cost" value="${cost.cdId}" /> ${cost.cdNm}
	      		</c:if>
			</c:forEach>
			<form:errors path="cost" />
			<br />

			<input type="submit" id="write" value="등록하기" />
		</form:form>
	</div>
</body>
</html>