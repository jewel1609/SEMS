package com.ktds.sems.team.dao;

import com.ktds.sems.team.vo.TeamBBSVO;

public interface TeamDAO {

	public int addNewTeamBBSArticle(TeamBBSVO teamBBS);

	public int getNextTeamBBSId();

	public String getSysDate();

}
