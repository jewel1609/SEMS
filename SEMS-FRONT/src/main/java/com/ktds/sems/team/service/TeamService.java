package com.ktds.sems.team.service;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public interface TeamService {
	
	
	public ModelAndView getAllMyTeamList(int pageNo);

	public ModelAndView getOneMyTeamDetail(String teamId, HttpSession session, int pageNo);

	public ModelAndView getAllTeamListPage(TeamSearchVO teamSearchVO);
	
	public String addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, MultipartHttpServletRequest request, HttpSession session);

	public ModelAndView viewTeamBBSPage(int pageNo);

	public ModelAndView doSearchList(TeamBBSVO teamBBSVO, int pageNo );

	public ModelAndView checkPassword(String teamBBSId,String type);

	public ModelAndView viewModifyPage(String teamBBSId);
	
	public ModelAndView viewTeamBBSDetailPage(String teamBBSId, int pageNo, HttpSession session);

	public String doLikeBBSAction(String teamBBSId, HttpSession session);

	public String doDislikeBBSAction(String teamBBSId, HttpSession session);

	public ModelAndView doWriteBBSReplyAction(TeamBBSReplyVO replyVO, HttpSession session);

	public ModelAndView getOneTeamDetail(String teamId);

	public ModelAndView writeNewMinutes(String teamId, MinutesVO minutesVO, Errors errors, HttpSession session);

	public ModelAndView viewListMinutes(MinutesSearchVO intminutesSearchVO, int pageNo);

	public ModelAndView minutesInit();

	public ModelAndView getOneDetailMinutes(String minutesId, HttpSession session);
	
	public ModelAndView viewReReplyPage(String teamBBSId, HttpSession session, String parentReplyId);

	public ModelAndView getAllMinutes(MinutesSearchVO minutesSearchVO, int pageNo, HttpSession session);

	public void doDeleteTeamListByMemberId(String memberId);
	
}
