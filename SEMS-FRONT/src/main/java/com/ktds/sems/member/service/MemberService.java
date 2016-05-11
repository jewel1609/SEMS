package com.ktds.sems.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public ModelAndView addNewMember(MemberVO members, Errors errors, HttpSession session);

	public String login(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request);

	public ModelAndView modifySuccess(String id);

	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors);

	public void plusModifyFailCount(String sessionId);

	public void updateModifyAccountLock(String sessionId);

	public void resetModifyLockAndCount(String sessionId);

	public boolean isModifyAccountLock(String sessionId);

	public ModelAndView viewLoginHistoryPage(int pageNo);

	public void saveLoginHistoryAsExcel(HttpSession session);

	public void sendBlockAccountEmail(String sessionId);

	public String getSaltById(String id);

	public String getPasswordById(String id);

	public void logout(HttpSession session);

}
