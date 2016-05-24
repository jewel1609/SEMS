package com.ktds.sems.team.biz;

import java.util.List;

import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public interface TeamBiz {

	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS);

	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO);

	public int getSearchedBBSCount();

}
