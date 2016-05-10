package com.ktds.sems.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping("/mbrTpPage")
	public ModelAndView viewMbrTpPage () {
		return memberService.viewMbrTpPage();
	}
	@RequestMapping("doGrdtDelete/{cdId}")
	public String doGrdtDelete(@PathVariable String cdId){
		return memberService.doGrdtDelete(cdId);
	}
	@RequestMapping("doGrdtModify")
	public String doGrdtModify(HttpServletRequest request, HttpServletResponse response){
		
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		return memberService.doGrdtModify(cdId, cdNm);
	}
}
