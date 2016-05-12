package com.ktds.sems.education.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

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
		/*
		logger.info("TEST2" + searchVO.getEndIndex());
		*/
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
	public List<String> getMemberRegInfo(String id) {
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
	public List<QNAVO> getAllCommentByEducationId(String educationId) {
		return getSqlSession().selectList("EducationDAO.getAllCommentByEducationId", educationId);
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
	public int isApplyMemberByEducationId(String educationId, String id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		return getSqlSession().selectOne("EducationDAO.isApplyMemberByEducationId", paramMap);
	}

}
