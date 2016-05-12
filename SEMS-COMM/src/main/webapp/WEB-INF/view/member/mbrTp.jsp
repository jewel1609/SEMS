<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$(".mbrTpModifyBtn").click(function() {
			var root = $(this).parent().parent().children(":eq(0)");
			var cdNm = $(this).parent().parent().children(":eq(2)").children();

			if (cdNm.val() == "") {
				alert("CODE NAME을 써주세요.");
				return;
			}

			if (cdNm.val().length > 10) {
				alert("CODE NAME은 10글자 이하로 써주세요.");
				return;
			}

			if (confirm("입력한 내용으로 수정하시겠습니까?") == true) {

				$.post("<c:url value="/doMbrTpModify"/>", {
					"cdId" : root.val(),
					"cdNm" : cdNm.val()
				}, function(data) {
					if(data == "OK") {
						alert("수정되었습니다.");
						location.href="<c:url value="/mbrTpPage"/>";
					}
					else if(data == "FAIL"){
						alert("중복된 데이터로 수정할 수 없습니다.");
						location.href="<c:url value="/mbrTpPage"/>";
					}
				});
			} else {
				return;
			}
		});

		$(".mbrTpDeleteBtn").click(function() {

			var root = $(this).parent().parent().children(":eq(0)");
			console.log(root.val());

			if (confirm("삭제하시겠습니까?") == true) {
				location.href = "<c:url value="/doMbrTpDelete/"/>" + root.val();
			} else {
				return;
			}
		});

		$("#insertMbrTpBtn").click(function() {
			
			var cdId =  $("#cdId").val();
			var cdNm = $("#cdNm").val();
			
			if(cdId==""){
				alert("CODE ID를 써주세요.");
				return;
			}
			if(cdId.length > 4){
				alert("CODE ID는 4글자 이하로 써주세요.");
				return;
			}
			
			if(cdNm==""){
				alert("CODE NAME을 써주세요.");
				return;
			}
			
			if(cdNm.length > 10){
				alert("CODE NAME은 10글자 이하로 써주세요.");
				return;
			}
			
			if ( confirm("등록하시겠습니까?") == true ) {
				
				$.post("<c:url value="/doInsertMbrTp"/>", {
					"cdId" : cdId,
					"cdNm" : cdNm
				}, function(data) {
					if(data == "OK") {
						alert("등록되었습니다.");
						location.href="<c:url value="/mbrTpPage"/>";
					}
					else if(data == "FAIL"){
						alert("중복된 데이터를 넣을 수 없습니다.");
						location.href="<c:url value="/mbrTpPage"/>";
					}
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
	회원정보페이지
	<table border="1">
		<tr>
			<th>코드 아이디</th>
			<th>회원 등급</th>
			<th colspan="2"></th>
		</tr>
		<c:forEach items="${mbrTpVOList}" var="mbrTp">
			<tr>
				<input type="hidden" id="${mbrTp.cdId}" name="cdId"
					value="${mbrTp.cdId}" />
				<td>${mbrTp.cdId}</td>
				<td><input type="text" value="${mbrTp.cdNm}" /></td>
				<td><button type="button" class="mbrTpModifyBtn"
						style="font-size: 15px;">수정</button></td>
				<td><button type="button" class="mbrTpDeleteBtn"
						style="font-size: 15px;">삭제</button></td>
			</tr>
		</c:forEach>
		<tr>
				<td><input type="text" id="cdId" name="cdId"
					placeholder="코드를 입력하세요." value="${mbrTpVO.cdId}" /></td>
				<td><input type="text" id="cdNm" name="cdNm"
					placeholder="등급 이름을 입력하세요." value="${mbrTpVO.cdNm}" /></td>
				<td colspan="2"><input type="submit" id="insertMbrTpBtn"
					value="submit" /></td>
		</tr>
	</table>

</body>
</html>