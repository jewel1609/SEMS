package com.ktds.sems.cooperation.dao;

import java.util.List;

import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public interface CooperationDAO {

	public int getTotalCooperationCount();

	public List<CooperationVO> getAllCooperation(CooperationSearchVO searchVO);

	public CooperationVO getOneCooperation(String cooperationId);

	public int doDeleteCooperation(String cooperationId);

}
