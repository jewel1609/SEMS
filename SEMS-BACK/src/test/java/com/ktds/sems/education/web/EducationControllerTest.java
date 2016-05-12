package com.ktds.sems.education.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/fileContext", "/rootContext.xml" })
public class EducationControllerTest {
	
	@Autowired
	private EducationController educationController;
	
	@Test
	public void viewEduWritePageTest(){
		
		ModelAndView view = educationController.viewEduWritePage();
		assertNotNull(view);
		
		if ( view != null){
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
		} else {
			fail("view is null");
		}
	}
	
	
}
