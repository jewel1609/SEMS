package com.ktds.sems.education.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CostVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO{

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
}
