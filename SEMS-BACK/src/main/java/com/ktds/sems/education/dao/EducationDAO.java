package com.ktds.sems.education.dao;

import com.ktds.sems.education.vo.EducationVO;

public interface EducationDAO {

	public 	int nextEduSeq();
	public String nowDate();
	public int insertNewEducation(EducationVO educationVO);
	EducationVO getOneEducation(String educationId);

	int modifyNewEducation(EducationVO changedEducationVO);
}
