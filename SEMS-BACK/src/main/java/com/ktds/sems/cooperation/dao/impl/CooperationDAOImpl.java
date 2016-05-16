package com.ktds.sems.cooperation.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.cooperation.dao.CooperationDAO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public class CooperationDAOImpl extends SqlSessionDaoSupport implements CooperationDAO{

	@Override
	public int getTotalCooperationCount() {
		return getSqlSession().selectOne("CooperationDAO.getTotalCooperationCount");
	}

	@Override
	public List<CooperationVO> getAllCooperation(CooperationSearchVO searchVO) {
		return getSqlSession().selectList("CooperationDAO.getAllCooperation", searchVO);
	}

	@Override
	public CooperationVO getOneCooperation(String cooperationId) {
		return getSqlSession().selectOne("CooperationDAO.getOneCooperation", cooperationId);
	}

	@Override
	public int doDeleteCooperation(String cooperationId) {
		return getSqlSession().delete("CooperationDAO.doDeleteCooperation", cooperationId);
	}
	
}
