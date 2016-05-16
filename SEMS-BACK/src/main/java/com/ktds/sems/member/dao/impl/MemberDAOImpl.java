package com.ktds.sems.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
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
	public List<LoginHistoryVO> getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectList("MemberDAO.getAllMemberHistory", loginHistorySearchVO);
	}

	@Override
	public int getTotalMemberCount() {
		return getSqlSession().selectOne("MemberDAO.getTotalMemberCount");
	}

	@Override
	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO) {
		logger.info("DAO START : " + searchVO.getStartIndex());
		logger.info("DAO END : " + searchVO.getEndIndex());
		return getSqlSession().selectList("MemberDAO.getAllMemberList", searchVO);
	}

	@Override
	public String isExistEmail(String email) {
		return getSqlSession().selectOne("MemberDAO.isExistEmail", email);
	}

	@Override
	public int getTotalMemberHistoryCount() {
		return getSqlSession().selectOne("MemberDAO.getTotalMemberHistoryCount");
	}

	@Override
	public void massiveDeleteMember(String memberId) {
		getSqlSession().delete("MemberDAO.massiveDeleteMember",memberId);
	}

	@Override
	public MemberVO getMemberDetailById(String id) {
		return getSqlSession().selectOne("MemberDAO.getMemberDetailById", id);
	}

	@Override
	public List<String> getHighestEducationLevelCodeNames() {
		return getSqlSession().selectList("MemberDAO.getHighestEducationLevelCodeNames");
	}

	@Override
	public List<String> getGraduationType() {
		return getSqlSession().selectList("MemberDAO.getGraduationType");
	}

	@Override
	public void addNewMember(MemberVO member) {
		getSqlSession().insert("MemberDAO.addNewMember", member);
	}

	@Override
	public String getHelCodeId(String highestEducationLevel) {
		return getSqlSession().selectOne("MemberDAO.getHelCodeId", highestEducationLevel);
	}

	@Override
	public String getGraduationTypeCodeId(String graduationType) {
		return getSqlSession().selectOne("MemberDAO.getGraduationTypeCodeId", graduationType);
	}

	@Override
	public List<String> getMemberTypeCodeNameList() {
		return getSqlSession().selectList("MemberDAO.getMemberTypeCodeNameList");
	}

	@Override
	public String getMemberTypeCodeId(String memberType) {
		return getSqlSession().selectOne("MemberDAO.getMemberTypeCodeId", memberType);
	}

	
}
