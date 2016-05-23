package com.ktds.sems.pc.biz.impl;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcBizImpl implements PcBiz {
	private PcDAO pcDAO;

	public void setPcDAO(PcDAO pcDAO) {
		this.pcDAO = pcDAO;
	}

	@Override
	public List<EducationVO> getEduListByMember(MemberVO memberVO) {
		return pcDAO.getEduListByMember(memberVO);
	}

	@Override
	public List<UsedPcVO> getUsedPcListByMember(MemberVO memberVO) {
		return pcDAO.getUsedPcListByMember(memberVO);
	}

}
