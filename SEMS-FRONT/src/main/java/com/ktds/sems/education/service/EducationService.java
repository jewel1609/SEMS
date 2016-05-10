package com.ktds.sems.education.service;

import org.springframework.web.servlet.ModelAndView;


public interface EducationService {

	ModelAndView getOneEducationDetail(String educationId);
	
	public ModelAndView doSearchList(String startYear, String startMonth, String endYear, String endMonth, String eduName, String educationType, String cost, int pageNo);

	public ModelAndView getAllEducationList(int pageNo);
}
