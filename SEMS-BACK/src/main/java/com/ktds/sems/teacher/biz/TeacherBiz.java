package com.ktds.sems.teacher.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public interface TeacherBiz {
	
	TeacherVO getTeacherInfo(String memberId);

	List<EducationHistoryVO> getTeacherEducationHistory(String memberId);

	List<ProjectHistoryVO> getTeacherProjectHistory(String memberId);

	List<TeacherBookVO> getTeacherBook(String memberId);

	double getTeacherEducationGrade(String memberId);

	List<EducationVO> getEducationHistory(String memberId);

	public TeacherVO getOneTeacherInfo(String memberId);

	public List<TeacherBookVO> getOneTeacherBookInfo(String memberId);

	public List<ProjectHistoryVO> getOneTeacherProjectHistoryVO(String memberId);

	public List<EducationHistoryVO> getOneEducationHistoryVO(String memberId);

	public boolean doTeacherInfoModifyAction(TeacherVO teacherVO);

	public boolean deleteTeacherBookEduProHistory(String id, String type);

}
