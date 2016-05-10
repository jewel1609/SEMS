package com.ktds.sems.education.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;

import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;


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

	
	@RequestMapping("/cost")
	public String viewCostPage() {
		return "/education/cost";
	}
	
	@RequestMapping("/modifyEduCost")
	@ResponseBody
	public CostVO modifyEduCost(HttpServletRequest request, HttpServletResponse response) {
		String coId = request.getParameter("coId");
		String coNm = request.getParameter("coNm");
		
		CostVO cost = new CostVO();
		cost.setCoId(coId);
		cost.setCoNm(coNm);
		
		return educationService.modifyEduCost(cost);
	}
	
	@RequestMapping("/education/addLargeCategory")
	@ResponseBody
	public String  addNewLargeCategory(@Valid CategoryVO categoryVO, Errors erros){
		return educationService.addNewLargeCategory(categoryVO);
	}


}
