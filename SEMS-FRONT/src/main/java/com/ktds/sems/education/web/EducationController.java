package com.ktds.sems.education.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

@Controller
public class EducationController {

	private EducationService educationService;

	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}
	
	@RequestMapping("/educationList")
	public ModelAndView viewEducationListPage(@RequestParam(required=false, defaultValue="0") int pageNo){
		return educationService.getAllEducationList(pageNo);
	}
	
	@RequestMapping("/searchList")
	public ModelAndView doSearchList(@PathVariable String startYear, @PathVariable String startMonth, 
			@PathVariable String endYear, @PathVariable String endMonth, @PathVariable String eduName,
			@PathVariable String educationType, @PathVariable String cost, @RequestParam(required=false, defaultValue="0") int pageNo){
		return educationService.doSearchList(startYear, startMonth, endYear, endMonth, eduName, educationType, cost, pageNo );
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
}


