package com.ktds.sems.member.web;

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
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/fileContext.xml", "/memberContext.xml",
		"/rootContext.xml" })
public class MemberControllerTest {
	
	@Autowired
	private MemberController memberController;
	@Autowired
	private MemberDAO memberDAO;
	

	@Test
	public void doModifyMemberTypeActionTest(){
		String memberType = "일반회원";
		List<String> deleteMemberId = new ArrayList<String>();
		deleteMemberId.add("sosdig1");
		
		ModelAndView view = memberController.doModifyMemberTypeAction(memberType, deleteMemberId);
		if( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/memberManage/memberList");
			
			String isModifySuccess = (String) view.getModelMap().get("isModifySuccess");
			assertNotNull(isModifySuccess );
			assertEquals(isModifySuccess , "OK");
		}
		else{
			fail("Fail....");
		}
	}
	
	@Test
	public void doModifyMemberTypeActionTestErrorCaseMemberType(){
		
		List<String> deleteMemberId = new ArrayList<String>();
		deleteMemberId.add("sosdig1");
		ModelAndView view = memberController.doModifyMemberTypeAction( null , deleteMemberId);
		
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
	public void doModifyMemberTypeActionTestErrorCaseMemberIds(){
		
		String memberType = "일반회원";
		ModelAndView view = memberController.doModifyMemberTypeAction( memberType , null);
		
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
	public void viewMemberManagePageTest() {
		
		ModelAndView view = memberController.viewMemberManagePage();
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName, "member/memberManagePage");
		}
		else {
			fail("view is null");
		}
		
	}
	
	@Test
	public void viewAddMemberPageTest() {
		
		ModelAndView view = memberController.viewAddMemberPage();
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName, "member/addMemberPage");
		}
		else {
			fail("view is null");
		}
		
	}
	
	
}
