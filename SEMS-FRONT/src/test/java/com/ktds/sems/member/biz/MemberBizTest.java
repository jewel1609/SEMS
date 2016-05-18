package com.ktds.sems.member.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

import kr.co.hucloud.utilities.web.Paging;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberBizTest extends SemsTestCase {

	@Autowired
	private MemberBiz memberBiz;

	/**
	 * 계정 잠겨있는지 확인
	 * 
	 */
	@Test
	public void isAccountLockTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.isAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인
	 * 
	 */
	@Test
	public void loginTest() {

		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest reqeust = new MockHttpServletRequest();

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setPassword("123qwe!@#qwe");

		boolean isSuccess = memberBiz.login(session, memberVO, reqeust);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 성공 확인
	 * 
	 */
	@Test
	public void loginSuccessTest() {

		String id = "cocomo12";
		boolean isSuccess = memberBiz.loginSuccess(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 실패 횟수 증가
	 * 
	 */
	@Test
	public void plusLoginFailCountTest() {

		String id = "cocomo12";
		boolean isSuccess = memberBiz.plusLoginFailCount(id);
		assertTrue(isSuccess);
	}

	/**
	 * 계정 잠금상태 최신화
	 * 
	 */
	@Test
	public void updateAccountLockTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.updateAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 30일이 지나서 비밀번호 바꿔야 하는지 확인
	 * 
	 */
	@Test
	public void needToChangPasswordTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.needToChangPassword(id);
		assertTrue(isSuccess);
	}

	/**
	 * 존재하는 아이디 인지 확인
	 * 
	 */
	@Test
	public void isExistIdTest() {
		String id = "cocomo12";
		boolean isSuccess = memberBiz.isExistId(id);
		assertTrue(isSuccess);
	}

	/**
	 * 탈퇴한 회원인지 확인
	 * 
	 */
	@Test
	public void isResignTest() {
		String id = "cocomo12";
		boolean isSuccess = !memberBiz.isResign(id);
		assertTrue(isSuccess);
	}
	
	@Test
	public void memberTypeCodeName(){
		String id = "aaa";
		String result = memberBiz.memberTypeCodeName(id);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void getGraduationTypeCodeIdTest(){
		String graduationType = "JUNIT졸업";
		String result = memberBiz.getGraduationTypeCodeId(graduationType);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void gethelCodeIdTest(){
		String helCodeName = "초졸";
		String result = memberBiz.gethelCodeId(helCodeName);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void getSelectedHighestEducationLevelCodeNameTest(){
		String id = "aaa";
		String result = memberBiz.getSelectedHighestEducationLevelCodeName(id);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void getHighestEducationLevelCodeNamesTest(){
		
		List<String> helCodeNameList = memberBiz.getHighestEducationLevelCodeNames();
		
		assertNotNull(helCodeNameList);
		assertTrue(helCodeNameList.size() >= 0 );
		
	}
	
	@Test
	public void getGraduationTypeTest(){
		
		List<String> graduationTypeList = memberBiz.getGraduationType();

		assertNotNull(graduationTypeList);
		assertTrue(graduationTypeList.size() >= 0 );
	}
	
	@Test
	public void selectedGraduationTypeCodeNameTest(){
		String id = "aaa";
		String result = memberBiz.selectedGraduationTypeCodeName(id);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void getSaltByIdTest(){
		String id = "aaa";
		String result = memberBiz.getSaltById(id);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void getPasswordByIdTest(){
		String id = "aaa";
		String result = memberBiz.getPasswordById(id);
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void isModifyAccountLockTest(){
		String id = "aaa";
		boolean isSuccess = !memberBiz.isModifyAccountLock(id);
		assertTrue(isSuccess);
 	}
	
	@Test
	public void getMenuCategoryListTest() {
		List<MenuManageVO> menu = memberBiz.getMenuCategoryList();
	    assertNotNull(menu);
	    assertTrue(menu.size() > 0);
	}

	@Test
	public void addNewMemberTest(){
		MemberVO member = new MemberVO();
		member.setId("JunitIdTest1");
		member.setPassword("44cc5a083ad03370997e88834c4de95460fc54dc0562c401b71b9d504fe9d9b3");
		member.setHighestEducationLevel("HIGH");
		member.setName("JunitName");
		member.setEmail("junit1@naver.com");
		member.setUniversityName("JUNIT대학교");
		member.setMajorName("JUNIT과");
		member.setGraduationType("GRAD");
		member.setBirthDate("1991-06-03");
		member.setPhoneNumber("010-0000-1154");
		member.setMemberType("MBR");
		member.setSalt("c21586d7786ea63b");
		
		boolean checkAddNewMember = memberBiz.addNewMember(member);
		assertTrue(checkAddNewMember);
	}
	
	@Test
	public void isVerifyIdTest(){
		String id = "JunitIdTest1";
		boolean checkVerifyId = memberBiz.isVerifyId(id);
		assertTrue(checkVerifyId);
	}
	@Test
	public void isVerifyPasswordTest(){
		String pw = "123qwe!@#qwe";
		boolean checkVerifyPw = memberBiz.isVerifyPassword(pw);
		assertTrue(checkVerifyPw);
	}
	@Test
	public void isVerifyPhoneNumberTest(){
		String phoneNumber = "010-0000-1154";
		boolean checkVerifyPn = memberBiz.isVerifyPhoneNumber(phoneNumber);
		assertTrue(checkVerifyPn);
	}
	@Test
	public void isVerifyEmailTest(){
		String email = "junit1@naver.com";
		boolean checkVerifyEmail = memberBiz.isVerifyEmail(email);
		assertTrue(checkVerifyEmail);
	}
	@Test
	public void isExistEmailTest(){
		String email = "junit1@naver.com";
		boolean isExistEmail = memberBiz.isExistEmail(email);
		assertTrue(isExistEmail);
	}
	
	//오름차순에서 맨마지막에 실행하기위해
	@Test
	public void zdoDeleteTest(){
		String id = "JunitIdTest1";
		int doDeleteTest = memberBiz.delectJunitTestMember(id);
		assertTrue(doDeleteTest > 0);
	}
	
	/**
	 *	나의 교육 이력 보기 
	 */
	@Test
	public void getAllEducationHistoryListByIdWithPagingTest() {
		
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		
		int totalArticleNumber = memberBiz.getTotalEducationHistoryCountById("test01");
		
		if(totalArticleNumber != 0) {
			
			paging.setTotalArticleCount(totalArticleNumber);
			EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
			educationHistorySearchVO.setPageNo(0);
			educationHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
			educationHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
			
			educationHistorySearchVO.setMemberId("test01");
			List<EducationHistoryVO> educationHistoryList = memberBiz.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO);
			
			if(educationHistoryList != null) {
				
				for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
					assertNotNull(educationHistoryVO.getEducationHistoryId());
					assertNotNull(educationHistoryVO.getEducationId());
					assertNotNull(educationHistoryVO.getMemberId());
					assertNotNull(educationHistoryVO.getEducationHistoryDate());
					assertNotNull(educationHistoryVO.getState());
					assertNotNull(educationHistoryVO.getIp());
					assertNotNull(educationHistoryVO.getStartDate());
					assertNotNull(educationHistoryVO.getEndDate());
				}
				
			} else {
				fail("fail");
			}
			
		} else {
			fail("fail");
		}

	}

	@Test
	public void viewLoginHistoryPageTest() {
		
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setId("test04");

		int totalLoginHistoryCount = memberBiz.getTotalLoginHistoryCount(loginHistorySearchVO);
		
		if(totalLoginHistoryCount != 0) {
			Paging paging = new Paging();
			paging.setTotalArticleCount(totalLoginHistoryCount);
			paging.setPageNumber(0 + "");
			loginHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
			loginHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
			loginHistorySearchVO.setLgiHtrId(1048);
			loginHistorySearchVO.setBeginDate("2016/05/17");
			loginHistorySearchVO.setCloseDate("2016/05/17");
			
			List<LoginHistoryVO> loginHistoryList = memberBiz.getAllLoginHistory(loginHistorySearchVO);
			if(loginHistoryList != null) {
				for (LoginHistoryVO loginHistoryVO : loginHistoryList) {
					assertNotNull(loginHistoryVO.getLgiHtrId());
					assertNotNull(loginHistoryVO.getId());
					assertNotNull(loginHistoryVO.getLgiIp());
					assertNotNull(loginHistoryVO.getLgiDt());
					assertNotNull(loginHistoryVO.getLgoDt());
					assertNotNull(loginHistoryVO.getIsReq());
					assertNotNull(loginHistoryVO.getChkCnt());
				}
			}
			else {
				fail("[Biz Part] viewLoginHistoryPageTest < loginHistoryList Fail.");
			}
			
			LoginHistoryListVO loginHistoryListVO = new LoginHistoryListVO();
			loginHistoryListVO.setLoginHistoryList(loginHistoryList);
			loginHistoryListVO.setPaging(paging);
			assertNotNull(paging);
		}else {
			fail("[Biz Part] viewLoginHistoryPageTest < totalLoginHistoryCount Fail.");
		}
	}
	
	@Test
	public void doRequestIpHistoryTest(){
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiHtrId(1048);
		
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		assertTrue(memberCheck);
	}
	
	@Test
	public void doCheckIpTest(){
		
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiHtrId(1048);
		
		boolean checkIp = memberBiz.doCheckIp(loginHistoryVO);
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		
		assertTrue(checkIp);
		assertTrue(memberCheck);
	}
	
	/**
	 * 나의 교육 이력 엑셀 다운로드
	 */
	@Test
	public void eduationHistoryExportExcelTest() {
		
		String id = "test02";
		boolean isSuccess = memberBiz.eduationHistoryExportExcel(id);
		
		assertTrue(isSuccess);
	}
	
	/**
	 * 진행중인 교육 이력만 보기
	 */
	@Test
	public void getJoinEducationListTest() {
		String memberId = "test02";
		List<EducationHistoryVO> educationHistoryList = memberBiz.getJoinEducationList(memberId);
		
		if( educationHistoryList != null ) {
			
			for (EducationHistoryVO educationHistoryVO : educationHistoryList) {
				assertNotNull(educationHistoryVO.getEducationHistoryId());
				assertNotNull(educationHistoryVO.getEducationId());
				assertNotNull(educationHistoryVO.getMemberId());
				assertNotNull(educationHistoryVO.getEducationHistoryDate());
				assertNotNull(educationHistoryVO.getState());
				assertNotNull(educationHistoryVO.getIp());
				assertNotNull(educationHistoryVO.getStartDate());
				assertNotNull(educationHistoryVO.getEndDate());
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void stampLoginTimeTest() {
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setLgiHtrId(1048);
		loginHistoryVO.setId("test04");
		loginHistoryVO.setLgiIp(request.getRemoteHost());
		
		session.setAttribute(Session.LOGIN_HISTORY, loginHistoryVO);
		
		boolean check = memberBiz.stampLoginTime(session, request, loginHistoryVO);
		assertTrue(check);
		if( check ) {
			assertNotNull(check);
		} else {
			fail("[Biz Part] stampLoginTimeTest Fail.");
		}
	}
	
	@Test
	public void stampLogoutTimeTest() {
		MockHttpSession session = new MockHttpSession();
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setLgiHtrId(1048);
		session.setAttribute(Session.LOGIN_HISTORY, loginHistoryVO);
		
		boolean check = memberBiz.stampLogoutTime(session);
		assertTrue(check);
		if( check ) {
			assertNotNull(check);
		} else {
			fail("[Biz Part] stampLogoutTimeTest Fail.");
		}
	}
	
}
