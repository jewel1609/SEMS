package com.ktds.sems.teacher.biz;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public class TeacherBizTest extends SemsTestCase{

	@Autowired
	private TeacherBiz teacherBiz;
	
	@Test
	public void getTeacherInfoTest(){
		String memberId = "gangsa1";
		TeacherVO teacherVO = teacherBiz.getTeacherInfo(memberId);
		assertNotNull(teacherVO);
	}
	
	@Test
	public void getTeacherEducationHistoryTest(){
		String memberId = "teacher02";
		List<EducationHistoryVO> teacherEducationHistories = teacherBiz.getTeacherEducationHistory(memberId);
		assertNotNull(teacherEducationHistories);
	}
	
	@Test
	public void getTeacherProjectHistoryTest(){
		String memberId = "teacher02";
		List<ProjectHistoryVO> teacherProjectHistories = teacherBiz.getTeacherProjectHistory(memberId);
		assertNotNull(teacherProjectHistories);
	}
	
	@Test
	public void getTeacherBookTest(){
		String memberId = "teacher02";
		List<TeacherBookVO> teacherBooks = teacherBiz.getTeacherBook(memberId);
		assertNotNull(teacherBooks);
	}
	
	@Test
	public void getEducationHistoryTest(){
		String memberId = "teacher02";
		List<EducationVO> educationHistories = teacherBiz.getEducationHistory(memberId);
		assertNotNull(educationHistories);
	}
	
	@Test
	public void getTeacherEducationGradeTest(){
		String memberId = "teacher02";
		double grade = teacherBiz.getTeacherEducationGrade(memberId);
		assertNotNull(grade);
	}
}
