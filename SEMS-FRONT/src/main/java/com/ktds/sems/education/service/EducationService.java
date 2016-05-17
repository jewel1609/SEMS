package com.ktds.sems.education.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.file.vo.FileVO;

public interface EducationService {

	public ModelAndView getOneEducationDetail(String educationId, HttpSession session, int pageNo);
	
	public ModelAndView getAllEducationList(int pageNo);

	public String doApplyEducation(String educationId, String educationType, HttpSession session);
	
	public ModelAndView writeNewComment(HttpSession session, QNAVO qnaVO, Errors errors, String educationId);

	public ModelAndView doSearchList(EducationVO educationVO, int pageNo);

	public String doCancelEducation(String educationId, HttpSession session);

	public void doDownloadFile( String educationId, HttpServletRequest request, HttpServletResponse response);

	public String doTransCostId(String cost);

	public String doTransTypeId(String educationType);

	public ModelAndView showMyQNAList(int pageNo, HttpSession session);

	public ModelAndView showMyQNADetail(String replyId, HttpSession session);

	public void exportQNAListAsExcel(HttpSession session);

	public ModelAndView viewRequestRetractionPage(HttpSession session, HttpServletRequest request, String educationId);

	
}
