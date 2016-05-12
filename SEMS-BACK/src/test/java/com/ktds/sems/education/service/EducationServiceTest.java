package com.ktds.sems.education.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/fileContext.xml", "/rootContext.xml", "/memberContext.xml" })
public class EducationServiceTest {

	@Autowired
	private EducationService educationeService;

	@Test
	public void getOneEducationForUpdateTest(){
		String educationId = "ED-20160512-000069";
		ModelAndView view = educationeService.getOneEducationForUpdate(educationId);
		String viewName = view.getViewName();
		assertNotNull(view);
		assertTrue(viewName == "education/update");
	}
	
}
