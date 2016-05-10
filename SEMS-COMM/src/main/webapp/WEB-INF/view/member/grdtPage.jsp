<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>졸업 구분 코드 설정 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		//수정버튼 클릭시
		$(".grdtModifyBtn").click(function (){
			
			if ( confirm("입력한 내용으로 수정하시겠습니까?") == true ) {
				
				var root =  $(this).parent().parent().children(":eq(0)");
				var cdNm = $(this).parent().parent().children(":eq(2)").children();
				
				$.post("/comm/doGrdtModify", {
					"cdId" : root.val(),
					"cdNm" : cdNm.val()
				}, function(data) {

					var jsonData = {};

					try {
						jsonData = JSON.parse(data);
					} catch (e) {
						jsonData.result = false;
					}
					
				});
			}
			else {
				return;
			}
		});
		
		
		//삭제버튼 클릭시
		$(".grdtDeleteBtn").click(function (){
			
			var root = $(this).parent().parent().children(":eq(0)");
			console.log(root.val());
			
			if ( confirm("삭제하시겠습니까?") == true ) {
				location.href = "/comm/doGrdtDelete/"+ root.val();
			}
			else {
				return;
			} 
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
					<input type="hidden" id="${graduation.cdId}" name="cdId" value="${graduation.cdId}"/>
					<td style="text-align:center">${graduation.cdId}</td>
					<td>
						<input style="text-align:center" type="text" id="${graduation.cdNm}" name="cdNm" value="${graduation.cdNm}"/>
					</td>
					
					<td><input type="button" class="grdtModifyBtn" value="수정"/></td>
					<td><input type="button" class="grdtDeleteBtn" value="삭제"/></td>
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