<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<script type="text/javascript">
	$(document).ready(function () {
		
		$("#massiveSelectCheckBox").click(function () {
			var isChecked = $(this).prop("checked");
			$(".deleteMemberId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click(function() {
			var isChecked = false;
			$(".deleteMemberId").each(function (index, data) {
				if(data.checked){
					isChecked = data.checked;
				}
			});
			
			if(!isChecked) {
				alert("삭제할 대상을 선택하세요.")
				return;
			}
			
			if (confirm("정말 삭제하시겠습니까?")) {
				var form = $("#searchForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/doMassiveDeleteMember" />");
				form.submit();
			}
		});
	});
</script>
<title>MemberListPage</title>
</head>
<body>
	<form name="searchForm" id="searchForm">
		<table style="text-align:center">
			<tr>
				<th style="width: 15px">
					<input type="checkbox" id="massiveSelectCheckBox" />
				</th>
				<th>아이디</th>
				<th>이름</th>
				<th>회원타입</th>
				<th>접속잠김여부</th>
				<th>탈퇴여부</th>
				<th>수정잠김여부</th>
				<th>비밀번호 변경</th>
			</tr>
			<c:forEach items="${ memberListVO.memberList }" var="member">
				<tr>
					<td>
						<input class="deleteMemberId" name="deleteMemberId" value="${member.id}" type="checkbox"/>
					</td>
					<td>
						<a href="<c:url value='/memberDetail/${member.id}'/>">${ member.id }</a>
					</td>
					<td>${member.name}</td>		
					<td>${member.memberType}</td>	
					<td>${member.isAccountLock}</td>
					<td>${member.isResign}</td>	
					<td>${member.isModifyLock}</td>	
					<td><input id="changePassword" type="button" value="비밀번호 변경" /></td>
				</tr>
			</c:forEach>


			<tr>
				<td colspan="7" align="center">
					<c:if test="${ memberListVO ne null }">
						${memberListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
					</c:if> 
				</td>
			</tr>

		</table>
	</form>
	<div>
		<span id="massiveDeleteBtn" style="cursor: pointer;">일괄삭제</span>
	</div>
</body>
</html>