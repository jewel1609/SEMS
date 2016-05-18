package com.ktds.sems.member.biz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/fileContext.xml", "/rootContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberBizTest  {

	@Autowired
	private MemberBiz memberBiz;
	
	@Test
	public void changePasswordTest () {
		MemberVO member = new MemberVO();
		member.setId("sosdig1");
		member.setPassword("4c5b7ab6a121aae1acda84fc71ed4b135e9f8eb7f1a25013515845e9c7ddc9f8");
		member.setSalt("9ca0645b12e961ac");
		boolean isSuccess = memberBiz.changePassword(member);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void getMemberTypeTest () {
		List<String> memberTypeList = memberBiz.getMemberType();
		assertNotNull(memberTypeList);
	}
	
	@Test
	public void getMemberTypeCodeTest () {
		String memberType = "일반회원";
		String memberTypeCode = memberBiz.getMemberTypeCode(memberType);
		
		assertNotNull(memberTypeCode);
		assertEquals(memberTypeCode, "MBR");
	}
	
	@Test
	public void modifyMemberTypeByIdTest () {
		Map<String, String> modifyMemberType = new HashMap<String, String>();
		modifyMemberType.put("memberId", "sosdig1");
		modifyMemberType.put("memberTypeCode", "MBR");
		boolean isSuccess = memberBiz.modifyMemberTypeById(modifyMemberType);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isDuplicationIdTest(){
		String id = "test01";
		boolean isSuccess = memberBiz.isDuplicationId(id);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isVerifyPasswordTest() {
		String password = "asdfasdf123!";
		boolean isSuccess = memberBiz.isVerifyPassword(password);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isVerifyEmailTest() {
		String email = "test01@naver.com";
		boolean isSuccess = memberBiz.isVerifyEmail(email);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isExistEmailTest() {
		String email = "teacher01@naver.com";
		boolean isSuccess = memberBiz.isExistEmail(email);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isVerifyPhoneNumberTest() {
		String phoneNumber = "010-1234-1234";
		boolean isSuccess = memberBiz.isVerifyPhoneNumber(phoneNumber);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void getHighestEducationLevelCodeNames() {
		List<String> helCodeNameList = memberBiz.getHighestEducationLevelCodeNames();
		
		assertNotNull(helCodeNameList);
	}
	
	@Test
	public void getGraduationTypeTest() {
		List<String> graduationTypeList = memberBiz.getGraduationType();
		
		assertNotNull(graduationTypeList);
	}
	
	@Test
	public void getHelCodeIdTest() {
		String highestEducationLevel = "대졸";
		String helCodeId = memberBiz.getHelCodeId(highestEducationLevel);
		
		assertNotNull(helCodeId);
		assertEquals(helCodeId, "UNIV");
	}
	
	@Test
	public void getGraduationTypeCodeIdTest() {
		String graduationType = "졸업예정";
		String grdtCodeId = memberBiz.getGraduationTypeCodeId(graduationType);
		
		assertNotNull(grdtCodeId);
		assertEquals(grdtCodeId, "EXPT");
	}
	
	@Test
	public void getMemberTypeCodeNameListTest() {
		List<String> memberTypeCodeNameList = memberBiz.getMemberTypeCodeNameList();
		
		assertNotNull(memberTypeCodeNameList);
	}
	
	@Test
	public void getMemberTypeCodeIdTest() {
		String memberType = "강사";
		String memberTypeCodeId = memberBiz.getMemberTypeCodeId(memberType);
		
		assertNotNull(memberTypeCodeId);
		assertEquals(memberTypeCodeId, "TR");
	}
	
	
	
	
	
	
	
	
}
