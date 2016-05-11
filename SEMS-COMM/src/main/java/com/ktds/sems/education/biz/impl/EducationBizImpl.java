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
	public boolean validCategoryId(CategoryVO categoryVO) {
		return educationDAO.validCategoryId(categoryVO) > 0;
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
	public boolean validCategoryName(CategoryVO categoryVO) {
		return educationDAO.validCategoryName(categoryVO) > 0;
	}

	@Override
	public boolean addNewCategory(CategoryVO categoryVO) {
		return educationDAO.addNewCategory(categoryVO) > 0;
	}

	@Override
	public List<CategoryVO> getAllLargeCategory() {
		return educationDAO.getAllLargeCategory();
	}

	@Override
	public List<CategoryVO> getChildCategory(CategoryVO categoryVO) {
		return educationDAO.getChildCategory(categoryVO);
	}

	@Override
	public boolean deleteCategory(CategoryVO categoryVO) {
		boolean result = false;
		if ( categoryVO.getCategoryType().equals("large") ) {
			result = deleteLargeCategory(categoryVO);
		}
		else if ( categoryVO.getCategoryType().equals("medium") ) {
			result = deleteMediumCategory(categoryVO);
		}
		else if ( categoryVO.getCategoryType().equals("small") ) {
			result = deleteSmallCategory(categoryVO);
		}
		
		return result;
	}

	@Override
	public boolean modifyCategory(CategoryVO categoryVO) {
		return educationDAO.modifyCategory(categoryVO) > 0;
	}

	@Override
	public boolean deleteLargeCategory(CategoryVO categoryVO) {
		
		List<CategoryVO> childCategories = getChildCategory(categoryVO);

		for (CategoryVO tmpCategoryVO : childCategories) {
			if ( !deleteMediumCategory(tmpCategoryVO) ) {
				return false;
			}
		}
		
		return deleteCategory(categoryVO);
	}

	@Override
	public boolean deleteMediumCategory(CategoryVO categoryVO) {
		List<CategoryVO> childCategories = getChildCategory(categoryVO);

		for (CategoryVO tmpCategoryVO : childCategories) {
			if ( !deleteSmallCategory(tmpCategoryVO) ) {
				return false;
			}
		}
		
		return deleteCategory(categoryVO);
	}

	@Override
	public boolean deleteSmallCategory(CategoryVO categoryVO) {
		return deleteCategory(categoryVO);
	}
	
}
