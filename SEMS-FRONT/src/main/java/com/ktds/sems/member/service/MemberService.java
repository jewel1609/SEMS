package com.ktds.sems.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public ModelAndView addNewMember(MemberVO member, Errors errors, HttpSession session);

	public String login(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request);

	public ModelAndView modifySuccess(String id);

	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors, String graduationType, String helCodeName);

	public void checkValidationById(String id, HttpServletResponse response);

	public void checkValidationByPassword(String password, HttpServletResponse response);

	public void plusModifyFailCount(String sessionId);

	public void updateModifyAccountLock(String sessionId);

	public void resetModifyLockAndCount(String sessionId);

	public boolean isModifyAccountLock(String sessionId);

	public ModelAndView viewLoginHistoryPage(LoginHistorySearchVO loginHistorySearchVO, Errors errors, int pageNo, HttpSession session, HttpServletRequest request);

	public ModelAndView saveLoginHistoryAsExcel(HttpSession session);

	public void sendBlockAccountEmail(String sessionId);

	public String getSaltById(String id);

	public String getPasswordById(String id);

	public void checkValidationByEmail(String email, HttpServletResponse response);

	public void checkValidationByRepeatPassword(String password, String repeatPassword, HttpServletResponse response);

	public void logout(HttpSession session);

	public ModelAndView changePassword(MemberVO memberVO);

	public String insertUuidForResign(HttpSession session);

	public ModelAndView sendEmailForResign(HttpSession session, String uuid);

	public ModelAndView loginForResign(String resignCode, String id);

	public String doResign(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request, String resignCode);

	public ModelAndView registerStudent();

	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response);

	public String doCheckPrevPassword(String id, String prevPassword, HttpServletRequest request);
	
	public ModelAndView viewMyPageMenu();

}
