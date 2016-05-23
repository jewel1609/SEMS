package com.ktds.sems.team.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktds.sems.team.service.TeamService;

@Controller
public class TeamController {
	
	private TeamService teamService;

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	@RequestMapping("/team/write")
	public String viewWriteTeamBBSPage() {
		return "team/writeTeamBBS";
	}
	
}
