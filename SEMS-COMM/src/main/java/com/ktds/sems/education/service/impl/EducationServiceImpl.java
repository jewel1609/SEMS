package com.ktds.sems.education.service.impl;

import org.springframework.validation.Errors;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.JsonResponseVO;
import com.ktds.sems.education.vo.CostVO;

public class EducationServiceImpl implements EducationService {
	
	private EducationBiz educationBiz;

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public String validCategoryId(String categoryId, String categoryType) {
		boolean isExist = educationBiz.validCategoryId(categoryId, categoryType);
		if ( isExist ) {
			return "true";
		}
		else {
			return "false";
		}
	}

	@Override
	public CostVO modifyEduCost(CostVO cost) {
		CostVO costVO = new CostVO();
		
		boolean isModify = educationBiz.modifyEduCost(cost);
		if (isModify) {
			costVO = educationBiz.getEduCostByCdId(cost.getCdId());
		}
		return costVO;
	}

	@Override
	public List<CostVO> getAllEduCost() {
		return educationBiz.getAllEduCost();
	}


	@Override
	public ModelAndView deleteEduCost(String cdId) {
		ModelAndView view = new ModelAndView();
		
		boolean delectResult = educationBiz.deleteEduCost(cdId);
		
		if (!delectResult) {
			view.setViewName("redirect:/cost");
		}
		view.setViewName("redirect:/cost");
		return view;
	}

	@Override
	public ModelAndView insertEduCost(CostVO cost) {
		ModelAndView view = new ModelAndView();
		
		boolean insertResult = educationBiz.insertEduCost(cost);
		
		if (!insertResult) {
			view.setViewName("redirect:/cost");
		}
		view.setViewName("redirect:/cost");
		return view;
	}

	@Override
	public String validCategoryName(String categoryName, String categoryType) {
		boolean isExist = educationBiz.validCategoryName(categoryName, categoryType);
		if ( isExist ) {
			return "true";
		}
		else {
			return "false";
		}
	}

	@Override
	public JsonResponseVO addNewCategory(CategoryVO categoryVO, Errors errors) {
		JsonResponseVO jsonResponseVO = new JsonResponseVO();
		
		if ( errors.hasErrors() ) {
			jsonResponseVO.setResult(false);
			jsonResponseVO.setData(errors.getAllErrors());
		}
		else {
			boolean result = educationBiz.addNewCategory(categoryVO);
			jsonResponseVO.setResult(true);
		}
		
		return jsonResponseVO;
	}

}
