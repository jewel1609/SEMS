package com.ktds.sems.education.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

public class EducationControllerTest {

	@Autowired
	private EducationController educationController;
	
	@Test
	public void viewCategoryPageTest(){
		ModelAndView view = educationController.viewCategoryPage();
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertTrue(viewName == "education/category");
	}
	
	@Test
	public void viewCostPageTest() {
		ModelAndView view = educationController.viewCostPage();
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/cost");
			
			List<CostVO> costList = (List<CostVO>) view.getModelMap().get("costList");
			assertNotNull(costList);
			assertTrue(costList.size() >= 0);
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void deleteEduCostTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setParameter("cdId", "TES1");
		request.setParameter("cdNm", "TES1");

		educationController.insertEduCost(request, response);
		
		ModelAndView view = educationController.deleteEduCost("TES1");
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/cost");
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void viewTimePageTest() {
		ModelAndView view = educationController.viewTimePage();
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/time");
			
			List<TimeVO> timeList = (List<TimeVO>) view.getModelMap().get("timeList");
			assertNotNull(timeList);
			assertTrue(timeList.size() >= 0);
		}
		else {
			fail("view is null");
		}
	}
	
	@Test
	public void deleteEduTimeTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setParameter("cdId", "TES1");
		request.setParameter("cdNm", "TES1");

		educationController.insertEduTime(request, response);
				
		ModelAndView view = educationController.deleteEduTime("TES1");
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/time");
		}
		else {
			fail("view is null");
		}
	}
	
	

}
