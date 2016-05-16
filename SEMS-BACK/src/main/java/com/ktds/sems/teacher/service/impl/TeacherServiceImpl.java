package com.ktds.sems.teacher.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.service.TeacherService;
import com.ktds.sems.teacher.vo.EducationHistoryVO;

public class TeacherServiceImpl implements TeacherService {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherBiz teacherBiz;
	
	public void setTeacherBiz(TeacherBiz teacherBiz) {
		this.teacherBiz = teacherBiz;
	}

	@Override
	public ModelAndView viewDetail(String memberId) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("teacher/detail");
		view.addObject("teacherInfo", teacherBiz.getTeacherInfo(memberId));
		view.addObject("teacherEducationHistory", teacherBiz.getTeacherEducationHistory(memberId));
		view.addObject("teacherProjectHistory", teacherBiz.getTeacherProjectHistory(memberId));
		view.addObject("teacherBook", teacherBiz.getTeacherBook(memberId));
		view.addObject("educationHistory", teacherBiz.getEducationHistory(memberId));
		view.addObject("teacherEducationGrade", teacherBiz.getTeacherEducationGrade(memberId));
		
		return view;
	}

}
