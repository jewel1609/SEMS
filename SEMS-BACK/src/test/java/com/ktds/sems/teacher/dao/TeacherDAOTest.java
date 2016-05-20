package com.ktds.sems.teacher.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;

@Transactional
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
	public void doDeleteTeacherTest() {
		
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setMemberId("junit");
		teacherVO.setCompanyName("junit");
		teacherVO.setBusinessNumber("1234");
		teacherVO.setAnnual(1);
		int isInsert = teacherDAO.doInsertNewTeacher(teacherVO);
		if (isInsert > 0) {
			int result = teacherDAO.doDeleteTeacher(teacherVO.getMemberId());
			assertNotNull(result);
		}

	}
	@Test
	public void doDeleteProjectHistoryTest() {
		
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000001");
		projectHistoryVO.setMemberId("junit");
		projectHistoryVO.setStartDate("d");
		projectHistoryVO.setEndDate("d");
		projectHistoryVO.setProjectName("d");
		projectHistoryVO.setProjectLocation("d");
		
		int isInsert = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
		if ( isInsert > 0 ){
			int result = teacherDAO.doDeleteProjectHistory(projectHistoryVO.getMemberId());
			assertNotNull(result);
		}

	}
	@Test
	public void doDeleteEducationHistoryTest() {
		
		EducationHistoryVO teacherEduHistoryVO = new EducationHistoryVO();
		teacherEduHistoryVO.setId("TEH-20160519-000001");
		teacherEduHistoryVO.setMemberId("junit");
		teacherEduHistoryVO.setStartDate("r");
		teacherEduHistoryVO.setEndDate("r");
		teacherEduHistoryVO.setEducationName("r");
		teacherEduHistoryVO.setEducationLocation("r");
		int isInsert = teacherDAO.doInsertTeacherEduHis(teacherEduHistoryVO);
		if ( isInsert > 0){
			int result = teacherDAO.doDeleteEducationHistory(teacherEduHistoryVO.getMemberId());
			assertNotNull(result);
		}
		

	}
	@Test
	public void doDeleteTeacherBookTest() {
		
		ProjectHistoryVO projectHistoryVO = new ProjectHistoryVO();
		projectHistoryVO.setId("TPH-20160519-000001");
		projectHistoryVO.setMemberId("junit");
		projectHistoryVO.setStartDate("d");
		projectHistoryVO.setEndDate("d");
		projectHistoryVO.setProjectName("d");
		projectHistoryVO.setProjectLocation("d");
		int isInsert = teacherDAO.doInsertTeacherProHis(projectHistoryVO);
		
		if ( isInsert > 0 ){
			int result = teacherDAO.doDeleteProjectHistory(projectHistoryVO.getMemberId());
			assertNotNull(result);
		}
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
