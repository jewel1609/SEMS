package com.ktds.sems.team.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;
import com.ktds.sems.team.vo.TeamBBSVO;

public class TeamDAOImpl  extends SqlSessionDaoSupport implements TeamDAO{

	@Override
	public int getTotalteamCount() {
		return getSqlSession().selectOne("teamDAO.getTotalteamCount");
	}

	@Override
	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO) {
		return getSqlSession().selectList("teamDAO.getAllTeamList", searchVO);
	}

	@Override
	public int addNewTeamBBSArticle(TeamBBSVO teamBBS) {
		return getSqlSession().insert("teamDAO.addNewTeamBBS", teamBBS);
		
//	public int addNewTeamBBSArticle(TeamBBSVO TeamBBSVO) {
//		return getSqlSession().insert("teamDAO.addNewTeamBBS", TeamBBSVO);
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

	@Override
	public TeamsListVO getOneTeamDetail(String teamId) {
		return getSqlSession().selectOne("teamDAO.getOneTeamDetail", teamId);
	}
	@Override
	public TeamBBSVO getTeamBBS(String teamBBSId) {
		return getSqlSession().selectOne("teamDAO.getTeamBBS", teamBBSId);
	}

	@Override
	public int addHitsRecord(TeamBBSVO TeamBBSVO) {
		return getSqlSession().insert("teamDAO.addHitsRecord", TeamBBSVO);
	}

	@Override
	public int isAlreadyCheckBBS(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.isAlreadyCheckBBS",TeamBBSVO);
	}

	@Override
	public int getNextBBSHistorySeq() {
		return getSqlSession().selectOne("teamDAO.getNextBBSHistorySeq");
	}

	@Override
	public int checkDislikeByTeamBBSVO(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.checkDislikeByTeamBBSVO", TeamBBSVO);
	}

	@Override
	public int addLikeRecord(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addLikeRecord", TeamBBSVO);
	}

	@Override
	public int checkLikeByTeamBBSVO(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.checkLikeByTeamBBSVO", TeamBBSVO);
	}

	@Override
	public int addDislikeRecord(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addDislikeRecord", TeamBBSVO);
	}


}
