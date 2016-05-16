package com.ktds.sems.teacher.biz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.dao.TeacherDAO;

public class TeacherBizImpl implements TeacherBiz {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherDAO teacherDAO;
	
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

}
