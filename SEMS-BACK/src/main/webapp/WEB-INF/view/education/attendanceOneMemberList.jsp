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
$(document).ready(function() {
	$("#searchBtn").click(function() {
		if ($("#startDate") || $("#endDate")) {
			
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();

			if (startDate == "" || endDate == "") {

				if (startDate == "") {
					alert("검색 시작일을 입력해주세요.");
					return;
				}
				if (endDate == "") {
					alert("검색 종료일을 입력해주세요.");
					return;
				}
			} else if (startDate > endDate) {
				alert("검색일을 잘못 입력하셨습니다.");
				return;
			}
		} else {
			alert("검색 조건을 입력하세요.");
		}
		movePage('0');
	});
});
</script>
<title>수강생 출결 이력</title>
</head>
<body>
	수강생 출결 이력<br/><br/>
	
	수강생 아이디 : ${ AllEduAllAttendanceList.get(0).get(0).memberId } <br/><br/>
	
	정상 출석 : ○ / 지각 : △ / 조퇴 : ● / 결석 : X<br/><br/>
	
	
	
		
		<c:forEach items="${ AllEduAllAttendanceList }" var="oneMemberATD">
		
			교육명 : ${ oneMemberATD.get(0).educationTitle } <br/>
			교육 아이디 : ${ oneMemberATD.get(0).educationId } <br/>
			<table>
				<tr>
					<th>날짜</th>
					<th>출결 상태</th>
				</tr>
		
				<c:forEach items="${ oneMemberATD }" var="attend">
					<tr>
						<td style="CURSOR:hand;" title="${ attend.leaveTime }">${ attend.attendTime }</td>
						<td>
						<c:if test="${ attend.state eq 'X' }">
							${ attend.state } <a href="<c:url value='/attendanceHistory/updateState'/>">변경</a>
						</c:if>
						<c:if test="${ attend.state ne 'X' }">${ attend.state }</c:if>
						</td>
					</tr>
				</c:forEach>	
			</table><br/>
		</c:forEach>
	
	<%
		int day = 0;
		int late = 0;
		int absence = 0;
		int earlyLeave = 0;
		int attend = 0;
	%>
	
	<c:forEach items="${ AllEduAllAttendanceList }" var="oneMemberATD">
			<c:forEach items="${ oneMemberATD }" var="attend">
				<%
					day++;
				%>
				<c:if test="${ attend.state eq '△' }">
					<%
						late++;
					%>
				</c:if>
				<c:if test="${ attend.state eq 'X' }">
					<%
						absence++;
					%>
				</c:if>
				<c:if test="${ attend.state eq '●' }">
					<%
						earlyLeave++;
					%>
				</c:if>
				<c:if test="${ attend.state eq '○' }">
					<%
						attend++;
					%>
				</c:if>
		</c:forEach>
	</c:forEach>
	
	<table>
		<tr>
			<th>전체 일수</th>
			<th>정상 출석 일수</th>
			<th>지각/조퇴 일수</th>
			<th>결석 일수</th>
		</tr>
		<tr>
			<td>
				<% pageContext.setAttribute("day", day); %>
				${ day }
			</td>
			<td>
				<% pageContext.setAttribute("attend", attend); %>
				${ attend }
			</td>
			<td>
				<% 
					int lateAndEarlyLeave = late + earlyLeave;
					pageContext.setAttribute("lateAndEarlyLeave", lateAndEarlyLeave);
				%>
				${ lateAndEarlyLeave }
			</td>
			<td>
				<% pageContext.setAttribute("absence", absence); %>
				${ absence }
			</td>
		</tr>
	</table>
	
	<form name="searchForm" id="searchForm">
		<input type="date" id="startDate" name="startDate"/> 
		<input type="date" id="endDate" name="endDate"/> 
		<input type="button" id="searchBtn" value="검색" /> 
		<input type="button" id="searchInitBtn" value="검색 초기화" />
	</form>
</body>
</html>