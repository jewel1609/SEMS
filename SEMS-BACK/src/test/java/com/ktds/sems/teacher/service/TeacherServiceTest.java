package com.ktds.sems.teacher.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;

public class TeacherServiceTest extends SemsTestCase{
	
	@Autowired
	private TeacherService teacherService;
	
	@Test
	public void viewDetailTest(){
		String memberId = "teacher01";
		ModelAndView view = teacherService.viewDetail(memberId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "teacher/detail");
	}


}
