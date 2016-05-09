package com.ktds.sems.member.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;

@Controller
public class MemberController {
	
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public ModelAndView registerNewMember (@Valid MemberVO member, Errors errors) {
		return memberService.registerNewMember(member, errors);
	}
	
}
