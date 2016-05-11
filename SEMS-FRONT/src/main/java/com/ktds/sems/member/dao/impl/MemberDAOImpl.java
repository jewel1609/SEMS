package com.ktds.sems.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
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
		return ("Y").equals(getSqlSession().selectOne("MemberDAO.isAccountLock", id));
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

	@Override
	public void resetModifyLockAndCount(String id) {
		getSqlSession().update("MemberDAO.resetModifyLockAndCount", id);
	}

	@Override
	public void plusModifyFailCount(String id) {
		getSqlSession().update("MemberDAO.plusModifyFailCount", id);
	}

	@Override
	public void updateModifyAccountLock(String id) {
		getSqlSession().update("MemberDAO.updateModifyAccountLock", id);
	}

	@Override
	public int isModifyAccountLock(String id) {
		return getSqlSession().selectOne("MemberDAO.isModifyAccountLock", id);
	}

	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("MemberDAO.getNowDate");
	}

	@Override
	public String needToChangPassword(String id) {
		return getSqlSession().selectOne("MemberDAO.needToChangPassword", id);
	}

	/**
	 * @author 이기연
	 */
	@Override
	public List<LoginHistoryVO> saveLoginHistoryAsExcel(String memberId) {
		return getSqlSession().selectList("MemberDAO.saveLoginHistoryAsExcel", memberId);
	}

	@Override
	public int getTotalLoginHisotryCount() {
		return getSqlSession().selectOne("MemberDAO.getTotalLoginHisotryCount");
	}

	@Override
	public List<EducationVO> getEduListByMember(MemberVO loginVO) {
		return getSqlSession().selectList("MemberDAO.getEduListByMember", loginVO);
	}

	@Override
	public void insertAttendByMember(AttendVO attendVO) {
		getSqlSession().insert("MemberDAO.insertAttendByMember", attendVO);
	}

	@Override
	public String getLastDate(EducationVO educationVO) {
		return getSqlSession().selectOne("MemberDAO.getLastDate", educationVO ) == null ? "" : this.getSqlSession().selectOne("MemberDAO.getLastDate", educationVO );
	}

	/**
	 * @author 이기연
	 */
	@Override
	public void modifyMemberInfo(MemberVO member) {
		getSqlSession().update("MemberDAO.modifyMemberInfo", member);
	}


	@Override
	public String isExistId(String id) {
		return getSqlSession().selectOne("MemberDAO.isExistId", id);
	}
	
	@Override
	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().insert("MemberDAO.stampLoginTime", newLoginHistoryVO);
	}

	/**
	 * @author 이기연
	 */
	@Override
	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().update("MemberDAO.stampLogoutTime", newLoginHistoryVO);
	}

	@Override
	public int nextLoginHistorySeq() {
		return getSqlSession().selectOne("MemberDAO.nextLoginHistorySeq");
	}

	@Override
	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectList("MemberDAO.getAllLoginHistory", loginHistorySearchVO);
	}

	@Override
	public String getPasswordById(String id) {
		return getSqlSession().selectOne("MemberDAO.getPasswordById", id);
	}

	@Override
	public List<String> getGraduationType() {
		return getSqlSession().selectList("MemberDAO.getGraduationType");
	}

}
