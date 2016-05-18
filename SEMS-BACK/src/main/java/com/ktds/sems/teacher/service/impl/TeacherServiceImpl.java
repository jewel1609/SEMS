package com.ktds.sems.teacher.service.impl;


import java.util.List;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.service.TeacherService;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;
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

	@Override
	public ModelAndView getOneTeacherInfoForUpdate(String memberId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		if ( memberType.equals("ADM") ) {
			TeacherVO teacherVO = teacherBiz.getOneTeacherInfo(memberId);

			List<TeacherBookVO> teacherBookVO = teacherBiz.getOneTeacherBookInfo(memberId);
			List<ProjectHistoryVO> projectHistoryVO = teacherBiz.getOneTeacherProjectHistoryVO(memberId);
			List<EducationHistoryVO> educationHistoryVO = teacherBiz.getOneEducationHistoryVO(memberId);
			
			view.addObject("teacherVO", teacherVO);
			view.addObject("teacherBookVO", teacherBookVO);
			view.addObject("projectHistoryVO", projectHistoryVO);
			view.addObject("educationHistoryVO", educationHistoryVO);
			
			view.setViewName("teacher/teacherUpdate");
		}else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		
		return view;
	}

	@Override
	public ModelAndView doTeacherInfoModifyAction(TeacherVO teacherVO, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();

		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		if ( memberType.equals("ADM") ) {
			if ( errors.hasErrors() ) {
				view.setViewName("teacher/teacherUpdate"+"/"+teacherVO.getMemberId());
				view.addObject("teacherVO", teacherVO);
				view.addObject("teacherBookVO", teacherBiz.getOneTeacherBookInfo(teacherVO.getMemberId()));
				view.addObject("projectHistoryVO", teacherBiz.getOneTeacherProjectHistoryVO(teacherVO.getMemberId()));
				view.addObject("educationHistoryVO", teacherBiz.getOneEducationHistoryVO(teacherVO.getMemberId()));
				return view;
			}
			else{
				boolean result = teacherBiz.doTeacherInfoModifyAction(teacherVO);
				if ( result) {
					view.setViewName("redirect:/teacherModify/" + teacherVO.getMemberId());
				}
				else {
					throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
			}
		}
		else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}

	@Override
	public ModelAndView deleteTeacherBookEduProHistory(String id, String type, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		
		if ( memberType.equals("ADM") ) {
			boolean result = teacherBiz.deleteTeacherBookEduProHistory(id, type);
		}else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}


}
