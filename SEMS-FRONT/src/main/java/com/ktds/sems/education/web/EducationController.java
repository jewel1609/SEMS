package com.ktds.sems.education.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationVO;

@Controller
public class EducationController {

	private EducationService educationService;

	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}
	
	@RequestMapping("/educationList")
	public ModelAndView viewEducationListPage(){
		ModelAndView view = new ModelAndView(); 
		view.setViewName("education/list");
		return view;
	}
	
	@RequestMapping("/searchList")
	public ModelAndView doSearchList(@Valid EducationVO educationVO, Errors errors){
		return educationService.doSearchList(educationVO, errors);
	}
	

}
