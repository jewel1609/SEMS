package com.ktds.sems.team.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.TeamBBSListVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamListVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;

import kr.co.hucloud.utilities.web.Paging;

public class TeamServiceImpl implements TeamService{
	private Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
	private TeamBiz teamBiz;
	
	public void setTeamBiz(TeamBiz teamBiz) {
		this.teamBiz = teamBiz;
	}

	@Override
	public ModelAndView getAllTeamListPage(TeamSearchVO teamSearchVO) {
		
		if(teamSearchVO == null) {
			teamSearchVO = new TeamSearchVO();
			teamSearchVO.setPageNo(0);
		}
		
		TeamListVO teamListVO = new TeamListVO();
		Paging paging = new Paging();
		teamListVO.setPaging(paging);
		
		paging.setPageNumber(teamSearchVO.getPageNo() + "");
		
		int totalTeamCount = teamBiz.getTotalTeamCount();
		paging.setTotalArticleCount(totalTeamCount);
		
		teamSearchVO.setStartIndex(paging.getStartArticleNumber());
		teamSearchVO.setEndIndex(paging.getEndArticleNumber());	
		
		List<TeamVO> teamList = teamBiz.getAllTeamList(teamSearchVO);
		teamListVO.setTeamList(teamList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("team/teamList");
		view.addObject("teamListVO",teamListVO);
		view.addObject("teamSearchVO", teamSearchVO);
		
		return view;
		
	}

	@Override
	public String addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, MultipartHttpServletRequest request,  HttpSession session) {
	
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			teamBBS.setMemberId(sessionMember.getId());
		} 
		
		if(errors.hasErrors()){
			return "redirect:/team/teamBBS/write";
		}else{
			boolean success = teamBiz.addNewTeamBBSArticle(teamBBS, request);
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

	@Override
	public ModelAndView getOneTeamDetail(String teamListId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("team/teamDetail");
		view.addObject("teamsListVO", teamBiz.getOneTeamDetail(teamListId));
		return view;
	}
	
	@Override
	public ModelAndView viewTeamBBSDetailPage(String teamBBSId, int pageNo, HttpSession session) {
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");

		if(member != null) {
			TeamBBSVO bbs = new TeamBBSVO();
			bbs.setMemberId(member.getId());
			bbs.setTeamBBSId(teamBBSId);
			// 조회수를 추가해준다.
			teamBiz.addHitsRecord(bbs);
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		ModelAndView view = new ModelAndView();
		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId); 
		view.addObject("teamBBS",teamBBS );
		view.setViewName("team/detailTeamBBS");
		return view;
	}

	@Override
	public String doLikeBBSAction(String teamBBSId, HttpSession session) {
		// 접속한 멤버 정보를 가져온다. 
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		if(member != null) {
			TeamBBSVO bbs = new TeamBBSVO();
			bbs.setMemberId(member.getId());
			bbs.setTeamBBSId(teamBBSId);
			
			// 먼저 같은 게시글에 싫어요 했는지 체크
			boolean isExistedDislike = teamBiz.checkDislikeByTeamBBSVO(bbs);
			
			// 같은 게시글에 싫어요가 있다면 
			if ( isExistedDislike ) {
				throw new RuntimeException("이미 싫어요로 선택하셨습니다.");
			} else { // 싫어요가 없다면 기록해준다.
				teamBiz.addLikeRecord(bbs);
			}
			
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		return "redirect:/team/teamBBS/detail/{teamBBSId}";
	}

	@Override
	public String doDislikeBBSAction(String teamBBSId, HttpSession session) {
		// 접속한 멤버 정보를 가져온다. 
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		if(member != null) {
			TeamBBSVO bbs = new TeamBBSVO();
			bbs.setMemberId(member.getId());
			bbs.setTeamBBSId(teamBBSId);
			
			// 먼저 같은 게시글에 좋아요했는지 체크
			boolean isExistedlike = teamBiz.checkLikeByTeamBBSVO(bbs);
			
			// 같은 게시글에 좋아요가 있다면 
			if ( isExistedlike ) {
				throw new RuntimeException("이미 좋아요로 선택하셨습니다.");
			} else { // 싫어요가 없다면 기록해준다.
				teamBiz.addDislikeRecord(bbs);
			}
			
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		return "redirect:/team/teamBBS/detail/{teamBBSId}";
	}

	@Override
	public String addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
