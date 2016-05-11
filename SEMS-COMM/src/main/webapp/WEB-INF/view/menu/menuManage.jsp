<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/comm/resources/js/jquery-1.12.1.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		/* $("#updateBtn").click(function(){
			if ( $(".sort").val() == "" ) {
				alert("값을 입력해주세요.");
				$(".sort").focus();
				return;
			}
			
		}); */
		
	});
	
</script>
<body>

	<form:form commandName="MenuManageVO" method="post" action="/comm/doMenuUpdate">
		<table>
			<c:forEach items="${menuList }" var="menuList" >
				<tr>
					<td><input type="hidden" class="codeId${menuList.codeId}" name="codeId" value="${menuList.codeId }" /> ${menuList.codeId }</td>
					<td><input type="hidden" class="codeName${menuList.codeName}" name="codeName" value="${menuList.codeName }" />${menuList.codeName }</td>
					<td><input type="hidden" class="url${menuList.url}" name="url" value="${menuList.url }" />${menuList.url }</td>
					<td><input type="text" id="sort" name="sort" value="${menuList.sort }"/></td>
					<td><form:errors path="sort" /></td>
				</tr>
			</c:forEach>
			
		</table>
		<input type="submit" id="updateBtn" value="수정" />
	</form:form>
</body>
</html>