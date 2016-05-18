package com.ktds.sems.member.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.member.vo.MemberVO;

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

}
