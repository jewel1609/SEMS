package com.ktds.sems.member.web;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.MemberListVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberVO;

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
	
	@Test
	public void registerNewMemberTest() {
		
		MemberVO member = new MemberVO();
		MemberVO sessionMember = new MemberVO();
		BindingResult errors = new BeanPropertyBindingResult(member, "member");
		MockHttpSession session = new MockHttpSession();
		
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		
		member.setId("registerTest01");
		member.setPassword("asdfasdf123!");
		member.setName("JUNIT홍길동");
		member.setEmail("JUNIT@naver.com");
		member.setPhoneNumber("010-1234-1234");
		member.setBirthDate("1999-12-01");
		member.setMemberType("수강생");
		member.setUniversityName("A대학");
		member.setMajorName("B학과");
		member.setHighestEducationLevel("고졸");
		
		ModelAndView view = memberController.registerNewMember(member, errors, session);
		
		if ( view != null ) {
			assertNotNull(view);
		}
		else {
			fail("fail");
		}
	}
	
	@Test
	public void viewMemberListPageTest(){
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberController.viewMemberListPage(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}
	}
	
	@Test
	public void viewMemberListPageTestSearchTypeId() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("test");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberController.viewMemberListPage(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getId().contains("test"));
			}

			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}
	
	
	@Test
	public void viewMemberListPageTestSearchTypeMemberType() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("강사");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberController.viewMemberListPage(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getMemberType().equals("강사"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}
	
	
	@Test
	public void viewMemberListPageTestSearchTypeIsRgsn() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("Y");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberController.viewMemberListPage(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getIsResign().equals("Y"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}	
	
	@Test
	public void viewMemberListPageTestSearchTypeConnLock() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("Y");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		ModelAndView view = memberController.viewMemberListPage(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getIsAccountLock().equals("Y"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}	
	@Test
	public void viewMemberListPageTestSearchTypeModLock() { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		int pageNo = 0;
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("Y");
		
		ModelAndView view = memberController.viewMemberListPage(memberSearchVO, pageNo);
		assertNotNull(view);

		if ( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/memberListPage");
			
			MemberListVO memberListVO = (MemberListVO) view.getModelMap().get("memberListVO");
			assertNotNull(memberListVO);
			for (MemberVO member : memberListVO.getMemberList()) {
				assertTrue(member.getIsModifyLock().equals("Y"));
			}
			
			memberSearchVO = (MemberSearchVO) view.getModelMap().get("memberSearchVO");
			assertNotNull(memberSearchVO);
			
			List<MemberVO> memberTypeList = (List<MemberVO>) view.getModelMap().get("memberTypeList");
			assertNotNull(memberTypeList );
			assertTrue(memberTypeList.size() > 0);
			
			List<String> searchTypeList = (List<String>) view.getModelMap().get("searchTypeList");
			assertNotNull(searchTypeList );
			assertTrue(searchTypeList.size() > 0);
			
		}
		else{
			fail("Fail...");
		}		
	}		

}
