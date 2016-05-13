package com.ktds.sems.member.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/menuContext.xml", "/educationContext.xml", "/memberContext.xml",
		"/rootContext.xml" })
public class MemberDAOTest {

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
	
	@Test
	public void getAllGrdtListTest(){
		List<GrdtTpVO> grdtTpList = memberDAO.getAllGrtdList();
		assertNotNull(grdtTpList);
		assertTrue(grdtTpList.size() >= 0);
	}
	
	@Test
	public void doGrdtInsertTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");
		
		int checkGrdtInsert = memberDAO.doGrdtInsert(grdtTpVO);
		assertTrue(checkGrdtInsert >= 1);
	}
	
	@Test
	public void doGrdtModifyTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");
		
		int checkGrdtModify = memberDAO.doGrdtModify(grdtTpVO);
		assertTrue(checkGrdtModify >= 1);
	}
	
	@Test
	public void doGrdtDeleteTest(){
		String cdId = "TEST";
		int checGrdtkDelete = memberDAO.doGrdtDelete(cdId);
		assertTrue(checGrdtkDelete >= 1);
	}
	
	@Test
	public void isExistDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("EXPT");
		grdtTpVO.setCdNm("졸업");
		
		int checkExistData = memberDAO.isExistData(grdtTpVO);
		assertTrue(checkExistData >= 1);
	}
	
	@Test
	public void isExistCdNmDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdNm("졸업예정");
		
		int checkExistCdNmData = memberDAO.isExistCdNmData(grdtTpVO);
		assertTrue(checkExistCdNmData >= 1);
	}
	
	@Test
	public void getAllMbrTpListTest(){
		List<MbrTpVO> mbrTpList = memberDAO.getAllMbrTpList();
		assertNotNull(mbrTpList);
		assertTrue(mbrTpList.size() >= 0);
	}
	
	@Test
	public void doInsertMbrTpTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");
		
		int checkMbrTpInsert = memberDAO.doInsertMbrTp(mbrTpVO);
		assertTrue(checkMbrTpInsert >= 1);
	}
	
	@Test
	public void doMbrTpModifyTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");
		
		int checkMbrTpModify = memberDAO.doMbrTpModify(mbrTpVO);
		assertTrue(checkMbrTpModify >= 1);
	}
	
	@Test
	public void doMbrTpDeleteTest(){
		String cdId = "TEST";
		int checkMbrTpDelete = memberDAO.doMbrTpDelete(cdId);
		assertTrue(checkMbrTpDelete >= 1);
	}
	
	@Test
	public void isExistMbrTpDataTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");
		
		int checkExistMbrTpData = memberDAO.isExistMbrTpData(mbrTpVO);
		assertTrue(checkExistMbrTpData >= 1);
	}
	
}
