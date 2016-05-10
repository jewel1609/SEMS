package com.ktds.sems.member.biz.impl;

import java.util.List;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public class MemberBizImpl implements MemberBiz{

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}



	@Override
	public GrdtTpVO getAllGrtdList() {
		memberDAO.getAllGrtdList();
		return null;
	}



	@Override
	public List<MbrTpVO> getAllMbrTpList() {
		List<MbrTpVO> mbrtpList = memberDAO.getAllMbrTpList();
		return mbrtpList;
	}

}
