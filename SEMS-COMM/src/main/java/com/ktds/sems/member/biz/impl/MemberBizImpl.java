package com.ktds.sems.member.biz.impl;

import java.util.List;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
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
	public int doGrdtModify(GrdtTpVO grdtTpVO) {
		return memberDAO.doGrdtModify(grdtTpVO);
		
	}

	@Override
	public int doGrdtInsert(GrdtTpVO grdtTpVO) {
		return memberDAO.doGrdtInsert(grdtTpVO);
	}

	@Override
	public int isExistData(GrdtTpVO grdtTpVO) {
		return memberDAO.isExistData(grdtTpVO);
	}
	public void doMbrTpDelete(String cdId) {
		memberDAO.doMbrTpDelete(cdId);
	}

	@Override
	public void doMbrTpModify(MbrTpVO mbrTpVO) {
		memberDAO.doMbrTpModify(mbrTpVO);
		
	}
	/* Highest Edu */
	@Override
	public List<HighestEduTpVO> getAllHighestEduList() {
		return memberDAO.getAllHighestEduList();
	}

	@Override
	public boolean doHighestEduDelete(String cdId) {
		return memberDAO.doHighestEduDelete(cdId) > 0;
	}

	@Override
	public boolean doHighestEduModify(HighestEduTpVO highestEduTpVO) {
		return memberDAO.doHighestEduModify(highestEduTpVO) > 0;
	}

	@Override
	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO) {
		return memberDAO.isExistHighestEduData(highestEduTpVO);
	}

	@Override
	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO) {
		return memberDAO.doHighestEduInsert(highestEduTpVO);
	}
	@Override
	public int isExistCdNmData(GrdtTpVO grdtTpVO) {
		return memberDAO.isExistCdNmData(grdtTpVO);
	}
}
