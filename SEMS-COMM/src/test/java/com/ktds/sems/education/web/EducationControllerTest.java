package com.ktds.sems.education.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.service.EducationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
								  "/applicationContext.xml"
								, "/educationContext.xml"
								, "/memberContext.xml"
								, "/menuContext.xml"
								, "/rootContext.xml"})
public class EducationControllerTest {

	@Autowired
	private EducationService educationService;
	
	@Test
	public void viewCategoryPageTest(){
		ModelAndView view = educationService.viewCategoryPage();
		assertNotNull(view.getViewName());
	}

}
