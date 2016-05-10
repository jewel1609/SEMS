package com.ktds.sems.education.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;

public class EducationServiceImpl implements EducationService {
	
	private EducationBiz educationBiz;

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public String addNewLargeCategory(CategoryVO categoryVO) {
		return "addNewLargeCategory success";
	}

	@Override
	public CostVO modifyEduCost(CostVO cost) {
		CostVO costVO = new CostVO();
		
		boolean isModify = educationBiz.modifyEduCost(cost);
		if (isModify) {
			costVO = educationBiz.getEduCostByCdId(cost.getCdId());
		}
		return costVO;
	}

	@Override
	public List<CostVO> getAllEduCost() {
		return educationBiz.getAllEduCost();
	}

}
