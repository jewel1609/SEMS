package com.ktds.sems.education.service.impl;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationVO;

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

}
