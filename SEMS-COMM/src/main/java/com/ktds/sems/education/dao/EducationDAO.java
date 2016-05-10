package com.ktds.sems.education.dao;

import com.ktds.sems.education.vo.CostVO;

public interface EducationDAO {

	public CostVO getEduCostByCoId(String coId);

	public int modifyEduCost(CostVO modifyCost);

}
