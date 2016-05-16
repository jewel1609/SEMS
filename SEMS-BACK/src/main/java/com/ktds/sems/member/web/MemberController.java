package com.ktds.sems.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/main")
	public ModelAndView viewMainPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("common/main");
		return view;
	}
	
	@RequestMapping("/login")
	public void login(@Valid MemberVO loginVO, Errors errors, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String loginStatus = memberService.login(loginVO, errors, session, request);
		AjaxUtil.sendResponse(response, loginStatus);
	}

	@RequestMapping("/memberManage")
	public ModelAndView viewMemberManagePage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("member/memberManagePage");

		return view;
	}

	@RequestMapping("/memberManage/addMember")
	public ModelAndView viewAddMemberPage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("/member/addMemberPage");

		return view;
	}

	@RequestMapping("/checkValidationById")
	public void checkValidationById(@RequestParam String id, HttpServletResponse response) {
		memberService.checkValidationById(id, response);
	}

	@RequestMapping("/checkValidationByPassword")
	public void checkValidationByPassword(@RequestParam String password, HttpServletResponse response) {
		memberService.checkValidationByPassword(password, response);
	}

	@RequestMapping("/checkValidationByRepeatPassword")
	public void checkValidationByRepeatPassword(@RequestParam String password, @RequestParam String repeatPassword,
			HttpServletResponse response) {
		memberService.checkValidationByRepeatPassword(password, repeatPassword, response);
	}

	@RequestMapping("/checkValidationByEmail")
	public void checkValidationByEmail(@RequestParam String email, HttpServletResponse response) {
		memberService.checkValidationByEmail(email, response);
	}

	@RequestMapping("/checkValidationByPhoneNumber")
	public void checkValidationByPhoneNumber(@RequestParam String phoneNumber, HttpServletResponse response) {
		memberService.checkValidationByPhoneNumber(phoneNumber, response);
	}

}
