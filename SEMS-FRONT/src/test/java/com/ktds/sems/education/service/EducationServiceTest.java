package com.ktds.sems.education.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.education.biz.EducationBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml" })
public class EducationServiceTest {

	@Autowired
	private EducationBiz educationBiz;
	
	@Test
	public void doCancelEducation() {
		
		String educationId = "ED-20160510-000004";
		String loginMember = "cocomo12";
		
		//성공하면 result에 true
		boolean result = educationBiz.doCancelEducation(educationId , loginMember);
	
		// 실패하면 에러
		// True 면 에러
		assertTrue(!result);
		
	}
	
}
