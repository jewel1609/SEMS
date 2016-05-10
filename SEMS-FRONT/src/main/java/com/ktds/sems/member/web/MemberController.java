package com.ktds.sems.member.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/member/doRegisterAction", method = RequestMethod.POST)
	public ModelAndView registerNewMember(@Valid MemberVO member, Errors errors, HttpSession session) {
		return memberService.addNewMember(member, errors, session);
	}

	@RequestMapping("/member/changePassword")
	public String viewChangePasswordPage() {
		return "member/changePassword";
	}
	
	@RequestMapping("/member/register")
	public String viewRegisterPage() {
		return "member/register";
	}

	@RequestMapping(value = ("/member/login"), method = RequestMethod.GET)
	public void login(@Valid MemberVO memberVO, Errors errors, HttpSession session, HttpServletResponse response) {
		String loginStatus = memberService.login(memberVO, errors, session);
		AjaxUtil.sendResponse(response, loginStatus);
	}
	@RequestMapping("/member/myPage")
	public ModelAndView viewMyPage () { 
		ModelAndView view = new ModelAndView();
		view.setViewName("member/myPage");
		return view;
	}
	
	@RequestMapping("/member/myPage/checkPassword")
	public ModelAndView viewCheckPasswordPage() { 
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/checkPassword");
		return view;
	}
	
	@RequestMapping("/member/myPage/modify")
	public ModelAndView viewModifyPage(@RequestParam String password, HttpSession session){
		ModelAndView view = new ModelAndView();
		
		//임의로 정한 값 
		//TODO 세션정보를 넘겨받기
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String sessionId = "aaa";
		String sessionPassword = "1234";
		
		if( password.equals(sessionPassword) ) {
			/*
			 * 1. MODIFY_FAIL_COUNT를 0 으로 초기화한다.
			 * 2. IS_MODIFY_ACCOUNT_LOCK을 'N'으로 초기화한다.
			 */
			return memberService.modifySuccess(sessionId);
		}
		else {
		
			/*
			 * 1. MODIFY_FAIL_COUNT 를 1 증가시킨다.
			 *  
			 */
			
			/*
			 * 1. MODIFY_FAIL_COUNT 가 3 이상이라면 IS_MODIFY_ACCOUNT_LOCK 'Y'로 수정한다.
			 */

			
			/*
			 * 
			 * 1. IS_ACCOUNT_LOCK이 'Y'라면 사용자의 이메일로 비밀번호가 3회 이상 틀려 접속이 차단되었음을 알린다.
			 *  
			 * 2. IS_ACCOUNT_LOCK이 'Y'라면 브라우저에게 'OVER' 라고 보낸다.
			 * 'OVER'를 응답으로 받은 브라우저는 '다시 접속을 원할 경우 운영자 혹은 관리자에게 문의하세요' 를 출력한다.
			 * 
			 */
		}
		
		return view;
	}
	
	@RequestMapping("/member/myPage/doModifyAction")
	public ModelAndView doModifyAction(@Valid MemberVO member, Errors errors){
		return memberService.modifyMemberInfo(member, errors);
	}
	
	
}
