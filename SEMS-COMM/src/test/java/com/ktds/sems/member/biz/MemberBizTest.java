package com.ktds.sems.member.biz;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/menuContext.xml", "/educationContext.xml",
		"/memberContext.xml", "/rootContext.xml" })
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

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setPassword("123qwe!@#qwe");

		boolean isSuccess = memberBiz.login(session, memberVO);
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
		boolean isSuccess = !memberBiz.isExistId(id);
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
	public void getAllGrdtListTest(){
		List<GrdtTpVO> grdtTpList = memberBiz.getAllGrtdList();
		assertNotNull(grdtTpList);
		assertTrue(grdtTpList.size() >= 0);
	}
	
	@Test
	public void doGrdtInsertTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");
		
		int checkGrdtInsert = memberBiz.doGrdtInsert(grdtTpVO);
		assertTrue(checkGrdtInsert >= 1);
	}
	
	@Test
	public void doGrdtModifyTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");
		
		int checkGrdtModify = memberBiz.doGrdtModify(grdtTpVO);
		assertTrue(checkGrdtModify >= 1);
	}
	
	@Test
	public void doGrdtDeleteTest(){
		String cdId = "TEST";
		int checGrdtkDelete = memberBiz.doGrdtDelete(cdId);
		assertTrue(checGrdtkDelete >= 1);
	}
	
	@Test
	public void isExistDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("EXPT");
		grdtTpVO.setCdNm("졸업");
		
		int checkExistData = memberBiz.isExistData(grdtTpVO);
		assertTrue(checkExistData >= 1);
	}
	
	@Test
	public void isExistCdNmDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdNm("졸업예정");
		
		int checkExistCdNmData = memberBiz.isExistCdNmData(grdtTpVO);
		assertTrue(checkExistCdNmData >= 1);
	}
}
