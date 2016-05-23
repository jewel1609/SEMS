package com.ktds.sems.pc.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.dao.PcDAO;

public class PcDAOImpl extends SqlSessionDaoSupport implements PcDAO {

	@Override
	public List<EducationVO> getEduListByMember(MemberVO memberVO) {
		return getSqlSession().selectList("PcDAO.getEduListByMember", memberVO);
	}

}
