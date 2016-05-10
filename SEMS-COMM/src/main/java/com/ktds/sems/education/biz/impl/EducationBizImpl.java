package com.ktds.sems.education.biz.impl;

import java.util.List;

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
		
		orignCost = educationDAO.getEduCostByCdId(cost.getCdId());
		if ( cost.getCdId().equals(orignCost.getCdId())) {
			modifyCost.setCdId(orignCost.getCdId());
			
			if ( !cost.getCdNm().equals(orignCost.getCdNm()) ) {
				modifyCost.setCdNm(cost.getCdNm());
			}
		}
		
		return educationDAO.modifyEduCost(modifyCost) > 0;
	}

	@Override
	public CostVO getEduCostByCdId(String coId) {
		return educationDAO.getEduCostByCdId(coId);
	}

	@Override
	public List<CostVO> getAllEduCost() {
		return educationDAO.getAllEduCost();
	}
	
	
	

}
