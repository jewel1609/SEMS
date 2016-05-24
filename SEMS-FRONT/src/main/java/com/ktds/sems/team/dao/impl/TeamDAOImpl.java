package com.ktds.sems.team.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public class TeamDAOImpl  extends SqlSessionDaoSupport implements TeamDAO{

	@Override
	public int addNewTeamBBSArticle(TeamBBSVO TeamBBSVO) {
		return getSqlSession().insert("teamDAO.addNewTeamBBS", TeamBBSVO);
	}

	@Override
	public int getNextTeamBBSSeq() {
		return getSqlSession().selectOne("teamDAO.getNextTeamBBSSeq");
	}

	@Override
	public String getSysDate() {
		return getSqlSession().selectOne("teamDAO.getSysDate");
	}

	@Override
	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO) {
		return getSqlSession().selectList("teamDAO.getTeamBBSList", searchVO);
	}

	@Override
	public int getSearchedBBSCount() {
		return getSqlSession().selectOne("teamDAO.getSearchedBBSCount");
	}

}
