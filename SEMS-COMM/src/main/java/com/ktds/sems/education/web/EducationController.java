package com.ktds.sems.education.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;

@Controller
public class EducationController {
	
	private EducationService educationService;
	
	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}

	@RequestMapping("/education/category")
	public String viewCategoryPage(){
		return "/education/category";
	}
	
	@RequestMapping("/education/addLargeCategory")
	@ResponseBody
	public String  addNewLargeCategory(@Valid CategoryVO categoryVO, Errors erros){
		return educationService.addNewLargeCategory(categoryVO);
	}

}
