package com.ktds.sems.education.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CostVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO{

	@Override
	public CostVO getEduCostByCoId(String coId) {
		return getSqlSession().selectOne("", coId);
	}

	@Override
	public int modifyEduCost(CostVO modifyCost) {
		return getSqlSession().update("", modifyCost);
	}

}
