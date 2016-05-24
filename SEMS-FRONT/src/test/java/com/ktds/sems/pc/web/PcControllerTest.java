package com.ktds.sems.pc.web;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;

@Transactional
public class PcControllerTest extends SemsTestCase {

	@Autowired
	private PcController pcController;
	
	@Test
	public void viewMyReportedPcListPageTest() {
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		session.setAttribute(Session.MEMBER, memberVO);
		
		ReportedPcSearchVO reportedPcSearchVO = new ReportedPcSearchVO();
		reportedPcSearchVO.setPageNo(0);
		reportedPcSearchVO.setSearchType("reportedPcId");
		reportedPcSearchVO.setSearchKeyword("0");
		
		ModelAndView view = pcController.viewMyReportedPcListPage(session, reportedPcSearchVO);
		
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
		ModelAndView view = pcController.viewReportPcPage(pcId, session, request);
		
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/pc/reportMyPc");
		}
		else {
			fail("fail....");
		}
		
	}
	
}
