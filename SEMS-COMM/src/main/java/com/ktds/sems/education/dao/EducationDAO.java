package com.ktds.sems.education.dao;

import java.util.List;
import java.util.Map;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public interface EducationDAO {
	
	int validCategoryId(Map<String, String> category);

	public CostVO getEduCostByCdId(String cdId);

	public int modifyEduCost(CostVO modifyCost);

	public List<CostVO> getAllEduCost();

	public int validCategoryName(Map<String, String> category);

	public int addNewCategory(CategoryVO categoryVO);

	public int deleteEduCost(String cdId);

	public int insertEduCost(CostVO cost);

	List<CategoryVO> getAllLargeCategory();

}
