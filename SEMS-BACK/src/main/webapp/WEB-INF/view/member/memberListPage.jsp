<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.min.js"'/>"></script>
<title>MemberListPage</title>
</head>
<body>
	<form name="searchForm" id="searchForm">
		<table>
			<tr>
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
						<a href="<c:url value='/memberDetail/${member.id}'/>">${ member.id }</a>
					</td>
					<td>member.password</td>	
					<td>member.name</td>	
					<td>member.email</td>	
					<td>member.highestEducationLevel</td>	
					<td>member.universityName</td>	
					<td>member.majorName</td>	
					<td>member.graduationType</td>	
					<td>member.birthDate</td>	
					<td>member.phoneNumber</td>	
					<td>member.memberType</td>	
					<td>member.loginFailCount</td>	
					<td>member.isAccountLock</td>	
					<td>member.latestLoginDate</td>	
					<td>member.resignDate</td>	
					<td>member.isResign</td>	
					<td>member.modifyFailCount</td>	
					<td>member.isModifyLock</td>	
				</tr>
			</c:forEach>


			<tr>
				<td colspan="18" align="center">
					<c:if test="${ memberListVO ne null }">
						${memberListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm")}
					</c:if> 
				</td>
			</tr>

		</table>
	</form>
</body>
</html>