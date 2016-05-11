package com.ktds.sems.education.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.JsonResponseVO;

@Controller
public class EducationController {
	
	private EducationService educationService;
	
	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}

	@RequestMapping("/education/category")
	public ModelAndView viewCategoryPage(){
		return educationService.viewCategoryPage();
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
	public ModelAndView viewCostPage() {
		ModelAndView view = new ModelAndView();
		view.addObject("costList", educationService.getAllEduCost());
		view.setViewName("education/cost");
		return view;
	}
	
	@RequestMapping("/modifyEduCost")
	@ResponseBody
	public CostVO modifyEduCost(HttpServletRequest request) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		CostVO cost = new CostVO();
		cost.setCdId(cdId);
		cost.setCdNm(cdNm);
		
		return educationService.modifyEduCost(cost);
	}
	
	@RequestMapping("/deleteEduCost/{cdId}")
	public ModelAndView deleteEduCost(@PathVariable String cdId) {
		return educationService.deleteEduCost(cdId);
	}
	
	
	@RequestMapping("/insertEduCost")
	public ModelAndView insertEduCost(HttpServletRequest request) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		CostVO cost = new CostVO();
		cost.setCdId(cdId);
		cost.setCdNm(cdNm);
		
		return educationService.insertEduCost(cost);
	}

	@RequestMapping("/education/getChildCategory")
	@ResponseBody
	public JsonResponseVO getChildCategory(@RequestParam String parentCategoryId,@RequestParam String categoryType){
		return educationService.getChildCategory(parentCategoryId, categoryType);
	}
	
	@RequestMapping("/education/deleteCategory")
	@ResponseBody
	public String deleteCategoryAction(@RequestParam String categoryId, @RequestParam String categoryType){
		return educationService.deleteCategory(categoryId, categoryType);
	}

	@RequestMapping("/education/modifyCategory")
	@ResponseBody
	public JsonResponseVO modifyCategoryAction(@Valid CategoryVO categoryVO, Errors errors){
		return educationService.modifyCategory(categoryVO, errors);
	}

}
