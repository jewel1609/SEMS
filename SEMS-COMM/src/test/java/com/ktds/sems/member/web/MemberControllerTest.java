package com.ktds.sems.member.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.MemberVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberControllerTest extends SemsTestCase {

	@Autowired
	private MemberController memberController;
	
	
	@Test
	public void viewGrdtPageTest(){
		ModelAndView view = memberController.viewGrdtPage();
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
	public void deleteGrdtTest(){
		String cdId = "LEAV";
		
		String checkStr = memberController.doGrdtDelete(cdId);
		assertNotNull(checkStr);
		
	}
	
	@Test
	public void viewMbrTpPageTest(){
		ModelAndView view = memberController.viewMbrTpPage();
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
	public void doMbrTpDeleteTest(){
		String cdId = "JunitTest";		
		String view = memberController.doMbrTpDelete(cdId);
		assertNotNull(view);		
	}
	
	/* Code viewCodeMngPage */
	@Test
	public void viewCodeMngPageTest() {
		
		ModelAndView view = memberController.viewCodeMngPage();
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "member/codeMngPage");
		} else {
			fail("fail");
		}
		
	}
	
	/* Code doCodeMngInsert */
	@Test
	public void doCodeMng_A_Insert() {
		CodeMngVO codeMngVO = new CodeMngVO();
		codeMngVO.setCdId("TEST");
		codeMngVO.setCdNm("테스트");
		codeMngVO.setCdTp("TE");
		codeMngVO.setCdTp2("ST");
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		memberController.doCodeMngInsert(codeMngVO, response);
	}

	/* Code doCodeMngDelete */
	@Test
	public void doCodeMng_B_DeleteTest() {
		String cdId = "TEST";
		String viewName = memberController.doCodeMngDelete(cdId);
		
		assertNotNull(viewName);
		assertEquals(viewName,"redirect:/codeMngPage");
	}

}
