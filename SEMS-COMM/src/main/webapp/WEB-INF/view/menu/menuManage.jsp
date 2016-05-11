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
		
		$(".up").click(function() {
			
			form.attr("method","POST")
			form.attr("action")
			
		});
		
	});
	
</script>
<body>

	<table border="1">
		<tr>
			<th>메뉴 순서</th>
			<th>메뉴 명</th>
			<th>메뉴 링크</th>
			<th>메뉴 변경</th>
		</tr>
		<c:forEach items="${menuList }" var="menuList" >
			<tr align="center">
				<td>${menuList.sort}</td>
				<td>${menuList.codeName}</td>
				<td>${menuList.url}</td>
				<td>
					<div>
						<c:if test="${menuList.sort gt 1}">
							<a href="/comm/upMenuList/${menuList.sort}/${menuList.codeId}">
								<input type="button" class="up" value="▲" />
							</a>
						</c:if>
					</div>
					<div>
						<c:if test="${menuList.sort lt 5}">
							<a href="/comm/downMenuList/${menuList.sort}/${menuList.codeId}">
								<input type="button" class="down" value="▼" />
							</a>
						</c:if>
					</div>						
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>