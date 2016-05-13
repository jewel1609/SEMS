package com.ktds.sems.member.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.member.vo.MemberVO;

public class MemberControllerTest extends SemsTestCase {

	@Autowired
	private MemberController memberController;

	
	@Test
	public void viewModifyPageTest() {
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		member.setId("aaa");
		session.setAttribute("_MEMBER_", member);
		ModelAndView view = memberController.viewModifyPage(session);
				
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName, "member/modifyMyInfo");
			
		}
		else {
			fail("view is null");
		}
	
	}
	
	@Test
	public void doModifyActionTest() {
		MemberVO member = new MemberVO();
		member.setId("aaa");
		member.setPassword("1234");
		member.setName("JUNIT홍길동");
		member.setEmail("JUNIT@naver.com");
		member.setPhoneNumber("010-1234-1234");
		member.setBirthDate("1999-12-01");
		String graduationType = "JUNIT졸업";
		String helCodeName = "초졸";
		BindingResult errors = new BeanPropertyBindingResult(member, "member");
		
		ModelAndView view = memberController.doModifyAction(member, errors, graduationType, helCodeName);
		
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName, "member/myPage");
			
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void viewMyPageTest() {
		
		ModelAndView view = memberController.viewMyPage();
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/myPage");
		}
		else {
			fail("view is null");
		}
	
	}
	
	@Test
	public void viewCheckPasswordPageTest() {
		
		ModelAndView view = memberController.viewCheckPasswordPage();
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(view);
			assertEquals(viewName, "member/checkPassword");
		}
		else {
			fail("view is null");
		}
	
	}

	
}
