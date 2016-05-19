package com.ktds.sems.member.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberServiceTest extends SemsTestCase {

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
	
	@Test
	public void viewMbrTpPageTest(){
		ModelAndView view = memberService.viewMbrTpPage();
		assertNotNull(view.getViewName());
		
		if ( view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/mbrTp");
			
			List<MemberVO> memberList = (List<MemberVO>) view.getModelMap().get("mbrTpVOList");
			assertNotNull(memberList);
			assertTrue(memberList.size() > 0);
		}
	}
	@Test
	public void doInsertMbrTpTest(){
		MbrTpVO newMbrTpVO = new MbrTpVO();
		newMbrTpVO.setCdId("JunitTestCdId");
		newMbrTpVO.setCdNm("JunitTestCdNm");
		
		String checkStr = memberService.doGrdtModify(newMbrTpVO.getCdId(), newMbrTpVO.getCdNm());
		assertNotNull(checkStr);
	}
	
	@Test
	public void doMbrTpModifyTest(){
		MbrTpVO newMbrTpVO = new MbrTpVO();
		newMbrTpVO.setCdId("JunitTestCdId");
		newMbrTpVO.setCdNm("JunitTestCdNm");
		
		String checkStr = memberService.doMbrTpModify(newMbrTpVO.getCdId(), newMbrTpVO.getCdNm());
		assertNotNull(checkStr);
	}
	@Test
	public void doMbrTpDeleteTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("JunitTestCdId");
		
		String checkStr = memberService.doMbrTpDelete(mbrTpVO.getCdId());
		assertNotNull(checkStr);
		assertEquals(checkStr, "redirect:/mbrTpPage");
		
	}
	
	/**
	 * viewCodeMngPage
	 */
	@Test
	public void viewCodeMngPage() {
		
		ModelAndView view = memberService.viewCodeMngPage();
		assertNotNull(view);
	}

	/**
	 * doCodeMngInsert
	 */
	@Test
	public void doCodeMng_A_Insert() {
		CodeMngVO codeMngVO = new CodeMngVO();
		codeMngVO.setCdId("TEST2");
		codeMngVO.setCdNm("테스트2");
		codeMngVO.setCdTp("TE2");
		codeMngVO.setCdTp2("ST2");
		
		String testStr = memberService.doCodeMngInsert(codeMngVO);
		assertNotNull(testStr);
		assertEquals(testStr, "redirect:/codeMngPage");
	}
	
	/**
	 * doCodeMngModify
	 */
	@Test
	public void doCodeMng_B_Modify() {
		CodeMngVO codeMngVO = new CodeMngVO();
		codeMngVO.setCdId("TEST2");
		codeMngVO.setCdNm("테스트2");
		codeMngVO.setCdTp2("ST2");
		
		String testStr = memberService.doCodeMngModify(codeMngVO);
		assertNotNull(testStr);
		assertEquals(testStr, "OK");
	}
	
	/**
	 *  doCodeMngDelete
	 */
	@Test
	public void doCodeMng_C_Delete() {
		String cdId = "TEST2";
		String testStr = memberService.doCodeMngDelete(cdId);
		
		assertNotNull(testStr);
		assertEquals(testStr, "redirect:/codeMngPage");
	}
}
