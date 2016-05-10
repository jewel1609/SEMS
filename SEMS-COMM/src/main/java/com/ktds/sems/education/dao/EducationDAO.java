package com.ktds.sems.education.dao;

import java.util.List;

import com.ktds.sems.education.vo.CostVO;

public interface EducationDAO {

	public CostVO getEduCostByCdId(String cdId);

	public int modifyEduCost(CostVO modifyCost);

	public List<CostVO> getAllEduCost();

}
