package com.ktds.sems.teacher.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import com.ktds.sems.SemsTestCase;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.TeacherListVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.common.Session;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.validator.teacher.TeacherVOValidator;

public class TeacherControllerTest extends SemsTestCase {

	@Autowired
	private TeacherController teacherController;
	@Autowired
	private TeacherDAO teacherDAO;

	@Test
	public void viewDetailPageTest() {
		String memberId = "teacher01";
		ModelAndView view = teacherController.viewDetailPage(memberId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertEquals(viewName, "teacher/detail");
	}
	
	@Test
	public void doTeacherInfoModifyActionTest(){
		
		// 1. TeacherVO
		TeacherVO teacherVO = new TeacherVO();
		
		teacherVO.setAnnual(1); 
		teacherVO.setMemberId("teacher01");
		teacherVO.setBusinessNumber("JUNIT...TT^TT");
		teacherVO.setCompanyName("JUNIT...TT^TT");
		teacherVO.setName("JUNIT...TT^TT");
		
		List<TeacherBookVO> teacherBookList = new ArrayList<TeacherBookVO>();
		TeacherBookVO teacherBookVO = new TeacherBookVO();
		teacherBookVO.setId("TB-20160519-000028");
		teacherBookList.add(teacherBookVO);
		teacherVO.setTeacherBookList(teacherBookList);
		
		List<ProjectHistoryVO> projectHistoryList = new ArrayList<ProjectHistoryVO>();
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000017");
		projectHistoryList.add(projectHistoryVO);
		teacherVO.setProjectHistoryList(projectHistoryList);
		
		List<EducationHistoryVO> educationHistoryList = new ArrayList<EducationHistoryVO>();
		EducationHistoryVO educationHistoryVO = new EducationHistoryVO();
		educationHistoryVO.setId("TEH-20160519-000016");
		educationHistoryList.add(educationHistoryVO);
		teacherVO.setEducationHistoryList(educationHistoryList);
		
		// 2. Errors
		BindingResult errors = new BeanPropertyBindingResult(teacherVO, "teacherUpdateForm");
		TeacherVOValidator validator = new TeacherVOValidator();
		validator.validate(teacherVO, errors);
		
		// 3. Session
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(Session.MEMBER_TYPE, "ADM");
		
		// 파라미터 3개 확인
		ModelAndView view = teacherController.doTeacherInfoModifyAction(teacherVO, errors, session);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
		} else {
			fail("fail");
		}
		}
	@Test
	public void viewteaacherListPageTest(){
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		int pageNo = 0;
		ModelAndView view = teacherController.viewteaacherListPage(pageNo, request);
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
	
	@Test
	public void doDeleteTeacherTest(){
		String deleteTeacherId = "skawnsgh1234";
		ModelAndView view = teacherController.doDeleteTeacher(deleteTeacherId);
		assertEquals(view.getViewName(), "/teacher/detail/skawnsgh1234");
		
	}
	
	@Test
	public void massiveDeleteTeacherTest(){
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		String[] deleteTeacherIds = {"teacher03", "teacher02"};
		request.setParameter("deleteTeacherId", deleteTeacherIds);
		String result = teacherController.massiveDeleteTeacher(request);
		assertEquals(result, "redirect:/teacher/teaacherList");
	}
}
