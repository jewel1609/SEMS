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
	
	/**
	 * nowDate
	 * @author 김동규
	 * @return getSqlSession
	 */
	@Override
	public String nowTime() {
		return getSqlSession().selectOne("MemberDAO.nowTime");
	}
	/**
	 * increaseHistorySeq
	 * @author 김동규
	 * @return getSqlSession
	 */
	@Override
	public int increaseHistorySeq() {
		return getSqlSession().selectOne("MemberDAO.increaseHistorySeq");
	}
	/**
	 * loginHistory
	 * @author 김동규
	 * @param loginHistoryVO
	 * @return getSqlSession
	 */
	@Override
	public int loginHistory(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().insert("MemberDAO.loginHistory", loginHistoryVO);
	}
	/**
	 * logoutHistory
	 * @author 김동규
	 * @param loginHistoryVO
	 * @return getSqlSession
	 */
	@Override
	public int logoutHistory(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().insert("MemberDAO.logoutHistory", loginHistoryVO);
	}
	
}
