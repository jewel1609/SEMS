package com.ktds.sems.team.dao;

import java.util.List;

import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

public interface TeamDAO {

	public int getTotalteamCount();

	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO);
	
	public int addNewTeamBBSArticle(TeamBBSVO teamBBS);

	public int getNextTeamBBSSeq();

	public String getSysDate();

	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO);

	public int getSearchedBBSCount();

	public TeamsListVO getOneTeamDetail(String teamId);
	
	public TeamBBSVO getTeamBBS(String teamBBSId);

	public int addHitsRecord(TeamBBSVO bbs);

	public int isAlreadyCheckBBS(TeamBBSVO bbs);

	public int getNextBBSHistorySeq();

	public int checkDislikeByTeamBBSVO(TeamBBSVO bbs);

	public int addLikeRecord(TeamBBSVO bbs);

	public int checkLikeByTeamBBSVO(TeamBBSVO bbs);

	public int addDislikeRecord(TeamBBSVO bbs);

	public String getLikeState(TeamBBSVO bbs);

	public String getDislikeState(TeamBBSVO bbs);

	public int addLikeCount(TeamBBSVO bbs);

	public int addDislikeCount(TeamBBSVO bbs);

	public int writeBBSReply(TeamBBSReplyVO replyVO);

	public List<TeamBBSReplyVO> getTeamBBSReplies(String teamBBSId);

	public int getNextTeamBBSReplySeq();

	public int writeBBSReReply(TeamBBSReplyVO replyVO);

	public int getNextOrderNoByParentId(String parentReplyId);

	public int insertNewMinutes(MinutesVO minutesVO);

	public List<MinutesVO> getAllMinutesList(MinutesSearchVO minutesSearchVO);

	public int getTotalMinutesCount(MinutesSearchVO minutesSearchVO);

	public int nextMinutesSeq();

}
