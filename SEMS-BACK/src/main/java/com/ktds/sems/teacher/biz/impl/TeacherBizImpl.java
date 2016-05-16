package com.ktds.sems.teacher.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public class TeacherBizImpl implements TeacherBiz {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherDAO teacherDAO;
	
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	@Override
	public TeacherVO getTeacherInfo(String memberId) {
		return teacherDAO.getTeacherInfo(memberId);
	}

	@Override
	public List<EducationHistoryVO> getTeacherEducationHistory(String memberId) {
		return teacherDAO.getTeacherEducationHistory(memberId);
	}

	@Override
	public List<ProjectHistoryVO> getTeacherProjectHistory(String memberId) {
		return teacherDAO.getTeacherProjectHistory(memberId);
	}

	@Override
	public List<TeacherBookVO> getTeacherBook(String memberId) {
		return teacherDAO.getTeacherBook(memberId);
	}

	@Override
	public double getTeacherEducationGrade(String memberId) {
		return teacherDAO.getTeacherEducationGrade(memberId);
	}

}
