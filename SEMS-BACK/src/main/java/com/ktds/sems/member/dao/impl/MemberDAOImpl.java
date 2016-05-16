package com.ktds.sems.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.service.impl.MemberServiceImpl;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	private Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Override
	public String isExistId(String id) {
		return getSqlSession().selectOne("MemberDAO.isExistId", id);
	}

	@Override
	public boolean isAccountLock(String id) {
		return ("Y").equals(getSqlSession().selectOne("MemberDAO.isAccountLock", id));
	}

	@Override
	public int loginSuccess(String id) {
		return getSqlSession().update("MemberDAO.loginSuccess", id);
	}

	@Override
	public String needToChangPassword(String id) {
		return getSqlSession().selectOne("MemberDAO.needToChangPassword", id);
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
	public String getSaltById(String id) {
		return getSqlSession().selectOne("MemberDAO.getSaltById", id);
	}

	@Override
	public MemberVO login(MemberVO loginVO) {
		return getSqlSession().selectOne("MemberDAO.login", loginVO);
	}

	@Override
	public String isResign(String id) {
		return getSqlSession().selectOne("MemberDAO.isResign", id);
	}

	@Override
	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().update("MemberDAO.stampLogoutTime", newLoginHistoryVO);
	}

	@Override
	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().insert("MemberDAO.stampLoginTime", newLoginHistoryVO);
	}

	@Override
	public int nextLoginHistorySeq() {
		return getSqlSession().selectOne("MemberDAO.nextLoginHistorySeq");
	}

	@Override
	public int getTotalMemberCount() {
		return getSqlSession().selectOne("MemberDAO.getTotalMemberCount");
	}

	@Override
	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO) {
		logger.info("DAO : " + searchVO.getEndIndex());
		return getSqlSession().selectList("MemberDAO.getAllMemberList", searchVO);
	}

	@Override
	public String isExistEmail(String email) {
		return getSqlSession().selectOne("MemberDAO.isExistEmail", email);
	}

	
}
