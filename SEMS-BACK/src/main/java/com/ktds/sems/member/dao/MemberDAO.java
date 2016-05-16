package com.ktds.sems.member.dao;

import java.util.List;

import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberSearchVO;
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

	public String isResign(String id);

	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO);

	public int nextLoginHistorySeq();

	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO);

	public int getTotalMemberCount();

	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO);

	public String isExistEmail(String email);

	public MemberVO getMemberDetailById(String id);

	public List<String> getHighestEducationLevelCodeNames();

	public List<String> getGraduationType();

	public void addNewMember(MemberVO member);

	public String getHelCodeId(String highestEducationLevel);

	public String getGraduationTypeCodeId(String graduationType);


}
