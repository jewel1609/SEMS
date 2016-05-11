package com.ktds.sems.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO{

	
	@Override
	public List<GrdtTpVO> getAllGrtdList() {
		return  getSqlSession().selectList("MemberDAO.getAllGrtdList");
	}

	@Override
	public List<MbrTpVO> getAllMbrTpList() {
		return getSqlSession().selectList("MemberDAO.getAllMbrTp");
	}

	@Override
	public void doInsertMbrTp(MbrTpVO newMbrTpVO) {
		getSqlSession().insert("MemberDAO.doInsertMbrTp", newMbrTpVO);
	}

	@Override
	public void doGrdtDelete(String cdId) {
		getSqlSession().delete("MemberDAO.doGrdtDelete", cdId);
	}

	@Override
	public int doGrdtModify(GrdtTpVO grdtTpVO) {
		return getSqlSession().update("MemberDAO.doGrdtModify",grdtTpVO);
		
	}

	@Override
	public int doGrdtInsert(GrdtTpVO grdtTpVO) {
		return getSqlSession().insert("MemberDAO.doGrdtInsert",grdtTpVO);
	}
	public void doMbrTpDelete(String cdId) {
		getSqlSession().delete("MemberDAO.doMbrTpDelete", cdId);
		
	}

	@Override
	public void doMbrTpModify(MbrTpVO mbrTpVO) {
		getSqlSession().update("MemberDAO.doMbrTpModify", mbrTpVO);
		
	}

	@Override
	public int isExistData(GrdtTpVO grdtTpVO) {
		return getSqlSession().selectOne("MemberDAO.isExistData", grdtTpVO);
	}

	/* Highest Edu */
	@Override
	public List<HighestEduTpVO> getAllHighestEduList() {
		return getSqlSession().selectList("MemberDAO.getAllHighestEduList");
	}

	@Override
	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO) {
		return getSqlSession().selectOne("MemberDAO.isExistHighestEduData", highestEduTpVO);
	}

	@Override
	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO) {
		return getSqlSession().insert("MemberDAO.doHighestEduInsert", highestEduTpVO);
	}

	@Override
	public int doHighestEduModify(HighestEduTpVO highestEduTpVO) {
		return getSqlSession().update("MemberDAO.doHighestEduModify", highestEduTpVO);
	}
	
	@Override
	public int doHighestEduDelete(String cdId) {
		return getSqlSession().delete("MemberDAO.doHighestEduDelete", cdId);
	}

	@Override
	public int isExistCdNmData(GrdtTpVO grdtTpVO) {
		return getSqlSession().selectOne("MemberDAO.isExistCdNmData", grdtTpVO);
	}
}
