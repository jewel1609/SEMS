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

	@RequestMapping("/educationModify/{educationId}")
	public ModelAndView viewEducationModifyPage(@PathVariable String educationId){
		ModelAndView view = educationService.getOneEducation(educationId); 
		view.setViewName("education/update");
		return view;
	}
	
	@RequestMapping("/doEducationModifyAction")
	public ModelAndView doEducationModifyAction(@Valid EducationVO educationVO, Errors errors){
		return educationService.modifyNewEducation(educationVO, errors);
	}
	

}
