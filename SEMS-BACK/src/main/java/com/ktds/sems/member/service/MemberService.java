package com.ktds.sems.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;

import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public String login(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request);

}
