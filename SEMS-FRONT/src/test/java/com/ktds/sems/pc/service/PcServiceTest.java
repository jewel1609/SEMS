package com.ktds.sems.pc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.validator.member.MemberValidator;

@Transactional
public class PcServiceTest extends SemsTestCase {

	@Autowired
	private PcService pcService;
	
	@Autowired
	private MemberService memberService;

	@Before
	public void setUp() {

		// 테스트 멤버 생성
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				MemberVO member = new MemberVO();
				member.setId("testMember01");
				member.setPassword("123qwe!@#qwe");
				member.setName("name");
				member.setEmail("test01@naver.com");
				member.setBirthDate("2016-01-01");
				member.setPhoneNumber("010-6291-1316");
				member.setMemberType("ADM");
				
				String repeatPassword = "123qwe!@#qwe";
				
				BindingResult errors = new BeanPropertyBindingResult(member, "registerForm");
				MemberValidator memberValidator = new MemberValidator();
				memberValidator.validate(member, errors);
				
				MockHttpSession session = new MockHttpSession();
				MockHttpServletResponse response = new MockHttpServletResponse();
				MockHttpServletRequest request= new MockHttpServletRequest();
				
				memberService.addNewMember(member, repeatPassword, errors, session, response, request);
			}
		});
		
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				
			}
		});
	}
	
	@After
	public void tearDown() {
		
		testHelper(new Testable() {
			@Override
			public void preparedTest() {
				
			}
		});
	}
	
	@Test
	public void getMyReportedPcListTest() {
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		session.setAttribute(Session.MEMBER, memberVO);
		
		ReportedPcSearchVO reportedPcSearchVO = new ReportedPcSearchVO();
		reportedPcSearchVO.setPageNo(0);
		reportedPcSearchVO.setSearchType("reportedPcId");
		reportedPcSearchVO.setSearchKeyword("0");
		
		ModelAndView view = pcService.getMyReportedPcList(session, reportedPcSearchVO);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "/myPage/pc/myReportedPcList");
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void viewReportPcPageTest() {
		
		String pcId = "testPcNumber";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		session.setAttribute(Session.MEMBER, memberVO);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelAndView view = pcService.viewReportPcPage(pcId, session, request);
		
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/pc/reportMyPc");
		} 
		else {
			fail("fail....");
		}
		
	}

	@Test
	public void reportProblemPcTest() {
		ReportedPcVO reportedPcVO = new ReportedPcVO();
		reportedPcVO.setReportedCategory("JUnit");
		reportedPcVO.setReportedComment("JUnit test...");
		reportedPcVO.setPcId("testPcNumber");
		
		MockHttpServletRequest request = new MockHttpServletRequest();

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		session.setAttribute(Session.MEMBER, memberVO);
		
		BindingResult errors = new BeanPropertyBindingResult(memberVO, "reportPc");
		
		String reportProblemPc = pcService.reportProblemPc(reportedPcVO, errors, session, request);
		assertNotNull(reportProblemPc);
	}
	
	@Test
	public void viewMyPcPageTest() {
		MockHttpServletRequest request= new MockHttpServletRequest();
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String myPcIp= request.getRemoteHost();
		
		ModelAndView view = pcService.viewMyPcPage(session, request);
		assertNotNull(view);
		assertEquals(view.getViewName(), "myPage/pc/myPc");
	}
	
	@Test
	public void doRegisterMyPcTest1() {
		String educationId = "JunitTest";
		String eduLocation = "JunitTest";
		String usedPcIp="00.00JunitTest";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String result = pcService.doRegisterMyPc(educationId, eduLocation, usedPcIp, session);
		assertNotNull(result);
	}
	
	@Test
	public void doRegisterMyPcTest2() {
		String educationId = "JunitTest";
		String eduLocation = "JunitTest";
		String usedPcIp="10.225.152.167";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testMember01");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String result = pcService.doRegisterMyPc(educationId, eduLocation, usedPcIp, session);
		assertNotNull(result);
	}
	
	@Test
	public void doDeleteMyPcTest() {
		String pcId = "JunitTest";
		String result = pcService.doDeleteMyPc(pcId);
		assertEquals(result, "redirect:/member/myPc");
	}

}
