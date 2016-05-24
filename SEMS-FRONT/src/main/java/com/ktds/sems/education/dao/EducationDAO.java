package com.ktds.sems.education.dao;

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

public interface EducationDAO {

	public int getTotalEducationCount();

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public EducationVO getOneEducationDetail(String educationId);

	public List<EducationVO> getMemberRegInfo(String id);

	public int doApplyEducation(String educationId, String id);

	public int getSearchedEducationCount(EducationVO educationVO);

	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO);

	public int insertNewComment(QNAVO qnaVO);
	

	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO);

	public String getNowDate();

	public int getNextReplySeq();

	public int doCancelEducation(String educationId, String id);

	public String isApplyMemberByEducationId(String educationId, String id);

	public int getEduReplyCount(String educationId);

	public List<String> getCostName();

	public List<String> getTypeName();

	public String doTransCostId(String cost);

	public String doTransTypeId(String educationType);

	public int getTotalQNACount(QNASearchVO QNASearchVO);

	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO);

	public int doReReplyInsert(QNAVO qnaVO);

	public QNAVO getSelectedQNA(String replyId);

	public List<QNAVO> getSelectedQNAAnswer(String replyId);

	public List<QNAVO> exportQNAListAsExcel(String memberId);

	public List<EducationVO> getApplyHistory(String memberId, String educationId);

	public String getEmail(String id);

	public int getNextReReplyEval();

	public int plusReReplyLike(String replyId);

	public int insertReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public int checkReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public String getStartYear();

	public String getEndYear();
	
	public int insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO);

	public int plusReReplyDislike(String replyId);

	public int doRequestRetraction(String educationId, String retractionMsg, String memberId);

	public int doReReplyDelete(QNAVO qnaVO);

	public int deleteReReplyEval(ReRplyEvalVO reRplyEvalVO);

	public int getTotalMemberNumber(String educationId);

	public int doReserveEducation(String educationId, String id);

	public int updateStateToApply(String educationId);

	public List<EducationVO> getMyEducationList(String id);

	public List<EducationQNABBSVO> getAllEducationQNAList();

	public void addQNABBS(EducationQNABBSVO eduBBS);

	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO);

	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO);

	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId);

	public int getNextReportSeq();

	public void doReportWriteAction(EducationReportVO educationReportVO);

	public void addHitsByAtcId(String atcId);

	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO);

	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO);

	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO);

	public List<MemberVO> getAllMemberOfEducation(String educationId);

	public int addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip);

}
