package com.ktds.sems.team.service;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;

import com.ktds.sems.team.vo.TeamBBSVO;

public interface TeamService {

	public String addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, HttpSession session);

}
