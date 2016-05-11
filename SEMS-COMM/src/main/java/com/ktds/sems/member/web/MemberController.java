package com.ktds.sems.member.web;

import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/*Graduate*/
	@RequestMapping("/grdtPage")
	public ModelAndView viewGrdtPage () {
		return memberService.viewGrdtPage();
	}
	@RequestMapping("doGrdtDelete/{cdId}")
	public String doGrdtDelete(@PathVariable String cdId){
		return memberService.doGrdtDelete(cdId);
	}
	@RequestMapping("doGrdtModify")
	public void doGrdtModify(HttpServletRequest request, HttpServletResponse response){
		
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		String status = memberService.doGrdtModify(cdId, cdNm);
		AjaxUtil.sendResponse(response, status);
	}
	
	@RequestMapping("doGrdtInsert")
	public void doGrdtInsert(HttpServletRequest request, HttpServletResponse response){
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		String status = memberService.doGrdtInsert(cdId, cdNm);
		AjaxUtil.sendResponse(response, status);
	}
	
	/*Member Type*/
	@RequestMapping("/mbrTpPage")
	public ModelAndView viewMbrTpPage () {
		return memberService.viewMbrTpPage();
	}
	
	@RequestMapping("/doInsertMbrTp")
	public String doInsertMbrTp (HttpServletRequest request, HttpServletResponse response) {
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		return memberService.doInsertMbrTp(cdId, cdNm);
	}
	@RequestMapping("doMbrTpDelete/{cdId}")
	public String doMbrTpDelete(@PathVariable String cdId){
		return memberService.doMbrTpDelete(cdId);
	}
	@RequestMapping("doMbrTpModify")
	public String doMbrTpModify(HttpServletRequest request, HttpServletResponse response){
		
		String cdId = request.getParameter("cdId");
		String cdNm = request.getParameter("cdNm");
		
		return memberService.doMbrTpModify(cdId, cdNm);
	}
}
