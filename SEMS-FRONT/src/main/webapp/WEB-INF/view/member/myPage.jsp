<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".강의게시판").click( function() {
			$.post("<c:url value="/checkValidationCourseAccess" />", { }, function(data) {
				if(!data) {
					alert("인터넷 연결이 끊겼습니다.");
				} else if (data == "NO") {
					alert("진행 중인 교육을 수강하고 있지 않아 접근이 불가능합니다.");
					document.location.href = "<c:url value="/member/mypage" />";
					return;
				} 
			});
		});
		
	});
</script>
<title>회원 정보</title>
</head>
<body>

	<table border="1" style="border-collapse: collapse; text-align: center;">
		<c:forEach items="${menuList }" var="menuList" >
			<tr>
				<td class="${menuList.codeName}"><a href="<c:url value='/member/${menuList.url }' />">${menuList.codeName }</a></td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>