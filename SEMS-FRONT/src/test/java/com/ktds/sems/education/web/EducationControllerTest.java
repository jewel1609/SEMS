package com.ktds.sems.education.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.member.vo.MemberVO;

@Transactional
public class EducationControllerTest extends SemsTestCase {

	@Autowired
	private EducationController educationController;
	
	@Test
	public void viewRequestRetractionPageTest(){
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		String educationId = "ED-20160516-000185";
		ModelAndView view = educationController.viewRequestRetractionPage(session, educationId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/retraction");
		// 교육이 이미 시작되었을때
		//assertEquals(viewName, "redirect:/member/myPage/course");
	}
	
	// 교육 아이디 ED-20160513-000173에 대한 멤버 test04의 신청 내역이 있고, 교육이 시작 전이어야 제대로된 테스트 진행.
//	@Test
	public void doRequestRetractionActionTest(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "ED-20160513-000173");
		request.setParameter("retractionMessage", "하기싫어요");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String result = educationController.doRequestRetractionAction(request, session);
		assertNotNull(result);
		assertEquals(result, "redirect:/member/myPage");
		// 교육이 이미 시작 했을때
		//assertEquals(result, "redirect:/member/myPage/course");
	}
	
	@Test
	public void showMyQNAListTest() {
		
		QNASearchVO qnaSearchVO = new QNASearchVO();
		MockHttpSession session = new MockHttpSession();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = educationController.showMyQNAList(qnaSearchVO, session);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
		} else {
			fail("fail");
		}
	}

}
