<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("#writeBtn").click(function() {
		if($("#subject").val() == "") {
			alert("제목을 입력하세요!");
			$("#subject").focus();
			return;
		}
		
		$("#writeForm").submit();
	});
	
});
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>팀 게시판 댓글</title>
</head>
<body>
	
	<div class="content" style="float:left;">
		<form id="writeForm" name="writeForm" method="post" action="/team/doWrite" enctype="multipart/form-data">
		<table>
			<tr>
				<td>	
					공지사항 : <input type="checkbox" id="myCheck">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="subject" id="subject" size="50" style="width:550px;" placeholder="제목을 입력하세요." />
				</td>
			</tr>
			<tr>
				<td>
					<textarea name="content" rows="20" id="content" cols="49" style="width:550px;" placeholder="내용을 입력하세요."></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="file" name="file" />
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" id="writeBtn" value="저장" />
				</td>
			</tr>
		</table>
		
		
		</form>
	</div>
	<div class="clear"></div>
	
	
</body>
</html>