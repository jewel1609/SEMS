package com.ktds.sems.education.service.impl;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;

import kr.co.hucloud.utilities.web.Paging;

public class EducationServiceImpl implements EducationService {
	
	private EducationBiz educationBiz;

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public ModelAndView doSearchList(EducationVO educationVO, Errors errors) {
		
		ModelAndView view = new ModelAndView();
		
		if ( errors.hasErrors() ) {
			view.setViewName("redirect:/educationList");
			return view;
		}
		else {
			List<EducationVO> educations = educationBiz.doSearchList(educationVO);
			view.addObject("educationList", educations);
			view.setViewName("/education/list");
		}
		
		return view;
	}

	@Override
	public ModelAndView getAllEducationList(int pageNo) {
		EducationListVO educationListVO = new EducationListVO();
//		Paging paging = new Paging(15,15);
		Paging paging = new Paging();
			
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

}
