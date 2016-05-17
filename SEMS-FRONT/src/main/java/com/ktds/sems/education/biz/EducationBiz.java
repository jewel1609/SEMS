package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationBiz {

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public int getTotalEducationCount();

	public EducationVO getOneEducationDetail(String educationId);

	public List<String> getMemberRegInfo(String id);

	public boolean writeNewComment(QNAVO qnaVO);

	public boolean doApplyEducation(String educationId, String id);

	public boolean doCancelEducation(String educationId, String id);

	public int getSearchedEducationCount(EducationVO educationVO);

	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO);

	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO);

	public String getNowDate();

	public int getNextReplySeq();

	public int isApplyMemberByEducationId(String educationId, String id);

	public int getEduReplyCount(String educationId);

	public List<String> getTypeName();

	public List<String> getCostName();

	public String doTransTypeId(String educationType);

	public String doTransCostId(String cost);

	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO);

	public int getTotalQNACount(String memberId);

	public boolean doReReplyInsert(QNAVO qnaVO);

	public QNAVO getSelectedQNA(String replyId);

	public QNAVO getSelectedQNAAnswer(String replyId);

	public void exportQNAListAsExcel(String memberId);

	public boolean hasApplyHistory(String memberId, String educationId);

	public boolean isEducationStarted(String educationId);

	public String getEmail(String id);

	public void sendEmailByReReply(QNAVO questionVO, QNAVO answerVO, String email);

}
