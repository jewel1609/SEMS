package com.ktds.sems.education.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.education.dao.EducationDAO;
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

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO {
	
	private Logger logger = LoggerFactory.getLogger(EducationDAOImpl.class);	
	
	@Override
	public int getTotalEducationCount() {
		return getSqlSession().selectOne("EducationDAO.getTotalEducationCount");
	}

	@Override
	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationList", searchVO);
	}

	@Override
	public EducationVO getOneEducationDetail(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getOneEducationDetail", educationId);
	}

	@Override
	public int getSearchedEducationCount(EducationVO educationVO) {
		logger.info(educationVO.getStartDate());
		logger.info(educationVO.getEndDate());
		logger.info("타이틀"+educationVO.getEducationTitle());
		logger.info("COST"+educationVO.getCost());
		logger.info("교육타입"+educationVO.getEducationType());
		
		return getSqlSession().selectOne("EducationDAO.getSearchedEducationCount", educationVO);
		
	}

	@Override
	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("educationVO", educationVO);
		paramMap.put("searchVO", searchVO );

/*		paramMap.put("startIndex", String.valueOf(searchVO.getStartIndex()));
		paramMap.put("endIndex", String.valueOf(searchVO.getEndIndex()));*/
		
		return getSqlSession().selectList("EducationDAO.doSearchList", paramMap);
	}
	
	@Override
	public int insertNewComment(QNAVO qnaVO) {
		// <!-- Not Null 상태의 Columns 들의 데이터 전달 필요 ( 현재 고정 입력 값 지정 상태 )-->
		return getSqlSession().insert("EducationDAO.insertNewComment", qnaVO);
	}
	
	@Override
	public int doCancelEducation(String educationId, String id) {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().delete("EducationDAO.doCancelEducation", paramMap);
	}

	@Override
	public List<EducationVO> getMemberRegInfo(String id) {
		return getSqlSession().selectList("EducationDAO.getMemberRegInfo", id);
	}

	@Override
	public int doApplyEducation(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().insert("EducationDAO.doApplyEducation", paramMap);
	}
	@Override
	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("startIndex", searchVO.getStartIndex()+"");
		paramMap.put("endIndex", searchVO.getEndIndex()+"");
		
		return getSqlSession().selectList("EducationDAO.getAllCommentByEducationId", paramMap);
	}
	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("EducationDAO.getNowDate");
	}

	@Override
	public int getNextReplySeq() {
		return getSqlSession().selectOne("EducationDAO.getNextReplySeq");
	}

	@Override
	public String isApplyMemberByEducationId(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().selectOne("EducationDAO.isApplyMemberByEducationId", paramMap);
	}

	@Override
	public int getEduReplyCount(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getEduReplyCount", educationId);
	}

	@Override
	public List<String> getCostName() {
		return getSqlSession().selectList("EducationDAO.getCostName");
	}

	@Override
	public List<String> getTypeName() {
		return getSqlSession().selectList("EducationDAO.getTypeName");
	}

	@Override
	public String doTransCostId(String cost) {
		return getSqlSession().selectOne("EducationDAO.doTransCostId", cost);
	}

	@Override
	public String doTransTypeId(String educationType) {
		return getSqlSession().selectOne("EducationDAO.doTransTypeId", educationType);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public int getTotalQNACount(QNASearchVO qnaSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalQNACount", qnaSearchVO);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllQNAList", qnaSearchVO);
	}

	@Override
	public int doReReplyInsert(QNAVO qnaVO) {
		return getSqlSession().insert("EducationDAO.doReReplyInsert", qnaVO);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public QNAVO getSelectedQNA(String replyId) {
		return getSqlSession().selectOne("EducationDAO.getSelectedQNA" ,replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getSelectedQNAAnswer(String replyId) {
		return getSqlSession().selectList("EducationDAO.getSelectedQNAAnswer" ,replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> exportQNAListAsExcel(String memberId) {
		return getSqlSession().selectList("EducationDAO.exportQNAListAsExcel", memberId);
	}
	
	@Override
	public List<EducationVO> getApplyHistory(String memberId, String educationId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("educationId", educationId);
		return getSqlSession().selectList("EducationDAO.getApplyHistory", map);
	}

	@Override
	public String getEmail(String id) {
		return getSqlSession().selectOne("EducationDAO.getEmail", id);
	}

	@Override
	public int getNextReReplyEval() {
		return getSqlSession().selectOne("EducationDAO.getNextReReplyEval");
	}

	@Override
	public int plusReReplyLike(String replyId) {
		return getSqlSession().update("EducationDAO.plusReReplyLike", replyId);
	}

	@Override
	public int insertReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().insert("EducationDAO.insertReReplyEval", reRplyEvalVO);
	}

	@Override
	public int checkReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().selectOne("EducationDAO.checkReReplyEval", reRplyEvalVO);
	}

	@Override
	public String getStartYear() {
		return getSqlSession().selectOne("EducationDAO.getStartYear");
	}

	@Override
	public String getEndYear() {
		return getSqlSession().selectOne("EducationDAO.getEndYear");
	}

	@Override
	public int insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().insert("EducationDAO.insertReReplyEvalByDislike",reRplyEvalVO);
	}

	@Override
	public int plusReReplyDislike(String replyId) {
		return getSqlSession().update("EducationDAO.plusReReplyDislike", replyId);
	}

	@Override
	public int doRequestRetraction(String educationId, String retractionMsg, String memberId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("educationId", educationId);
		map.put("retractionMsg", retractionMsg);
		map.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.doRequestRetraction", map);
	}
	
	/**
	 * @author 206-002 공정민
	 */
	@Override
	public int getTotalMemberNumber(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getTotalMemberNumber", educationId);
	}

	@Override
	public int doReserveEducation(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().insert("EducationDAO.doReserveEducation", paramMap);
	}

	@Override
	public int doReReplyDelete(QNAVO qnaVO) {
		
		return getSqlSession().delete("EducationDAO.doReReplyDelete",qnaVO);
	}

	@Override
	public int deleteReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return getSqlSession().delete("EducationDAO.deleteReReplyEval", reRplyEvalVO);
	}

	@Override
	public int updateStateToApply(String educationId) {		
		return getSqlSession().update("EducationDAO.updateStateToApply", educationId);
	}


	@Override
	public List<EducationVO> getMyEducationList(String id) {
		return getSqlSession().selectList("EducationDAO.getMyEducationList", id);
	}


	@Override
	public List<EducationQNABBSVO> getAllEducationQNAList() {
		return getSqlSession().selectList("EducationDAO.getAllEducationQNAList");
	}

	@Override
	public void addQNABBS(EducationQNABBSVO eduBBS) {
		getSqlSession().insert("EducationDAO.addQNABBS", eduBBS);
	}

	@Override
	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationReportList", educationReportSearchVO);
	}

	@Override
	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEducationReportCount", educationReportSearchVO);
	}

	@Override
	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId) {
		return getSqlSession().selectOne("EducationDAO.getOneQNABBSByAtcId", atcId);
	}

	@Override
	public int getNextReportSeq() {
		return getSqlSession().selectOne("EducationDAO.getNextReportSeq");
	}

	@Override
	public void doReportWriteAction(EducationReportVO educationReportVO) {
		getSqlSession().insert("EducationDAO.doReportWriteAction", educationReportVO);
	}

	@Override
	public void addHitsByAtcId(String atcId) {
		getSqlSession().update("EducationDAO.addHitsByAtcId", atcId);
	}

	@Override
	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO) {
		getSqlSession().insert("EducationDAO.addQNAReply", eduBBSReplyVO);
	}

	@Override
	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduReportCount", eduReportSearchVO);
	}

	@Override
	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduReport", eduReportSearchVO);
	}
	
	@Override
	public List<MemberVO> getAllMemberOfEducation(String educationId) {
		return getSqlSession().selectList("EducationDAO.getAllMemberOfEducation", educationId);
	}

	@Override
	public int addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("educationId", educationId);
		map.put("retractionMsg", retractionMsg);
		map.put("memberId", memberId);
		map.put("ip", ip);
		return getSqlSession().insert("EducationDAO.addRequestRetractionHistory", map);
	}

}
