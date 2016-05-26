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
import com.ktds.sems.team.vo.MinutesListVO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSListVO;
import com.ktds.sems.team.vo.TeamBBSReplyListVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
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
	public ModelAndView getAllMyTeamList(int pageNo) {
		
		TeamListVO teamListVO = new TeamListVO();
		Paging paging = new Paging(15,15);

		teamListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int totalTeamCount = teamBiz.getTotalTeamCount();
		if( totalTeamCount == 0 ){
			totalTeamCount++;
		}
		paging.setTotalArticleCount(totalTeamCount);

		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());	
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<TeamVO> teamList = teamBiz.getAllMyTeamList(searchVO);
		teamListVO.setTeamList(teamList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("myPage/myTeamList");
		logger.info("!!!");
		if(teamListVO.getTeamList().size() > 0 ){
			view.addObject("teamListVO", teamListVO);
		}
		return view;
	}



	@Override
	public ModelAndView getOneMyTeamDetail(String teamId, HttpSession session, int pageNo) {
	
		ModelAndView view = new ModelAndView();
		TeamVO team = teamBiz.getOneMyTeamDetail(teamId);
		view.addObject("team", team);
		view.setViewName("myPage/teamDetail");		

	
		return view;


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
		
		String startYear = teamBiz.getStartYear();
		String endYear = teamBiz.getEndYear();
		
		ModelAndView view = new ModelAndView();
		List<TeamBBSVO> teamBBSList = teamBiz.getTeamBBSList(searchVO);
		searchedListVO.setTeamBBSList(teamBBSList);
		
	//	logger.info("teamBBSList사이즈"+teamBBSList.size());
		view.addObject("fromYear", startYear);
		view.addObject("toYear", endYear);
		view.addObject("TeamBBSListVO",searchedListVO );
		view.setViewName("team/teamBoard");
		return view;
	}
	
	@Override
	public ModelAndView checkPassword(String teamBBSId, String type) {
		ModelAndView view = new ModelAndView();

		view.addObject("teamBBSId", teamBBSId);
		view.addObject("type", type);
		view.setViewName("team/checkPassword");

		return view;
	}

	@Override
	public ModelAndView viewModifyPage(String teamBBSId) {
		ModelAndView view = new ModelAndView();

		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId);
		view.addObject("teamBBS",teamBBS );
		view.setViewName("team/modifyBBS");
		return view;
	}

	@Override
	public ModelAndView getOneTeamDetail(String teamListId) {
		ModelAndView view = new ModelAndView();
		TeamsListsVO teamsListsVO = new TeamsListsVO();

		teamsListsVO.setTeamsListsVO(teamBiz.getOneTeamDetail(teamListId));

		view.setViewName("team/teamDetail");
		view.addObject("teamsListVO", teamBiz.getOneTeamDetail(teamListId));
		return view;
	}
	
	@Override
	public ModelAndView doSearchList(TeamBBSVO teamBBSVO, int pageNo ) {
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
		
		List<TeamBBSVO> searchedBBS = teamBiz.doSearchList(teamBBSVO, searchVO);
		
		searchedListVO.setTeamBBSList(searchedBBS);
		
		String startYear = teamBiz.getStartYear();
		String endYear = teamBiz.getEndYear();
		ModelAndView view = new ModelAndView();
		
		view.addObject("fromYear", startYear);
		view.addObject("toYear", endYear);
		
		view.addObject("searchKeyword", teamBBSVO);
		view.addObject("searchedListVO", searchedListVO);
		
		view.setViewName("team/teamBoard");
		return view;
	}

	
	@Override
	public ModelAndView viewTeamBBSDetailPage(String teamBBSId, int pageNo, HttpSession session ) {
		
		TeamBBSReplyListVO teamBBSReplyListVO = new TeamBBSReplyListVO();
		Paging paging = new Paging(15,15);
		
		teamBBSReplyListVO.setPaging(paging);
		paging.setPageNumber(pageNo +"");
		
		int replyCount = teamBiz.getReplyCountByTeamBBSId(teamBBSId);
		if(replyCount == 0 ){
			replyCount ++;
		}
		paging.setTotalArticleCount(replyCount);
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		searchVO.setTeamBBSId(teamBBSId);
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId); 
		List<TeamBBSReplyVO> replies = teamBiz.getTeamBBSReplies(searchVO);
		
		if(member != null) {
			TeamBBSVO bbs = new TeamBBSVO();
			bbs.setMemberId(member.getId());
			bbs.setTeamBBSId(teamBBSId);
			// 조회수를 추가해준다.
			teamBiz.addHitsRecord(bbs);
			
			// 좋아요 싫어요 정보를 가져온다.
			String likeState = teamBiz.getLikeState(bbs);
			String dislikeState = teamBiz.getDislikeState(bbs);
			logger.info("likeState" + likeState);
			logger.info("dislikeState" + dislikeState);
			teamBBS.setLikeState(likeState);
			teamBBS.setDislikeState(dislikeState);
		
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		
		ModelAndView view = new ModelAndView();
		List<String> fileName = teamBiz.getFileInfo(teamBBSId);
		
		teamBBSReplyListVO.setTeamBBSReplyList(replies);
		view.addObject("teamBBSReplyListVO", teamBBSReplyListVO);
		view.addObject("teamBBS",teamBBS );
		view.addObject("fileName", fileName);
		view.addObject("memberId", member.getId());
	//	view.addObject("replies", replies);
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
				teamBiz.addLikeCount(bbs);
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
				teamBiz.addDislikeCount(bbs);
			}
			
		}else{
			throw new RuntimeException("멤버 정보가 없습니다.");
		}
		return "redirect:/team/teamBBS/detail/{teamBBSId}";
	}

	@Override
	public ModelAndView doWriteBBSReplyAction(TeamBBSReplyVO replyVO, HttpSession session) {
		MemberVO member= (MemberVO) session.getAttribute("_MEMBER_");
		String id = member.getId();
		logger.info("member"+id);
		logger.info("descript"+replyVO.getDescript());
		logger.info("teambbsId"+replyVO.getTeamBBSId());
		logger.info("groupId"+replyVO.getGroupId());
		logger.info("parentReplyId "+replyVO.getParentReplyId());
		logger.info("depth"+replyVO.getDepth());
		logger.info("orderNo"+replyVO.getOrderNo());
		
		replyVO.setMemberId(id);
		replyVO.setDescript(replyVO.getDescript().replaceAll("\n", "<br/>").replaceAll("\r", ""));
		
		logger.info("Descript"+replyVO.getDescript());
		
		// 대댓글이 아닐시
		if(replyVO.getParentReplyId().equals("0")){
			teamBiz.writeBBSReply(replyVO);
		}else{ //대댓글 일시
			
			teamBiz.writeBBSReReply(replyVO);
		}
		
		return new ModelAndView("redirect:/team/teamBBS/detail/" + replyVO.getTeamBBSId());
	}

	@Override
	public ModelAndView writeNewMinutes(String teamId, MinutesVO minutesVO, Errors errors, HttpSession session) {
		
		minutesVO.setStartDate(minutesVO.getAgendaDate() + " " + minutesVO.getStartTime());
		minutesVO.setEndDate(minutesVO.getAgendaDate() + " " + minutesVO.getEndTime());
		
		ModelAndView view = new ModelAndView();
		MemberVO memberVO = (MemberVO) session.getAttribute("_MEMBER_");
		
		
		if ( memberVO != null ) {
			minutesVO.setMemberId(memberVO.getId());
			minutesVO.setTeamId(teamId);
			System.out.println("Controller teamId"+teamId);
			System.out.println(memberVO.getId());
		}
		
		if (errors.hasErrors()) {
			view.setViewName("team/writeMinutes");
			view.addObject("minutesVO", minutesVO);
			return view;
		}
		else {
			boolean result = teamBiz.writeNewMinutes(minutesVO);
			if ( result ) {
				view.setViewName("redirect:/listMinutes");
			}
			else {
				throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시 후 다시 이용해주세요.");
			}
		}
		
		return view;
	}

	@Override
	public ModelAndView viewListMinutes(MinutesSearchVO minutesSearchVO, int pageNo) {
		
		MinutesListVO minutesListVO = new MinutesListVO();
		Paging paging = new Paging(5, 5);
		
		minutesListVO.setPaging(paging);
		int totalHistoryCount = teamBiz.getTotalMinutesCount(minutesSearchVO);
		
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalHistoryCount);
		
		minutesSearchVO.setStartIndex(paging.getStartArticleNumber());
		minutesSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<MinutesVO> minutesList = teamBiz.getAllMinutesList(minutesSearchVO);
		minutesListVO.setMinutesList(minutesList);

		ModelAndView view = new ModelAndView();
		view.setViewName("team/listMinutes");
		view.addObject("minutesListVO", minutesListVO);
		view.addObject("minutesSearchVO", minutesSearchVO);
		
		return view;
	}

	@Override
	public ModelAndView minutesInit() {
		
		ModelAndView view =new ModelAndView();
		
		view.addObject("startDate", null);
		view.addObject("endDate", null);
		view.setViewName("redirect:/listMinutes");
		
		return view;
	}

	@Override
	public ModelAndView viewReReplyPage(String teamBBSId, HttpSession session, String parentReplyId) {

		List<TeamBBSReplyVO> reReplies = teamBiz.getTeamBBSReReplies(parentReplyId); 
		
		ModelAndView view = new ModelAndView();
		view.addObject("reReplies", reReplies);
		logger.info("reReplies"+reReplies.size());
		view.setViewName("team/viewReply");
		return view;
	}

	@Override
	public ModelAndView getOneDetailMinutes(String minutesId, HttpSession session) {
		
		//MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		//String loginMemberId = loginMember.getId();
		
		//System.out.println(loginMemberId);
		//System.out.println(memberId);
		if (minutesId != null) {
			MinutesVO minutesVO = teamBiz.getOneDetailMinutes(minutesId);
			
			String startMinutesDate = teamBiz.getOneDetailMinutesDate(minutesId);
			ModelAndView view = new ModelAndView();
			minutesVO.setMinutesDate(startMinutesDate);
			view.addObject("minutesVO", minutesVO);
			view.setViewName("team/detailMinutes");
			
			return view;
		} else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
	}

	@Override
	public ModelAndView getAllMinutes(MinutesSearchVO minutesSearchVO, int pageNo, HttpSession session) {

		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);

		MinutesListVO minutesListVO = new MinutesListVO();
		Paging paging = new Paging();

		minutesListVO.setPaging(paging);
		int totalMinutesCount = teamBiz.getTotalMinutesCountForAdmin(minutesSearchVO);

		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalMinutesCount);

		minutesSearchVO.setStartIndex(paging.getStartArticleNumber());
		minutesSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<MinutesVO> minutesList = teamBiz.getAllMinutes(minutesSearchVO);
		minutesListVO.setMinutesList(minutesList);

		ModelAndView view = new ModelAndView();

		if( memberType.equals("ADM") || memberType.equals("TR") || memberType == null){
			view.setViewName("team/minutesList");
			view.addObject("minutesListVO", minutesListVO);
			view.addObject("minutesSearchVO", minutesSearchVO);
		}
		else{
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		return view;
	}
	@Override
	public void doDeleteTeamListByMemberId(String memberId) {
		teamBiz.doDeleteTeamListByMemberId(memberId);
	}

}
