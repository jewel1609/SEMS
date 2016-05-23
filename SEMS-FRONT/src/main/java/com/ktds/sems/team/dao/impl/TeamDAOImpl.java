package com.ktds.sems.team.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamBBSVO;

public class TeamDAOImpl  extends SqlSessionDaoSupport implements TeamDAO{

	@Override
	public int addNewTeamBBSArticle(TeamBBSVO teamBBS) {
		return getSqlSession().insert("teamDAO.addNewTeamBBS", teamBBS);
	}

	@Override
	public int getNextTeamBBSId() {
		return getSqlSession().selectOne("teamDAO.getNextTeamBBSId");
	}

	@Override
	public String getSysDate() {
		return getSqlSession().selectOne("teamDAO.getSysDate");
	}

}
