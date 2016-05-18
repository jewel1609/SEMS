package com.ktds.sems.member.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberDAOTest extends SemsTestCase {

	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void changePasswordTest () {
		MemberVO member = new MemberVO();
		member.setId("sosdig1");
		member.setPassword("4c5b7ab6a121aae1acda84fc71ed4b135e9f8eb7f1a25013515845e9c7ddc9f8");
		member.setSalt("9ca0645b12e961ac");
		int updateCount = memberDAO.changePassword(member);
		
		assertTrue(updateCount > 0);
	}
	
	@Test
	public void getMemberTypeTest () {
		List<String> memberTypeList = memberDAO.getMemberType();
		assertNotNull(memberTypeList);
	}
	
	@Test
	public void getMemberTypeCodeTest () {
		String memberType = "일반회원";
		String memberTypeCode = memberDAO.getMemberTypeCode(memberType);
		
		assertNotNull(memberTypeCode);
		assertEquals(memberTypeCode, "MBR");
	}
	
	@Test
	public void modifyMemberTypeByIdTest () {
		Map<String, String> modifyMemberType = new HashMap<String, String>();
		modifyMemberType.put("memberId", "sosdig1");
		modifyMemberType.put("memberTypeCode", "MBR");
		int updateCount = memberDAO.modifyMemberTypeById(modifyMemberType);
		
		assertTrue(updateCount > 0);
	}
	
	@Test
	public void getSaltByIdTest() {
		String id = "test01";
		String salt = memberDAO.getSaltById(id);
		assertNotNull(salt);
	}
	
	@Test
	public void getHighestEducationLevelCodeNamesTest() {
		List<String> helList = memberDAO.getHighestEducationLevelCodeNames();
		assertNotNull(helList);
	}
	
	@Test
	public void getHelCodeIdTest() {
		String highestEducationLevel = "대졸";
		String helCodeId = memberDAO.getHelCodeId(highestEducationLevel);
		
		assertNotNull(helCodeId);
		assertEquals(helCodeId, "UNIV");
	}
	
	@Test
	public void getGraduationTypeCodeIdTest() {
		String graduationType = "졸업예정";
		String grdtCodeId = memberDAO.getGraduationTypeCodeId(graduationType);
		
		assertNotNull(grdtCodeId);
		assertEquals(grdtCodeId, "EXPT");
	}
	
	@Test
	public void getMemberTypeCodeNameListTest() {
		List<String> memberTypeCodeNameList = memberDAO.getMemberTypeCodeNameList();
		assertNotNull(memberTypeCodeNameList);
	}
	
	@Test
	public void getMemberTypeCodeIdTest() {
		String memberType = "강사";
		String memberTypeCodeId = memberDAO.getMemberTypeCodeId(memberType);
		
		assertNotNull(memberTypeCodeId);
		assertEquals(memberTypeCodeId, "TR");
	}
	
	@Test
	public void getTotalMemberCountTest(){
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		int totalCount = memberDAO.getTotalMemberCount(memberSearchVO);
		assertTrue(totalCount >= 0);
	}
	
	@Test
	public void getAllMemberListTest(){
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");		
		memberSearchVO.setStartIndex(0);
		memberSearchVO.setEndIndex(10);
		
		List<MemberVO> memberList = memberDAO.getAllMemberList(memberSearchVO);
		
		assertNotNull(memberList);
		if(memberList != null){
			assertTrue(memberList.size() == 10);
		}
		else{
			fail("Fail...");
		}
	}
	
	@Test
	public void massiveDeleteMemberTest () {
		MemberVO member = new MemberVO();
		member.setId("Junit1");
		member.setPassword("4c5b7ab6a121aae1acda84fc71ed4b135e9f8eb7f1a25013515845e9c7ddc9f8");
		member.setSalt("9ca0645b12e961ac");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		memberDAO.addNewMember(member);
		
		int deleteCount = memberDAO.massiveDeleteMember(member.getId());
		assertTrue(deleteCount > 0 );
	}
	
	/**
	 * getTargetMemberEmail
	 * getSysdate
	 * getPersonalInfoIdSeq
	 * doWriteMemberDetailInfo
	 * 
	 */
	
	@Test
	public void getTargetMemberEmailTest() {
		String id = "test01";
		String getTargetMemberEmail  = memberDAO.getTargetMemberEmail(id);
		assertNotNull(getTargetMemberEmail);
		assertEquals(getTargetMemberEmail, "hihelloho@nate.com");
	}
	
	@Test
	public void getSysdateTest() {
		String getSysdate  = memberDAO.getSysdate();
		assertNotNull(getSysdate);
	}
	
	//@Test
	public void getPersonalInfoIdSeq() {
		int getPersonalInfoIdSeq = memberDAO.getPersonalInfoIdSeq();
		assertNotNull(getPersonalInfoIdSeq);
		assertTrue(getPersonalInfoIdSeq > 0);
	}
	
	//@Test
	public void doWriteMemberDetailInfo() {
		PersonalInfoReadVO personalInfoReadVO = new PersonalInfoReadVO();
		personalInfoReadVO.setId("testJunitDAO");
		personalInfoReadVO.setMemberId("junitTest");
		personalInfoReadVO.setTargetMemberId("test01");
		personalInfoReadVO.setDescription("desc");
		personalInfoReadVO.setReadDate("Junitdate");
		
		int doWriteMemberDetailInfo = memberDAO.doWriteMemberDetailInfo(personalInfoReadVO);
		assertNotNull(doWriteMemberDetailInfo);
		assertTrue(doWriteMemberDetailInfo > 0);
	}
	
}
