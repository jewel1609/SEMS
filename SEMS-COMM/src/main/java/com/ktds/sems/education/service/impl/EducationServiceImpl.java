package com.ktds.sems.education.service.impl;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.CategoryVO;

public class EducationServiceImpl implements EducationService {
	
	private EducationBiz educationBiz;

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	@Override
	public String addNewLargeCategory(CategoryVO categoryVO) {
		return "addNewLargeCategory success";
	}

}
