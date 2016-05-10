<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>졸업 구분 코드 설정 페이지</title>
<script type="text/javascript" src="<c:url value="/resources/js/query-1.12.1.js"/>"></script>
<script type="text/javascript">

	$(document).ready(function (){
		
		$("#grdtModifyBtn").click(function (){
			
			var modiAndDelForm = $("#modiAndDelForm").
			form.
			
		});
		
	});
	
</script>

</head>
<body>

	<form id="modiAndDelForm">
	
		<table>
			<th>Code Id</th>
			<th>Code Name</th>
			
			<c:forEach items="${grtdTpList}" var="graduation">
				<tr>
					<input type="hidden" name="cdId" value="${graduation.cdId}"/>
					<td style="text-align:center">${graduation.cdId}</td>
					<td>
						<input style="text-align:center" type="text" class="cdNm" name="cdNm" value="${graduation.cdNm}"/>
						<form:errors path="cdNm"/><br/>
					</td>
					
					<td><input type="submit" id="grdtModifyBtn" value="수정"/></td>
					<td><input type="submit" id="grdtDeleteBtn" value="삭제"/></td>
				</tr>
			</c:forEach>
		
	</form>
	
	<form:form commandName="GrdtTpVO"  method="post" action="/doInsertGrdtCd">
			<tr>
				<td>
					<input type="text" id="cdId" name="newCdId" value=""/>
					<form:errors path="newCdId"/><br/>
				</td>
				<td>
					<input type="text" id="cdNm" name="newCdNm" value=""/>
					<form:errors path="newCdNm"/><br/>
				</td>
				<td><input type="button" id="grdtInsertBtn" value="추가" /></td>
				<td><input type="reset" value="취소" /></td>
			</tr>
		</table>
	</form:form>	
		
	
	
	
</body>
</html>