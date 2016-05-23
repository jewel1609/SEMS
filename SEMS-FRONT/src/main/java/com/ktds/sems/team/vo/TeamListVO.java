package com.ktds.sems.team.vo;

import java.util.ArrayList;
import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class TeamListVO {
	
	private List<TeamVO> teamList;
	private Paging paging;
	
	public List<TeamVO> getTeamList() {
		List<TeamVO> teams = new ArrayList<TeamVO>();
		teams.addAll(teamList);
		return teams;
	}
	
	public void setTeamList(List<TeamVO> educationList) { 		//educationList?
		List<TeamVO> teams = new ArrayList<TeamVO>();
		teams.addAll(educationList);
		
		this.teamList = teams;
	}
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
