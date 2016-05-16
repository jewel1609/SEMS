package com.ktds.sems.teacher.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
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
	public double getTeacherEducationGrade(String memberId) {
		return getSqlSession().selectOne("TeacherDAO.getTeacherEducationGrade", memberId);
	}

}
