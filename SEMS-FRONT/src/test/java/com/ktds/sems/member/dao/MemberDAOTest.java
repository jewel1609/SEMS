package com.ktds.sems.member.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberDAOTest extends SemsTestCase {

	@Autowired
	private MemberDAO memberDAO;

	/**
	 * SALT 얻어오기
	 */
	@Test
	public void getSaltByIdTest() {
		String id = "cocomo12";
		String salt = memberDAO.getSaltById(id);
		assertNotNull(salt);
	}
 
	/**
	 * 로그인
	 */
	@Test
	public void loginTest() {
		MemberVO loginVO = new MemberVO();
		loginVO.setId("cocomo12");
		loginVO.setPassword("cbf430bc6a30cc454e07fe1243eed8f7cb712395dfefbe38ff0c1c030b8e136c");
		
		MemberVO memberVO = memberDAO.login(loginVO);
		if(memberVO != null) {
			assertNotNull(memberVO.getId());
			assertNotNull(memberVO.getPassword());
			assertNotNull(memberVO.getName());
			assertNotNull(memberVO.getEmail());
			assertNotNull(memberVO.getBirthDate());
			assertNotNull(memberVO.getPhoneNumber());
			assertNotNull(memberVO.getMemberType());
			assertNotNull(memberVO.getSalt());
			assertNotNull(memberVO.getLoginFailCount());
			assertNotNull(memberVO.getIsResign());
			assertNotNull(memberVO.getModifyFailCount());
			assertNotNull(memberVO.getIsModifyLock());
		} else {
			fail("fail");
		}
	}

	/**
	 * 계정 잠겼는지 확인
	 */
	@Test
	public void isAccountLockTest() {
		String id = "cocomo12";
		boolean isSuccess = memberDAO.isAccountLock(id);
		assertTrue(!isSuccess);
	}

	/**
	 * 로그인 성공 확인
	 */
	@Test
	public void loginSuccessTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.loginSuccess(id);
		assertTrue(executeQuery > 0);
	}

	/**
	 * 로그인 실패 횟수 증가
	 */
	@Test
	public void plusLoginFailCountTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.plusLoginFailCount(id);
		assertTrue(executeQuery > 0);
	}

	/**
	 * 계정 잠금 상태 최신화
	 */
	@Test
	public void updateAccountLockTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.updateAccountLock(id);
		assertTrue(executeQuery == 0);
	}

	/**
	 * 로그인한지 30일 지났는지 확인
	 */
	@Test
	public void needToChangPasswordTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.needToChangPassword(id);
		assertNull(checkStr);
	}

	/**
	 * 존재하는 아이디인지 확인
	 */
	@Test
	public void isExistIdTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.isExistId(id);
		assertNotNull(checkStr);
	}

	/**
	 * 탈퇴한 회원인지 확인
	 */
	@Test
	public void isResignTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.isResign(id);
		assertNull(checkStr);
	}
	
	/**
	 * 한명의 회원 정보 가져오기 
	 */
	@Test
	public void getOneMemberTest(){
		
		String id = "test02";
		MemberVO memberVO = memberDAO.getOneMember(id);
		
		if( memberVO != null ){
			assertNotNull(memberVO.getId());
			assertNotNull(memberVO.getPassword());
			assertNotNull(memberVO.getName());
			assertNotNull(memberVO.getEmail());
			assertNotNull(memberVO.getHighestEducationLevel());
			assertNotNull(memberVO.getUniversityName());
			assertNotNull(memberVO.getMajorName());
			assertNotNull(memberVO.getGraduationType());
			assertNotNull(memberVO.getBirthDate());
			assertNotNull(memberVO.getPhoneNumber());
			assertNotNull(memberVO.getMemberType());
			assertNotNull(memberVO.getUuid());
		}
		else {
			fail("fail");
		}
	}

	
	/**
	 * 계정 잠겨있는지 확인
	 */
	@Test
	public void isModifyAccountLockTest(){
		String id = "aaa";
		int executeQuery = memberDAO.isModifyAccountLock(id);
			assertTrue(executeQuery == 0); 
	}
	
	/**
	 * 졸업 유형 이름 가져오기
	 */
	@Test
	public void selectedGraduationTypeCodeNameTest(){
		String id = "aaa";
		String checkStr = memberDAO.selectedGraduationTypeCodeName(id);
		assertNotNull(checkStr);
	}
	
	/**
	 * 최종학력 리스트 가져오기 
	 */
	@Test
	public void getHighestEducationLevelCodeNamesTest(){
		
		List<String> checkList = memberDAO.getHighestEducationLevelCodeNames();
		assertNotNull(checkList);
		assertTrue(checkList.size() >= 0 );

	}
	
	/**
	 * 최종학력 이름 가져오기
	 */
	@Test
	public void getSelectedHighestEducationLevelCodeNameTest(){
		String id = "aaa";
		String checkStr = memberDAO.getSelectedHighestEducationLevelCodeName(id);
		assertNotNull(checkStr);
	}
	
	/**
	 * 졸업유형 이름을 코드 아이디로 변환
	 */
	@Test
	public void getGraduationTypeCodeIdTest(){
		String graduationType = "졸업";
		String checkStr = memberDAO.getGraduationTypeCodeId(graduationType);
		assertNotNull(checkStr);
	}
	
	/**
	 * 최종학력 이름을 코드 아이디로 변환
	 */
	@Test
	public void gethelCodeIdTest(){
		String helCodeName = "대졸";
		String checkStr = memberDAO.gethelCodeId(helCodeName);
		assertNotNull(checkStr);		
	}
	
	/**
	 * 회원 코드 이름 가져오기
	 */
	@Test
	public void memberTypeCodeNameTest(){
		String id = "aaa";
		String checkStr = memberDAO.memberTypeCodeName(id);
		assertNotNull(checkStr);
	}
	
