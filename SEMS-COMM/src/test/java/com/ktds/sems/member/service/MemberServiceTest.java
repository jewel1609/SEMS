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

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml","/menuContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml" })
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	/**
	 * 로그인
	 */
	@Test
	public void loginTest() {

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setPassword("123qwe!@#qwe");

		BindingResult errors = new BeanPropertyBindingResult(memberVO, "loginForm");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();

		String checkStr = memberService.login(memberVO, errors, session, request);
		assertNotNull(checkStr);
	}
	
	@Test
	public void viewGrdtPageTest(){
		ModelAndView view = memberService.viewGrdtPage();
		assertNotNull(view.getViewName());
		
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/grdtPage");
			
			List<MemberVO> memberList = (List<MemberVO>) view.getModelMap().get("grtdTpList");
			assertNotNull(memberList);
			assertTrue(memberList.size() > 0);
		}
	}
	@Test
	public void doGrdtInsertTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("JunitTestCdId");
		grdtTpVO.setCdNm("JunitTestCdNm");
		
		String checkStr = memberService.doGrdtModify(grdtTpVO.getCdId(), grdtTpVO.getCdNm());
		assertNotNull(checkStr);
	}
	
	@Test
	public void doGrdtModifyTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("JunitTestCdId");
		grdtTpVO.setCdNm("JunitTestModifyCdNm");
		
		String checkStr = memberService.doGrdtModify(grdtTpVO.getCdId(), grdtTpVO.getCdNm());
		assertNotNull(checkStr);
	}
	@Test
	public void doGrdtDeleteTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("JunitTestCdId");
		
		String checkStr = memberService.doGrdtDelete(grdtTpVO.getCdId());
		assertNotNull(checkStr);
		assertEquals(checkStr, "redirect:/grdtPage");
		
	}
}
