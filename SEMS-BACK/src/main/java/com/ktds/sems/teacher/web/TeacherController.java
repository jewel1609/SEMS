package com.ktds.sems.teacher.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.service.TeacherService;

@Controller
public class TeacherController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherService teacherService;
	
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
}
