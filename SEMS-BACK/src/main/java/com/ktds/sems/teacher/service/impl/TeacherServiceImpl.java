package com.ktds.sems.teacher.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherBiz teacherBiz;
	
	public void setTeacherBiz(TeacherBiz teacherBiz) {
		this.teacherBiz = teacherBiz;
	}

}
