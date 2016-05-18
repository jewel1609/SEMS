package com.ktds.sems.teacher.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public class TeacherDAOImpl extends SqlSessionDaoSupport implements TeacherDAO{
	
	@Override
	public TeacherVO getTeacherInfo(String memberId) {
		return getSqlSession().selectOne("TeacherDAO.getTeacherInfo", memberId);
	}

	@Override
	public List<EducationHistoryVO> getTeacherEducationHistory(String memberId) {
		return getSqlSession().selectList("TeacherDAO.getTeacherEducationHistory", memberId);
	}

	@Override
	public List<ProjectHistoryVO> getTeacherProjectHistory(String memberId) {
		return getSqlSession().selectList("TeacherDAO.getTeacherProjectHistory", memberId);
	}

	@Override
	public List<TeacherBookVO> getTeacherBook(String memberId) {
		return getSqlSession().selectList("TeacherDAO.getTeacherBook", memberId);
	}
	
	@Override
	public List<EducationVO> getEducationHistory(String memberId) {
		return getSqlSession().selectList("TeacherDAO.getEducationHistory", memberId);
	}

	@Override
	public double getTeacherEducationGrade(String memberId) {
		return getSqlSession().selectOne("TeacherDAO.getTeacherEducationGrade", memberId);
	}
	
	@Override
	public TeacherVO getOneTeacherInfo(String memberId) {
		return getSqlSession().selectOne("TeacherDAO.getOneTeacherInfo", memberId);
	}
	
	@Override
	public List<TeacherBookVO> getOneTeacherBookInfo(String memberId) {
		return getSqlSession().selectList("TeacherDAO.getOneTeacherBookInfo", memberId);
	}

	@Override
	public List<ProjectHistoryVO> getOneTeacherProjectHistoryVO(String memberId) {
		return getSqlSession().selectList("TeacherDAO.getOneTeacherProjectHistoryVO", memberId);
	}

	@Override
	public List<EducationHistoryVO> getOneEducationHistoryVO(String memberId) {
		return getSqlSession().selectList("TeacherDAO.getOneEducationHistoryVO", memberId);
	}

	@Override
	public int doTeacherInfoModifyAction(TeacherVO teacherVO) {
		return getSqlSession().update("TeacherDAO.doTeacherInfoModifyAction", teacherVO);
	}

	@Override
	public int doTeacherBookModifyAction(TeacherBookVO teacherBookVO) {
		return getSqlSession().update("TeacherDAO.doTeacherBookModifyAction", teacherBookVO);
	}

	@Override
	public int doTeacherProjectModifyAction(ProjectHistoryVO projectHistoryVO) {
		return getSqlSession().update("TeacherDAO.doTeacherProjectModifyAction", projectHistoryVO);
	}

	@Override
	public int doTeacherEducationModifyAction(EducationHistoryVO teacherEduHistoryVO) {
		return getSqlSession().update("TeacherDAO.doTeacherEducationModifyAction", teacherEduHistoryVO);
	}

	@Override
	public int deleteTeacherBookEduProHistory(HashMap<String, Object> map) {
		return getSqlSession().delete("TeacherDAO.deleteTeacherBookEduProHistory", map);
	}

	@Override
	public int getTotalTeacherCount(Map<String,String> searchInfo) {
		return getSqlSession().selectOne("TeacherDAO.getTotalTeacherCount", searchInfo);
	}

	@Override
	public List<TeacherVO> getAllTeacher(TeacherSearchVO searchVO) {
		return getSqlSession().selectList("TeacherDAO.getAllTeacher", searchVO);
	}

	@Override
	public int getSearchedEducationCount(TeacherVO teacherVO) {
		return getSqlSession().selectOne("TeacherDAO.getSearchedEducationCount", teacherVO);
	}

	@Override
	public int doDeleteTeacher(String memberId) {
		return getSqlSession().delete("TeacherDAO.doDeleteTeacher", memberId);
	}

	@Override
	public int doDeleteProjectHistory(String memberId) {
		return getSqlSession().delete("TeacherDAO.doDeleteProjectHistory", memberId);
	}

	@Override
	public int doDeleteEducationHistory(String memberId) {
		return getSqlSession().delete("TeacherDAO.doDeleteEducationHistory", memberId);
	}

	@Override
	public int doDeleteTeacherBook(String memberId) {
		return getSqlSession().delete("TeacherDAO.doDeleteTeacherBook", memberId);
	}



}
