package com.ktds.sems.teacher.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.TeacherListVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;

@Transactional
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

	@Test
	public void getAllTeaacherListTest(){
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		int pageNo = 0;

		
		ModelAndView view = teacherService.getAllTeaacherList(pageNo, request);
		assertNotNull(view);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "teacher/teaacherList");
			
			TeacherSearchVO searchVO = (TeacherSearchVO) view.getModelMap().get("searchVO");
			assertNotNull(searchVO);
			
			TeacherListVO searchedListVO = (TeacherListVO) view.getModelMap().get("searchedListVO");
			assertNotNull(searchedListVO);
		}
		else{
			fail("Fail...");
		}
		
	}
	

	public void doDeleteTeacher(){
		String deleteTeacherId = "skawnsgh1234";
		ModelAndView view = teacherService.doDeleteTeacher(deleteTeacherId);
		assertEquals(view.getViewName(), "/teacher/detail/skawnsgh1234");
		
	}
	

	public void massiveDeleteTeacherTest(){
		
		String[] deleteTeacherIds = {"teacher03", "teacher02"};
		String result = teacherService.massiveDeleteTeacher(deleteTeacherIds);
		assertEquals(result, "redirect:/teacher/teaacherList");
	}
	

}
