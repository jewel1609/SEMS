package com.ktds.sems.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberService {

	public String login(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request);

	public void logout(HttpSession session);

	public ModelAndView getAllMemberHistory(int pageNo);

	public ModelAndView getAllMemberList(int pageNo);

	public void checkValidationById(String id, HttpServletResponse response);

	public void checkValidationByPassword(String password, HttpServletResponse response);

	public void checkValidationByRepeatPassword(String password, String repeatPassword, HttpServletResponse response);

	public void checkValidationByEmail(String email, HttpServletResponse response);

	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response);
	public String massiveDeleteMember(String[] deleteMemberIds);


	public ModelAndView getMemberDetailById(String id);

	public ModelAndView addNewMember(MemberVO member, Errors errors, HttpSession session);

	public List<String> getHighestEducationLevelCodeNames();

	public List<String> getGraduationType();

}
