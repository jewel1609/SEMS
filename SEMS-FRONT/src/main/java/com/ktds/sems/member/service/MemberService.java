package com.ktds.sems.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public ModelAndView addNewMember(MemberVO members , Errors errors, HttpSession session);

	public String login(MemberVO memberVO, Errors errors, HttpSession session);

	public ModelAndView modifySuccess(String id);

	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors);
	
}
