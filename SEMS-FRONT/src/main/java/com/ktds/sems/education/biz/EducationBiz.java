package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;

public interface EducationBiz {

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public int getTotalEducationCount();

	public EducationVO getOneEducationDetail(String educationId);

	public List<EducationVO> getMemberRegInfo(String id);

	public boolean writeNewComment(QNAVO qnaVO);

	public boolean doApplyEducation(String educationId, String id);

	public boolean doCancelEducation(String educationId, String id);

	public int getSearchedEducationCount(EducationVO educationVO);

	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO);

	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO);

	public String getNowDate();

	public int getNextReplySeq();

	public String isApplyMemberByEducationId(String educationId, String id);

	public int getEduReplyCount(String educationId);

	public List<String> getTypeName();

	public List<String> getCostName();

	public String doTransTypeId(String educationType);

	public String doTransCostId(String cost);

	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO);

	public int getTotalQNACount(QNASearchVO qnaSearchVO);

	public boolean doReReplyInsert(QNAVO qnaVO);

	public QNAVO getSelectedQNA(String replyId);

	public List<QNAVO> getSelectedQNAAnswer(String replyId);

	public void exportQNAListAsExcel(String memberId);

	public boolean hasApplyHistory(String memberId, String educationId);

	public boolean isEducationStarted(String educationId);

	public String getEmail(String id);

	public void sendEmailByReReply(QNAVO questionVO, QNAVO answerVO, String email);

	public int getNextReReplyEval();

	public boolean plusReReplyLike(String replyId);

	public boolean insertReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public boolean checkReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public String getStartYear();

	public String getEndYear();

	public boolean insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO);

	public boolean plusReReplyDislike(String replyId);

	public boolean doRequestRetraction(String educationId, String retractionMsg, String memberId);

	public int getTotalMemberNumber(String educationId);

	public boolean doReserveEducation(String educationId, String id);

	public boolean updateStateToApply(String educationId);

}
