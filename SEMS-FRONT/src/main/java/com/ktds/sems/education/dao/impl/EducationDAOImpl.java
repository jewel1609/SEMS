package com.ktds.sems.education.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationVO;

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO {

	@Override
	public List<EducationVO> doSearchList(EducationVO educationVO) {
		return getSqlSession().selectList("EducationDAO.doSearchList", educationVO);
	}

}
