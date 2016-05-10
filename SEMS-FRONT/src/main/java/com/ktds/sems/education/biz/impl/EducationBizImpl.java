package com.ktds.sems.education.biz.impl;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationVO;

public class EducationBizImpl implements EducationBiz {

	private EducationDAO educationDAO;
	
	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}


}
