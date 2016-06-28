<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	table {
	     border-collapse: collapse;
	     text-align: center;
	     width: 800px;
	}
	
	 table, th, td {
	     border: 1px solid black;
	}
</style>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript">

</script>
<title>수강생 출결 이력</title>
</head>
<body>
	수강생 출결 이력<br/><br/>
	
	교육명 : ${ attendanceList.get(0).educationTitle } <br/>
	교육 아이디 : ${ attendanceList.get(0).educationId } <br/><br/>
		
	<table>
		<tr>
			<th>날짜</th>
			<th>전체 출결</th>
		</tr>
		
		<c:forEach items="${ attendanceList }" var="attend">
			<tr>
				<td>${ attend.attendTime }</td>
				<td>${ attend.state }</td>
			</tr>
		</c:forEach>
	</table>
	<form id="searchForm">
		${paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm") }
	</form>
</body>
</html>