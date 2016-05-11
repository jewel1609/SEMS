package com.ktds.sems.member.dao;

import com.ktds.sems.member.vo.MemberVO;

public interface MemberDAO {

	public String isExistId(String id);

	public boolean isAccountLock(String id);

	public int loginSuccess(String id);

	public String needToChangPassword(String id);

	public int plusLoginFailCount(String id);

	public int updateAccountLock(String id);

	public String getSaltById(String id);

	public MemberVO login(MemberVO loginVO);


}
