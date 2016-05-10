package com.ktds.sems.member.biz.impl;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

public class MemberBizImpl implements MemberBiz{

	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean addNewMember(MemberVO member) {
		return memberDAO.addNewMember(member) > 0;
	}

	@Override
	public boolean loginHistory(LoginHistoryVO loginHistoryVO) {
		//id
		//ip
		
		return memberDAO.loginHistory(loginHistoryVO) > 0;		
	}
	
	@Override
	public boolean logoutHistory(LoginHistoryVO loginHistoryVO) {
		
		return memberDAO.logoutHistory(loginHistoryVO) > 0;
	}
	
	@Override
	public MemberVO getOneMember(String id) {
		return memberDAO.getOneMember(id);
	}
	
}

