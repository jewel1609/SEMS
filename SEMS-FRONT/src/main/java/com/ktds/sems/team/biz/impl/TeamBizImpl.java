package com.ktds.sems.team.biz.impl;

import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamBBSVO;

public class TeamBizImpl implements TeamBiz{

	private TeamDAO teamDAO;

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	@Override
	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS) {
		
		String teamBBSId = String.valueOf( teamDAO.getNextTeamBBSId() );
		String sysdate = teamDAO.getSysDate();
		
		// 따로 teamId을 받아와야하나 테스트용으로 만듬 
		String teamId = "cannon";
		
		teamBBS.setTeamId(teamId);
		
		teamBBSId = lpad(teamBBSId);
		teamBBSId = "TEAM-BBS" + '-' + sysdate +'-'+ teamBBSId;
	
		teamBBS.setTeamBBSId(teamBBSId);
		
		return teamDAO.addNewTeamBBSArticle(teamBBS) > 0 ;
	}

	private String lpad(String teamBBSId) {
		
		for( int i = 0 ; i < 6 - teamBBSId.length() ; i++){
			teamBBSId = '0' + teamBBSId;
		}
		
		return teamBBSId;
	}
	
	
}
