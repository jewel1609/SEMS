package com.ktds.sems.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GrdtTpVO;
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
	public void doGrdtDelete(String cdId) {
		getSqlSession().delete("MemberDAO.doGrdtDelete", cdId);
	}

	@Override
	public void doGrdtModify(GrdtTpVO grdtTpVO) {
		getSqlSession().update("MemberDAO.doGrdtModify",grdtTpVO);
		
	}

	@Override
	public void doGrdtInsert(GrdtTpVO grdtTpVO) {
		getSqlSession().insert("MemberDAO.doGrdtInsert",grdtTpVO);
		
	}
}
