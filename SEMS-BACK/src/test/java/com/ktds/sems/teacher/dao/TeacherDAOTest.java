package com.ktds.sems.teacher.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;

public class TeacherDAOTest extends SemsTestCase {

	@Autowired
	private TeacherDAO teacherDAO;
	
	@Test
	public void getTotalTeacherCountTest() {

		Map<String,String> searchInfo = new HashMap<String,String>();
	
		searchInfo.put("searchType", "1");
		searchInfo.put("searchKeyword", "강사1");
		
		int totalCount = teacherDAO.getTotalTeacherCount(searchInfo);
		assertNotNull(totalCount);
		
	}
	@Test
	public void getAllTeacherTest() {
		
		TeacherSearchVO searchVO = new TeacherSearchVO();
		searchVO.setSearchKeyword("");
		searchVO.setSearchType("");
		searchVO.setMemberId("");
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		searchVO.setPageNo(10);
		
		List<TeacherVO> teacherList = teacherDAO.getAllTeacher(searchVO);
		
		assertNotNull(teacherList);
		if ( teacherList != null){
			assertTrue(teacherList.size() >0);
		}else{
			fail("Fail...");
		}
	}
	@Test
	public void getSearchedEducationCountTest() {
		
	}
	
	@Test
	public void doDeleteTeacherTest() {
		
		String deleteTeacherId = teacherDAO.getOneTeacherId();
		int result = teacherDAO.doDeleteTeacher(deleteTeacherId);
		assertNotNull(result);
	}
	@Test
	public void doDeleteProjectHistoryTest() {
		String deleteTeacherId = teacherDAO.getOneTeacherId();
		int result = teacherDAO.doDeleteProjectHistory(deleteTeacherId);
		assertNotNull(result);
	}
	@Test
	public void doDeleteEducationHistoryTest() {
		String deleteTeacherId = teacherDAO.getOneTeacherId();
		int result = teacherDAO.doDeleteEducationHistory(deleteTeacherId);
		assertNotNull(result);
	}
	@Test
	public void doDeleteTeacherBookTest() {
		String deleteTeacherId = teacherDAO.getOneTeacherId();
		int result = teacherDAO.doDeleteTeacherBook(deleteTeacherId);
		assertNotNull(result);
	}
	@Test
	public void getTeacherInfoTest(){
		String memberId = "teacher01";
		TeacherVO teacherVO = teacherDAO.getTeacherInfo(memberId);
		assertNotNull(teacherVO);
	}

	@Test
	public void getTeacherEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationHistoryVO> teacherEducations = teacherDAO.getTeacherEducationHistory(memberId);
		assertNotNull(teacherEducations);
	}

	@Test
	public void getTeacherProjectHistoryTest(){
		String memberId = "teacher01";
		List<ProjectHistoryVO> teacherProjects = teacherDAO.getTeacherProjectHistory(memberId);
		assertNotNull(teacherProjects);
	}

	@Test
	public void getTeacherBookTest(){
		String memberId = "teacher01";
		List<TeacherBookVO> teacherBooks = teacherDAO.getTeacherBook(memberId);
		assertNotNull(teacherBooks);
	}

	@Test
	public void getTeacherEducationGradeTest(){
		String memberId = "teacher01";
		double teacherGrade = teacherDAO.getTeacherEducationGrade(memberId);
		assertNotNull(teacherGrade);
	}

	@Test
	public void getEducationHistoryTest(){
		String memberId = "teacher01";
		List<EducationVO> educations = teacherDAO.getEducationHistory(memberId);
		assertNotNull(educations);
	}
	
	
}
