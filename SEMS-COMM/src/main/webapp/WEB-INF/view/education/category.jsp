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
		var categoryId = $("#categoryId");
		function validCatagoryId(){
			if ( categoryId.val() == null || categoryId.val().length < 2 || categoryId.val().length > 4 ) {
				$("#categoryIdError").empty();
				$("#categoryIdError").append("카테고리 아이디는 2글자 이상 4글자 이하여야 합니다.");
			}
			else {
				$.post(
						'<c:url value="/education/validCategoryId"/>'
						, 'categoryId='+categoryId.val()
						, function(response){
							
						}
				);
			}
		}
		$("#newLargeCategoryBtn").click(function(){
			$("#largeCategoryListContainer").append($("#newCategoryContainer").detach());
		});
		$("#newMediumCategoryBtn").click(function(){
			$("#mediumCategoryListContainer").append($("#newCategoryContainer").detach());
		});
		$("#newSmallCategoryBtn").click(function(){
			$("#smallCategoryListContainer").append($("#newCategoryContainer").detach());
		});
		$("#categoryId").keyup(function(){
			validCatagoryId();
		});
		$("#addLargeCategoryBtn").click(function(){
			$.post(
					'<c:url value="/education/addCategory"/>'
					, $("#newCategoryForm").serialize()
					, function(response){
						if ( response.result ) {
							
						}
						else {
							for ( var i = 0; i < response.data.length; i++ ) {
								$("#"+response.data[i].field+"Error").append(response.data[i].defaultMessage+"<br/>");
							}
						}
					}
			);			
		});
	});
</script>
<style type="text/css">
	#newLargeCategoryContainer {
	}
</style>
</head>
<body>

	<div id="largeCategoryListContainer">
		<ul>
			<c:if test="${ not empty largeCategoryList }">
				<c:forEach items="${ largeCategoryList }">
					<li></li>
				</c:forEach>
			</c:if>
		</ul>
		<input type="button" id="newLargeCategoryBtn" value="추가">
		<div id="newCategoryContainer">
			<form:form commandName="newCategoryForm" method="post">
				<input type="hidden" id="parentCategoryId" value="" >
				<input type="text" id="categoryId" name="categoryId" placeholder="category id">
				<span id="categoryIdError"></span>
				<input type="text" id="categoryName" name="categoryName" placeholder="category name">
				<span id="categoryNameError"></span>
				<input type="button" id="addCategoryBtn" value="추가하기">
			</form:form>
		</div>
	</div>
	
	<div id="mediumCategoryListContainer">
		<ul>
			<c:if test="${ not empty mediumCategoryList }">
				<c:forEach items="${ mediumCategoryList }">
					<li></li>
				</c:forEach>
			</c:if>
		</ul>
		<input type="button" id="newMediumCategoryBtn" value="추가">
	</div>
	
	<div id="smallCategoryListContainer">
		<ul>
			<c:if test="${ not empty smallCategoryList }">
				<c:forEach items="${ smallCategoryList }">
					<li></li>
				</c:forEach>
			</c:if>
		</ul>
		<input type="button" id="newSmallCategoryBtn" value="추가">
	</div>

</body>
</html>