package com.ktds.sems.teacher.dao;

import java.util.HashMap;
import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public interface TeacherDAO {
	
	TeacherVO getTeacherInfo(String memberId);

	List<EducationHistoryVO> getTeacherEducationHistory(String memberId);

	List<ProjectHistoryVO> getTeacherProjectHistory(String memberId);

	List<TeacherBookVO> getTeacherBook(String memberId);

	List<EducationVO> getEducationHistory(String memberId);

	double getTeacherEducationGrade(String memberId);

	public TeacherVO getOneTeacherInfo(String memberId);

	public List<TeacherBookVO> getOneTeacherBookInfo(String memberId);

	public List<ProjectHistoryVO> getOneTeacherProjectHistoryVO(String memberId);

	public List<EducationHistoryVO> getOneEducationHistoryVO(String memberId);

	public int doTeacherInfoModifyAction(TeacherVO teacherVO);
	
	public int doTeacherBookModifyAction(TeacherBookVO teacherBookVO);
	
	public int doTeacherProjectModifyAction(ProjectHistoryVO projectHistoryVO);
	
	public int doTeacherEducationModifyAction(EducationHistoryVO teacherEduHistoryVO);

	public int deleteTeacherBookEduProHistory(HashMap<String, Object> map);

}
