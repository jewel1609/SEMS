package com.ktds.sems.member.biz;

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

}
