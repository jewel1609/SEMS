package com.ktds.sems.education.web;




import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationVO;



@Controller
public class EducationController {

	private EducationService educationService;

	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}
	
	@RequestMapping("/eduregister")
	public ModelAndView viewEduWritePage() { 
		return educationService.getAllEduCode();
	}
	
	@RequestMapping("/doWriteAction")
	public ModelAndView doWriteAction(@Valid EducationVO educationVO, Errors errors, MultipartHttpServletRequest request) {		
		return educationService.writeNewEducation(educationVO, errors, request);
	}
	
	@RequestMapping("/educationModify/{educationId}")
	public ModelAndView viewEducationModifyPage(@PathVariable String educationId){
		ModelAndView view = educationService.getOneEducationForUpdate(educationId); 
		return view;
	}
	
	@RequestMapping("/doEducationModifyAction")
	public ModelAndView doEducationModifyAction(@Valid EducationVO educationVO, Errors errors, MultipartHttpServletRequest request){
		return educationService.modifyNewEducation(educationVO, errors, request);
	}
	
	@RequestMapping("/educationHistory")
	public ModelAndView viewEduHistoryManagePage(@RequestParam(required=false, defaultValue="0") int pageNo){
		ModelAndView view = educationService.getAllEducationHistory(pageNo); 
		return view;
	}
	
	@RequestMapping("/checkEduApplicant")
	public ModelAndView viewCheckApplicantPage(@RequestParam(required=false, defaultValue="0") int pageNo){
		// JC (JOIN_CMPL)
		ModelAndView view = educationService.getJCEduHistory(pageNo); 
		return view;
	}
	
	@RequestMapping("/applyJoin/{educationId}/{memberId}")
	public ModelAndView applyJoin(@PathVariable String educationId, String memberId ){
		ModelAndView view = educationService.applyJoinEducationByMemberId(educationId, memberId);
		return view;
	}
}
