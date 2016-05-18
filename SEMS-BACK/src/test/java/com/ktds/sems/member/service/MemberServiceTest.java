package com.ktds.sems.member.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.dao.MemberDAO;

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
}
