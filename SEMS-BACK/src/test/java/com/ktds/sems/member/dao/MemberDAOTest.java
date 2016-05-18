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

import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/fileContext.xml", "/rootContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberDAOTest  {

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
	
	
	
	
	
	
	
	
	
}
