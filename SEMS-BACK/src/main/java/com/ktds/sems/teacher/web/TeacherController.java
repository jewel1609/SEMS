package com.ktds.sems.teacher.web;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.service.TeacherService;
import com.ktds.sems.teacher.vo.TeacherVO;

@Controller
public class TeacherController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherService teacherService;
	
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@RequestMapping("/teacher/detail/{memberId}")
	public ModelAndView viewDetailPage(@PathVariable String memberId){
		return teacherService.viewDetail(memberId);
	}
	
	@RequestMapping("/teacherModify/{memberId}")
	public ModelAndView viewTeacherModifyPage(@PathVariable String memberId, HttpSession session){
		ModelAndView view = teacherService.getOneTeacherInfoForUpdate(memberId, session); 
		return view;
	}

	@RequestMapping("/doTeacherInfoModifyAction")
	public ModelAndView doTeacherInfoModifyAction(@Valid TeacherVO teacherVO, Errors errors, HttpSession session){
		return teacherService.doTeacherInfoModifyAction(teacherVO, errors, session);
	}
	
	@RequestMapping("/deleteTeacherBookEduProHistory/{id}/{type}")
	public ModelAndView deleteTeacherBookEduProHistory(@PathVariable String id, @PathVariable String type, HttpSession session){
		return teacherService.deleteTeacherBookEduProHistory(id, type, session);
	}
}
