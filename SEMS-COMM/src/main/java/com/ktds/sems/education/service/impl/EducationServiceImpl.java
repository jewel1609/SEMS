package com.ktds.sems.education.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.JsonResponseVO;

public class EducationServiceImpl implements EducationService {
	
	private EducationBiz educationBiz;

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public String validCategoryId(String categoryId, String categoryType) {
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId(categoryId);
		categoryVO.setCategoryType(categoryType);
		
		boolean isExist = educationBiz.validCategoryId(categoryVO);
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

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryName(categoryName);
		categoryVO.setCategoryType(categoryType);
		
		boolean isExist = educationBiz.validCategoryName(categoryVO);
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
			if ( result ) {
				jsonResponseVO.setResult(true);
			}
			else {
				throw new RuntimeException("카테고리를 추가중 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
			}
		}
		
		return jsonResponseVO;
	}

	@Override
	public ModelAndView viewCategoryPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("education/category");
		
		List<CategoryVO> largeCategoryList = educationBiz.getAllLargeCategory();
		view.addObject("largeCategoryList", largeCategoryList);
		
		return view;
	}

	@Override
	public JsonResponseVO getChildCategory(String parentCategoryId, String categoryType) {
		
		JsonResponseVO jsonResponseVO = new JsonResponseVO();
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId(parentCategoryId);
		categoryVO.setCategoryType(categoryType);
		
		List<CategoryVO> childCategories = educationBiz.getChildCategory(categoryVO);
		
		if ( childCategories != null ) {
			jsonResponseVO.setResult(true);
			jsonResponseVO.setData(childCategories);
		}
		else {
			jsonResponseVO.setResult(false);
			jsonResponseVO.setData("에러 발생");
		}
		
		return jsonResponseVO;
		
	}

}