//	/**
//	 * 탈퇴 회원 업데이트
//	 */
//	@Test
//	public void doDeleteMemberTest() {
//		String id = "cocomo12";
//		int executeQuery = memberDAO.doDeleteMember(id);
//		assertTrue(executeQuery > 0);
//	}
	
	@Test
	public void getMenuCategoryListTest() {
		List<MenuManageVO> menu = memberDAO.getMenuCategoryList();
	    assertNotNull(menu);
	    assertTrue(menu.size() > 0);
	}
	
	@Test
	public void getEduListByMemberTest(){
		MemberVO loginVO = new MemberVO();
		loginVO.setId("JUnitTest");
		List<EducationVO> eduList = memberDAO.getEduListByMember(loginVO);
		assertNotNull(eduList);
		assertTrue(eduList.size() >= 0);
	}
	
	@Test
	public void getLastDateTest(){
		Map<String, String> eduIdAndMemberId = new HashMap<String, String>();
		eduIdAndMemberId.put("educationId", "JUnitTestEdu");
		eduIdAndMemberId.put("memberId", "JUnitTestmbrId");
		String date = memberDAO.getLastDate(eduIdAndMemberId);
		assertNotNull(date);
	}
	
	/**
	 * @author 남준호
	 */
	@Test
	public void addNewMemberTest() {
	      MemberVO member = new MemberVO();
	      member.setId("JunitId1");
	      member.setPassword("44cc5a083ad03370997e88834c4de95460fc54dc0562c401b71b9d504fe9d9b3");
	      member.setHighestEducationLevel("HIGH");
	      member.setName("Junitdd");
	      member.setEmail("Junitdd@naver.com");
	      member.setUniversityName("JUNIT대학교");
	      member.setMajorName("JUNIT과");
	      member.setGraduationType("GRAD");
	      member.setBirthDate("1991-06-03");
	      member.setPhoneNumber("010-0000-1154");
	      member.setMemberType("MBR");
	      member.setSalt("c21586d7786ea63b");
		int addNewMember = memberDAO.addNewMember(member);
		
		assertTrue(addNewMember > 0);
	}
	
	@Test
	public void isExistEmailTest() {
		String isExistEmail = memberDAO.isExistEmail("Junitdd@naver.com");
		assertNotNull(isExistEmail);
		assertTrue(isExistEmail.equals("JunitId1"));
		
	}
	
	@Test
	public void zdelectJunitTestMember() {
		memberDAO.delectJunitTestMember("JunitId1");
	}
	
	
}
