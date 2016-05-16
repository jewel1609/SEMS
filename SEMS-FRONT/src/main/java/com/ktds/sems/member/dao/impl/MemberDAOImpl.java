package com.ktds.sems.member.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	// 준호
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
	public int getTotalLoginHistoryCount(String memberId) {
		return getSqlSession().selectOne("MemberDAO.getTotalLoginHistoryCount", memberId);
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
	public String getLastDate(Map<String,String> eduIdAndMemberId) {
		return getSqlSession().selectOne("MemberDAO.getLastDate", eduIdAndMemberId ) == null ? "" : this.getSqlSession().selectOne("MemberDAO.getLastDate", eduIdAndMemberId);
	}

	/**
	 * @author 이기연
	 */
	@Override
	public void modifyMemberInfo(MemberVO member) {
		getSqlSession().update("MemberDAO.modifyMemberInfo", member);
	}


	// 준호
	@Override
	public String isExistId(String id) {
		return getSqlSession().selectOne("MemberDAO.isExistId", id);
	}
	
	@Override
	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().insert("MemberDAO.stampLoginTime", newLoginHistoryVO);
	}

	// 준호
	@Override
	public String isExistEmail(String email) {
		return getSqlSession().selectOne("MemberDAO.isExistEmail", email);
	}

	/**
	 * @author 이기연
	 */
	@Override
	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO) {
		return getSqlSession().update("MemberDAO.stampLogoutTime", newLoginHistoryVO);
	}
	
	/**
	 * @author 이기연
	 */
	@Override
	public int stampLogoutTimeByMemberId(String memberId) {
		return getSqlSession().update("MemberDAO.stampLogoutTimeByMemberId", memberId);
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
	public String isResign(String id) {
		return getSqlSession().selectOne("MemberDAO.isResign", id);
	}

	@Override
	public List<String> getGraduationType() {
		return getSqlSession().selectList("MemberDAO.getGraduationType");
	}

	@Override
	public void insertUuidForResign(MemberVO member) {
		getSqlSession().update("MemberDAO.insertUuidForResign", member);
	}

	@Override
	public int doDeleteMember(String id) {
		return getSqlSession().update("MemberDAO.doDeleteMember", id);
	}

	@Override
	public String selectedGraduationTypeCodeName(String id) {
		return getSqlSession().selectOne("MemberDAO.selectedGraduationTypeCodeName", id);
	}

	@Override
	public List<String> getHighestEducationLevelCodeNames() {
		return getSqlSession().selectList("MemberDAO.getHighestEducationLevelCodeNames");
	}

	@Override
	public String getSelectedHighestEducationLevelCodeName(String id) {
		return getSqlSession().selectOne("MemberDAO.getSelectedHighestEducationLevelCodeName", id);
	}

	@Override
	public String getGraduationTypeCodeId(String graduationType) {
		return getSqlSession().selectOne("MemberDAO.getGraduationTypeCodeId", graduationType);
	}

	@Override
	public String gethelCodeId(String helCodeName) {
		return getSqlSession().selectOne("MemberDAO.gethelCodeId", helCodeName);
	}

	@Override
	public String memberTypeCodeName(String id) {
		return getSqlSession().selectOne("MemberDAO.memberTypeCodeName", id);
	}

	@Override
	public int changePassword(MemberVO memberVO) {
		return getSqlSession().update("MemberDAO.changePassword", memberVO);
	}

	@Override
	public int getDateSearchLoginHistoryCount(String memberId) {
		return getSqlSession().selectOne("MemberDAO.getDateSearchLoginHistoryCount", memberId);
	}

	@Override
	public List<LoginHistoryVO> getDateSearchLoginHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return getSqlSession().selectList("MemberDAO.getDateSearchLoginHistory", loginHistorySearchVO);
	}

	@Override
	public List<MenuManageVO> getMenuCategoryList() {
		return getSqlSession().selectList("MemberDAO.getMenuCategoryList");
	}

	@Override
	public int isTeacher(String id) {
		return getSqlSession().selectOne("MemberDAO.isTeacher", id);
	}
	
	// 준호
	@Override
	public int delectJunitTestMember(String id) {
		return getSqlSession().delete("MemberDAO.delectJunitTestMember", id);
	}

	@Override
	public String doMatchHistoryWithMember(LoginHistoryVO loginHistoryVO) {
		return getSqlSession().selectOne("MemberDAO.doMatchHistoryWithMember", loginHistoryVO);
	}

	@Override
	public void doRequestIpHistory(int lgiHtrId) {
		getSqlSession().insert("MemberDAO.doRequestIpHistory", lgiHtrId);
	}
}
