package com.ktds.sems.pc.web;

import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;

@Transactional
public class PcControllerTest extends SemsTestCase {

	/*
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
	*/
	
}
