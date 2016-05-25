package com.ktds.sems.team.dao;

import java.util.List;

import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public interface TeamDAO {

	public int addNewTeamBBSArticle(TeamBBSVO teamBBS);

	public int getNextTeamBBSSeq();

	public String getSysDate();

	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO);

	public int getSearchedBBSCount();

	public TeamBBSVO getTeamBBS(String teamBBSId);

	public int addHitsRecord(TeamBBSVO bbs);

	public int isAlreadyCheckBBS(TeamBBSVO bbs);

	public int getNextBBSHistorySeq();

	public int checkDislikeByTeamBBSVO(TeamBBSVO bbs);

	public int addLikeRecord(TeamBBSVO bbs);

	public int checkLikeByTeamBBSVO(TeamBBSVO bbs);

	public int addDislikeRecord(TeamBBSVO bbs);


}
