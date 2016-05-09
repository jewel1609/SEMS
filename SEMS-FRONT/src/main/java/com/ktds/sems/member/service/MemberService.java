package com.ktds.sems.member.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public ModelAndView registerNewMember (MemberVO members , Errors errors);
	
}
