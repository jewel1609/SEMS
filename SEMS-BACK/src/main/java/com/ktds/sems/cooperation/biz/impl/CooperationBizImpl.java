package com.ktds.sems.cooperation.biz.impl;

import java.util.List;

import com.ktds.sems.cooperation.biz.CooperationBiz;
import com.ktds.sems.cooperation.dao.CooperationDAO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public class CooperationBizImpl implements CooperationBiz{

	private CooperationDAO cooperationDAO;

	public void setCooperationDAO(CooperationDAO cooperationDAO) {
		this.cooperationDAO = cooperationDAO;
	}

	@Override
	public int getTotalCooperationCount() {
		return cooperationDAO.getTotalCooperationCount();
	}

	@Override
	public List<CooperationVO> getAllCooperation(CooperationSearchVO searchVO) {
		return cooperationDAO.getAllCooperation(searchVO);
	}

	@Override
	public CooperationVO getOneCooperation(String cooperationId) {
		return cooperationDAO.getOneCooperation(cooperationId);
	}

	@Override
	public boolean doDeleteCooperation(String cooperationId) {
		return cooperationDAO.doDeleteCooperation(cooperationId) > 0;
	}
	
}
