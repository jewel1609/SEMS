<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.12.1.js"/>"></script>
<script type="text/javascript">

	$(document).ready(function () {
		
		$(".modifyEduCostBtn").click(function () {
			
			var root = $(this).parent().chldren(":equal(1)");
			
			if (root.val() == null) {
				alert("수정할 이름을 입력하세요.");
				return 0;
			}
			
			alert(root.attr("id"));
			
			$.post(
					"/modifyEduCost"
					, {
						"coNm" : root.val()
						, "coId" : root.attr("id")
					}
					, function(data) {
						try {
							jsonData = JSON.parse(data);
						}
						catch(e) {
							jsonData.result = false;
						}
						if(jsonData.result){
							alert("수정되었습니다.");
							var coId = jsonData.majorGroupId;
							var resultCoId = "#" + coId;
							var coNm = jsonData.coNm;
							URLEncoder.encode(coNm , "UTF-8");
							$(resultCoId).val(coNm);
						}
						else{
							alert("세션이 만료되었습니다. 다시 로그인해주세요.");
							location.href="<c:url value="/comm"/>";
						}
					}
					
			);
			

		});
		
	});
</script>

</head>
<body>

<div>
	<table>
		<c:forEach items="${educationCost}" var="cost">
			<tr>
				<td>${cost.coId}</td>
				<td><input type="text" id="${cost.coId}" name="coNm" value="${cost.coNm}" /></td>
				<td><span class="modifyEduCostBtn" id="modifyEduCostBtn${cost.coId}">수정</span></td>
				<td><span class="deleteEduCostBtn" id="deleteEduCostBtn${cost.coId}">삭제</span></td>
			</tr>
		</c:forEach>
			<tr>
				<td>코드 : <input type="text" id="newCode" name="newCode" /></td>
				<td>가격 : <input type="text" id="newCost" name="newCost" /></td>
				<td colspan="2"><input type="button" id="insertEduBtn" name="insertEduBtn" value="추가" /></td>
			</tr>
	</table>
</div>

</body>
</html>