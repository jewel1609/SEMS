package com.ktds.sems.teacher.dao;

import java.util.List;

import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public interface TeacherDAO {

	TeacherVO getTeacherInfo(String memberId);

	List<EducationHistoryVO> getTeacherEducationHistory(String memberId);

	List<ProjectHistoryVO> getTeacherProjectHistory(String memberId);

	List<TeacherBookVO> getTeacherBook(String memberId);

	double getTeacherEducationGrade(String memberId);

}
