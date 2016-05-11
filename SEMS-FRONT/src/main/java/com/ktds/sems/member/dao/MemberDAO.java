package com.ktds.sems.member.dao;

import java.util.List;

import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberDAO {

	public int addNewMember(MemberVO member);
	
	public int loginHistory(LoginHistoryVO loginHistoryVO);

	public int logoutHistory(LoginHistoryVO loginHistoryVO);

	public String getSaltById(String id);

	public MemberVO login(MemberVO loginVO);

	public boolean isAccountLock(String id);

	public int loginSuccess(String id);

	public int plusLoginFailCount(String id);

	public int updateAccountLock(String id);

	public MemberVO getOneMember(String id);

	public void resetModifyLockAndCount(String id);

	public void plusModifyFailCount(String id);

	public void updateModifyAccountLock(String id);

	public int isModifyAccountLock(String id);

	public String getNowDate();

	public String needToChangPassword(String id);

	public List<LoginHistoryVO> saveLoginHistoryAsExcel(String memberId);

	public int getTotalLoginHisotryCount();

	public void modifyMemberInfo(MemberVO member);

	public String isExistId(String id);

	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO);

	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO);

	public int nextLoginHistorySeq();
	
	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO);

	public String getPasswordById(String id);

}
