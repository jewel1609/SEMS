package com.ktds.sems.education.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.util.DownloadUtil;
import com.ktds.sems.education.vo.EduReplyListVO;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAListVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.vo.MemberVO;

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
/*		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		boolean isApply =  true;
		if (educationBiz.isApplyMemberByEducationId(educationId, loginMember.getId()) > 0 ){
			isApply = false;
		}
		else{
			isApply = true;
		}
		view.addObject("isApply", isApply);*/
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		
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

	@Override
	public String doApplyEducation(String educationId, String educationType, HttpSession session) {
		// 현재 로그인된 멤버가 가입한 educationId에 해당하는 주/야간 정보 가져오기
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		List<String> eduType = educationBiz.getMemberRegInfo(loginMember.getId());
		// 버튼을 통해 가져온 educationType에 해당하는 주/야간 정보와 비교하기
		if ( eduType.size() == 0) {
			boolean isResult = educationBiz.doApplyEducation(educationId, loginMember.getId());
			if( isResult ){
				return "OK";
			} else{
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
		else{
			for (String eduTp : eduType) {
				if( eduTp.equals(educationType) ){
					return "FAIL";
				}
			}
			// 다르다면  for 문을 빠져나와서 insert시킨다.
			boolean isResult = educationBiz.doApplyEducation(educationId, loginMember.getId());
			if( isResult ){
				return "OK";
			} else{
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
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
			boolean result = educationBiz.doCancelEducation(educationId , loginMember.getId() );
		
			if( result ) {
				return "redirect:/educationList";
			}else{
				throw new RuntimeException("	실패하였습니다.	");
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
	public ModelAndView showMyQNAList(int pageNo, HttpSession session) {

		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		String memberId = memberVO.getId(); 
		int totalQNACount = educationBiz.getTotalQNACount(memberId);

		// page setting
		Paging paging = new Paging();
		paging.setTotalArticleCount(totalQNACount);
		paging.setPageNumber(pageNo+"");
		
		QNASearchVO qnaSearchVO = new QNASearchVO();
		qnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		qnaSearchVO.setEndIndex(paging.getEndArticleNumber());
		qnaSearchVO.setId(memberId);
		
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
		QNAVO qnaAnswerVO = educationBiz.getSelectedQNAAnswer(replyId);
		
		// 질문 qna
		view.addObject("qnaVO", qnaVO);
		
		// 답변 qna
		view.addObject("qnaAnswerVO", qnaAnswerVO);
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
	public ModelAndView viewRequestRetractionPage(HttpSession session, HttpServletRequest request, String educationId) {
		ModelAndView view = new ModelAndView();
		//교육참가 신청내역이 있는지 확인
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		boolean hasApplyHistory = educationBiz.hasApplyHistory(memberVO.getId(), educationId);
		if ( hasApplyHistory ) {
			//교육이 시작되었는지 확인
			boolean isEducationStarted = educationBiz.isEducationStarted(educationId);
			if ( isEducationStarted ) {
				//교육이 시작 되었다면 교육 포기 페이지로 이동
				view.setViewName("redirect:/education/abandon");
			}
			else {
				//교육이 아직 시작 전이라면 취소신청 페이지로 이동
				view.addObject("educationId", educationId);
				view.setViewName("education/retraction");
			}
		}
		else {
			//교육참가 신청이 없을경우 접근 불가능
			//TODO 어느 페이지로 보내야 하지?
			return null;
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

}


