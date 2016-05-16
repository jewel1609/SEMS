package com.ktds.sems.education.biz.impl;

import java.util.List;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
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
		//qnaVO.getReplyId();
		return educationDAO.insertNewComment(qnaVO) > 0;
	}

	@Override
	public boolean doApplyEducation(String educationId, String id) {
		return educationDAO.doApplyEducation(educationId, id) > 0;
	}
	
	@Override
	public boolean doCancelEducation(String educationId, String id) {
		return educationDAO.doCancelEducation(educationId, id) > 0;
	}

	@Override
	public String getNowDate() {
		return educationDAO.getNowDate();
	}

	@Override
	public int getNextReplySeq() {
		return educationDAO.getNextReplySeq();
	}

	@Override
	public int isApplyMemberByEducationId(String educationId, String id) {
		return educationDAO.isApplyMemberByEducationId(educationId, id);
	}

	@Override
	public int getEduReplyCount(String educationId) {
		return educationDAO.getEduReplyCount(educationId);
	}

	@Override
	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO) {
		return educationDAO.getAllCommentByEducationId(educationId, searchVO);
	}

	@Override
	public List<String> getTypeName() {
		return educationDAO.getTypeName();
	}

	@Override
	public List<String> getCostName() {
		return educationDAO.getCostName();
	}

	@Override
	public String doTransTypeId(String educationType) {
		return educationDAO.doTransTypeId(educationType);
	}

	@Override
	public String doTransCostId(String cost) {
		// TODO Auto-generated method stub
		return educationDAO.doTransCostId(cost);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public int getTotalQNACount(String memberId) {
		return educationDAO.getTotalQNACount(memberId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO) {
		return educationDAO.getAllQNAList(qnaSearchVO);
	}
}


