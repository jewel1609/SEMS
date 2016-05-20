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
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherListVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

@Transactional
public class TeacherServiceTest extends SemsTestCase{
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherDAO teacherDAO;

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
	

	public void doDeleteTeacherTest(){
		
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setMemberId("junit");
		teacherVO.setCompanyName("junit");
		teacherVO.setBusinessNumber("1234");
		teacherVO.setAnnual(1);
		EducationHistoryVO teacherEduHistoryVO = new EducationHistoryVO();
		teacherEduHistoryVO.setId("TEH-20160519-000001");
		teacherEduHistoryVO.setMemberId("junit");
		teacherEduHistoryVO.setStartDate("r");
		teacherEduHistoryVO.setEndDate("r");
		teacherEduHistoryVO.setEducationName("r");
		teacherEduHistoryVO.setEducationLocation("r");
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000001");
		projectHistoryVO.setMemberId("junit");
		projectHistoryVO.setStartDate("d");
		projectHistoryVO.setEndDate("d");
		projectHistoryVO.setProjectName("d");
		projectHistoryVO.setProjectLocation("d");
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("TB-20160519-000001");
		teacherBookVO.setMemberId("junit");
		teacherBookVO.setBookName("d");
		teacherBookVO.setBookCompany("d");
		
		ModelAndView view = teacherService.doDeleteTeacher(teacherVO.getMemberId());
		assertEquals(view.getViewName(), "teacher/teaacherList");
	}
	
	@Test
	public void massiveDeleteTeacherTest(){
		
		String[] deleteTeacherIds = {"teacher03", "teacher02"};
		String result = teacherService.massiveDeleteTeacher(deleteTeacherIds);
		assertEquals(result, "redirect:/teacher/teaacherList");
	}
	

}
