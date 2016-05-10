<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
		
	$(document).ready(function() {
		
		$("#searchBtn").click(function() {
			
			var startYear = $("#startYear").val();
			var startMonth = $("#startMonth").val();
			
			startMonth = fillString(startMonth);
			
			var endYear = $("#endYear").val();
			var endMonth = $("#endMonth").val();

			endMonth = fillString(endMonth);
			
			var startSearchDate = startYear + startMonth;
			var endSearchDate = endYear + endMonth;
			
			startDate = parseInt(startDate);
			endDate = parseInt(endDate);
			
			if(startDate > endDate) {
				alert("기간 범위 오류");
				return;
			}
			
			$("#searchForm").attr("action", "<c:url value="/searchList"/>");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		});
	});
	
			function fillString(str) {
				
				if(str.length == 1) {
					str = "0" + str;
				}
				
				return str;
			}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="searchForm" id="searchForm">
<table>
	<tr>
		<th>기간</th>
		<td>
			<select id="startYear" name="startYear">
				<c:forEach var="year" begin="2010" end="2016" step="1">
					<option value="${ year }">${ year }</option>
				</c:forEach>
			</select>년
			
			<select id="startMonth" name="startMonth">
				<c:forEach var="month" begin="01" end="12" step="1">
					<option value="${ month }">${ month }</option>
				</c:forEach>
			</select>월
			~
			<select id="endYear" name="endYear">
				<c:forEach var="year" begin="2010" end="2016" step="1">
					<option value="${ year }">${ year }</option>
				</c:forEach>
			</select>년
			-
			<select id="endMonth" name="endMonth">
				<c:forEach var="month" begin="01" end="12" step="1">
					<option value="${ month }">${ month }</option>
				</c:forEach>
			</select>월
		</td>
	</tr>
	<tr>
		<th>교육명</th>
		<td>
			<input type="text" name="eduName" id="eduName" />
		</td>
	</tr>
	<tr>
		<th>교육 형태</th>
		<td>
			<select name="educationType" id="educationType">
				<option value="주간" selected="selected">주간</option>
				<option value="야간">야간</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>비용</th>
		<td>
			<select name="cost" id="cost">
				<option value="무료">무료</option>
				<option value="유료">유료</option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="검색" id="searchBtn" />
		</td>
	</tr>
</table>
</form>

<table border="1">
	<tr>
		<th>번호</th>
		<th>교육 이름</th>
		<th>교육분야</th>
		<th>강의 기간</th>
		<th>강의 형태</th>
		<th>비용</th>
	</tr>
</table>

</body>
</html>