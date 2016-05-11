package com.ktds.sems.member.web;

import javax.servlet.http.HttpServletRequest;
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

	@RequestMapping("/main")
	public String viewMainPage() {
		return "/common/main";
	}

	@RequestMapping("/changePassword")
	public String viewChangePasswordPage() {
		return "/member/changePassword";
	}

	@RequestMapping("/member/register")
	public String viewRegisterPage() {
		return "member/register";
	}

	@RequestMapping(value = ("/login"), method = RequestMethod.POST)
	public void login(@Valid MemberVO memberVO, Errors errors, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		String loginStatus = memberService.login(memberVO, errors, session, request);
		AjaxUtil.sendResponse(response, loginStatus);
	}

	@RequestMapping("/member/myPage")
	public ModelAndView viewMyPage() {
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


	@RequestMapping("/member/myPage/doCheckPassword")
	public ModelAndView doCheckPassword(@RequestParam String password, HttpSession session, HttpServletResponse response){
		
		ModelAndView view = new ModelAndView();

		//TODO 입력한 패스워드와
				//테이블의 member.getPassword() 혹은 세션패스워드 를 해독했을때의 암호가
				//일치한다면 SendMessage.send(response, "OK");
		
		//TODO 세션정보를 넘겨받기
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String sessionId = "aaa";
		String sessionPassword = "1234";

		if (password.equals(sessionPassword)) {

			memberService.resetModifyLockAndCount(sessionId);
		//	SendMessage.send(response, "OK");
			memberService.modifySuccess(sessionId);
			return null;
			
		} else {
			
			memberService.plusModifyFailCount(sessionId);
			
			memberService.updateModifyAccountLock(sessionId);

			boolean isLock = memberService.isModifyAccountLock(sessionId);
			
		//	SendMessage.send(response, isLock ? "OVER" : "NO");
			view.setViewName("/member/checkPassword");
		}

		return view;
	}

	@RequestMapping("/member/myPage/modify")
	public ModelAndView viewModifyPage(HttpSession session){
		
		//TODO session Id로 바꾸기
		String sessionId = "aaa";
		return memberService.modifySuccess(sessionId);
		
	}
	
	@RequestMapping("/member/myPage/doModifyAction")
	public ModelAndView doModifyAction(@Valid MemberVO member, Errors errors) {
		return memberService.modifyMemberInfo(member, errors);
	}
	
	/**
	 * @author 이기연
	 * @param session
	 */
	@RequestMapping("/member/myPage/saveAsExcel")
	public void saveLoginHistoryAsExcel(HttpSession session) {
		memberService.saveLoginHistoryAsExcel(session);
	}

	@RequestMapping("/member/loginHistory")
	public ModelAndView viewLoginHistoryPage(@RequestParam(required=false, defaultValue="0") int pageNo) {
		return memberService.viewLoginHistoryPage(pageNo);
	}
	
}
