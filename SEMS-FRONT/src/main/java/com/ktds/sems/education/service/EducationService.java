package com.ktds.sems.education.service;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationService {

	public ModelAndView getOneEducationDetail(String educationId);
	
	public ModelAndView getAllEducationList(int pageNo);

	public ModelAndView doApplyEducation(String educationId, HttpSession session);
	
	public ModelAndView writeNewComment(QNAVO qnaVO, Errors errors);

	public ModelAndView doSearchList(EducationVO educationVO, int pageNo);

	public String doCancelEducation(String educationId);
	
	
}
