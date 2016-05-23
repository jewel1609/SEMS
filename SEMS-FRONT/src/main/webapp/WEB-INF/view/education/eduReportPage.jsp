<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<script type="text/javascript"
   src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">

	$(document).ready(function() {
	 
		$("#searchInitBtn").click(function() {
			location.href="<c:url value='/${educationId}/eduReport' />";
		});
		
		$("#searchType").change(function(){
			var searchType = $(this).val();
			if ( searchType == "1" || searchType == "2" ) {
				$("#searchKeyword").show();				
				$("#searchKeyword").siblings().hide();
			}
			else if ( searchType == "3" ) {
				$("#trainee").show();
				$("#trainee").siblings().hide();
			}
			else if ( searchType == "4" ) {
				$("#team").show();
				$("#team").siblings().hide();
			}
		});
		
		$("#searchBtn").click( function() {
			
			var searchType = $("#searchType option:selected").val();
			
			if( searchType == "1" || searchType == "2"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			
			if( searchType == "3"){
				if ($("#trainee").val() == null){
					alert("검색가능한 교육생이 없습니다!");
					return;
				}
			}
			
			if( searchType == "4"){
				if ($("#team").val() == null){
					alert("검색가능한 팀이 없습니다!");
					return;
				}
			}
			
			movePage('0');
		});
	   
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<table>
		<tr>
			<td>eduReportId</td>
			<td>educationId</td>
			<td>isDelete</td>
			<td>memberId</td>
			<td>title</td>
			<td>contents</td>
			<td>createDate</td>
			<td>modifyDate</td>
			<td>hits</td>
		</tr>
		<c:forEach items="${ eduReportListVO.eduReportList }" var="eduReport">
				<tr>
					<td>${eduReport.eduReportId }</td>
					<td>${eduReport.educationId }</td>
					<td>${eduReport.isDelete }</td>
					<td>${eduReport.memberId }</td>
					<td>${eduReport.title }</td>
					<td>${eduReport.contents }</td>
					<td>${eduReport.modifyDate }</td>
					<td>${eduReport.hits }</td>
				</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ eduReportListVO ne null }">
							${eduReportListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
					<div style="text-align: right;">
						<select id="searchType" name="searchType">
							<option id="title" value="2">제목</option>
							<option id="id" value="1">아이디</option>
							<option value="3">교육생</option>
							<option value="4">팀명</option>
						</select>
						<div style="display: inline;">
							<select id="trainee" name="trainee" style="display: none;">
								<c:if test="${ eduTrainees eq null or eduTrainees.size() eq 0 }">
								<option disabled="disabled">교육생이 없습니다.</option>
								</c:if>
								<c:if test="${ eduTrainees.size() gt 0 }">
								<c:forEach items="${ eduTrainees }" var="trainee">
								<option value="${ trainee.id }">${ trainee.name }</option>
								</c:forEach>
								</c:if>
							</select>
							<select id="team" name="team" style="display: none;">
								<c:if test="${ eduTeam eq null or eduTeam.size() eq 0 }">
								<option disabled="disabled">팀이 없습니다.</option>
								</c:if>
								<c:if test="${ eduTeam.size() gt 0 }">
								<c:forEach items="${ eduTrainees }" var="team">
								<option value="${ team.id }">${ team.name }</option>
								</c:forEach>
								</c:if>
							</select>
							<input type="text" id="searchKeyword" name="searchKeyword" value="${ eduReportSearchVO.searchKeyword }"/>
						</div>
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>	
	</table>
	
	
</body>
</html>