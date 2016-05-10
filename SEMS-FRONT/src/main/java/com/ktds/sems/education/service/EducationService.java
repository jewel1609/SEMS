package com.ktds.sems.education.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;


public interface EducationService {

	ModelAndView getOneEducationDetail(String educationId);
	
	public ModelAndView doSearchList(String startYear, String startMonth, String endYear, String endMonth, String eduName, String educationType, String cost);

	public ModelAndView getAllEducationList(int pageNo);
}
