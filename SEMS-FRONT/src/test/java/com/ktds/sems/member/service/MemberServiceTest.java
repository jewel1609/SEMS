package com.ktds.sems.member.service;

import static org.junit.Assert.*;

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

import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml" })
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;

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
	 * 비밀번호 일치 시 회원정보 수정 페이지로 접근
	 */
	@Test
	public void modifySuccessTest(){
		
		String id = "aaa";
		
		ModelAndView view = memberService.modifySuccess(id);
		if( view != null ) { 
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/modifyMyInfo");
			
			MemberVO member = (MemberVO) view.getModelMap().get("member");
			assertNotNull(member);
			
			List<String> graduationTypeCodeNameList = (List<String>) view.getModelMap().get("graduationTypeList");	
			assertNotNull(graduationTypeCodeNameList);
			assertTrue(graduationTypeCodeNameList.size() >= 0 );
			
			String selectedGraduationTypeCodeName = (String) view.getModelMap().get("selectedGraduationTypeCodeName");
			assertNotNull(selectedGraduationTypeCodeName);
			
	         if ( selectedGraduationTypeCodeName.equals("JUNIT졸업") ) {
	            assertTrue(selectedGraduationTypeCodeName.length() > 0);
	         }
	         else if( selectedGraduationTypeCodeName.equals("재학") ) {
	        	 assertTrue(selectedGraduationTypeCodeName.length() > 0);
	         }
	         else if( selectedGraduationTypeCodeName.equals("휴학") ){
	        	 assertTrue(selectedGraduationTypeCodeName.length() > 0);
	         }
	         else if( selectedGraduationTypeCodeName.equals("졸업예정") ){
	        	 assertTrue(selectedGraduationTypeCodeName.length() > 0);
	         }
	         else {
	        	 fail("selectedGraduationTypeCodeName is null");
	         }
	         
			List<String> highestEducationLevelCodeNameList = (List<String>) view.getModelMap().get("highestEducationLevelCodeNameList");
			assertNotNull(highestEducationLevelCodeNameList);
			assertTrue(highestEducationLevelCodeNameList.size() >= 0 );
			 
			String selectedHighestEducationLevelCodeName = (String) view.getModelMap().get("selectedHighestEducationLevelCodeName");
			
			assertNotNull(selectedHighestEducationLevelCodeName);		
			
			if ( selectedHighestEducationLevelCodeName.equals("초졸") ) {
				 assertTrue(selectedHighestEducationLevelCodeName.length() > 0);
			}
	         else if( selectedHighestEducationLevelCodeName.equals("고졸") ) {
	        	 assertTrue(selectedHighestEducationLevelCodeName.length() > 0);
	         }
	         else if( selectedHighestEducationLevelCodeName.equals("대졸") ){
	        	 assertTrue(selectedHighestEducationLevelCodeName.length() > 0);
	         }		
	         else {
	        	 fail("selectedHighestEducationLevelCodeName is null");
	         }
			
			
			String memberTypeCodeName = (String) view.getModelMap().get("memberTypeCodeName");
			assertNotNull(memberTypeCodeName);	
			
			if ( memberTypeCodeName.equals("일반회원") ) {
				 assertTrue(memberTypeCodeName.length() > 0);
			}
	         else if( memberTypeCodeName.equals("강사") ) {
	        	 assertTrue(memberTypeCodeName.length() > 0);
	         }
	         else if( memberTypeCodeName.equals("관리자") ){
	        	 assertTrue(memberTypeCodeName.length() > 0);
	         }
	         else if( memberTypeCodeName.equals("수강생") ){
	        	 assertTrue(memberTypeCodeName.length() > 0); 
	         }
	         else {
	        	 fail("memberTypeCodeName view is null");
	         }			
		}
	}
	
	/**
	 * 회원정보 수정 
	 */
	@Test
	public void modifyMemberInfoTest(){
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("aaa");
		memberVO.setPassword("1234");
		memberVO.setName("홍길동");
		memberVO.setEmail("ahnshinmi@gmail.com");
		memberVO.setHighestEducationLevel("초졸");
		memberVO.setGraduationType("JUNIT졸업");
		memberVO.setPhoneNumber("010-9271-6775");
		memberVO.setBirthDate("1991-02-26");
		
		BindingResult errors = new BeanPropertyBindingResult(memberVO, "memberInfoForm");	
		String graduationType = "JUNIT졸업";
		String helCodeName = "초졸";
		
		ModelAndView view = memberService.modifyMemberInfo(memberVO, errors, graduationType, helCodeName);
		if( view != null ){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/myPage");
		}
		else {
			fail("modifyMemberInfo view is null");
		}
	}
	
	/**
	 * 계정 잠겨있는지 확인
	 */
	@Test
	public void isModifyAccountLockTest(){
		String sessionId = "aaa";
		boolean isSuccess = !memberService.isModifyAccountLock(sessionId);
		assertTrue(isSuccess);
	}
	
}
