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


	@RequestMapping("/memberManage")
	public ModelAndView viewMemberManagePage() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/memberManagePage");
		
		return view;
	}
	
	@RequestMapping("/memberManage/addMember")
	public ModelAndView viewAddMemberPage() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/memberManage/addMember");
		
		return view;
	}
	
}
