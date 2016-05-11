package com.ktds.sems.education.web;

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
import com.ktds.sems.education.service.impl.EducationServiceImpl;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

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
		return educationService.getAllEducationList(pageNo);
	}
	
	@RequestMapping("/searchList")
	public ModelAndView doSearchList(@RequestParam String startYear, @RequestParam String startMonth, 
			@RequestParam String endYear, @RequestParam String endMonth, @RequestParam String eduName,
			@RequestParam String educationType, @RequestParam String cost, @RequestParam(required=false, defaultValue="0") int pageNo){
		logger.info("검색");
		
		EducationVO educationVO = new EducationVO();
		if(startMonth.length() == 1) {
			startMonth = "0" + startMonth;
		}
		if(endMonth.length() == 1) {
			endMonth = "0" + endMonth;
		}
		
		educationVO.setStartDate(startYear + "/" + startMonth);
		educationVO.setEndDate(endYear + "/" + endMonth);
		educationVO.setEducationTitle(eduName);
		educationVO.setEducationType(educationType);
		educationVO.setCost(cost);
		
		return educationService.doSearchList(	educationVO , pageNo );
	}
	
	@RequestMapping("/eduDetail/{educationId}")
	public ModelAndView getOneEducationDetail(@PathVariable String educationId){
		return educationService.getOneEducationDetail(educationId);
	}
	
	@RequestMapping("/doApplyEducation/${ education.educationId }")
	public ModelAndView doApplyEducation(@PathVariable String educationId, HttpSession session){
		return educationService.doApplyEducation(educationId, session);
	}
	
	@RequestMapping("/doWriteComment")
	public ModelAndView doWriteAction(@Valid QNAVO qnaVO, Errors errors){
		return educationService.writeNewComment(qnaVO, errors);
	}
	
	@RequestMapping("/doCancelEducation/${/{educationId}")
	public String doCancelEducation(@PathVariable String educationId) {
		return educationService.doCancelEducation(educationId);
	}
	
	
}


