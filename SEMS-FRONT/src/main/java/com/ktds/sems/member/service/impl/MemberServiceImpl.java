package com.ktds.sems.member.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberBiz memberBiz;

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	@Override
	public ModelAndView addNewMember(MemberVO member, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		
		if ( sessionMember != null ) {
			throw new RuntimeException("유효한 접근이 아닙니다.");
		}
		else if ( errors.hasErrors() ) {
			view.setViewName("redirect:member/register");
			view.addObject("member", member);
		}
		else {
			// TODO 비밀번호 암호화
			// 1. salt 생성
			memberBiz.addNewMember(member);
			// TODO 회원 가입 후 보여질 페이지
			view.setViewName("");
		}
		
		return view;
	}
}
