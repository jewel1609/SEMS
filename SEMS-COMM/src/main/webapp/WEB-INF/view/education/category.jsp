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
		var categoryName = $("#categoryName");
		var categoryType = $("#categoryType");
		
		function initCategoryIdAndName(){
			categoryId.val('');
			categoryName.val('');
			$("#categoryIdError").empty();
			$("#categoryNameError").empty();
		}
		
		function validCatagoryId(){
			if ( categoryId.val() == null || categoryId.val().length < 2 ) {
				$("#categoryIdError").empty();
				$("#categoryIdError").append("카테고리 아이디는 2글자 이상 4글자 이하여야 합니다.");
				return false;
			}
			else {
				$("#categoryIdError").empty();
				$.post(
						'<c:url value="/education/validCategoryId"/>'
						, 'categoryId=' + categoryId.val() + '&categoryType=' + categoryType.val()
						, function(isExist){
							if ( isExist == 'true' ) {
								$("#categoryIdError").empty();
								$("#categoryIdError").append("이미 존재하는 아이디 입니다.");
							}
							else {
								$("#categoryIdError").empty();
								$("#categoryIdError").append("사용 가능한 아이디 입니다.");
							}
						}
				);
				return true;
			}
		}
		
		function validCatagoryName(){
			if ( categoryName.val() == null || categoryName.val().length < 1 ) {
				$("#categoryNameError").empty();
				$("#categoryNameError").append("카테고리 이름은 30글자 이하여야 합니다.");
				return false;
			}
			else {
				$("#categoryNameError").empty();
				$.post(
						'<c:url value="/education/validCategoryName"/>'
						, 'categoryName=' + categoryName.val() + '&categoryType=' + categoryType.val()
						, function(isExist){
							if ( isExist == 'true' ) {
								$("#categoryNameError").empty();
								$("#categoryNameError").append("이미 존재하는 이름 입니다.");
							}
							else {
								$("#categoryNameError").empty();
								$("#categoryNameError").append("사용 가능한 이름 입니다.");
							}
						}
				);
				return true;
			}
		}
		
		$("#newLargeCategoryBtn").click(function(){
			initCategoryIdAndName();
			categoryType.val('large');
			$("#largeCategoryListContainer").append($("#newCategoryContainer").detach());
		});
		
		$("#newMediumCategoryBtn").click(function(){
			initCategoryIdAndName();
			categoryType.val('medium');
			$("#mediumCategoryListContainer").append($("#newCategoryContainer").detach());
		});
		
		$("#newSmallCategoryBtn").click(function(){
			initCategoryIdAndName();
			categoryType.val('small');
			$("#smallCategoryListContainer").append($("#newCategoryContainer").detach());
		});
		
		$("#categoryId").keyup(function(){
			validCatagoryId();
		});
		
		$("#categoryName").keyup(function(){
			validCatagoryName();
		});
		
		$("#addCategoryBtn").click(function(){
			if ( validCatagoryId() && validCatagoryName() ) {
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
			}
		});
		
	});
</script>
<style type="text/css">
	#newLargeCategoryContainer {
	}
	
	select {
		size: "10";
	}
</style>
</head>
<body>

	<div id="largeCategoryListContainer">
		<select id="largeCategoryList" size="10">
			<c:if test="${ not empty largeCategoryList }">
				<c:forEach items="${ largeCategoryList }" var="category">
					<option value="${ category.categoryId }"> ${ category.categoryName } </option>
				</c:forEach>
			</c:if>
		</select>
		<input type="button" id="newLargeCategoryBtn" value="추가">
		<div id="newCategoryContainer">
			<form:form commandName="newCategoryForm" method="post">
				<input type="hidden" id="parentCategoryId" name="parentCategoryId" >
				<input type="hidden" id="categoryType" name="categoryType" value="large" >
				<input type="text" id="categoryId" name="categoryId" placeholder="category id" maxlength="4">
				<span id="categoryIdError"></span>
				<input type="text" id="categoryName" name="categoryName" placeholder="category name" maxlength="30">
				<span id="categoryNameError"></span>
				<input type="button" id="addCategoryBtn" value="추가하기">
			</form:form>
		</div>
	</div>
	
	<div id="mediumCategoryListContainer">
		<select id="mediumCategoryList">
		</select>
		<input type="button" id="newMediumCategoryBtn" value="추가">
	</div>
	
	<div id="smallCategoryListContainer">
		<select id="smallCategoryList">
		</select>
		<input type="button" id="newSmallCategoryBtn" value="추가">
	</div>

</body>
</html>