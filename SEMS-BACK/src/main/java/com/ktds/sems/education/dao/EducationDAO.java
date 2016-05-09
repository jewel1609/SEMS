package com.ktds.sems.education.dao;

import com.ktds.sems.education.vo.EducationVO;

public interface EducationDAO {

	EducationVO getOneEducation(String educationId);

	int modifyNewEducation(EducationVO changedEducationVO);

}
