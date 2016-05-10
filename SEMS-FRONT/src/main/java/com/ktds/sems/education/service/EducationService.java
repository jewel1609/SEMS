package com.ktds.sems.education.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;


public interface EducationService {
	
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors);
	

	ModelAndView getOneEducation(String educationId);

	ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors);

}
