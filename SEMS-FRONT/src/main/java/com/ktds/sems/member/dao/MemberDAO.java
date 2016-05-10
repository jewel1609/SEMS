package com.ktds.sems.member.dao;

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

	public void resetModifyLockAndcount(String id);

	public void plusModifyFailCount(String id);

	public void updateModifyAccountLock(String id);

	public int isModifyAccountLock(String id);

	public String getNowDate();

	public int needToChangPassword(MemberVO loginVO);

	
}
