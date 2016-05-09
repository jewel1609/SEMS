package com.ktds.sems.member.biz.impl;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.MemberVO;

public class MemberBizImpl implements MemberBiz{

	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean registerNewMember(MemberVO member) {
		return memberDAO.registerNewMember(member) > 0;
	}
	
	
}
