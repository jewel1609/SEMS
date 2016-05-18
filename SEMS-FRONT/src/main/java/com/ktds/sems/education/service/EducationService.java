package com.ktds.sems.education.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationService {

	public ModelAndView getOneEducationDetail(String educationId, HttpSession session, int pageNo);
	
	public ModelAndView getAllEducationList(int pageNo);

	public String doApplyEducation(EducationVO educationVO, HttpSession session);
	
	public ModelAndView writeNewComment(HttpSession session, QNAVO qnaVO, Errors errors, String educationId);

	public ModelAndView doSearchList(EducationVO educationVO, int pageNo);

	public String doCancelEducation(String educationId, HttpSession session);

	public void doDownloadFile( String educationId, HttpServletRequest request, HttpServletResponse response);

	public String doTransCostId(String cost);

	public String doTransTypeId(String educationType);

	public ModelAndView showMyQNAList(QNASearchVO qnaSearchVO, HttpSession session);

	public ModelAndView showMyQNADetail(String replyId, HttpSession session);

	public void exportQNAListAsExcel(HttpSession session);

	public String doReReplyInsert(String replyId, String eduId, String id, String description, HttpSession session);

	public ModelAndView viewRequestRetractionPage(HttpSession session, String educationId);

	public String plusReReplyLike(String replyId, HttpSession session);

	public String plusReReplyDislike(String replyId, HttpSession session);

	public String doRequestRetraction(HttpServletRequest request, HttpSession session);

	public String doReserveEducation(String educationId, HttpSession session);

	
}
