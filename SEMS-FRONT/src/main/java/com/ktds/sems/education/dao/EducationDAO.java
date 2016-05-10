package com.ktds.sems.education.dao;

import java.util.List;

import com.ktds.sems.education.vo.EducationSearchVO;
import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationDAO {

	public int getTotalEducationCount();

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public EducationVO getOneEducationDetail(String educationId);

	public int getSearchedEducationCount(String startDate, String endDate, String eduName, String educationType, String cost);

	public List<EducationVO> doSearchList(String startDate, String endDate, String eduName, String educationType,
			String cost, EducationSearchVO searchVO);
	public int insertNewComment(QNAVO qnaVO);
}
