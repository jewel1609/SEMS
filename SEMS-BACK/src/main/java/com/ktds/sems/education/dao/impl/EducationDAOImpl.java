package com.ktds.sems.education.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationTypeVO;
import com.ktds.sems.education.vo.EducationVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO {

	@Override
	public int nextEduSeq() {
		return getSqlSession().selectOne("EducationDAO.nextEduSeq");
	}
	
	@Override
	public String nowDate() {
		return getSqlSession().selectOne("EducationDAO.nowDate");
	}

	@Override
	public int insertNewEducation(EducationVO educationVO) {
		return getSqlSession().insert("EducationDAO.insertNewEducation", educationVO);
	}
	
	@Override
	public EducationVO getOneEducation(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getOneEducation", educationId);
	}

	@Override
	public int modifyNewEducation(EducationVO changedEducationVO) {
		return getSqlSession().update("EducationDAO.modifyNewEducation", changedEducationVO);
	}

	@Override
	public List<CostVO> costCodeList() {
		return getSqlSession().selectList("EducationDAO.costCodeList");
	}

	@Override
	public List<EducationTypeVO> typeCodeList() {
		return getSqlSession().selectList("EducationDAO.typeCodeList");
	}

	@Override
	public List<CategoryVO> categoryCodeList() {
		return getSqlSession().selectList("EducationDAO.categoryCodeList");
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationHistory",eduHistorySearchVO);
	}

	@Override
	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectOne("EducationDAO.getAllEduHistoryCount",eduHistorySearchVO);
	}

	@Override
	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		logger.info(eduHistorySearchVO.getSearchKeyword());
		return getSqlSession().selectOne("EducationDAO.getJCEduHistoryCount", eduHistorySearchVO);
	}

	@Override
	public List<EducationHistoryVO> getJCEduHistoryHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectList("EducationDAO.getJCEduHistoryHistory", eduHistorySearchVO);
	}

	@Override
	public int applyJoinEducationByMemberId(String educationHistoryId, String changeState) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationHistoryId", educationHistoryId);
		paramMap.put("changeState", changeState);
		return getSqlSession().update("EducationDAO.applyJoinEdcationByMemberId", paramMap);
	}

	@Override
	public int cancelJoinEducationByMemberId(String educationHistoryId, String changeState) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationHistoryId", educationHistoryId);
		paramMap.put("changeState", changeState);
		return getSqlSession().update("EducationDAO.cancelJoinEducationByMemberId", paramMap);
	}

	@Override
	public String getStateByEducationHistroyId(String educationHistoryId) {
		return getSqlSession().selectOne("EducationDAO.getStateByEducationHistroyId", educationHistoryId);
	}

	
}
