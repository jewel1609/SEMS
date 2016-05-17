package com.ktds.sems.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public ModelAndView addNewMember(MemberVO member, Errors errors, String repeatPassword, HttpSession session);

	public String login(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request);

	public ModelAndView modifySuccess(String id);

	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors, String graduationType, String helCodeName);

	public void checkValidationById(String id, HttpServletResponse response);

	public void checkValidationByPassword(String password, HttpServletResponse response);

	public void plusModifyFailCount(String sessionId);

	public void updateModifyAccountLock(String sessionId);

	public void resetModifyLockAndCount(String sessionId);

	public boolean isModifyAccountLock(String sessionId);

	public ModelAndView viewLoginHistoryPage(LoginHistorySearchVO loginHistorySearchVO, int pageNo, HttpSession session, HttpServletRequest request);

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

	public String doResign(MemberVO loginVO, Errors errors, String resignCode);

	public ModelAndView registerStudent(HttpSession session);

	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response);

	public String doCheckPrevPassword(String id, String prevPassword, HttpServletRequest request);
	
	public ModelAndView viewMyPageMenu();

	public String registerTeacher(HttpSession session);

	public ModelAndView getAllEducationHistoryList(int pageNo, HttpSession session);

	public String registerPolicy(HttpSession session);

	public ModelAndView loginHistoryInit();

	public ModelAndView doRequestIpHistory(int lgiHtrId, HttpSession session);

	public ModelAndView doCheckIp(int lgiHtrId, HttpSession session);

	public String eduationHistoryExportExel(HttpSession session);

}
