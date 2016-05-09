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
	public ModelAndView registerNewMember(MemberVO member, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			throw new RuntimeException("��ȿ�� ������ �ƴմϴ�.");
		}
		else if ( errors.hasErrors() ) {
			view.setViewName("member/register");
			view.addObject("member", member);
		}
		else {
			// TODO ȸ�� ���� �� ������ ������
			view.setViewName("");
		}
		
		return view;
	}
}
