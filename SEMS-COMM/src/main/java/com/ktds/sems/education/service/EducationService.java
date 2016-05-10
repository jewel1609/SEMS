package com.ktds.sems.education.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.JsonResponseVO;
import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public interface EducationService {
	
	JsonResponseVO addNewCategory(CategoryVO categoryVO, Errors errors);
	
	String validCategoryId(String categoryId, String categoryType);

	public CostVO modifyEduCost(CostVO cost);

	public List<CostVO> getAllEduCost();

	String validCategoryName(String categoryName, String categoryType);

}
