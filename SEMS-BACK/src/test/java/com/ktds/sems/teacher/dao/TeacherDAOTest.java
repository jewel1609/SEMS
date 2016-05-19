package com.ktds.sems.teacher.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public class TeacherDAOTest extends SemsTestCase {

	@Autowired
	private TeacherDAO teacherDAO;
	
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
