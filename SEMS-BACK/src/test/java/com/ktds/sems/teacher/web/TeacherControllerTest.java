package com.ktds.sems.teacher.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;

public class TeacherControllerTest extends SemsTestCase{

	@Autowired
	private TeacherController teacherController;
	
	@Test
	public void viewDetailPageTest(){
		String memberId = "teacher02";
		ModelAndView view = teacherController.viewDetailPage(memberId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertEquals(viewName, "teacher/detail");
	}

}
