package com.ktds.sems.teacher.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

@Transactional
public class TeacherBizTest extends SemsTestCase{

	@Autowired
	private TeacherBiz teacherBiz;
	
	@Autowired
	private TeacherDAO teacherDAO;
	
	@Test
	public void getTeacherInfoTest(){
		String memberId = "teacher01";
		TeacherVO teacherVO = teacherBiz.getTeacherInfo(memberId);
		assertNotNull(teacherVO);
	}
	
	@Test
	public void getTeacherEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationHistoryVO> teacherEducationHistories = teacherBiz.getTeacherEducationHistory(memberId);
		assertNotNull(teacherEducationHistories);
	}
	
	@Test
	public void getTeacherProjectHistoryTest(){
		String memberId = "teacher01";
		List<ProjectHistoryVO> teacherProjectHistories = teacherBiz.getTeacherProjectHistory(memberId);
		assertNotNull(teacherProjectHistories);
	}
	
	@Test
	public void getTeacherBookTest(){
		String memberId = "teacher01";
		List<TeacherBookVO> teacherBooks = teacherBiz.getTeacherBook(memberId);
		assertNotNull(teacherBooks);
	}
	
	@Test
	public void getEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationVO> educationHistories = teacherBiz.getEducationHistory(memberId);
		assertNotNull(educationHistories);
	}
	
	@Test
	public void getTeacherEducationGradeTest(){
		String memberId = "teacher01";
		double grade = teacherBiz.getTeacherEducationGrade(memberId);
		assertNotNull(grade);
	}
	
	@Test
	public void getTotalTeacherCountTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		String searchType = "";
		String searchKeyword ="";
		
		Map<String,String> searchInfo = new HashMap<String,String>();
		searchInfo.put("searchType", searchType);
		searchInfo.put("searchKeyword", searchKeyword);
		
		int totalCount = teacherBiz.getTotalTeacherCount(request);
		assertTrue(totalCount >= 0);
	}
	@Test
	public void getAllTeacherTest() {
		TeacherSearchVO searchVO = new TeacherSearchVO();
		searchVO.setSearchKeyword("");
		searchVO.setSearchType("");
		searchVO.setMemberId("");
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		List<TeacherVO> techerList = teacherBiz.getAllTeacher(searchVO);
		assertNotNull(techerList);
		if (techerList != null){
			assertTrue(techerList.size() >0);
		}
		else{
			fail("Fail...");
		}
	}
	

	public void doDeleteTeacherTest(){
		
		String memberId = teacherDAO.getOneTeacherId();
		boolean result = teacherBiz.doDeleteTeacher(memberId);
		assertTrue(result);
	}
	

	public void doDeleteProjectHistoryTest(){
		
		String memberId = teacherDAO.getOneTeacherId();
		boolean result = teacherBiz.doDeleteProjectHistory(memberId);
		assertTrue(result);
	}
	

	public void doDeleteEducationHistoryTest(){
		
		String memberId = teacherDAO.getOneTeacherId();
		boolean result = teacherBiz.doDeleteEducationHistory(memberId);
		assertTrue(result);
	}
	

	public void doDeleteTeacherBookTest(){
		
		String memberId = teacherDAO.getOneTeacherId();
		boolean result = teacherBiz.doDeleteTeacherBook(memberId);
		assertTrue(result);
	}
	
	
	
	
}
