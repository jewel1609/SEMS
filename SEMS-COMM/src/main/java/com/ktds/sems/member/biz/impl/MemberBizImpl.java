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
	public List<GrdtTpVO> getAllGrtdList() {
		return memberDAO.getAllGrtdList();
	}



	@Override
	public List<MbrTpVO> getAllMbrTpList() {
		List<MbrTpVO> mbrtpList = memberDAO.getAllMbrTpList();
		return mbrtpList;
	}

	@Override
	public void doInsertMbrTp(MbrTpVO newMbrTpVO) {
		memberDAO.doInsertMbrTp(newMbrTpVO);
	}

	@Override
	public void doGrdtDelete(String cdId) {
		memberDAO.doGrdtDelete(cdId);
		
	}

	@Override
	public void doGrdtModify(GrdtTpVO grdtTpVO) {
		memberDAO.doGrdtModify(grdtTpVO);
	}

	@Override
	public void doMbrTpDelete(String cdId) {
		memberDAO.doMbrTpDelete(cdId);
	}

	@Override
	public void doMbrTpModify(MbrTpVO mbrTpVO) {
		memberDAO.doMbrTpModify(mbrTpVO);
		
	}
	@Override
	public void doGrdtInsert(GrdtTpVO grdtTpVO) {
		memberDAO.doGrdtInsert(grdtTpVO);
		
	}
}
