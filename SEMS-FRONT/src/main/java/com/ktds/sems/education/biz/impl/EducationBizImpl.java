package com.ktds.sems.education.biz.impl;

import java.util.List;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;

public class EducationBizImpl implements EducationBiz {

	private EducationDAO educationDAO;
	
	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}

	@Override
	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO) {
		return educationDAO.getAllEducationList(searchVO);
	}

	@Override
	public int getTotalEducationCount() {
		return educationDAO.getTotalEducationCount();
	}

	@Override
	public EducationVO getOneEducationDetail(String educationId) {
		return educationDAO.getOneEducationDetail(educationId);
	}

	@Override
	public int getSearchedEducationCount(String startDate, String endDate, String eduName, String educationType, String cost) {
		return educationDAO.getSearchedEducationCount(startDate, endDate, eduName, educationType, cost);
	}

	@Override
	public List<EducationVO> doSearchList(String startDate, String endDate, String eduName, String educationType,
			String cost, EducationSearchVO searchVO) {
		return educationDAO.doSearchList(startDate, endDate, eduName, educationType, cost, searchVO);
	}

}
