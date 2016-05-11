package com.ktds.sems.education.dao;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.EducationTypeVO;
import com.ktds.sems.education.vo.EducationVO;

public interface EducationDAO {

	public 	int nextEduSeq();
	public String nowDate();
	public int insertNewEducation(EducationVO educationVO);
	public List<CostVO> costCodeList();
	public List<EducationTypeVO> typeCodeList();
	public List<CategoryVO> categoryCodeList();
	
	EducationVO getOneEducation(String educationId);
	int modifyNewEducation(EducationVO changedEducationVO);
}
