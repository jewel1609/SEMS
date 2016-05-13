package com.ktds.sems.education.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class EducationController {

	private EducationService educationService;
	private Logger logger = LoggerFactory.getLogger(EducationController.class);	
	
	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}
	
	@RequestMapping("/educationList")
	public ModelAndView viewEducationListPage(@RequestParam(required=false, defaultValue="0") int pageNo){
		logger.info("실행");
		logger.info(""+pageNo);
		return educationService.getAllEducationList(pageNo);
	}
	
	@RequestMapping("/calendar")
	public ModelAndView viewEducationCalendarPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("education/calendar");
		
		return view;
	}

	
	@RequestMapping("/searchList")
	public ModelAndView doSearchList(@RequestParam String startYear, @RequestParam String startMonth, 
				@RequestParam String endYear, @RequestParam String endMonth, @RequestParam String eduName,
				@RequestParam String educationType, @RequestParam String cost, @RequestParam(required=false, defaultValue="0") String pageNo){
/*		logger.info("검색");
		logger.info(startYear);
		logger.info(startMonth);
		logger.info(endYear);
		logger.info(endMonth);
		logger.info(eduName);
		logger.info(educationType);
		logger.info(cost);
		logger.info(pageNo);*/
		if(pageNo.length() > 1) {
			pageNo = "0";
		}
//		String pageNo =request.getParameter("pageNo");
		EducationVO educationVO = new EducationVO();
		
		if(startMonth.length() > 0 && endMonth.length() > 0 ) {
			if(startMonth.length() == 1) {
				startMonth = "0" + startMonth;
			}
			if(endMonth.length() == 1) {
				endMonth = "0" + endMonth;
			}
			educationVO.setStartDate(startYear + "-" + startMonth);
			educationVO.setEndDate(endYear + "-" + endMonth);
		}
		else {
			educationVO.setStartDate(null);
			educationVO.setEndDate(null);
		}
		if(eduName.equals("")) {
			eduName = null;
		}
		if(educationType.equals("")){
			educationType = null;
		}
		if(cost.equals("")){
			cost = null;
		}
		
		eduName=eduName.trim().toLowerCase();
		
		educationVO.setEducationTitle(eduName);
		educationVO.setEducationType(educationType);
		educationVO.setCost(cost);
		
		educationVO.setStartYear(startYear);
		educationVO.setStartMonth(startMonth);
		educationVO.setEndYear(endYear);
		educationVO.setEndMonth(endMonth);
		
		return educationService.doSearchList( educationVO , Integer.parseInt(pageNo) );
	}
	
	@RequestMapping("/eduDetail/{educationId}")
	public ModelAndView getOneEducationDetail(@PathVariable String educationId, HttpSession session, @RequestParam(required=false, defaultValue="0") int pageNo){
		return educationService.getOneEducationDetail(educationId, session, pageNo);
	}
	
	@RequestMapping("/doApplyEducation")
	public void doApplyEducation(@RequestParam String educationId, @RequestParam String educationType, HttpSession session, HttpServletResponse response){
		String applyStatus = educationService.doApplyEducation(educationId, educationType, session);
		AjaxUtil.sendResponse(response, applyStatus);
	}
	
	@RequestMapping("/doWriteComment")
	public ModelAndView doWriteAction(@Valid QNAVO qnaVO, Errors errors, String educationId, HttpSession session){
		return educationService.writeNewComment(session, qnaVO, errors, educationId);
	}

	@RequestMapping("/downloadFile/{educationId}")
	public ModelAndView doDownloadFile(@PathVariable String educationId, HttpServletRequest request, HttpServletResponse response){
		return educationService.doDownloadFile(educationId, request, response);
	}


	@RequestMapping("/doCancelEducation/{educationId}")
	public String doCancelEducation(@PathVariable String educationId,  HttpSession session) {
		return educationService.doCancelEducation(educationId, session);
	}
	
}


