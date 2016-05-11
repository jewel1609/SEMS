package com.ktds.sems.member.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberBiz {

	public boolean addNewMember(MemberVO member);

	public boolean isAccountLock(String id);

	public boolean login(HttpSession session, MemberVO memberVO, HttpServletRequest request);

	public boolean loginSuccess(String id);

	public boolean plusLoginFailCount(String id);

	public boolean updateAccountLock(String id);

	public MemberVO getOneMember(String id);

	public void resetModifyLockAndCount(String id);

	public void plusModifyFailCount(String sessionId);

	public void updateModifyAccountLock(String sessionId);

	public boolean isModifyAccountLock(String sessionId);

	public String getNowDate();

	public boolean needToChangPassword(String id);

	public void saveLoginHistoryAsExcel(String memberId);

	public boolean stampLoginTime(HttpSession session, HttpServletRequest request, MemberVO loginVO);

	public boolean stampLogoutTime(HttpSession session);

	public int getTotalLoginHisotryCount();

	public void attend(MemberVO loginVO);

	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO);

	public void modifyMemberInfo(MemberVO member);

	public boolean isExistId(String id);

	public String getSaltById(String id);

	public String getPasswordById(String id);

	public boolean isResign(String id);

}
