package com.ktds.sems.education.service;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.QNAVO;

public interface EducationService {

	ModelAndView getOneEducationDetail(String educationId);
	
	public ModelAndView doSearchList(String startYear, String startMonth, String endYear, String endMonth, String eduName, String educationType, String cost, int pageNo);

	public ModelAndView getAllEducationList(int pageNo);

	public ModelAndView doApplyEducation(String educationId, HttpSession session);
	
	public ModelAndView writeNewComment(QNAVO qnaVO, Errors errors);
}
