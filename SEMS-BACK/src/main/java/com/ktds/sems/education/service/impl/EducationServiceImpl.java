package com.ktds.sems.education.service.impl;

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
	public ModelAndView getOneEducation(String educationId) {
		
		EducationVO educationVO = educationBiz.getOneEducation(educationId);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/update");
		view.addObject("educationVO", educationVO);
		return view;
	}

	@Override
	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors) {
		
		ModelAndView view = new ModelAndView();
		if ( errors.hasErrors() ) {
			view.setViewName("");
			view.addObject("educationVO", educationVO);
			return view;
		}
		else{
			boolean result = educationBiz.modifyNewEducation(educationVO);
			String educationId = educationVO.getEducationId();
			if ( result ) {
				view.setViewName("redirect:/detail/" + educationId);
			}
			else {
				throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
		view.addObject("educationVO", educationVO);
		return view;
	}

}
