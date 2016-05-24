package com.ktds.sems.pc.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;

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
}
