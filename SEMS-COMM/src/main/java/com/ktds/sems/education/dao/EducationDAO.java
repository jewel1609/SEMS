package com.ktds.sems.education.dao;

import java.util.List;

import com.ktds.sems.education.vo.CostVO;

public interface EducationDAO {
	
	int validCategoryId(String categoryId);

	public CostVO getEduCostByCdId(String cdId);

	public int modifyEduCost(CostVO modifyCost);

	public List<CostVO> getAllEduCost();

}
