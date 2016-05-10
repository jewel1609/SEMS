package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.CostVO;

public interface EducationBiz {

	public boolean modifyEduCost(CostVO cost);
	
	public CostVO getEduCostByCdId(String coId);

	public List<CostVO> getAllEduCost();

}
