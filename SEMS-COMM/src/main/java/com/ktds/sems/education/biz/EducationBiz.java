package com.ktds.sems.education.biz;

import com.ktds.sems.education.vo.CostVO;

public interface EducationBiz {

	public boolean modifyEduCost(CostVO cost);
	
	public CostVO getEduCostByCoId(String coId);

}
