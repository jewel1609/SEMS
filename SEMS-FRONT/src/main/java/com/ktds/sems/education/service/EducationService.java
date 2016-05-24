package com.ktds.sems.education.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
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

	public List<EducationQNABBSVO> getAllEducationQNAList();

	public ModelAndView doQNAWrite(EducationQNABBSVO eduBBS, Errors errors, HttpSession session);

	public ModelAndView viewReportListPage(EducationReportSearchVO educationReportSearchVO);

	public ModelAndView viewReportWrite(String educationId, HttpSession session);

	public ModelAndView getAllReportArticle(EduReportSearchVO eduReportSearchVO, int pageNo);
	
	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId);

	public ModelAndView doReportWriteAction(EducationReportVO educationReportVO, Errors errors, MultipartHttpServletRequest request, HttpSession session);
	
	public ModelAndView doQNAReplyWriteAction(EducationQNAReplyVO eduBBSReplyVO, Errors errors, HttpSession session);

	public ModelAndView viewDetailEducationReport(EducationReportVO educationReportVO);

}
