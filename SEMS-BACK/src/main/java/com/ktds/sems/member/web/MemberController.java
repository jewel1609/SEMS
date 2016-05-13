package com.ktds.sems.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	
	@RequestMapping("/memberManage")
	public ModelAndView viewMemberManagePage() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/memberManagePage");
		
		return view;
	}
	
}
