package com.ktds.sems.member.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberSearchVO;
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

	public List<LoginHistoryVO> getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO);

	public int getTotalMemberCount();

	public List<MemberVO> getAllMemberList(MemberSearchVO searchVO);

	public boolean isVerifyId(String id);

	public boolean isDuplicationId(String id);

	public boolean isVerifyPassword(String password);

	public boolean isVerifyEmail(String email);

	public boolean isExistEmail(String email);

	public boolean isVerifyPhoneNumber(String phoneNumber);

	public int getTotalMemberHistoryCount();

	public void massiveDeleteMember(String memberId);

	public MemberVO getMemberDetailById(String id);

	public List<String> getHighestEducationLevelCodeNames();

	public List<String> getGraduationType();

	public String getHelCodeId(String highestEducationLevel);

	public String getGraduationTypeCodeId(String graduationType);

	public void addNewMember(MemberVO member);

}
