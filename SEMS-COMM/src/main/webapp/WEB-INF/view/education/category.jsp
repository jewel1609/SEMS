<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.12.1.js"/> "></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#newLargeCategoryBtn").click(function(){
			$("#newLargeCategoryContainer").toggle();
		});
		$("#addLargeCategoryBtn").click(function(){
			$.post(
					'<c:url value="/education/addLargeCategory"/>'
					, $("#newLargeCategoryForm").serialize()
					, function(data){
						console.log(data);
					}
			);			
		});
	});
</script>
<style type="text/css">
	#newLargeCategoryContainer {
		display: none;
	}
</style>
</head>
<body>

	<ul>
		<c:if test="${ not empty largeCategoryList }">
			<c:forEach items="${ largeCategoryList }">
				<li></li>
			</c:forEach>
		</c:if>
	</ul>
	<input type="button" id="newLargeCategoryBtn" value="추가">
	<div id="newLargeCategoryContainer">
		<form:form commandName="newLargeCategoryForm" method="post">
			<input type="text" id="newLargeCategoryId" placeholder="category id">
			<input type="text" id="newLargeCategoryName" placeholder="category name">
			<input type="button" id="addLargeCategoryBtn" value="추가하기">
		</form:form>
	</div>
	
	<ul>
		<li></li>
	</ul>
	
	<ul>
		<li></li>
	</ul>

</body>
</html>