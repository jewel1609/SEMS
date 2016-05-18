package com.ktds.sems.teacher.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.teacher.biz.TeacherBiz;
import com.ktds.sems.teacher.dao.TeacherDAO;
import com.ktds.sems.teacher.vo.EducationHistoryVO;
import com.ktds.sems.teacher.vo.ProjectHistoryVO;
import com.ktds.sems.teacher.vo.TeacherBookVO;
import com.ktds.sems.teacher.vo.TeacherSearchVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public class TeacherBizImpl implements TeacherBiz {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private TeacherDAO teacherDAO;

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
	
	@Override
	public TeacherVO getTeacherInfo(String memberId) {
		return teacherDAO.getTeacherInfo(memberId);
	}

	@Override
	public List<EducationHistoryVO> getTeacherEducationHistory(String memberId) {
		return teacherDAO.getTeacherEducationHistory(memberId);
	}

	@Override
	public List<ProjectHistoryVO> getTeacherProjectHistory(String memberId) {
		return teacherDAO.getTeacherProjectHistory(memberId);
	}

	@Override
	public List<TeacherBookVO> getTeacherBook(String memberId) {
		return teacherDAO.getTeacherBook(memberId);
	}

	@Override
	public double getTeacherEducationGrade(String memberId) {
		return teacherDAO.getTeacherEducationGrade(memberId);
	}

	@Override
	public List<EducationVO> getEducationHistory(String memberId) {
		return teacherDAO.getEducationHistory(memberId);
	}

	@Override
	public int getTotalTeacherCount(HttpServletRequest request) {
		
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		Map<String,String> searchInfo = new HashMap<String,String>();
		searchInfo.put("searchType", searchType);
		searchInfo.put("searchKeyword", searchKeyword);
		
		return teacherDAO.getTotalTeacherCount(searchInfo);
	}

	@Override
	public List<TeacherVO> getAllTeacher(TeacherSearchVO searchVO) {
		return teacherDAO.getAllTeacher(searchVO);
	}

	@Override
	public int getSearchedEducationCount(TeacherVO teacherVO) {
		return teacherDAO.getSearchedEducationCount(teacherVO);
	}

	@Override
	public boolean doDeleteTeacher(String memberId) {
		return  teacherDAO.doDeleteTeacher(memberId) > 0;
	}

	@Override
	public boolean doDeleteProjectHistory(String memberId) {
		return teacherDAO.doDeleteProjectHistory(memberId) > 0;
	}

	@Override
	public boolean doDeleteEducationHistory(String memberId) {
		return teacherDAO.doDeleteEducationHistory(memberId) > 0;
	}

	@Override
	public boolean doDeleteTeacherBook(String memberId) {
		return teacherDAO.doDeleteTeacherBook(memberId) > 0; 
	}


	@Override
	public TeacherVO getOneTeacherInfo(String memberId) {
		return teacherDAO.getOneTeacherInfo(memberId);
	}

	@Override
	public List<TeacherBookVO> getOneTeacherBookInfo(String memberId) {
		return teacherDAO.getOneTeacherBookInfo(memberId);
	}

	@Override
	public List<ProjectHistoryVO> getOneTeacherProjectHistoryVO(String memberId) {
		return teacherDAO.getOneTeacherProjectHistoryVO(memberId);
	}

	@Override
	public List<EducationHistoryVO> getOneEducationHistoryVO(String memberId) {
		return teacherDAO.getOneEducationHistoryVO(memberId);
	}

	@Override
	public boolean doTeacherInfoModifyAction(TeacherVO teacherVO) {
		List<TeacherBookVO> teacherBookList = teacherVO.getTeacherBookList();
		List<ProjectHistoryVO> projectHistoryList = teacherVO.getProjectHistoryList();
		List<EducationHistoryVO> educationHistoryList = teacherVO.getEducationHistoryList();
		int result = 0;
		for (TeacherBookVO teacherBookVO : teacherBookList) {
			result = teacherDAO.doTeacherBookModifyAction(teacherBookVO);
			if( result < 1 ){
				return false;
			}
		}
		for (ProjectHistoryVO projectHistoryVO : projectHistoryList) {
			result = teacherDAO.doTeacherProjectModifyAction(projectHistoryVO);
			if( result < 1 ){
				return false;
			}
		}
		for (EducationHistoryVO teacherEduHistoryVO : educationHistoryList) {
			result = teacherDAO.doTeacherEducationModifyAction(teacherEduHistoryVO);
			if( result < 1 ){
				return false;
			}
		}
		System.out.println("4");
		return teacherDAO.doTeacherInfoModifyAction(teacherVO) > 0;
	}

	@Override
	public boolean deleteTeacherBookEduProHistory(String id, String type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return teacherDAO.deleteTeacherBookEduProHistory(map) > 0;
	}

}
