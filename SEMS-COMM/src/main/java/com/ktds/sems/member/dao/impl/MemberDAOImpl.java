package com.ktds.sems.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO{

	
	
	@Override
	public GrdtTpVO getAllGrtdList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MbrTpVO> getAllMbrTpList() {
		return getSqlSession().selectList("MemberDAO.getAllMbrTp");
	}

}
