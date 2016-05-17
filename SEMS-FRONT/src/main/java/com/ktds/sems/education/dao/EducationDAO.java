package com.ktds.sems.education.dao;

import java.util.List;

import com.ktds.sems.education.vo.EducationSearchVO;
import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationDAO {

	public int getTotalEducationCount();

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public EducationVO getOneEducationDetail(String educationId);

	public List<String> getMemberRegInfo(String id);

	public int doApplyEducation(String educationId, String id);

	public int getSearchedEducationCount(EducationVO educationVO);

	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO);

	public int insertNewComment(QNAVO qnaVO);
	

	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO);

	public String getNowDate();

	public int getNextReplySeq();

	public int doCancelEducation(String educationId, String id);

	public int isApplyMemberByEducationId(String educationId, String id);

	public int getEduReplyCount(String educationId);

	public List<String> getCostName();

	public List<String> getTypeName();

	public String doTransCostId(String cost);

	public String doTransTypeId(String educationType);

	public int getTotalQNACount(String memberId);

	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO);

	public int doReReplyInsert(QNAVO qnaVO);

	public QNAVO getSelectedQNA(String replyId);

	public QNAVO getSelectedQNAAnswer(String replyId);

	public List<QNAVO> exportQNAListAsExcel(String memberId);

	public List<EducationVO> getApplyHistory(String memberId, String educationId);

	public String getEmail(String id);

	public String getStartYear();

	public String getEndYear();

}
