package com.ktds.sems.education.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
								  "/applicationContext.xml"
								, "/educationContext.xml"
								, "/memberContext.xml"
								, "/menuContext.xml"
								, "/rootContext.xml"})
public class EducationControllerTest {

	@Autowired
	private EducationController educationController;
	private EducationController educationConroller;
	
	@Test
	public void viewCategoryPageTest(){
		ModelAndView view = educationController.viewCategoryPage();
		assertNotNull(view.getViewName());
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
			assertTrue(costList.size() > 0);
			
		}
	}
	
	@Test
	public void deleteEduCostTest() {
		String cdId = "CSTF";
		
		ModelAndView view = educationController.deleteEduCost(cdId);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/cost");
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
			assertTrue(timeList.size() > 0);
			
		}
	}
	
	@Test
	public void deleteEduTimeTest() {
		String cdId = "TIMD";
		
		ModelAndView view = educationController.deleteEduTime(cdId);
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/education/time");
		}
	}

}
