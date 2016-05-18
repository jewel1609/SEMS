package com.ktds.sems.member.dao;

import java.util.List;
import java.util.Map;

import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

public interface MemberDAO {

	public int addNewMember(MemberVO member);

	public int loginHistory(LoginHistoryVO loginHistoryVO);

	public int logoutHistory(LoginHistoryVO loginHistoryVO);

	public String getSaltById(String id);

	public MemberVO login(MemberVO loginVO);

	public boolean isAccountLock(String id);

	public int loginSuccess(String id);

	public int plusLoginFailCount(String id);

	public int updateAccountLock(String id);

	public MemberVO getOneMember(String id);

	public void resetModifyLockAndCount(String id);

	public void plusModifyFailCount(String id);

	public void updateModifyAccountLock(String id);

	public int isModifyAccountLock(String id);

	public String getNowDate();

	public String needToChangPassword(String id);

	public List<LoginHistoryVO> saveLoginHistoryAsExcel(String memberId);

	public int getTotalLoginHistoryCount(LoginHistorySearchVO loginHistorySearchVO);

	public List<EducationVO> getEduListByMember(MemberVO loginVO);

	public void insertAttendByMember(AttendVO attendVO);

	public String getLastDate(Map<String,String> eduIdAndMemberId);

	public void modifyMemberInfo(MemberVO member);

	public String isExistId(String id);

	public int stampLoginTime(LoginHistoryVO newLoginHistoryVO);

	public String isExistEmail(String email);

	public int stampLogoutTime(LoginHistoryVO newLoginHistoryVO);
	public int stampLogoutTimeByMemberId(String memberId);

	public int nextLoginHistorySeq();

	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO);

	public String getPasswordById(String id);

	public String isResign(String id);

	public List<String> getGraduationType();

	public void insertUuidForResign(MemberVO member);

	public int doDeleteMember(String id);

	public String selectedGraduationTypeCodeName(String id);

	public List<String> getHighestEducationLevelCodeNames();

	public String getSelectedHighestEducationLevelCodeName(String id);

	public String getGraduationTypeCodeId(String graduationType);

	public String gethelCodeId(String helCodeName);

	public String memberTypeCodeName(String id);

	public int changePassword(MemberVO memberVO);

	public List<MenuManageVO> getMenuCategoryList();

	public int isTeacher(String id);
	
	public int delectJunitTestMember(String id);

	public int getTotalEducationHistoryCountById(String id);

	public List<EducationHistoryVO> getAllEducationHistoryListByIdWithPaging(EducationHistorySearchVO educationHistorySearchVO);

	public String doMatchHistoryWithMember(LoginHistoryVO loginHistoryVO);

	public void doRequestIpHistory(int lgiHtrId);

	public int doCheckIp(LoginHistoryVO loginHistoryVO);

	public LoginHistoryVO checkIpInfo(LoginHistoryVO loginHistoryVO);

	public List<EducationHistoryVO> getAllEducationHistoryListById(String id);

	public void ipCheckCountUpdate(LoginHistoryVO loginHistoryVO);

	public List<EducationHistoryVO> getJoinEducationList(String memberId);

	public int isAdmin(String id);

}
