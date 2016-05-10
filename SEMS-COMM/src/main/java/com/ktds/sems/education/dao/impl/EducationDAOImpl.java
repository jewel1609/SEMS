package com.ktds.sems.education.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO{
	
	@Override
	public int validCategoryId(Map<String, String> category) {
		return getSqlSession().selectOne("EducationDAO.validCategoryId", category);
	}

	@Override
	public CostVO getEduCostByCdId(String cdId) {
		return getSqlSession().selectOne("EducationDAO.getEduCostByCdId", cdId);
	}

	@Override
	public int modifyEduCost(CostVO modifyCost) {
		return getSqlSession().update("EducationDAO.modifyEduCost", modifyCost);
	}

	@Override
	public List<CostVO> getAllEduCost() {
		return getSqlSession().selectList("EducationDAO.getAllEduCost");
	}

	@Override
	public int deleteEduCost(String cdId) {
		return getSqlSession().delete("EducationDAO.deleteEduCost", cdId);
	}

	@Override
	public int insertEduCost(CostVO cost) {
		return getSqlSession().insert("EducationDAO.insertEduCost", cost);
	}

	@Override
	public int validCategoryName(Map<String, String> category) {
		return getSqlSession().selectOne("EducationDAO.validCategoryName", category);
	}

	@Override
	public int addNewCategory(CategoryVO categoryVO) {
		return getSqlSession().insert("EducationDAO.addNewCategory", categoryVO);
	}

	@Override
	public List<CategoryVO> getAllLargeCategory() {
		return getSqlSession().selectList("EducationDAO.getAllLargeCategory");
	}
}
