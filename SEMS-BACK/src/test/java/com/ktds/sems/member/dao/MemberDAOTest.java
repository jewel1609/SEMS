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

}
