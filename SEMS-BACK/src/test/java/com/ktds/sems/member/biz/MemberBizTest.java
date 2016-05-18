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
}
