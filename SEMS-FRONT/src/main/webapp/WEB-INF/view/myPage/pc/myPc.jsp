<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register My Computer</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">

	$(document).ready(function() {		
		
		$("#eduListByMember").click(function(event) {
			var educationTitle = $("#eduListByMember").val();
			$.post("<c:url value="/getEduLocationByTitle"/>", { 
					"title" : educationTitle
					}, function(data) {
						if (!data) {
							alert("인터넷 연결이 끊겼습니다.");
						} 
						else {
							$("#eduLocation").val(data);
							}
					});
		});		
	});
</script>

</head>
<body>
	<table border="1">
		<tr>
			<th>수강 중인 강의</th>
			<th>강의실</th>
			<th>내 PC IP</th>
			<th></th>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><input type="button" id="reportPC" value="신고"/></td>
		</tr>
		<tr>
			<td>
				<select id="eduListByMember" name="eduListByMember" tabindex="1">	 
					<c:forEach items="${eduListByMember}" var="eduList">	
					  <option value="${eduList.educationTitle}" id="educationTitle">${eduList.educationTitle}</option>
					</c:forEach>				  
				</select>	
			</td>
			<td>
				<input type="text" id="eduLocation" name="eduLocation" value=""/>
			</td>
			<td>
				<input type="text" value="${myPcIp}"/>
			</td>
			<td>
				<input type="button" id="doRegisterMyPC" value="등록"/>
			</td>
		</tr>
	</table>
</body>
</html>