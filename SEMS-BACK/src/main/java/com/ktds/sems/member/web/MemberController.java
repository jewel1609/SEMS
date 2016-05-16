package com.ktds.sems.member.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class MemberController {

	private MemberService memberService;
	private Logger logger = LoggerFactory.getLogger(MemberController.class);	
	

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
		
		List<String> highestEducationLevelCodeNameList = memberService.getHighestEducationLevelCodeNames();
		List<String> graduationTypeList = memberService.getGraduationType();
		List<String> memberTypeCodeNameList = memberService.getMemberTypeCodeNameList();
		
		view.setViewName("/member/addMemberPage");
		view.addObject("highestEducationLevelCodeNameList", highestEducationLevelCodeNameList);
		view.addObject("graduationTypeList", graduationTypeList);
		view.addObject("memberTypeCodeNameList", memberTypeCodeNameList);
		

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

	@RequestMapping("/memberManage/memberList")
	public ModelAndView viewMemberListPage(@RequestParam(required=false, defaultValue="0") int pageNo){
		logger.info("MemberList 실행 페이지 : " + pageNo);
		
		return memberService.getAllMemberList(pageNo);
	}
	
	@RequestMapping("/history")
	public ModelAndView viewHistoryPage(@RequestParam(required = false, defaultValue = "0") int pageNo){
		return memberService.getAllMemberHistory(pageNo);
	}

	@RequestMapping("/memberDetail/{id}")
	public ModelAndView viewMemberDetailPage(@PathVariable String id) {
		return memberService.getMemberDetailById(id);
	}	
	

	@RequestMapping(value = "/doRegisterAction", method = RequestMethod.POST)
	public ModelAndView registerNewMember(@Valid MemberVO member, Errors errors, HttpSession session) {
		return memberService.addNewMember(member, errors, session);
	}
	
	@RequestMapping("/doMassiveDeleteMember")
	public String massiveDeleteMember(HttpServletRequest request){
		String[] deleteMemberIds = request.getParameterValues("deleteMemberId");
		for (String string : deleteMemberIds) {
			logger.info("Controller로 넘어온 ID : " + string);
		}
		return memberService.massiveDeleteMember(deleteMemberIds);
	}
	
	@RequestMapping("/memberDelete/{id}")
	public ModelAndView memberDeleteById(@PathVariable String id) {
		return memberService.memberDeleteById(id);
	}
	
}
