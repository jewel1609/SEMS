package com.ktds.sems.member.service.impl;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;

public class MemberServiceImpl implements MemberService{

	private MemberBiz memberBiz;
	
	
	
	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}



	@Override
	public ModelAndView viewGrdtPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/graduation/list");
		view.addObject("grtdVO", memberBiz.getAllGrtdList());
		return view;
	}



	@Override
	public ModelAndView viewMbrTpPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/mbrTp");		
		view.addObject("mbrTpVOList", memberBiz.getAllMbrTpList());
		return view;
	}

}
