package com.ktds.sems.member.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.MemberVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	@Override
	public int registerNewMember(MemberVO member) {
		return getSqlSession().insert("MemberDAO.", member);
	}
	
}
