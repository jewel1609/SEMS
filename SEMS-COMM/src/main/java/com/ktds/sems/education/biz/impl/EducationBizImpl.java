package com.ktds.sems.education.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public class EducationBizImpl implements EducationBiz {
	
	private EducationDAO educationDAO;

	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}
	
	@Override
	public boolean validCategoryId(String categoryId, String categoryType) {
		Map<String, String> category = new HashMap<String, String>();
		category.put("categoryId", categoryId);
		category.put("categoryType", categoryType);
		
		return educationDAO.validCategoryId(category) > 0;
	}

	@Override
	public boolean modifyEduCost(CostVO cost) {
		CostVO modifyCost = new CostVO();
		CostVO orignCost = new CostVO();
		
		orignCost = educationDAO.getEduCostByCdId(cost.getCdId());
		if ( cost.getCdId().equals(orignCost.getCdId())) {
			modifyCost.setCdId(orignCost.getCdId());
			
			if ( !cost.getCdNm().equals(orignCost.getCdNm()) ) {
				modifyCost.setCdNm(cost.getCdNm());
			}
		}
		
		return educationDAO.modifyEduCost(modifyCost) > 0;
	}

	@Override
	public CostVO getEduCostByCdId(String coId) {
		return educationDAO.getEduCostByCdId(coId);
	}

	@Override
	public List<CostVO> getAllEduCost() {
		return educationDAO.getAllEduCost();
	}

	@Override
	public boolean deleteEduCost(String cdId) {
		return educationDAO.deleteEduCost(cdId) > 0;
	}

	@Override
	public boolean insertEduCost(CostVO cost) {
		return educationDAO.insertEduCost(cost) > 0;
	}


	@Override
	public boolean validCategoryName(String categoryName, String categoryType) {
		Map<String, String> category = new HashMap<String, String>();
		category.put("categoryName", categoryName);
		category.put("categoryType", categoryType);
		
		return educationDAO.validCategoryName(category) > 0;
	}

	@Override
	public boolean addNewCategory(CategoryVO categoryVO) {
		return educationDAO.addNewCategory(categoryVO) > 0;
	}

	@Override
	public List<CategoryVO> getAllLargeCategory() {
		return educationDAO.getAllLargeCategory();
	}

}
