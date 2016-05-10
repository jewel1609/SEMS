package com.ktds.sems.member.biz;

import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberBiz {

	public boolean addNewMember(MemberVO member);
	
	public boolean loginHistory(LoginHistoryVO loginHistoryVO);
	
	public boolean logoutHistory(LoginHistoryVO loginHistoryVO);
}
