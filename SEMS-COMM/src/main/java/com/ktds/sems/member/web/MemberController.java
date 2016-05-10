package com.ktds.sems.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;

@Controller
public class MemberController {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/grdtPage")
	public ModelAndView viewGrdtPage () {
		return memberService.viewGrdtPage();
	}
	
	
}
