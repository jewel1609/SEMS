package com.ktds.sems.education.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
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
	public List<EducationHistoryVO> getAllEducationHistory() {
		return getSqlSession().selectList("EducationDAO.getAllEducationHistory");
	}

	@Override
	public int getAllEduHistoryCount() {
		return getSqlSession().selectOne("EducationDAO.getAllEduHistoryCount");
	}

	@Override
	public int getJCEduHistoryCount() {
		return getSqlSession().selectOne("EducationDAO.getJCEduHistoryCount");
	}

	@Override
	public List<EducationHistoryVO> getJCEduHistoryHistory() {
		return getSqlSession().selectList("EducationDAO.getJCEduHistoryHistory");
	}

	@Override
	public int applyJoinEducationByMemberId(String educationId, String memberId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.applyJoinEdcationByMemberId", paramMap);
	}

	@Override
	public int cancelJoinEducationByMemberId(String educationId, String memberId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.cancelJoinEducationByMemberId", paramMap);
	}
	
	@Override
	public int completeCancelEducationByMemberId(String educationId, String memberId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.completeCancelEducationByMemberId", paramMap);
	}
	
	@Override
	public int denyCancleEducationByMemberId(String educationId, String memberId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.denyCancleEducationByMemberId", paramMap);
	}
	
	@Override
	public int completeGiveUpEducationByMemberId(String educationId, String memberId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.completeGiveUpEducationByMemberId", paramMap);
	}
	
	@Override
	public int denyGiveUpEducationByMemberId(String educationId, String memberId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationId", educationId);
		paramMap.put("memberId", memberId);
		return getSqlSession().update("EducationDAO.denyGiveUpEducationByMemberId", paramMap);
	}

	
}
