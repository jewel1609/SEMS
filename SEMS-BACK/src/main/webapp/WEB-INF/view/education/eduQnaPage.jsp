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
			location.href="<c:url value='/${educationId}/eduQna' />";
		})
		;
	   $("#searchBtn").click( function() {
			
			if( $("#search option:selected").val() == "title"){
				if ($("#searchKeyword").val() == ""){
					alert("검색어를 입력하세요!");
					return;
				}
			}
			movePage('0');
		});
		
      $("#joinAplyBtn").click(function() {
            var eduHistoryId = $("#eduHistoryId").val();
                                 location.href = "<c:url value='/joinAply/"+eduHistoryId+"'/>";
                              });

      $("#joinCnclBtn").click(function() {
    	    var educationHistoryId = $("#historyId2").val();
			var memberId = $("#memberId2").val();
			var description = $("#description").val();
		
			if( description == null || description ==""){
				alert("내용을 입력해주세요.")
				return;
			}
			console.log(educationHistoryId);
			console.log(memberId);
			location.href = "<c:url value='/joinCncl/"+educationHistoryId+"/"+memberId+"/"+description+"'/>";
	   }); 
		
		$(".joinaply").click(function(){
			 $("#historyId").val($(this).data('id'));
			 $("#memberId").val($(this).data('whatever'));
	   });
		
		$(".cnclaply").click(function(){
			 $("#historyId2").val($(this).data('id'));
			 $("#memberId2").val($(this).data('whatever'));
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
			<td>eduQnaId</td>
			<td>educationId</td>
			<td>isDelete</td>
			<td>memberId</td>
			<td>title</td>
			<td>contents</td>
			<td>createDate</td>
			<td>modifyDate</td>
			<td>hits</td>
		</tr>
		<c:forEach items="${ eduQnaListVO.eduQnaList }" var="eduQna">
				<tr>
					<td>${eduQna.eduQnaId }</td>
					<td>${eduQna.educationId }</td>
					<td>${eduQna.isDelete }</td>
					<td>${eduQna.memberId }</td>
					<td>${eduQna.title }</td>
					<td>${eduQna.contents }</td>
					<td>${eduQna.modifyDate }</td>
					<td>${eduQna.hits }</td>
				</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<form name="searchForm" id="searchForm">
					<div style = "text-align:center;">
						<c:if test="${ eduQnaListVO ne null }">
							${eduQnaListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
						</c:if> 
					</div>
					<div style="text-align: right;">
						<select id="search" name="search">
							<option id="title" value="title">제목</option>
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword" value="${ eduQnaSearchVO.searchKeyword }"/>
						<input type="button" id="searchBtn" value="검색" />
						<input type="button" id="searchInitBtn" value="검색 초기화" />
					</div>
				</form>
			</td>
		</tr>	
	</table>
	
	
</body>
</html>