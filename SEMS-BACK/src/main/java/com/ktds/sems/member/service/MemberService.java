package com.ktds.sems.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

public interface MemberService {

	public String login(MemberVO memberVO, Errors errors, HttpSession session, HttpServletRequest request);

	public void logout(HttpSession session);

	public ModelAndView getAllMemberHistory(LoginHistorySearchVO loginHistorySearchVO, int pageNo, HttpSession session);
	
	public ModelAndView getAllMemberList(MemberSearchVO memberSearchVO, int pageNo);

	public void checkValidationById(String id, HttpServletResponse response);

	public void checkValidationByPassword(String password, HttpServletResponse response);

	public void checkValidationByRepeatPassword(String password, String repeatPassword, HttpServletResponse response);

	public void checkValidationByEmail(String email, HttpServletResponse response);

	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response);
	
	public String massiveDeleteMember(String[] deleteMemberIds);

	public ModelAndView addNewMember(MemberVO member, Errors errors, HttpSession session);

	public List<String> getHighestEducationLevelCodeNames();

	public List<String> getGraduationType();

	public ModelAndView changeMemberPassword(String id);

	public void sendAndChangePassword(String memberId, HttpServletResponse response);

	public ModelAndView modifyMemberType(String memberType, List<String> memberId);

	public ModelAndView memberDeleteById(String id);

	public List<String> getMemberTypeCodeNameList();

	public ModelAndView requestMemberDetail(String id);

	public ModelAndView doWriteMemberDetailInfo(PersonalInfoReadVO requestMemberDetailInfo, Errors errors);

	public ModelAndView loginHistoryInit();
	
	public ModelAndView getAllAdminHistory(int pageNo);

	public ModelAndView memberListInit();

}
