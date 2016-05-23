package com.ktds.sems.pc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.service.PcService;

import kr.co.hucloud.utilities.web.AjaxUtil;

public class PcServiceImpl implements PcService{

	private PcBiz pcBiz;

	public void setPcBiz(PcBiz pcBiz) {
		this.pcBiz = pcBiz;
	}

	@Override
	public ModelAndView viewMyPcPage(HttpSession session, HttpServletRequest request) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		List<EducationVO> eduListByMember = pcBiz.getEduListByMember(memberVO);
		
		String myPcIp= request.getRemoteHost();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/pc/myPc");
		view.addObject("eduListByMember", eduListByMember);
		view.addObject("myPcIp", myPcIp);
		return view;
	}

	@Override
	public void getEduLocationByTitle(String title, HttpServletResponse response, HttpSession session) {
		String location = "";
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		List<EducationVO> eduListByMember = pcBiz.getEduListByMember(memberVO);
		for (EducationVO educationVO : eduListByMember) {
			if(educationVO.getEducationTitle().equals(title)){
				location = educationVO.getEducationLocation();
			}
		}
		AjaxUtil.sendResponse(response, location);
		return;
	}
	
}
