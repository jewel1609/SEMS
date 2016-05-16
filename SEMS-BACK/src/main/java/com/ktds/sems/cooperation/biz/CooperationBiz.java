package com.ktds.sems.cooperation.biz;

import java.util.List;

import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public interface CooperationBiz {

	public int getTotalCooperationCount();

	public List<CooperationVO> getAllCooperation(CooperationSearchVO searchVO);

	public CooperationVO getOneCooperation(String cooperationId);

	public boolean doDeleteCooperation(String cooperationId);

}
