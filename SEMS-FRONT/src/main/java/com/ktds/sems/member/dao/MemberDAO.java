package com.ktds.sems.member.dao;

import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberDAO {

	public int addNewMember(MemberVO member);
	
	public String nowTime();
	
	public int increaseHistorySeq();
	
	public int loginHistory(LoginHistoryVO loginHistoryVO);

	public int logoutHistory(LoginHistoryVO loginHistoryVO);

}
