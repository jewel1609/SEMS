package com.ktds.sems.education.dao;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;

public interface EducationDAO {

	public List<EducationVO> doSearchList(EducationVO educationVO);
}
