package com.ktds.sems.education.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO {

	@Override
	public int writeNewEducation(EducationVO educationVO) {
		return getSqlSession().insert("EducationDAO.writeNewEducation", educationVO);
	}

}
