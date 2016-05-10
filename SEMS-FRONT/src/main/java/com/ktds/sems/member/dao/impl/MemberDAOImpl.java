package com.ktds.sems.member.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	@Override
	public int addNewMember(MemberVO member) {
		return getSqlSession().insert("MemberDAO.addNewMember", member);
	}
	
	@Override
	public int loginHistory(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().insert("MemberDAO.loginHistory", loginHistoryVO);
	}

	@Override
	public int logoutHistory(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().insert("MemberDAO.logoutHistory", loginHistoryVO);
	}

	@Override
	public String getSaltById(String id) {
		return getSqlSession().selectOne("MemberDAO.getSaltById", id);
	}

	@Override
	public MemberVO login(MemberVO loginVO) {
		return getSqlSession().selectOne("MemberDAO.login", loginVO);
	}

	@Override
	public boolean isAccountLock(String id) {
		return getSqlSession().selectOne("MemberDAO.isAccountLock", id).equals("Y");
	}

	@Override
	public int loginSuccess(String id) {
		return getSqlSession().update("MemberDAO.loginSuccess", id);
	}

	@Override
	public int plusLoginFailCount(String id) {
		return getSqlSession().update("MemberDAO.plusLoginFailCount", id);
	}

	@Override
	public int updateAccountLock(String id) {
		return getSqlSession().update("MemberDAO.updateAccountLock", id);
	}

	@Override
	public MemberVO getOneMember(String id) {
		return getSqlSession().selectOne("MemberDAO.getOneMember", id);
	}
	
}
