package com.ktds.sems.team.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.TeamBBSListVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

import kr.co.hucloud.utilities.web.Paging;

public class TeamServiceImpl implements TeamService{
	private Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
	private TeamBiz teamBiz;

	public void setTeamBiz(TeamBiz teamBiz) {
		this.teamBiz = teamBiz;
	}

	@Override
	public String addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, HttpSession session) {
	
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			teamBBS.setMemberId(sessionMember.getId());
		} 
		
		if(errors.hasErrors()){
			return "redirect:/team/teamBBS/write";
		}else{
			boolean success = teamBiz.addNewTeamBBSArticle(teamBBS);
			if(success){
				return "redirect:/team/teamBBS/board";
			}else { 
				throw new RuntimeException("팀별 게시판 글쓰기 실패");
			}
		}
	}

	@Override
	public ModelAndView viewTeamBBSPage(int pageNo) {
		
		TeamBBSListVO searchedListVO = new TeamBBSListVO();
		Paging paging = new Paging(15,15);
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int searchedBBSCount = teamBiz.getSearchedBBSCount();
		if(searchedBBSCount == 0 ){
			searchedBBSCount ++;
		}
		paging.setTotalArticleCount(searchedBBSCount);
		
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
		
		ModelAndView view = new ModelAndView();
		List<TeamBBSVO> teamBBSList = teamBiz.getTeamBBSList(searchVO);
		searchedListVO.setTeamBBSList(teamBBSList);
		
	//	logger.info("teamBBSList사이즈"+teamBBSList.size());
		view.addObject("TeamBBSListVO",searchedListVO );
		view.setViewName("team/teamBoard");
		return view;
	
	
	}

	
	
	
}
