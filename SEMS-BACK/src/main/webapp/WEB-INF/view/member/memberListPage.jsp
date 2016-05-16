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
				<th>비밀번호</th>
				<th>이름</th>
				<th>EMAIL</th>
				<th>최종학력</th>
				<th>대학교</th>
				<th>학과</th>
				<th>졸업구분</th>
				<th>생년월일</th>
				<th>전화번호</th>
				<th>회원타입</th>
				<th>접속실패횟수</th>
				<th>접속잠김여부</th>
				<th>최근접속날짜</th>
				<th>탈퇴날짜</th>
				<th>탈퇴여부</th>
				<th>수정실패횟수</th>
				<th>수정잠김여부</th>
			</tr>
			<c:forEach items="${ memberListVO.memberList }" var="member">
				<tr>
					<td>
						<input class="deleteMemberId" name="deleteMemberId" value="${member.id}" type="checkbox"/>
					</td>
					<td>
						<a href="<c:url value='/memberDetail/${member.id}'/>">${ member.id }</a>
					</td>
					<td>*****</td>	
					<td>${member.name}</td>	
					<td>${member.email}</td>	
					<td>${member.highestEducationLevel}</td>	
					<td>${member.universityName}</td>	
					<td>${member.majorName}</td>	
					<td>${member.graduationType}</td>	
					<td>${member.birthDate}</td>	
					<td>${member.phoneNumber}</td>	
					<td>${member.memberType}</td>	
					<td>${member.loginFailCount}</td>	
					<td>${member.isAccountLock}</td>	
					<td>${member.latestLoginDate}</td>	
					<td>${member.resignDate}</td>	
					<td>${member.isResign}</td>	
					<td>${member.modifyFailCount}</td>	
					<td>${member.isModifyLock}</td>	
				</tr>
			</c:forEach>


			<tr>
				<td colspan="19" align="center">
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