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
      $("#joinCnclBtn").click(function() {
            window.open('<c:url value="/webapp/WEB-INF/view/update.jsp"/>',
                     '1463457167300',
                     'width=700,height=500,toolbar=0,menubar=0,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');
                        });

      $("#joinAplyBtn").click(function() {
            var eduHistoryId = $("#eduHistoryId").val();
                                 location.href = "<c:url value='/applyJoin/"+eduHistoryId+"'/>";
                              });

      $("#myButtons1").click(function() {
         var educationId = $("#eduId").val();
         location.href = "<c:url value='/applyJoin/"+educationId+"'/>";
      });
         $('#myModal').modal('hide');

		$("#joinAplyBtn").click(function() {
			var educationId = $("#JAEduId").val();
			var memberId = $("#JAMbrId").val();
			
			location.href = "<c:url value='/joinApply/"+educationId+"/"+memberId+"'/>";
		});
		
		$("#joinCnclBtn").click(function() {
			var educationId = $("#JCEduId").val();
			var memberId = $("#JCMbrId").val();
			var description = $("#description").val();
			
			location.href = "<c:url value='/joinCncl/"+educationId+"/"+memberId+"/"+description"'/>";
		});
		
		$().click(function() {
			
		});
   });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <!-- JOIN_APLY, CNCL_APLY  GVUP_APLY  일 경우만 보여준다. -->
   <!--
    JOIN_APLY 일 경우 참가승인, 참가거절 버튼이있구      
    참가 승인 시 관련 컨트롤러로 이동 
    참가 완료(JOIN_CMPL)  상태로(update) 바꿔준다. 
    참가 거절을 클릭시 사용자에게 메일? 을 보낸 후 리스트에서 지워준다.     
   -->
   <!-- 
   CNCL_APLY 일 경우 취소 승인, 취소 거절 버튼이있구
   취소 승인 시 관련 컨트롤러로 이동
   취소 승인 시 취소완료(CNCL_CMPL) 상태로(update) 변경해준다.   
   취소 거절 시 참가완료(JOIN_CMPL)로 변경해준다.
   -->
   <!-- 
   GVUP_APLY 일 경우 포기 승인, 포기 거절 버튼이 있구
   포기 승인 시 관련 컨트롤러로 이동
   포기 승인 시 포기 완료(GVUP_CMPL) 상태로(update) 변경해준다.
   포기 거절 시 참가완료(JOIN_CMPL)로 변경해준다.
   -->

   <table>
      <tr>
         <td>교육명</td>
         <td>학생아이디</td>
         <td>신청자아이피</td>
         <td>상태변경일</td>
         <td>신청 상태</td>
         <td>신청(거절/포기) 사유</td>
         <!-- 상태마다 승인과 거절이있다. -->
         <td>승인버튼</td>
         <td>거절버튼</td>
      </tr>
      
		<c:forEach items="${ eduHistoryListVO.educationHistoryList }"var="eduHistory">
			<input type="hidden" id="eduHistoryId"
				value="${eduHistory.educationHistoryId}" />
			<c:if test="${ eduHistory.state eq 'JOIN_APLY' }">
				<tr>
					<td><input type="hidden" id="JAEduId" value="${eduHistory.educationId }"/>${eduHistory.educationId }</td>
					<td><input type="hidden" id="JAMbrId" value="${eduHistory.memberId }">${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
					<td>${eduHistory.state }</td>
					<td><c:if test="${eduHistory.cmnt ne null }">
						${eduHistory.cmnt }
					</c:if> <c:if test="${eduHistory.fdbk ne null }">
						${eduHistory.fdbk }
					</c:if></td>
					<td>
						<!-- 승인버튼 --> <button class="btn btn-primary btn-sm" data-toggle="modal"
		data-target="#JoinAplyModal">교육참여승인</button>
					</td>
					<td>
						<button class="btn btn-primary btn-sm" data-toggle="modal"
		data-target="#JoinCnclModal">교육참여거절</button>
					</td>
				</tr>
			</c:if>
			<c:if test="${ eduHistory.state eq 'CNCL_APLY' }">
				<tr>
					<td><input type="hidden" id="JCEduId" value="${eduHistory.educationId }"/>${eduHistory.educationId }</td>
					<td><input type="hidden" id="JCMbrId" value="${eduHistory.memberId }"/>${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
					<td>${eduHistory.state }</td>
					<td><c:if test="${eduHistory.cmnt ne null }">
						${eduHistory.cmnt }
					</c:if> <c:if test="${eduHistory.fdbk ne null }">
						${eduHistory.fdbk }
					</c:if></td>
					<td>
						<!-- 승인버튼 --> <input type="button" value="교육취소승인" id="cnclAplyBtn" />
					</td>
					<td>
						<!-- 거절버튼 --> <input type="button" value="교육취소거절" id="cnclCmplBtn" />
					</td>
				</tr>
			</c:if>
			<c:if test="${ eduHistory.state eq 'GVUP_APLY' }">
				<tr>
					<td>${eduHistory.educationId }</td>
					<td>${eduHistory.memberId }</td>
					<td>${eduHistory.ip }</td>
					<td>${eduHistory.educationHistoryDate }</td>
					<td>${eduHistory.state }</td>
					<td><c:if test="${eduHistory.cmnt ne null }">
						${eduHistory.cmnt }
					</c:if> <c:if test="${eduHistory.fdbk ne null }">
						${eduHistory.fdbk }
					</c:if></td>
					<td>
						<!-- 승인버튼 --> <input type="button" value="교육포기승인" id="gvupAplyBtn" />
					</td>
					<td>
						<!-- 거절버튼 --> <input type="button" value="교육포기거절" id="gvupCmplBtn" />
					</td>
				</tr>
			</c:if>

      </c:forEach>
      <tr>
         <td>${eduHistoryListVO.paging.getPagingList("pageNo", "[@]", "이전", "다음", "")}
         </td>	
      </tr>
   </table>

   

   <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-sm">
         <!--  큰창:<div class="modal-dialog modal-lg"> 
         작은창 :<div class="modal-dialog modal-sm" > <div class="modal-dialog modal-dialog" >  -->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×</button>
               <h4 class="modal-title" id="myModalLabel">교육 참가 승인</h4>
            </div>
            <div class="modal-body">정말로 승인 하시겠습니까??</div>
            
            <div class="modal-body-text">
			<textarea name="memo" rows=15 cols=38>
			
			</textarea>
			</div> 
            
            <div class="modal-footer">
               
               <button type="button" class="btn btn-primary" id="myButtons1">
               정말   승인</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">
                  취소</button>
            </div>
         </div>
      </div>
   </div>
	<div class="modal fade" id="JoinAplyModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<!--  큰창:<div class="modal-dialog modal-lg"> 작은창 :<div class="modal-dialog modal-sm" > <div class="modal-dialog modal-dialog" >  -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">교육 참가 승인</h4>
				</div>
				<div class="modal-body">정말로 승인 하시겠습니까??</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-primary" id="joinAplyBtn">
					정말	승인</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						취소</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="JoinCnclModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<!--  큰창:<div class="modal-dialog modal-lg"> 작은창 :<div class="modal-dialog modal-sm" > <div class="modal-dialog modal-dialog" >  -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">교육 참가 거절</h4>
				</div>
				<div class="modal-body">거절 사유와 함께 상태를 변경하겠습니다.<br/>
				<textarea id="description" rows="7" cols="70"></textarea>
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-primary" id="joinCnclBtn">
					정말	거절</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						취소</button>
				</div>
			</div>
		</div>
	</div>
	



</body>
</html>