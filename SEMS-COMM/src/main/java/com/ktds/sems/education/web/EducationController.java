package com.ktds.sems.education.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.JsonResponseVO;

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
		return "education/category";
	}
	
	@RequestMapping("/education/validCategoryId")
	@ResponseBody
	public String validCategoryIdAction(@RequestParam String categoryId, @RequestParam String categoryType){
		return educationService.validCategoryId(categoryId, categoryType);
	}
	
	@RequestMapping("/education/validCategoryName")
	@ResponseBody
	public String validCategoryNameAction(@RequestParam String categoryName, @RequestParam String categoryType){
		return educationService.validCategoryName(categoryName, categoryType);
	}
	
	@RequestMapping("/education/addCategory")
	@ResponseBody
	public JsonResponseVO addNewCategoryAction(@Valid CategoryVO categoryVO, Errors errors){
		return educationService.addNewCategory(categoryVO, errors);
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

}
