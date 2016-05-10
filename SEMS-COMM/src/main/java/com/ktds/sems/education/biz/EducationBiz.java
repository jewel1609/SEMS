package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public interface EducationBiz {
	
	boolean validCategoryId(String categoryId, String categoryType);

	public boolean modifyEduCost(CostVO cost);
	
	public CostVO getEduCostByCdId(String coId);

	public List<CostVO> getAllEduCost();


	public boolean deleteEduCost(String cdId);

	public boolean insertEduCost(CostVO cost);


	boolean validCategoryName(String categoryName, String categoryType);

	boolean addNewCategory(CategoryVO categoryVO);

	List<CategoryVO> getAllLargeCategory();

}
