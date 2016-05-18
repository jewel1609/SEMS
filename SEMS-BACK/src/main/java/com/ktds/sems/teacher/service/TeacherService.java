package com.ktds.sems.teacher.service;



import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;


import com.ktds.sems.teacher.vo.TeacherVO;

public interface TeacherService {
	
	ModelAndView viewDetail(String memberId);

	public ModelAndView getOneTeacherInfoForUpdate(String memberId, HttpSession session);

	public ModelAndView doTeacherInfoModifyAction(TeacherVO teacherVO, Errors errors, HttpSession session);

	public ModelAndView deleteTeacherBookEduProHistory(String id, String type, HttpSession session);

	


}
