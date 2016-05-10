package com.ktds.sems.education.service;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public interface EducationService {

	public String addNewLargeCategory(CategoryVO categoryVO);

	public CostVO modifyEduCost(CostVO cost);

	public List<CostVO> getAllEduCost();

}
