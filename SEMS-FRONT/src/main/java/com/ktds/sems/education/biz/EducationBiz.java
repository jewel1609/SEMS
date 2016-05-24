package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.member.vo.MemberVO;

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

	public boolean exportQNAListAsExcel(String memberId);

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
	
	public boolean doReReplyDelete(QNAVO qnaVO);
	
	public boolean deleteReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public int getTotalMemberNumber(String educationId);

	public boolean doReserveEducation(String educationId, String id);

	public boolean updateStateToApply(String educationId);

	public List<EducationVO> getMyEducationList(String id);

	public List<EducationQNABBSVO> getAllEducationQNAList();

	public void addQNABBS(EducationQNABBSVO eduBBS);

	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO);

	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO);

	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId);

	public void doReportWriteAction(EducationReportVO educationReportVO);

	public int getNextReportSeq();

	public void addHitsByAtcId(String atcId);

	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO);

	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO);

	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO);

	public List<MemberVO> getAllMemberOfEducation(String educationId);

	public boolean addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip);

	public EducationReportVO getOneEducationReport(EducationReportVO educationReportVO);

	public String getNowDateTime();

}
