package com.ktds.sems.education.service;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public interface EducationService {

	public String addNewLargeCategory(CategoryVO categoryVO);

	public CostVO modifyEduCost(CostVO cost);

}
