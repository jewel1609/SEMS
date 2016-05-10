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
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors) {
		
		ModelAndView view = new ModelAndView();
		
		if ( errors.hasErrors() ) {
			view.setViewName("/education/write");
			view.addObject("educationVO", educationVO);
			
			return view;
		}
		else {
			boolean result = educationBiz.writeNewEducation(educationVO);
			if ( result ) {
				view.setViewName("redirect:/list");
			} 
			else {
				throw new RuntimeException("일시적인 장애가 발생하였습니다.");
			}
		}
		
		return view;
	}

}
