package com.ktds.sems.pc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;

@Transactional
public class PcServiceTest extends SemsTestCase {

	@Autowired
	private PcService pcService;

	@Test
	public void getMyReportedPcListTest() {
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
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
		String pcId = "1";
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
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
		reportedPcVO.setReportedCategory("computer");
		reportedPcVO.setReportedComment("JUnit test...");
		reportedPcVO.setPcId("1");
		
		MockHttpServletRequest request = new MockHttpServletRequest();

		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		session.setAttribute(Session.MEMBER, memberVO);
		
		BindingResult errors = new BeanPropertyBindingResult(memberVO, "reportPc");
		
		String reportProblemPc = pcService.reportProblemPc(reportedPcVO, errors, session, request);
		assertNotNull(reportProblemPc);
	}

}
