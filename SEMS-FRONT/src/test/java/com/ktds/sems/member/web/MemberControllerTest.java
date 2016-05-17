package com.ktds.sems.member.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.validator.member.MemberValidator;

import kr.co.hucloud.utilities.web.Paging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml"})
public class MemberControllerTest extends SemsTestCase {

	@Autowired
	private MemberController memberController;
	@Autowired
	private MemberDAO memberDAO;

	
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
	
	@Test
	public void addNewTeacherMemberTest() {
		MemberVO member = new MemberVO();
		member.setId("JunitId1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("TR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		MockHttpSession session = new MockHttpSession();
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"redirect:/");
			
			assertTrue(memberDAO.isExistId(member.getId()) != null);
			
			memberDAO.delectJunitTestMember(member.getId());
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void addNewStudentMemberTest() {
		MemberVO member = new MemberVO();
		member.setId("JunitId1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		MockHttpSession session = new MockHttpSession();
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"redirect:/");
			
			assertTrue(memberDAO.isExistId(member.getId()) != null);
			memberDAO.delectJunitTestMember(member.getId());
		}
		else {
			fail("Fail...");
		}
	}

	@Test
	public void doRegisterMemberActionTestErrorCaseExistSession() {
		MemberVO member = new MemberVO();
		member.setId("JunitId1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		MemberVO sessionMember = new MemberVO();
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("_MEMBER_", sessionMember);
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registErrorPage");
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseId() {
		MemberVO member = new MemberVO();
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getId() == null);
			
			assertTrue(errors.getErrorCount() == 1);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCasePassword() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setName("Junit");
		String repeatPasswrod = ("JunitPassword1@");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getPassword() == null);
			
			assertTrue(errors.getErrorCount() == 1);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseName() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod,session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getName() == null);
			
			assertTrue(errors.getErrorCount() == 1);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseEmail() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getEmail() == null);
			
			assertTrue(errors.getErrorCount() == 1);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseHighestEducationLevel() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			String isEmptyHighestEducationLevel = (String) view.getModelMap().get("isEmptyHighestEducationLevel");
			assertNotNull(isEmptyHighestEducationLevel);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getHighestEducationLevel() == null);
			
			assertTrue(memberDAO.isExistId(member.getId()) == null);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseUniversityName() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			String isEmptyUniversityName = (String) view.getModelMap().get("isEmptyUniversityName");
			assertNotNull(isEmptyUniversityName); 
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getUniversityName() == null);
			
			assertTrue(memberDAO.isExistId(member.getId()) == null);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseMajorName() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			String isEmptyMajorName = (String) view.getModelMap().get("isEmptyMajorName");
			assertNotNull(isEmptyMajorName);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getMajorName() == null);
			
			assertTrue(memberDAO.isExistId(member.getId()) == null);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseGraduationType() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			String isEmptyGraduationType = (String) view.getModelMap().get("isEmptyGraduationType");
			assertNotNull(isEmptyGraduationType);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getGraduationType() == null);
			
			assertTrue(memberDAO.isExistId(member.getId()) == null);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseBirthDate() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getBirthDate() == null);
			
			assertTrue(errors.getErrorCount() == 1);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCasePhoneNumber() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			assertTrue(viewMember.getPhoneNumber() == null);
			
			assertTrue(errors.getErrorCount() == 1);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseMemberType() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registErrorPage");
			
			assertTrue(memberDAO.isExistId(member.getId()) == null);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseNotEqualRepeatPasswrod() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		String repeatPasswrod = ("Junit");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, repeatPasswrod, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			String isEqualsPassword = (String) view.getModelMap().get("isEqualsPassword");
			assertNotNull(isEqualsPassword);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			
			assertTrue(memberDAO.isExistId(member.getId()) == null);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doRegisterMemberActionTestErrorCaseEmptyRepeatPasswrod() {
		MemberVO member = new MemberVO();
		member.setId("JunitError1");
		member.setPassword("JunitPassword1@");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("대졸");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("졸업");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		
		BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
		MockHttpSession session = new MockHttpSession();
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(member, errors);
		
		ModelAndView view = memberController.doRegisterMemberAction(member, errors, null, session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName,"member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
			
			String isEmptyRepeatPassword = (String) view.getModelMap().get("isEmptyRepeatPassword");
			assertNotNull(isEmptyRepeatPassword);
			
			MemberVO viewMember = (MemberVO) view.getModelMap().get("member");
			assertNotNull(viewMember);
			
			assertTrue(memberDAO.isExistId(member.getId()) == null);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void registerPolicy () {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		
		String viewName = memberController.viewRegisterPage(session);
		assertNotNull(viewName);

		if ( viewName != null ) {
			assertEquals(viewName, "member/registerPolicy");
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void registerPolicyErrorCaseExistSession () {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		session.setAttribute("_MEMBER_", sessionMember);
		
		String viewName = memberController.viewRegisterPage(session);
		assertNotNull(viewName);
		
		if ( viewName != null ) {
			assertEquals(viewName, "member/registErrorPage");
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void registerStudent () {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		
		ModelAndView view = memberController.viewRegisterStudentPage(session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/registerStudent");
			
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModel().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);

			List<String> graduationTypeList = (List<String>) view.getModel().get("graduationTypeList");
			assertNotNull(graduationTypeList);
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void registerStudentErrorCaseExistSession () {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		session.setAttribute("_MEMBER_", sessionMember);
		
		ModelAndView view = memberController.viewRegisterStudentPage(session);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/registErrorPage");
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void registerTeacher () {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		
		String viewName = memberController.viewRegisterTeacherPage(session);
		assertNotNull(viewName);
		
		if ( viewName != null ) {
			assertEquals(viewName, "member/registerTeacher");
		}
		else {
			fail("Fail...");
		}
	}
	
	@Test
	public void registerTeacherErrorCaseExistSession () {
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		session.setAttribute("_MEMBER_", sessionMember);
		
		String viewName = memberController.viewRegisterTeacherPage(session);
		assertNotNull(viewName);
		
		if ( viewName != null ) {
			assertEquals(viewName, "member/registErrorPage");
		}
		else {
			fail("Fail...");
		}
	}

	/**
	 * 나의 교육 이력 보기
	 */
	@Test
	public void viewEducationHistroyPage() {

		MemberVO memberVO = new MemberVO();
		memberVO.setId("test01");

		int pageNo = 0;
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER, memberVO);

		ModelAndView view = memberController.viewEducationHistroyPage(pageNo, session);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/educationHistory");
			
			EducationHistoryListVO educationHistoryListVO = (EducationHistoryListVO) view.getModelMap().get("educationHistoryListVO");
			assertNotNull(educationHistoryListVO);
			
			List<EducationHistoryVO> educationHistoryList = educationHistoryListVO.getEducationHistoryList();
			assertNotNull(educationHistoryList);
			assertTrue(educationHistoryList.size() > 0);
			
			Paging paging = educationHistoryListVO.getPaging();
			assertNotNull(paging);
			assertTrue(paging.getTotalArticleCount() > 0);
			
		} else {
			fail("fail");
		}

	}
	
}
