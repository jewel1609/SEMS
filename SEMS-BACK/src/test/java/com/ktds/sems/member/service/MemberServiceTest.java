package com.ktds.sems.member.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/fileContext.xml", "/educationContext.xml", "/memberContext.xml",
		"/rootContext.xml" })
public class MemberServiceTest {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDAO memberDAO;
	
	@Test	
	public void modifyMemberTypeTest(){
		
		String memberType = "일반회원";
		List<String> memberIds = new ArrayList<String>();
		memberIds.add("sosdig1");
		
		ModelAndView view = memberService.modifyMemberType(memberType, memberIds);
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/memberManage/memberList");
			
			String isModifySuccess = (String) view.getModelMap().get("isModifySuccess");
			assertNotNull(isModifySuccess );
			assertEquals(isModifySuccess , "OK");
		}
		else{
			fail("Fail...");
		}
		
	}

	@Test	
	public void modifyMemberTypeTestErrorCaseMemberType(){
		
		List<String> deleteMemberId = new ArrayList<String>();
		deleteMemberId.add("sosdig1");
		ModelAndView view = memberService.modifyMemberType( null , deleteMemberId);
		
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/memberManage/memberList");
			
			String isModifySuccess = (String) view.getModelMap().get("isModifySuccess");
			assertNotNull(isModifySuccess );
			assertEquals(isModifySuccess , "NO");
		}
		else{
			fail("Fail....");
		}		
		
	}

	@Test	
	public void modifyMemberTypeTestErrorCaseMemberIds(){
		
		String memberType = "일반회원";
		ModelAndView view = memberService.modifyMemberType( memberType , null);
		
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/memberManage/memberList");
			
			String isModifySuccess = (String) view.getModelMap().get("isModifySuccess");
			assertNotNull(isModifySuccess );
			assertEquals(isModifySuccess , "NO");
		}
		else{
			fail("Fail....");
		}		
		
	}
	
	@Test
	public void addNewMemberTest() {
		
		MemberVO member = new MemberVO();
		MemberVO sessionMember = new MemberVO();
		BindingResult errors = new BeanPropertyBindingResult(member, "member");
		MockHttpSession session = new MockHttpSession();
		
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		
		member.setId("jewel1324");
		member.setPassword("asdfasdf123!");
		member.setName("JUNIT홍길동");
		member.setEmail("JUNIT@naver.com");
		member.setPhoneNumber("010-1234-1234");
		member.setBirthDate("1999-12-01");
		member.setMemberType("일반회원");
		member.setUniversityName("A대학");
		member.setMajorName("B학과");
		member.setHighestEducationLevel("고졸");
		
		ModelAndView view = memberService.addNewMember(member, errors, session);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName, "member/registerStudent");
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void getHighestEducationLevelCodeNamesTest() {
		
		List<String> helList = memberService.getHighestEducationLevelCodeNames();
		
		if ( helList != null ) {
			assertNotNull(helList);
		}
		else {
			fail("fail");
		}
	}
	
	@Test
	public void getGraduationTypeTest() {
		
		List<String> graduationList = memberService.getGraduationType();
		
		if ( graduationList != null ) {
			assertNotNull(graduationList);
		}
		else {
			fail("fail");
		}
	}
	
	@Test
	public void getMemberTypeCodeNameListTest() {
		
		List<String> memberTypeCodeNameList = memberService.getMemberTypeCodeNameList();
		
		if ( memberTypeCodeNameList != null ) {
			assertNotNull(memberTypeCodeNameList);
		}
		else {
			fail("fail");
		}
	}
	
	
	
}
