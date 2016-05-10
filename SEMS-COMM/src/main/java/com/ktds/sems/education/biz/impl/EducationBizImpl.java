package com.ktds.sems.education.biz.impl;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CostVO;

public class EducationBizImpl implements EducationBiz {
	
	private EducationDAO educationDAO;

	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}

	@Override
	public boolean modifyEduCost(CostVO cost) {
		CostVO modifyCost = new CostVO();
		
		CostVO orignCost = new CostVO();
		orignCost = educationDAO.getEduCostByCoId(cost.getCoId());
		if ( cost.getCoId().equals(orignCost.getCoId())) {
			modifyCost.setCoId(orignCost.getCoId());
			
			if ( !cost.getCoNm().equals(orignCost.getCoNm()) ) {
				modifyCost.setCoNm(cost.getCoNm());
			}
		}
		
		return educationDAO.modifyEduCost(modifyCost) > 0;
	}

	@Override
	public CostVO getEduCostByCoId(String coId) {
		return educationDAO.getEduCostByCoId(coId);
	}
	
	
	

}
