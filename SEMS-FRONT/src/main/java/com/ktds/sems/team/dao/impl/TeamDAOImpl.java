package com.ktds.sems.team.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

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

	@Override
	public List<TeamsListVO> getOneTeamDetail(String teamId) {
		return getSqlSession().selectList("teamDAO.getOneTeamDetail", teamId);
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

	@Override
	public String getLikeState(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.getLikeState", TeamBBSVO);
	}

	@Override
	public String getDislikeState(TeamBBSVO TeamBBSVO) {
		return getSqlSession().selectOne("teamDAO.getDislikeState", TeamBBSVO);
	}

	@Override
	public int addLikeCount(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addLikeCount", TeamBBSVO);
	}

	@Override
	public int addDislikeCount(TeamBBSVO TeamBBSVO) {
		return getSqlSession().update("teamDAO.addDislikeCount", TeamBBSVO);
	}

	@Override
	public int writeBBSReply(TeamBBSReplyVO TeamBBSReplyVO) {
		return getSqlSession().insert("teamDAO.writeBBSReply", TeamBBSReplyVO);
	}

	@Override
	public List<TeamBBSReplyVO> getTeamBBSReplies(String teamBBSId) {
		return getSqlSession().selectList("teamDAO.getTeamBBSReplies", teamBBSId);
	}

	@Override
	public int getNextTeamBBSReplySeq() {
		return getSqlSession().selectOne("teamDAO.getNextTeamBBSReplySeq");
	}

	@Override
	public int writeBBSReReply(TeamBBSReplyVO TeamBBSReplyVO) {
		return getSqlSession().insert("teamDAO.writeBBSReReply", TeamBBSReplyVO);
	}

	@Override
	public int getNextOrderNoByParentId(String parentReplyId) {
		return getSqlSession().selectOne("teamDAO.getNextOrderNoByParentId", parentReplyId);
	}

	@Override
	public int insertNewMinutes(MinutesVO minutesVO) {
		return getSqlSession().insert("teamDAO.insertNewMinutes", minutesVO);
	}

	@Override
	public List<MinutesVO> getAllMinutesList(MinutesSearchVO minutesSearchVO) {
		return getSqlSession().selectList("teamDAO.getAllMinutesList", minutesSearchVO);
	}

	@Override
	public int getTotalMinutesCount(MinutesSearchVO minutesSearchVO) {
		return getSqlSession().selectOne("teamDAO.getTotalMinutesCount", minutesSearchVO);
	}

	@Override
	public int nextMinutesSeq() {
		return getSqlSession().selectOne("teamDAO.nextMinutesSeq");
	}

}
