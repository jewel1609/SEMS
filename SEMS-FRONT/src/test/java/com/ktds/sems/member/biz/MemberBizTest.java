package com.ktds.sems.member.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml",
		"/rootContext.xml" })
public class MemberBizTest {

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
		
		List<String> result = memberBiz.getHighestEducationLevelCodeNames();
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
	}
	
	@Test
	public void getGraduationTypeTest(){
		
		List<String> result = memberBiz.getGraduationType();
		if ( result != null ) {
			assertNotNull(result);
		}
		else {
			fail("result is null");
		}
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
	


	
}
