package com.ktds.sems.education.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;


public interface EducationService {
	
	public ModelAndView doSearchList(EducationVO educationVO, Errors errors);


	public ModelAndView getAllEducationList(int pageNo);
}
