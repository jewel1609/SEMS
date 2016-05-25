<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready( function() {
		
		$("#searchType").hide();
		$("#searchKeyword").hide();
		$("#startDate").hide();
		$("#endDate").hide();
		
		$(".onlyText").keyup(function(event) {
			regexp = /[@\#$%<>&\()\=_\’]/gi;
	
			v = $(this).val();
			if (regexp.test(v)) {
				alert("특수문자를 포함할 수 없습니다.");
				$(this).val(v.replace(regexp, ''));
			}
		});
		
		$("#searchBtn").click( function() {
			
/* 			var startDate = $("#startDate").val();
			var closeDate = $("#endDate").val();
				
			if (startDate == "" || endDate == "") {
				// 검색 기간 입력 되지 않은 경우
				if (startDate == "") {
					alert("검색시작일을 지정해주세요.");
					$("#startDate").focus();
					return;
				}
				
				if (endDate == "") {
					alert("검색 마지막일을 지정해주세요.");
					$("#endDate").focus();
					return;
				}
			} 
			else{
				// 검색 기간 입력 되었지만
				// 검색 시작일이 더 클 경우
				if(startDate > endDate){
					alert("검색 기간이 잘못 설정되었습니다.");
					return;
				}
			} */
			
			if( $("#search option:selected").val() == "id"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "type"){
				if ($("#searchType").val() == ""){
					alert("회원 종류를 선택하세요!");
					return;
				}
			}
			else if( $("#search option:selected").val() == "date"){
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
					
				if (startDate == "" || endDate == "") {
					// 검색 기간 입력 되지 않은 경우
					if (startDate == "") {
						alert("검색시작일을 지정해주세요.");
						$("#startDate").focus();
						return;
					}
					
					if (endDate == "") {
						alert("검색 마지막일을 지정해주세요.");
						$("#endDate").focus();
						return;
					}
				} 
				else{
					// 검색 기간 입력 되었지만
					// 검색 시작일이 더 클 경우
					if(startDate > endDate){
						alert("검색 기간이 잘못 설정되었습니다.");
						return;
					}
				}
			}
			else{
				alert("검색조건을 입력하세요.");
			}
			
			movePage('0');
		});
		
		$("#searchInitBtn").click(function() {
			
			location.href="<c:url value='/member/loginHistoryInit' />";
			
		});
		
		$("#search").change(function() {
			var option = $("#search option:selected").val();
			if (option == "id") {
				$("#searchKeyword").show();
				$("#searchType").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "type"){
				$("#searchType").show();
				$("#searchKeyword").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			 else if( option == "date"){
				$("#searchType").hide();
				$("#searchKeyword").hide();
				$("#startDate").show();
				$("#endDate").show();
			}
		});
			var option = $("#search option:selected").val();
			if (option == "id") {
				$("#searchKeyword").show();
				$("#searchType").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			else if( option == "type"){
				$("#searchType").show();
				$("#searchKeyword").hide();
				$("#startDate").hide();
				$("#endDate").hide();
			}
			 else if( option == "date"){
				$("#searchType").hide();
				$("#searchKeyword").hide();
				$("#startDate").show();
				$("#endDate").show();
			}
	});
</script>
<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border-top: 1px solid #bcbcbc;
    border-bottom: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>

<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>minutesId</th>
			<th>memberId</th>
			<th>minutesDate</th>
			<th>minutesAgenda</th>
			<th>attendance</th>
			<th>minutesPlace</th>
			<th>minutesContent</th>
			<th>decisionSubject</th>
			<th>remarks</th>
			<th>teamId</th>
		</tr>
		<c:forEach items="${ minutesListVO.minutesList }" var="minutes">
		<tr>
			<td class="menutd">${ minutes.minutesId }</td>
			<td class="menutd">${ minutes.memberId }</td>
			<td class="menutd">${ minutes.minutesDate }</td>
			<td class="menutd">${ minutes.minutesAgenda }</td>
			<td class="menutd">${ minutes.attendance }</td>
			<td class="menutd">${ minutes.minutesPlace }</td>
			<td class="menutd">${ minutes.minutesContent }</td>
			<td class="menutd">${ minutes.decisionSubject }</td>
			<td class="menutd">${ minutes.remarks }</td>
			<td class="menutd">${ minutes.teamId }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
				<div style = "text-align:center;">
					<c:if test="${ loginHistoryListVO ne null }">
						${minutesListVO.paging.getPagingList("pageNo", "[@]", "[이전]", "[다음]", "searchForm")}
					</c:if> 
				</div>
				<div style="text-align: right;">
					<select id="search" name="search" >
						<option value="">선택</option>
						<c:if test="${minutesSearchVO.search eq 'date' }">
							<option id="date" value="date" selected="selected">날짜</option>
						</c:if>
						<c:if test="${minutesSearchVO.search ne 'date' }">
							<option id="date" value="date" >날짜</option>
						</c:if>
					</select>
					
					<input type="text" class="onlyText" id="searchKeyword" name="searchKeyword" value="${ minutesSearchVO.searchKeyword }"/>
					
					<input type="date" name="startDate" id="startDate" value="${minutesSearchVO.startDate}" />
					<input type="date" name="endDate" id="endDate" value="${minutesSearchVO.endDate}" /> 
					
					<input type="button" id="searchBtn" value="검색" />
					<input type="button" id="searchInitBtn" value="검색 초기화" />
				</div>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>