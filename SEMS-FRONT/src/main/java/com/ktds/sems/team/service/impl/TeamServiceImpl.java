package com.ktds.sems.team.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;

import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.TeamBBSVO;

public class TeamServiceImpl implements TeamService{
	
	private TeamBiz teamBiz;

	public void setTeamBiz(TeamBiz teamBiz) {
		this.teamBiz = teamBiz;
	}

	@Override
	public String addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, HttpSession session) {
	
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			teamBBS.setTeamId(sessionMember.getId());
		} 
		
		if(errors.hasErrors()){
			return "redirect:/team/teamBBS/write";
		}else{
			boolean success = teamBiz.addNewTeamBBSArticle(teamBBS);
		
			if(success){
				return "team/teamBBS/board";
			}else { 
				throw new RuntimeException("팀별 게시판 글쓰기 실패");
			}
		}
	}

	
	
	
}
