package com.ktds.sems.education.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
								  "/applicationContext.xml"
								, "/educationContext.xml"
								, "/memberContext.xml"
								, "/menuContext.xml"
								, "/rootContext.xml"})
public class EducationServiceTest {

	@Autowired
	private EducationService educationService;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
