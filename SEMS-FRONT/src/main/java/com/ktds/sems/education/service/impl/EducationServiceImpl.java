package com.ktds.sems.education.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.education.vo.QNAVO;

import kr.co.hucloud.utilities.web.Paging;

public class EducationServiceImpl implements EducationService {
	
	private Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);	
	
	private EducationBiz educationBiz;

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public ModelAndView getOneEducationDetail(String educationId) {
		ModelAndView view = new ModelAndView();
		EducationVO education = educationBiz.getOneEducationDetail(educationId);
		List<QNAVO> qna = educationBiz.getAllCommentByEducationId(educationId);
		//이미 신청된 회원인지 비교해서 boolean 값 보내기
	
		view.addObject("qna", qna);
		view.addObject("education", education);
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
		paging.setTotalArticleCount(totalEducationCount);
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		List<EducationVO> educationList = educationBiz.getAllEducationList(searchVO);
		educationListVO.setEducationList(educationList);

		ModelAndView view = new ModelAndView();
		view.setViewName("education/list");
		
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
	
	@Override
	public ModelAndView writeNewComment(QNAVO qnaVO, Errors errors, String educationId) {
		ModelAndView view = new ModelAndView();
		
		String nowDate = educationBiz.getNowDate();
		int nextSeq = educationBiz.getNextReplySeq();
		String realReplyId = "RP-" + nowDate + "-" + lpad(nextSeq + "", 6, "0");
		
		System.out.println(qnaVO.getMbrId());
		System.out.println(realReplyId);
		
		qnaVO.setEduId(educationId);
		qnaVO.setReplyId(realReplyId);
		
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
		EducationListVO searchedListVO = new EducationListVO();
		Paging paging = new Paging(15,15);
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int searchedEducationCount = educationBiz.getSearchedEducationCount(educationVO);
		paging.setTotalArticleCount(searchedEducationCount);
		
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
	
		logger.info("searchedEducationCount" + searchedEducationCount);
		logger.info("pageNo" + pageNo);
		logger.info("paging.getStartArticleNumber()" + paging.getStartArticleNumber());
		logger.info("paging.getEndArticleNumber()" + paging.getEndArticleNumber());

		List<EducationVO> searchedEducations = educationBiz.doSearchList(educationVO, searchVO);
		searchedListVO.setEducationList(searchedEducations);
	
		ModelAndView view = new ModelAndView();
		
		view.addObject("searchedListVO", searchedListVO);
		view.setViewName("education/list");
		return view;
	}
	
	@Override
	public String doCancelEducation(String educationId) {
		boolean result = educationBiz.doCancelEducation(educationId);
		if( result ) {
			return "redirect:/eduDetail/" + educationId;
		}else{
			throw new RuntimeException("	실패하였습니다.	");
		}
	}


}
