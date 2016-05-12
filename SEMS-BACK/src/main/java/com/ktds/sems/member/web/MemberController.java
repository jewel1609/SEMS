package com.ktds.sems.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = ("/login"), method = RequestMethod.POST)
	public void login(@Valid MemberVO memberVO, Errors errors, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		String loginStatus = memberService.login(memberVO, errors, session, request);
		AjaxUtil.sendResponse(response, loginStatus);
	}

	@RequestMapping("/main")
	public String viewMainPage() {
		return "/common/main";
	}
	
	@RequestMapping("/logout")
	public String logout (HttpSession session) {
		memberService.logout(session);
		return "redirect:/";
	}
}
