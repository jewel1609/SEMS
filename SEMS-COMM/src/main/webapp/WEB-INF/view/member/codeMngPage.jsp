<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>코드 설정 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#mainBtn").click(function(){
			location.href="<c:url value="/"/>";
		});
		
		$("#cancleBtn").click(function (){
			$("#cdId").val("");
			$("#cdNm").val("");
			$("#cdTp").val("");
			$("#cdTp2").val("");
			
		});
		
		//수정버튼 클릭시
		$(".codeMngModifyBtn").click(function (){
			var cdId =  $(this).parent().parent().children(":eq(0)");
			var cdNm = $(this).parent().parent().children(":eq(2)").children();
			var cdTp =  $(this).parent().parent().children(":eq(3)");
			var cdTp2 = $(this).parent().parent().children(":eq(5)").children();
			
			/* cdNm */
			if(cdNm.val()==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			
			if(cdNm.val().length > 20){
				alert("CODE NAME은 20글자 이하로 써주세요.");
				return;
			}
			
			/* cdTp2 */
			if(cdTp2.val()==""){
				alert("CODE 세부 카테고리를 써주세요.");
				return;
			}
			
			if(cdTp2.val().length > 8){
				alert("CODE 세부 카테고리는 8글자 이하로 써주세요.");
				return;
			}
			
			if ( confirm("입력한 내용으로 수정하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doCodeMngModify"/>", {
					"cdId" : cdId.val(),
					"cdNm" : cdNm.val(),
					"cdTp" : cdTp.val(),
					"cdTp2" : cdTp2.val()
				}, function(data) {
					
					if(data == 'OK') {
						alert("수정되었습니다.");
					}
					// location.href="<c:url value="/codeMngPage"/>";
				});
			}
			else {
				return;
			}
		});
		
		
		//삭제버튼 클릭시
		$(".codeMngDeleteBtn").click(function (){
			
			var root = $(this).parent().parent().children(":eq(0)");
			
			if ( confirm("삭제하시겠습니까?") == true ) {
				location.href = "/comm/doCodeMngDelete/"+ root.val();
			}
			else {
				return;
			} 
		});
		
		//추가버튼 클릭시
		$("#codeMngInsertBtn").click(function (){
			var cdId =  $("#cdId").val();
			var cdNm = $("#cdNm").val();
			var cdTp = $("#cdTp").val();
			var cdTp2 = $("#cdTp2").val();
			
			if(cdId==""){
				alert("CODE ID를 써주세요.");
				return;
			}
			if(cdId.length > 8){
				alert("CODE ID는 8글자 이하로 써주세요.");
				return;
			}
			
			if(cdNm==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			if(cdNm.length > 20){
				alert("CODE NAME은 20글자 이하로 써주세요.");
				return;
			}
			
			/* tp */
			if(cdTp==""){
				alert("CODE 카테고리를 써주세요.");
				return;
			}
			if(cdTp.length > 4){
				alert("CODE 카테고리는 4글자 이하로 써주세요.");
				return;
			}
			
			/* tp2 */
			if(cdTp2==""){
				alert("CODE 세부 카테고리를 써주세요.");
				return;
			}
			if(cdTp2.length > 8){
				alert("CODE 세부 카테고리는 8글자 이하로 써주세요.");
				return;
			}
			
			if ( confirm("등록하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doCodeMngInsert"/>", {
					"cdId" : cdId,
					"cdNm" : cdNm,
					"cdTp" : cdTp,
					"cdTp2" : cdTp2
				}, function(data) {
					location.href="<c:url value="/codeMngPage"/>";
				});
			}
			else {
				return;
			}
		});
		
	});
	
</script>

</head>
<body>

	<table>
		<th>코드 아이디</th>
		<th>코드 타이틀</th>
		<th>코드 카테고리</th>
		<th>코드 세부 카테고리</th>
		
		<c:forEach items="${codeMngList}" var="codeMng">
			<tr>
				<input type="hidden" id="${codeMng.cdId}" name="cdId" value="${codeMng.cdId}"/>
				<td style="text-align:center">${codeMng.cdId}</td>
				<td>
					<input style="text-align:center" type="text" id="${codeMng.cdNm}" name="cdNm" value="${codeMng.cdNm}"/>
				</td>
				<input type="hidden" id="${codeMng.cdTp}" name="cdTp" value="${codeMng.cdTp}"/>
				<td style="text-align:center">${codeMng.cdTp}</td>
				<td>
					<input style="text-align:center" type="text" id="${codeMng.cdTp2}" name="cdTp2" value="${codeMng.cdTp2}"/>
				</td>
				<td><input type="button" class="codeMngModifyBtn" value="수정"/></td>
				<td><input type="button" class="codeMngDeleteBtn" value="삭제"/></td>
			</tr>
		</c:forEach>
	
		<tr>
			<td>
				<input type="text" id="cdId" name="newCdId" value=""/>
			</td>
			<td>
				<input type="text" id="cdNm" name="newCdNm" value=""/>
			</td>
			<td>
				<input type="text" id="cdTp" name="newCdTp" value=""/>
			</td>
			<td>
				<input type="text" id="cdTp2" name="newCdTp2" value=""/>
			</td>
			<td><input type="button" id="codeMngInsertBtn" value="추가" /></td>
			<td><input type="button" id="cancleBtn" value="취소" /></td>
		</tr>
		<br/>
	</table>
		
		<input type="button" id="mainBtn" value="처음으로"/>
	
	
	
</body>
</html>