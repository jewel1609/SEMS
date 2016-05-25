package com.ktds.sems.education.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.util.DownloadUtil;
import com.ktds.sems.education.vo.EduReplyListVO;
import com.ktds.sems.education.vo.EducationFileBBSVO;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationReportListVO;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAListVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.ReportReplyListVO;
import com.ktds.sems.education.vo.ReportReplySearchVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.AjaxUtil;
import kr.co.hucloud.utilities.web.Paging;

public class EducationServiceImpl implements EducationService {
	
	private Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);	
	
	private EducationBiz educationBiz;
	private FileBiz fileBiz;
	
	public void setFileBiz(FileBiz fileBiz) {
		this.fileBiz = fileBiz;
	}

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public ModelAndView getOneEducationDetail(String educationId, HttpSession session, int pageNo) {
		EduReplyListVO eduReplyListVO = new EduReplyListVO();
		Paging paging = new Paging(10,10);
		eduReplyListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		int totalEduReplyCount = educationBiz.getEduReplyCount(educationId);
		if( totalEduReplyCount == 0 ){
			totalEduReplyCount++;
		}
		logger.info("totalEduReplyCount" +totalEduReplyCount);
		paging.setTotalArticleCount(totalEduReplyCount);
		
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		ModelAndView view = new ModelAndView();
		EducationVO education = educationBiz.getOneEducationDetail(educationId);
		
		List<FileVO> fileList = fileBiz.getOneFileId(educationId);
		
		List<QNAVO> qnas = educationBiz.getAllCommentByEducationId(educationId, searchVO);
		
		eduReplyListVO.setQnaList(qnas);
		
		
		
		//이미 신청된 회원인지 비교해서 boolean 값 보내기
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		
		String status = "";
		status = educationBiz.isApplyMemberByEducationId(educationId, loginMember.getId());
		
		if (status == null || status.equals("")) {
			status = "";
		} else if (status.equals("EDU_RE_A")) {
			status = "예약신청";
		} else if (status.equals("EDU_JN_A")) {
			status = "참가신청";
		}
		
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		
//		List<EducationVO> myEducation = new ArrayList<EducationVO>();
//		myEducation = educationBiz.getMyEducationList(loginMember.getId());
		
//		view.addObject("myEducation", myEducation);
		view.addObject("status", status);
		view.addObject("eduReplyListVO", eduReplyListVO);
		view.addObject("education", education);
		view.addObject("fileList", fileList);
		view.addObject("memberType", memberType);
		view.setViewName("education/eduDetail");
		return view;
	}

	@Override
	public ModelAndView getAllEducationList(int pageNo) {
		
		EducationListVO educationListVO = new EducationListVO();
		Paging paging = new Paging(15,15);
		
		educationListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int totalEducationCount = educationBiz.getTotalEducationCount();
		if( totalEducationCount == 0 ){
			totalEducationCount++;
		}
		logger.info("토탈 카운트"+ totalEducationCount);
		paging.setTotalArticleCount(totalEducationCount);
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EducationVO> educationList = educationBiz.getAllEducationList(searchVO);
		logger.info("educationList"+ educationList.size());
		educationListVO.setEducationList(educationList);
		
		// Type, Cost Name 가져오기
		List<String> typeName = educationBiz.getTypeName();
		List<String> costName = educationBiz.getCostName();
		
		String startYear = educationBiz.getStartYear();
		String endYear = educationBiz.getEndYear();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/list");
		
		view.addObject("typeName", typeName);
		view.addObject("costName", costName);
		view.addObject("fromYear", startYear);
		view.addObject("toYear", endYear);
		
		if(educationList.size() > 0 ){
			view.addObject("educationListVO", educationListVO);
		}
		return view;
	}
	
	/**
	 * @author 206-002 공정민
	 * 교육 참가 신청 조건 검사
	 */
	@Override
	public String doApplyEducation(EducationVO educationVO, HttpSession session) {
		
		// 현재 로그인된 멤버가 신청한 교육 정보 가져오기
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		List<EducationVO> registeredEducationVO = educationBiz.getMemberRegInfo(loginMember.getId());
		
		String educationId = educationVO.getEducationId();
		
		// 1. 이미 시작했는지 확인
		String nowDateAndTime = "";
		String startDateAndTime = educationVO.getStartDate() + " " + educationVO.getStartTime();
		boolean compareStartDate = compareDateAndTime(nowDateAndTime, startDateAndTime);
		if ( compareStartDate ) {
			return "DATE_FAIL";
		}
		
		// 신청 내역이 있을 때
		if ( registeredEducationVO.size() > 0 ) {
			for (EducationVO regEdu : registeredEducationVO) {
			
				boolean compareRegisteredStartDate1 = compareDate(regEdu.getStartDate(), educationVO.getStartDate());
				boolean compareRegisteredStartDate2 = compareDate(educationVO.getEndDate(), regEdu.getStartDate());
				boolean compareRegisteredEndDate1 = compareDate(regEdu.getEndDate(), educationVO.getStartDate());
				boolean compareRegisteredEndDate2 = compareDate(educationVO.getEndDate(), regEdu.getEndDate());
				
				boolean containNewDate1 = compareDate(educationVO.getStartDate(), regEdu.getStartDate());
				boolean containNewDate2 = compareDate(regEdu.getEndDate(), educationVO.getEndDate());
				boolean containRegisteredDate1 = compareDate(regEdu.getStartDate(), educationVO.getStartDate());
				boolean containRegisteredDate2 = compareDate(educationVO.getEndDate(), regEdu.getEndDate());
				
				// 2. 등록 기간 비교
				if ( (compareRegisteredStartDate1 && compareRegisteredStartDate2 )
						|| ( compareRegisteredEndDate1 && compareRegisteredEndDate2 )
						|| ( containNewDate1 && containNewDate2 )
						|| ( containRegisteredDate1 && containRegisteredDate2 ) ) {
					
					// 3. 주/야간 정보 비교
					if ( regEdu.getEducationType().equals(educationVO.getEducationType()) ){
						return "TYPE_FAIL";
					}
				}
			}
		}
		
		// 4. 정원 찼는지 확인
		if ( this.getTotalMemberNumber(educationId) >= educationVO.getMaxMember() ) {
			// 정원 초과됐다는 알림 준다.
			return "EX_MAX_MEM";
		}
		
		// 모든 조건 충족하면 교육 참가 신청 등록
		return this.insertEducation(educationId, loginMember.getId());
	}
	
	/**
	 * @author 206-002 공정민
	 * 날짜 비교
	 */
	public boolean compareDate(String one, String two) { // day1 >= day2 일 때 true 반환
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date day1 = new Date();
		Date day2 = new Date();
		
		try {
			if (!one.equals("")) {
				day1 = format.parse(one);
			}
			if (!two.equals("")) {
				day2 = format.parse(two);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int compare = day1.compareTo(day2);
		
		if ( compare > 0 ) { // day1 > day2
			return true;
		} else if ( compare < 0) { // day1 < day2
			return false;
		} else { // day1 = day2
			return true;
		}
	}
	
	/**
	 * @author 206-002 공정민
	 * 날짜, 시간 비교
	 */
	public boolean compareDateAndTime(String one, String two) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date day1 = new Date();
		Date day2 = new Date();
		
		try {
			if (!one.equals("")) {
				day1 = format.parse(one);
			}
			if (!two.equals("")) {
				day2 = format.parse(two);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int compare = day1.compareTo(day2);
		
		if ( compare > 0 ) { // day1 > day2
			return true;
		} else if ( compare < 0) { // day1 < day2
			return false;
		} else { // day1 = day2
			return true;
		}
	}
	
	/**
	 * @author 206-002 공정민
	 * 교육 참가 신청
	 */
	public String insertEducation(String educationId, String memberId) {
		boolean isResult = educationBiz.doApplyEducation(educationId, memberId);
		
		if( isResult ){
			return "OK";
		} else{
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}
	}
	
	/**
	 * @author 206-002 공정민
	 * 강의 신청 인원 조회
	 */
	public int getTotalMemberNumber (String educationId) {
		return educationBiz.getTotalMemberNumber(educationId);
	}
	
	/**
	 * @author 206-002 공정민
	 * 교육 예약 신청
	 */
	@Override
	public String doReserveEducation(String educationId, HttpSession session) {
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		
		boolean isResult = educationBiz.doReserveEducation(educationId, loginMember.getId());
		if( isResult ){
			return "OK";
		} else{
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}
	}
	
	@Override
	public ModelAndView writeNewComment(HttpSession session, QNAVO qnaVO, Errors errors, String educationId) {
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReplySeq();
		String realReplyId = "RP-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		qnaVO.setEduId(educationId);
		qnaVO.setReplyId(realReplyId);
		// 댓글 쓴 아이디를 집어넣음
		qnaVO.setMbrId(memberVO.getId());
		
		if( errors.hasErrors() ){ 
			view.setViewName("redirect:/eduDetail/" + educationId);
			view.addObject("qnaVO", qnaVO );
			return view;
		}else {
			boolean result = educationBiz.writeNewComment(qnaVO);
			if ( result ){
				view.setViewName("redirect:/eduDetail/" + educationId);
			} else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
		
		
		return view;
	}
	
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
		
	}

	@Override
	public ModelAndView doSearchList(EducationVO educationVO, int pageNo) {
		logger.info(educationVO.getCost());
		logger.info(educationVO.getEducationType());
		EducationListVO searchedListVO = new EducationListVO();
		Paging paging = new Paging(15,15);
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int searchedEducationCount = educationBiz.getSearchedEducationCount(educationVO);
		if(searchedEducationCount == 0 ){
			searchedEducationCount ++;
		}
		
		paging.setTotalArticleCount(searchedEducationCount);
		logger.info("searchedEducationCount" +searchedEducationCount);

		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
	
		List<EducationVO> searchedEducations = educationBiz.doSearchList(educationVO, searchVO);
		logger.info("searchedEducations" +searchedEducations.size());

		searchedListVO.setEducationList(searchedEducations);

		// Type, Cost Name 가져오기
		List<String> typeName = educationBiz.getTypeName();
		List<String> costName = educationBiz.getCostName();
			
		String startYear = educationBiz.getStartYear();
		String endYear = educationBiz.getEndYear();
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("typeName", typeName);
		view.addObject("costName", costName);
		view.addObject("fromYear", startYear);
		view.addObject("toYear", endYear);
		
		view.addObject("searchKeyword", educationVO);
		view.addObject("searchedListVO", searchedListVO);
		
		view.setViewName("education/list");
		return view;
	}
	
	@Override
	public String doCancelEducation(String educationId , HttpSession session) {
		
		if(session.getAttribute("_MEMBER_") != null){
			MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
			boolean result1 = false;
			
			String status = educationBiz.isApplyMemberByEducationId(educationId, loginMember.getId());
			
			// 참가 신청자는 취소와 동시에, 첫번째 예약자를 참가신청자로 변경한다.
			if (status.equals("EDU_JN_A")) {
				result1 = educationBiz.updateStateToApply(educationId);
			}
			
			// 예약 신청자는 취소만 이루어진다. 
			boolean result2 = educationBiz.doCancelEducation(educationId , loginMember.getId());
		
			if( result2 ) {
				return "redirect:/educationList";
			}else{
				throw new RuntimeException("교육 신청 취소를 실패하였습니다.");
			}
		} else{
			return "redirect:/";
		}
	}



	@Override
	public void doDownloadFile(String educationId, HttpServletRequest request, HttpServletResponse response) {
		List<FileVO> fileList = fileBiz.getOneFileId(educationId);
		
		for (FileVO fileVO : fileList){
			if ( fileVO.getArticleId().equals(educationId) ){
				DownloadUtil downloadUtil = DownloadUtil.getInstance("D:\\");
				String userFileName = fileVO.getFileName();
				String displayFileName = (fileVO.getFileLocation()).substring(3);
				try {
					downloadUtil.download(request, response, displayFileName, userFileName);
				} catch (UnsupportedEncodingException e) {}
			}
		}
		
	}

	@Override
	public String doTransCostId(String cost) {
		return educationBiz.doTransCostId(cost);
	}

	@Override
	public String doTransTypeId(String educationType) {
		return educationBiz.doTransTypeId(educationType);
	}

	/**
	 * @author 206-025 이기연
	 * 교육 문의 내용 리스트
	 */
	@Override
	public ModelAndView showMyQNAList(QNASearchVO qnaSearchVO, HttpSession session) {

		if( qnaSearchVO == null ) {
			qnaSearchVO = new QNASearchVO();
			qnaSearchVO.setPageNo(0);
		}
		
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		qnaSearchVO.setId(memberId);
		int totalQNACount = educationBiz.getTotalQNACount(qnaSearchVO);

		// page setting
		Paging paging = new Paging();
		paging.setTotalArticleCount(totalQNACount);
		
		paging.setPageNumber(qnaSearchVO.getPageNo() + "");
		
		qnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		qnaSearchVO.setEndIndex(paging.getEndArticleNumber());
		qnaSearchVO.setId(memberId);
		
		session.setAttribute(Session.SEARCH_QNA, qnaSearchVO);
		
		List<QNAVO> qnaList = educationBiz.getAllQNAList(qnaSearchVO);
		
		QNAListVO qnaListVO = new QNAListVO();
		qnaListVO.setQnaList(qnaList);
		qnaListVO.setPaging(paging);

		view.setViewName("myPage/myQNAList");
		view.addObject("qnaListVO", qnaListVO);
		
		return view;
	}

	/**
	 * @author 206-025 이기연
	 * 교육 문의 내용 디테일 페이지(팝업창)
	 */
	@Override
	public ModelAndView showMyQNADetail(String replyId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/myQNADetail");

		QNAVO qnaVO = educationBiz.getSelectedQNA(replyId);
		List<QNAVO> qnaAnswerList = educationBiz.getSelectedQNAAnswer(replyId);
		QNAListVO qnaListVO = new QNAListVO();
		qnaListVO.setQnaList(qnaAnswerList);
		
		// 질문 qna
		view.addObject("qnaVO", qnaVO);
		// 답변 qna
		view.addObject("qnaListVO", qnaListVO);
		return view;
	}

	/**
	 * @author 206-025 이기연
	 * 교육 문의 내용 디테일 엑셀 저장
	 */
	@Override
	public void exportQNAListAsExcel(HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		educationBiz.exportQNAListAsExcel(memberId);
	}

	@Override
	public String doReReplyInsert(String replyId, String eduId, String id, String description, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		QNAVO qnaVO = new QNAVO();
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReplySeq();
		String realReplyId = "RP-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		//강의ID
		qnaVO.setEduId(eduId);
		//대댓글 ID
		qnaVO.setReplyId(realReplyId);
		//댓글ID
		qnaVO.setParentReplyId(replyId);
		//내용
		qnaVO.setDescription(description);
		// 답글 쓴 아이디를 집어넣음
		qnaVO.setMbrId(memberVO.getId());
		
		boolean result = educationBiz.doReReplyInsert(qnaVO);
		
		if(!result){
			return "FAIL";
		}
		else{
			//댓글이 등록됐을때 이메일을 보냄.
			// 받는사람 Id의 Email (String id 로 받아온거 는 EMAIL 전송할때 써야징)
			String email = educationBiz.getEmail(id);
			
			//문의댓글 작성자, 내용, 날짜
			QNAVO questionVO = new QNAVO();
			questionVO = educationBiz.getSelectedQNA(replyId);
			
			//문의답글 제목, 내용, 날짜
			QNAVO answerVO = new QNAVO();
			answerVO = educationBiz.getSelectedQNA(realReplyId);
			
			//educationBiz.sendEmailByReReply(questionVO, answerVO, email);
			return "OK";
		} 
	}
	
	@Override
	public ModelAndView viewRequestRetractionPage(HttpSession session, String educationId) {
		ModelAndView view = new ModelAndView();
		//교육참가 신청내역이 있는지 확인
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		boolean hasApplyHistory = educationBiz.hasApplyHistory(memberVO.getId(), educationId);
		if ( hasApplyHistory ) {
			//교육이 시작되었는지 확인
			boolean isEducationStarted = educationBiz.isEducationStarted(educationId);
			if ( isEducationStarted ) {
				//교육이 시작 되었다면 교육 포기 페이지로 이동
				view.setViewName("redirect:/member/myPage/course");
			}
			else {
				//교육이 아직 시작 전이라면 취소신청 페이지로 이동
				view.addObject("educationId", educationId);
				view.setViewName("education/retraction");
			}
		}
		else {
			//교육참가 신청이 없을경우
			throw new RuntimeException("참가 신청을한 교육이 아닙니다.");
		}
		return view;
	}

	@Override
	public String plusReReplyLike(String replyId, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReReplyEval();
		String replyEvalId = "RE-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		//댓글ID
		reRplyEvalVO.setReplyId(replyId);
		
		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId(memberVO.getId());
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId(replyEvalId);
		
		if (!educationBiz.checkReReplyEval(reRplyEvalVO)){
			boolean result = educationBiz.insertReReplyEval(reRplyEvalVO);
			
			if(!result){
				return "FAIL";
			}
			else{
				if(educationBiz.plusReReplyLike(replyId)){
					return "OK";
				}
				else {
					return "FAIL";
				}
			} 
		}
		else {
			return "FAIL";
		}
	}

	@Override
	public String plusReReplyDislike(String replyId, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReReplyEval();
		String replyEvalId = "RE-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		//댓글ID
		reRplyEvalVO.setReplyId(replyId);
		
		// 싫어요 누른 아이디
		reRplyEvalVO.setMbrId(memberVO.getId());
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId(replyEvalId);
		
		if (!educationBiz.checkReReplyEval(reRplyEvalVO)){
			boolean result = educationBiz.insertReReplyEvalByDislike(reRplyEvalVO);
			
			if(!result){
				return "FAIL";
			}
			else{
				if(educationBiz.plusReReplyDislike(replyId)){
					return "OK";
				}
				else {
					return "FAIL";
				}
			} 
		}
		else {
			return "FAIL";
		}
	}

	@Override
	public String doRequestRetraction(HttpServletRequest request, HttpSession session) {
		String educationId = request.getParameter("educationId");
		String retractionMsg = request.getParameter("retractionMessage");
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId();
		
		boolean hasApplyHistory = educationBiz.hasApplyHistory(memberId, educationId);
		if ( hasApplyHistory ) {
			//교육이 시작되었는지 확인
			boolean isEducationStarted = educationBiz.isEducationStarted(educationId);
			if ( isEducationStarted ) {
				//교육이 시작 되었다면 교육 포기 페이지로 이동
				return "redirect:/member/myPage/course";
			}
			else {
				//교육이 아직 시작 전이라면 취소신청
				boolean isRetracRquestSuccess = educationBiz.doRequestRetraction(educationId, retractionMsg, memberId);
				isRetracRquestSuccess &= educationBiz.addRequestRetractionHistory(educationId, retractionMsg, memberId, request.getRemoteHost());
				if ( isRetracRquestSuccess ) {
					// 취소신청이 제대로 완료 되었을때
					// FIXME 어디로 가야하죠 아저씨 우는 손님이 처음인 가요
					return "redirect:/member/myPage";
				}
				else {
					// 취소신청이 제대로 이뤄지지 않았을때
					return "redirect:/education/retraction/" + educationId;
				}
			}
		}
		else {
			// 교육 아이디로 교육 신청 내역이 없을 경우
			throw new RuntimeException("참가 신청을한 교육이 아닙니다.");
		}
		
	}
	
	@Override
	public List<EducationQNABBSVO> getAllEducationQNAList() {
		return educationBiz.getAllEducationQNAList();
	}

	@Override
	public ModelAndView doQNAWrite(EducationQNABBSVO eduBBS, Errors errors, HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		eduBBS.setMbrId(sessionMember.getId());
		
		//ATC_ID 형식 변경
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReportSeq();
		
		String realAtcId = "AT-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		eduBBS.setAtcId(realAtcId);
		
		
		//TODO 강의 아이디 받아와서 집어넣기
		eduBBS.setEduId("ED-20160519-000233");
		
		if ( !errors.hasErrors() ) {
			educationBiz.addQNABBS(eduBBS);
			view.setViewName("redirect:/eduBoard/QNAList");
		}else{
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}
		
		
		return view;
	}

	@Override
	public ModelAndView viewReportListPage(EducationReportSearchVO educationReportSearchVO) {
		
		if( educationReportSearchVO == null ) {
			educationReportSearchVO = new EducationReportSearchVO();
			educationReportSearchVO.setPageNo(0);
		}
		
		ModelAndView view = new ModelAndView();
		
		int totalEduReportCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		
		// page setting
		Paging paging = new Paging();
		paging.setTotalArticleCount(totalEduReportCount);
		
		paging.setPageNumber(educationReportSearchVO.getPageNo() + "");
		
		educationReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationReportSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EducationReportVO> educationReportList = educationBiz.getAllEducationReportList(educationReportSearchVO);
		
		EducationReportListVO educationReportListVO = new EducationReportListVO();
		educationReportListVO.setEducationReportList(educationReportList);
		educationReportListVO.setPaging(paging);
		
		view.setViewName("education/reportList");
		view.addObject("educationReportListVO", educationReportListVO);
		view.addObject("educationId", educationReportSearchVO.getEducationId());
		
		return view;
	}

	@Override
	public ModelAndView viewReportWrite(String educationId, HttpSession session) {

		ModelAndView view = new ModelAndView();
		
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		
		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setEducationId(educationId);
		
		String nowDate = educationBiz.getNowDateTime();
		nowDate = nowDate.replaceAll(" ", "T");
		educationReportVO.setStartDate(nowDate);
		
		//과제 등록
		if ( loginMember.getMemberType().equals("TR") ) {
			view.setViewName("education/reportWrite");
			view.addObject("educationReportVO", educationReportVO);
			return view;
		}
		else {
			// 강사 아닐 시 
			view.setViewName("redirect:/");
			return view;
		}
		
	}

	@Override
	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId) {
		//조회수 증가
		educationBiz.addHitsByAtcId(atcId);
		
		return educationBiz.getOneQNABBSByAtcId(atcId);
	}

	@Override
	public ModelAndView doReportWriteAction(EducationReportVO educationReportVO, Errors errors, MultipartHttpServletRequest request, HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		educationReportVO.setMemberId(loginMember.getId());
		
		if( errors.hasErrors() ){ 
			view.setViewName("education/reportWrite");
			view.addObject("educationReportVO", educationReportVO );
			return view;
		}
		else {
			
			educationReportVO.setStartDate(educationReportVO.getStartDate().replaceAll("T", " "));
			educationReportVO.setEndDate(educationReportVO.getEndDate().replaceAll("T", " "));
			
			// ArticleID 형식 변경
			String nowDate = educationBiz.getNowDate();
			int nextSeq = educationBiz.getNextReportSeq();
			
			String realReportId = "RT-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
			educationReportVO.setArticleId(realReportId);
			
			educationBiz.doReportWriteAction(educationReportVO);
			
			MultipartFile file = request.getFile("file");
			
			String fileName = file.getOriginalFilename();
			String salt = SHA256Util.generateSalt();
			String saltFileName = SHA256Util.getEncrypt(fileName, salt);
			
			String filePath = "D:\\" + saltFileName;
			
			if ( !file.isEmpty() ) {
				
				File files = new File(filePath);
				
				try {
					file.transferTo(files);
					
					FileVO fileVO = new FileVO();
					fileVO.setArticleId(educationReportVO.getArticleId());
					fileVO.setFileName(fileName);
					fileVO.setFileLocation(filePath);
					
					fileBiz.doWriteFile(fileVO);
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}		
			
			view.setViewName("redirect:/education/reportList/" + educationReportVO.getEducationId());
			return view;
		}
		
	}

	@Override
	public ModelAndView doQNAReplyWriteAction(EducationQNAReplyVO eduBBSReplyVO, Errors errors, HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		String atcId = eduBBSReplyVO.getAtcId();
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReplySeq();
		String realReplyId = "RP-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		eduBBSReplyVO.setMbrId(sessionMember.getId());
		eduBBSReplyVO.setAtcId(atcId);
		eduBBSReplyVO.setReplyId(realReplyId);
		
		if ( !errors.hasErrors() ) {
			educationBiz.addQNAReply(eduBBSReplyVO);
			view.setViewName("redirect:/eduBoard/QNADetail/" + atcId);
			
		}else {
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}
		
		return view;
	}

	@Override
	public ModelAndView showEducationFileBBSPage(String educationId) {
		ModelAndView view = new ModelAndView();
		
		EducationVO educationVO = educationBiz.getOneEducationDetail(educationId);

		if ( educationVO == null) {
			throw new RuntimeException("해당 게시판이 존재하지 않습니다.");
		}
		
		List<EducationFileBBSVO> educationFileBBSList = educationBiz.getEducationFileBBSList(educationId);
		String teacherId = educationVO.getMemberId();
		
		view.addObject("educationFileBBSList", educationFileBBSList);
		view.addObject("teacherId", teacherId);
		view.setViewName("education/educationFileBBS");
		
		return view;
	}

	@Override
	public ModelAndView doWriteEducationFileBBSAction(EducationFileBBSVO educationFileBBSVO, MultipartHttpServletRequest request, HttpSession session) {
		ModelAndView view = new ModelAndView();

		String articleId = educationBiz.generateArticleId();
		educationFileBBSVO.setArticleId(articleId);
		MemberVO member = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = member.getId();
		String articleWriterId = educationBiz.getMemberIdByEducationId(educationFileBBSVO.getEducationId());

		boolean isSuccess = false;
		if (memberId.equals(articleWriterId)) {
			educationFileBBSVO.setMemberId(member.getId());
			isSuccess = educationBiz.writeNewFileBBS(educationFileBBSVO);
		}

		// 파일 등록
		if (isSuccess) {
			fileBiz.doUploadAndWriteFiles(request, articleId);
		}
		else {
			throw new RuntimeException("글쓰기 오류");
		}
		
		return view;
	}

	@Override
	public ModelAndView showWriteFileBBSPage(String educationId) {
		ModelAndView view = new ModelAndView();
		view.addObject("educationId", educationId);
		view.setViewName("education/writeEducationFileBBS");
		return view;
	}
	
/*	@Override
	public ModelAndView viewDetailEducationReport(EducationReportVO educationReportVO, HttpSession session, int pageNo) {
		//
		ReportReplyListVO reportReplsyListVO = new ReportReplyListVO();
		Paging paging = new Paging(10,10);
		
		reportReplsyListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		int totalReportReplyCount = educationBiz.getReportReplyCount(educationReportVO.getArticleId());
		
		if( totalReportReplyCount == 0 ){
			totalReportReplyCount++;
		}
		logger.info("totalReportReplyCount - " + totalReportReplyCount);
		paging.setTotalArticleCount(totalReportReplyCount);
		
		ReportReplySearchVO searchVO = new ReportReplySearchVO();
	}*/
	
	@Override	
	public ModelAndView getAllReportReply(ReportReplySearchVO reportReplySearchVO, int pageNo, HttpSession session) {
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");

		Paging paging = new Paging();
		ReportReplyListVO reportReplyListVO = new ReportReplyListVO();
		reportReplyListVO.setPaging(paging);
		
		reportReplySearchVO.setMbrId(loginMember.getId());
		
		int totalReportReplyCount = educationBiz.getTotalReportReplyCount(reportReplySearchVO);
		
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportReplyCount);
		
		reportReplySearchVO.setStartIndex(paging.getStartArticleNumber());
		reportReplySearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<ReportReplyVO> reportReplyVO = educationBiz.getAllReportReply(reportReplySearchVO);
		reportReplyListVO.setReportReplyList(reportReplyVO);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/myReportList");
		view.addObject("reportReplytListVO", reportReplyListVO);
		view.addObject("reportReplySearchVO", reportReplySearchVO);
		
		return view;
	}

	@Override
	public ModelAndView viewDetailEducationReport(EducationReportVO educationReportVO, HttpSession session, int pageNo) {
		ReportReplyListVO reportReplyListVO = new ReportReplyListVO();
		Paging paging = new Paging(10,10);
		
		reportReplyListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		int totalReportReplyCount = educationBiz.getReportReplyCount(educationReportVO.getArticleId());
		
		if( totalReportReplyCount == 0 ){
			totalReportReplyCount++;
		}
		logger.info("totalReportReplyCount - " + totalReportReplyCount);
		paging.setTotalArticleCount(totalReportReplyCount);
		
		ReportReplySearchVO searchVO = new ReportReplySearchVO();
		
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		ModelAndView view = new ModelAndView();
		
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		educationReportVO = educationBiz.getOneEducationReport(educationReportVO);
		List<FileVO> reportFile = fileBiz.getOneFileId(educationReportVO.getArticleId()); 
		
		List<ReportReplyVO> reportReplyList = educationBiz.getAllReportByArticleId(educationReportVO.getArticleId(), searchVO);
		reportReplyListVO.setReportReplyList(reportReplyList);
		
		// 날짜 지났는지 비교
		educationBiz.getNowDateTime();
		int result;
		
		
		view.setViewName("education/detailReportPage");
		view.addObject("educationReportVO", educationReportVO);
		view.addObject("reportReplyListVO", reportReplyListVO);
		view.addObject("loginMember", loginMember);
		view.addObject("reportFile", reportFile);
		
		return view;
		
	}

	@Override
	public List<EducationQNAReplyVO> getAllQNAReplyListByAtcId(String atcId) {
		return educationBiz.getAllQNAReplyListByAtcId(atcId);
	}

	@Override
	public ModelAndView doReportSubmit(ReportReplyVO reportReplyVO, MultipartHttpServletRequest request, HttpSession session) {
		ModelAndView view = new ModelAndView();
		

		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		reportReplyVO.setMbrId(loginMember.getId());
		// ArticleID 형식 변경
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReportReplySeq();
			
		String rptRplId = "RPTL-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		reportReplyVO.setRptRplId(rptRplId);
			
		educationBiz.doReportSubmit(reportReplyVO);
			
		MultipartFile file = request.getFile("file");
			
		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt);
		
		String filePath = "D:\\" + saltFileName;
		
		if ( !file.isEmpty() ) {
			
			File files = new File(filePath);
			
			try {
				file.transferTo(files);
				
				FileVO fileVO = new FileVO();
				fileVO.setArticleId(reportReplyVO.getRptRplId());
				fileVO.setFileName(fileName);
				fileVO.setFileLocation(filePath);
				
				fileBiz.doWriteFile(fileVO);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}		
		
		view.setViewName("redirect:/education/detailReport/" + reportReplyVO.getBbsId());
		return view;
	}

	@Override
	public ModelAndView modifyReport(EducationReportVO educationReportVO, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		educationReportVO = educationBiz.getOneEducationReport(educationReportVO);
		educationReportVO.setStartDate(educationReportVO.getStartDate().replaceAll(" ", "T"));
		educationReportVO.setEndDate(educationReportVO.getEndDate().replaceAll(" ", "T"));
		
		// 과제를 등록한 강사와 수정 요청한 로그인 세션 계정이 같은지
		if ( educationReportVO.getMemberId().equals(loginMember.getId()) ) {
			
			view.setViewName("education/reportWrite");
			view.addObject("educationReportVO", educationReportVO);
			
			return view;
		}
		else {
			view.setViewName("redirect:/education/detailReport/" + educationReportVO.getArticleId());
			return view;
		}
	}

	@Override
	public ModelAndView doModifyReport(EducationReportVO educationReportVO, HttpSession session, MultipartHttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		// 수정
		educationBiz.modifyReport(educationReportVO);
		
		MultipartFile file = request.getFile("file");
		
		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt);
		
		String filePath = "D:\\" + saltFileName;
		
		if ( !file.isEmpty() ) {
			
			File files = new File(filePath);
			
			try {
				file.transferTo(files);
				
				FileVO fileVO = new FileVO();
				fileVO.setArticleId(educationReportVO.getArticleId());
				fileVO.setFileName(fileName);
				fileVO.setFileLocation(filePath);
				
				fileBiz.updateFile(fileVO);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		view.setViewName("redirect:/education/detailReport/" + educationReportVO.getArticleId());
		
		return view;
	}

	@Override
	public String deleteReport(EducationReportVO educationReportVO, HttpSession session) {
		
		educationReportVO = educationBiz.getOneEducationReport(educationReportVO);
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		// 삭제
		if ( educationReportVO.getMemberId().equals(member.getId()) ) {
			educationBiz.deleteReport(educationReportVO);
			return "redirect:/education/reportList/" + educationReportVO.getArticleId();
		}
		
		return "redirect:/education/detailReport/" + educationReportVO.getArticleId();
		
	}

	@Override
	public void checkEndDate(String articleId, HttpServletResponse response) {
		String message="";
		logger.info("check: " + educationBiz.checkEndDate(articleId));
		if ( educationBiz.checkEndDate(articleId) == null || educationBiz.checkEndDate(articleId).equals("")) {
			message = "NO";
			AjaxUtil.sendResponse(response, message);
			return;
		}
		else {
			message = "OK";
			AjaxUtil.sendResponse(response, message);
			return;
		}
	}

}

