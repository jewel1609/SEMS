package com.ktds.sems.education.biz.impl;

import java.util.List;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

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
	public int getSearchedEducationCount(EducationVO educationVO) {
		return educationDAO.getSearchedEducationCount( educationVO);
	}

	@Override
	public List<String> getMemberRegInfo(String id) {
		return educationDAO.getMemberRegInfo(id);

	}

	@Override
	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO) {
		return educationDAO.doSearchList( educationVO , searchVO);
	}
	
	@Override
	public boolean writeNewComment(QNAVO qnaVO) {
		return educationDAO.insertNewComment(qnaVO) > 0;
	}

	@Override
	public boolean doApplyEducation(String educationId, String id) {
		return educationDAO.doApplyEducation(educationId, id) > 0;
	}
	
	@Override
	public boolean doCancelEducation(String educationId) {
		return educationDAO.doCancelEducation(educationId) > 0;
	}
	@Override
	public List<QNAVO> getAllCommentByEducationId(String educationId) {
		return educationDAO.getAllCommentByEducationId(educationId);
	}

}


