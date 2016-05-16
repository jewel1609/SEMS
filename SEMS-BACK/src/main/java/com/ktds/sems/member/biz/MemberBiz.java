package com.ktds.sems.member.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.sems.member.vo.MemberVO;

public interface MemberBiz {

	public boolean isExistId(String id);

	public boolean isAccountLock(String id);

	public boolean loginSuccess(String id);

	public boolean needToChangPassword(String id);

	public boolean plusLoginFailCount(String id);

	public boolean updateAccountLock(String id);

	public boolean login(HttpSession session, MemberVO loginVO);

	public boolean isResign(String id);

	public boolean stampLogoutTime(HttpSession session);

	public boolean stampLoginTime(HttpSession session, HttpServletRequest request, MemberVO loginVO);

	public boolean isVerifyId(String id);

	public boolean isDuplicationId(String id);

	public boolean isVerifyPassword(String password);

	public boolean isVerifyEmail(String email);

	public boolean isExistEmail(String email);

	public boolean isVerifyPhoneNumber(String phoneNumber);

}
