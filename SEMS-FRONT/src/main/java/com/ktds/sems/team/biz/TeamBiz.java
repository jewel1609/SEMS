package com.ktds.sems.team.biz;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;
import com.ktds.sems.team.vo.TeamBBSVO;

public interface TeamBiz {

	int getTotalTeamCount();

	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO);

	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS);
	
	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS, MultipartHttpServletRequest request);

	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO);

	public int getSearchedBBSCount();

	public TeamsListVO getOneTeamDetail(String teamId);

	public TeamBBSVO getTeamBBS(String teamBBSId);

	public boolean addHitsRecord(TeamBBSVO bbs);

	public boolean checkDislikeByTeamBBSVO(TeamBBSVO bbs);

	public boolean addLikeRecord(TeamBBSVO bbs);

	public boolean checkLikeByTeamBBSVO(TeamBBSVO bbs);

	public boolean addDislikeRecord(TeamBBSVO bbs);

}
