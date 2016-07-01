<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>

	<div>
		<span style="text-align: center;"><h3>${educationVO.educationTitle}</h3></span>
	</div>
	
	<br/>
	<br/>

<div>
	<div style="float: left; width: 30%; padding: 10px;">
		<span >강의 자료 <a href="<c:url value="/education/fileBBS/${educationVO.educationId}" />">더보기</a></span>
		<table style="text-align: center;">
			<tr>
				<th width="30%">제목</th>
				<th width="70%">작성일</th>
			</tr>
			<c:forEach items="${educationItems}" var="item">
				<tr>
					<td>
					<a href="<c:url value='/education/fileBBS/detail/${item.articleId}'/>">
						> ${item.title}</a>
					</td>
					<td>${item.createDate}</td>
				</tr>
			</c:forEach>
		
		</table>
	</div>
	
	<div style="float: left; width:30%; padding: 10px;">
		<span>질문/답변 <a href="<c:url value="/eduBoard/QNAList/${educationVO.educationId}" />">더보기</a></span>
		<table style="text-align: center;">
			<tr>
				<th width="30%">제목</th>
				<th width="70%">작성일</th>
			</tr>
			<c:forEach items="${educationQNAList}" var="QNA">
				<tr>
					<td><a href="<c:url value='/eduBoard/QNADetail/${QNA.atcId}'/>">
					> ${QNA.title}
				</a></td>
					<td>${QNA.createDate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div style="float: left; width: 30%; padding: 10px;">
		<span>과제 <a href="<c:url value="/education/reportList/${educationVO.educationId}" />">더보기</a></span>
		<table style="text-align: center;">
			<tr>
				<th width="30%">제목</th>
				<th width="70%">작성일</th>
			</tr>
			<c:forEach items="${educationReportList}" var="report">
				<tr>
					<td><a href="<c:url value='/education/detailReport/${report.articleId }' />">
						> ${report.title }
					</a></td>
					<td>${report.startDate}</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	<div style="clear:both;"></div>
</div>

<div>
	<div style="float: left; width: 30%; padding: 10px;">
		<span style="text-align: center;">시험</span>
		<a href="<c:url value="/eduBoard/${educationVO.educationId}" />"><span style="text-align: right;">더보기</span></a>
		<table style="text-align: center;">
			<tr>
				<th width="30%">제목</th>
				<th width="70%">작성일</th>
			</tr>
		</table>
	</div>
	
	<div style="float: left; width: 30%; padding: 10px;">
		<span>강의 평가 <a href="<c:url value="/eduBoard/${educationVO.educationId}" />">더보기</a></span>
		<table style="text-align: center;">
			<tr>
				<th width="30%">제목</th>
				<th width="70%">작성일</th>
			</tr>
		</table>
	</div>
	
	<div style="float: left; width: 30%; padding: 10px;">
		<span>최근 접속 사용자 </span>
		<table>
			<tr></tr>
			<c:forEach items="${memberList}" var="member">
				<tr>
					<td>${member}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="clear:both;"></div>

</div>

</div>	
</body>
</html>