package com.ktds.sems.education.service.impl;

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
		//이미 신청된 회원인지 비교해서 boolean 값 보내기
		
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
		view.addObject("educationListVO", educationListVO);
		
		return view;
	}

	@Override
	public ModelAndView doApplyEducation(String educationId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		// 현재 로그인된 멤버가 가입한 educationId에 해당하는 주/야간 정도 가져오기
		session.getAttribute("_MEMBER_");
		// 버튼을 통해 가져온 educationId에 해당하는 주/야간 정보와 비교하기
		
		// 같으면 redirect
		
		// 다르면 insert시킨다.
		
		return null;
	}
	
	@Override
	public ModelAndView writeNewComment(QNAVO qnaVO, Errors errors) {
		ModelAndView view = new ModelAndView();
		
		if( errors.hasErrors() ){ 
			view.setViewName("redirect:/eduDetail");
			view.addObject("qnaVO", qnaVO );
			return view;
		}else {
			boolean result = educationBiz.writeNewComment(qnaVO);
			if ( result ){
				view.setViewName("redirect:/eduDetail");
			} else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
		
		return view;
	}

	@Override
	public ModelAndView doSearchList(EducationVO educationVO, int pageNo) {
		EducationListVO searchedListVO = new EducationListVO();
		Paging paging = new Paging(15,15);
		searchedListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");

		ModelAndView view = new ModelAndView();
		
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	


		logger.info("StartDate"+educationVO.getStartDate());
		logger.info("EndDate"+ educationVO.getEndDate());
		
		int searchedEducationCount = educationBiz.getSearchedEducationCount(educationVO);
		paging.setTotalArticleCount(searchedEducationCount);

		List<EducationVO> searchedEducations = educationBiz.doSearchList(educationVO, searchVO);
		
		logger.info("서치 카운트 확인"+ searchedEducations.size());

		/*searchedListVO.setEducationList(searchedEducations);
		view.addObject("searchedEducations", searchedEducations);
		view.setViewName("education/list");*/

		return view;
	}
	
	@Override
	public String doCancelEducation(String educationId) {
		boolean result = educationBiz.doCancelEducation(educationId);
		if( result ) {
			return "redirect:/eduDetail";
		}else{
			throw new RuntimeException("	실패하였습니다.	");
		}
	}


}
