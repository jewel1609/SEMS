<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.inputButton {
	border:none;
	border-radius:5px;
	padding:6px 12px;
	font-weight:bold;
	text-transform:uppercase;
	color:#FFFFFF;
	background-color:#E05149;
}
</style>
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
				var form = $("#memberListForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/doMassiveDeleteMember" />");
				form.submit();
			}
		});
		
		$("#changePassword").click(function () {
			var checkCount = 0;
			var memberId = 0;
			
			$(".deleteMemberId").each(function (index, data) {
				if(data.checked){
					checkCount = checkCount + 1;
					memberId = $(this).parent().parent().children(":eq(1)").text();
				}
			});
			
			if ( checkCount > 1 ) {
				alert("하나만 선택하세요.");
			}
			else if (checkCount == 1) {
				var jspopup = window.open("<c:url value="/memberManage/changePassword/" />" + memberId
						, "javascript 페이지...."
						, "width=500, height=450, resizable=no, scrollbars=no");
			}
			else {
				alert("비밀번호를 변경할 대상을 선택하세요.")
			}
			
		});
	});
</script>
<title>MemberListPage</title>
</head>
<body>
	
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
			</tr>
			<form name="memberListForm" id="memberListForm">
				<c:forEach items="${ memberListVO.memberList }" var="member">
					<tr>
						<td>
							<input class="deleteMemberId" name="deleteMemberId" value="${member.id}" type="checkbox"/>
						</td>
						<td>
							<a href="<c:url value="/memberDetail/${member.id}"/>">${ member.id }</a>
						</td>
						<td>${member.name}</td>		
						<td>${member.memberType}</td>	
						<td>${member.isAccountLock}</td>
						<td>${member.isResign}</td>	
						<td>${member.isModifyLock}</td>	
					</tr>
				</c:forEach>
			</form>
			<tr>
				<td colspan="7" align="center">
					<form id="searchForm">
						<div>
							<c:if test="${ memberListVO ne null }">
								${memberListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
							</c:if> 
						</div>
					</form>
				</td>
			</tr>

		</table>
	
	<div>
		<input id="massiveDeleteBtn" class="inputButton" type="button" value="일괄삭제" style="cursor: pointer;" />
		<input id="changePassword" class="inputButton" type="button" value="비밀번호 변경" />
		<input id="modifyMemberType" class="inputButton" type="button" value="회원권한 변경" />
	</div>
</body>
</html>