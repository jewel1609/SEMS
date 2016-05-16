<!-- 이기연 -->
<!-- ㅂㅈㄷㄱㅂㅈㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹ
ㄷㅂㅈㄷㅂㅈㄷㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹ
ㅂㅈㄷㅂㅈㄷㅇㄴㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇ
ㅂㅈㄷㄴㅇㅍㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴ
ㅂㅈㄷㅂㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷ
ㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷ
ㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅂㅈㄷㅈㅂㄷㅂㅈㄷ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${qnaVO.replyId} detail page......
<table>
	<tr>
		<th>문의 내용</th>
		<th>답변 사항</th>
	</tr>
	<tr>
		<td>${qnaVO.description}</td>
		<td>${qnaAnswerVO.description}</td>
	</tr>
</table>
</body>
</html>