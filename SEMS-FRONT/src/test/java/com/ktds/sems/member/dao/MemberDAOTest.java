package com.ktds.sems.member.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml",
		"/menuContext.xml", "/rootContext.xml" })
public class MemberDAOTest {

	@Autowired
	private MemberDAO memberDAO;

	/**
	 * SALT 얻어오기
	 */
	@Test
	public void getSaltByIdTest() {
		String id = "cocomo";
		String salt = memberDAO.getSaltById(id);
		assertNotNull(salt);
	}

	/**
	 * 로그인
	 */
	@Test
	public void loginTest() {
		MemberVO loginVO = new MemberVO();
		loginVO.setId("cocomo");
		loginVO.setPassword("123qwe!@#qwe");
		
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

	@Test
	public void isAccountLockTest() {
		String id = "cocomo";
		boolean isSuccess = memberDAO.isAccountLock(id);
		assertTrue(!isSuccess);
	}

	@Test
	public void loginSuccessTest() {
		String id = "cocomo";
		int executeQuery = memberDAO.loginSuccess(id);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void plusLoginFailCountTest() {
		String id = "cocomo";
		int executeQuery = memberDAO.plusLoginFailCount(id);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void updateAccountLockTest() {
		String id = "cocomo";
		int executeQuery = memberDAO.updateAccountLock(id);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void needToChangPasswordTest() {
		String id = "cocomo";
		String checkStr = memberDAO.needToChangPassword(id);
		assertNull(checkStr);
	}

	@Test
	public void isExistIdTest() {
		String id = "cocomo";
		String checkStr = memberDAO.isExistId(id);
		assertNotNull(checkStr);
	}

	@Test
	public void isResignTest() {
		String id = "cocomo";
		String checkStr = memberDAO.isResign(id);
		assertNull(checkStr);
	}
}
