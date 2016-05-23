package com.ktds.sems.team.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ktds.sems.member.web.MemberController;
import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.TeamBBSVO;

@Controller
public class TeamController {
	private Logger logger = LoggerFactory.getLogger(TeamController.class);
	private TeamService teamService;

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	@RequestMapping("/team/teamBBS/board")
	public String viewTeamBBSPage() {
	
		return "team/teamBoard";
	}
	
	@RequestMapping("/team/teamBBS/write")
	public String viewWriteTeamBBSPage() {
		return "team/writeTeamBBS";
	}

	@RequestMapping(value = "/team/teamBBS/doWrite", method = RequestMethod.POST)
	public String doWriteTeamBBSAction(@Valid TeamBBSVO teamBBS, Errors errors,HttpSession session) {
		logger.info("teamBBS"+teamBBS.getTitle());
		logger.info("teamBBS"+teamBBS.getDescript());
		logger.info("teamBBS"+teamBBS.isNotice());
		return teamService.addNewTeamBBSArticle(teamBBS, errors,session);
	}
}
