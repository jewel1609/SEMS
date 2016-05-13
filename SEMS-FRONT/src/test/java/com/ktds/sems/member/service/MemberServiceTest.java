package com.ktds.sems.member.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;
import com.ktds.sems.validator.member.MemberValidator;

public class MemberServiceTest extends SemsTestCase {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDAO memberDAO;

	/**
	 * 로그인
	 */
	@Test
	public void loginTest() {

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo");
		memberVO.setPassword("123qwe!@#qwe");
		
		BindingResult errors = new BeanPropertyBindingResult(memberVO, "loginForm");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		
		String checkStr = memberService.login(memberVO, errors, session, request);
		assertNotNull(checkStr);
	}

	/**
	 * 회원탈퇴 신청 시 uuid 삽입
	 */
	@Test
	public void insertUuidForResignTest() {
		
		MockHttpSession session = new MockHttpSession();
		MemberVO member = new MemberVO();
		
		member.setId("JunitResign");
		
		session.setAttribute("_MEMBER_", member);
		String checkStr = memberService.insertUuidForResign(session);
		assertNotNull(checkStr);
	}
	
	/**
	 * 탈퇴 신청한 회원에게 이메일 보내기
	 * 이메일이 보내지지 않아 Junit에러 발생
	 */
	@Test
	public void sendEmailForResignTest() {
		
		ModelAndView view = new ModelAndView();
		MemberVO member = new MemberVO();
		MockHttpSession session = new MockHttpSession();
		
		member.setId("test02");
		session.setAttribute("_MEMBER_", member);
		
		String uuid = "This is uuid sample";
		
		view = memberService.sendEmailForResign(session, uuid);
		assertNotNull(view.getViewName());
	}
	
	/**
	 * 탈퇴
	 */
	@Test
	public void loginForResignTest() {
		
		ModelAndView view = new ModelAndView();
		String resignCode = "This is resignCode sample";
		String id = "This is id sample";
		
		view = memberService.loginForResign(resignCode, id);
		assertNotNull(view);
	}
	
	@Test
	public void viewMyPageMenuTest() {
		ModelAndView view = memberService.viewMyPageMenu();
		assertNotNull(view);
		
		if ( view != null ) {
			List<MenuManageVO> menuList = (List<MenuManageVO>) view.getModelMap().get("menuList");
			assertNotNull(menuList);
			assertTrue(menuList.size() > 0);
		}
		else {
			fail("list is null");
		}
	}
	
}
