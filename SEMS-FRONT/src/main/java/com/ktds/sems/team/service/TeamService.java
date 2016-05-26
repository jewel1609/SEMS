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

	public String addNewTeamBBSArticle(TeamBBSVO teamBBS, Errors errors, MultipartHttpServletRequest request, HttpSession session);

	public ModelAndView viewTeamBBSPage(int pageNo);

	public ModelAndView viewTeamBBSDetailPage(String teamBBSId, int pageNo, HttpSession session);

	public String doLikeBBSAction(String teamBBSId, HttpSession session);

	public String doDislikeBBSAction(String teamBBSId, HttpSession session);

	public ModelAndView doWriteBBSReplyAction(TeamBBSReplyVO replyVO, int pageNo, HttpSession session);

	public ModelAndView getAllTeamListPage(TeamSearchVO teamSearchVO);
	
	public ModelAndView getOneTeamDetail(String teamId);

	public ModelAndView writeNewMinutes(String teamId, MinutesVO minutesVO, Errors errors, HttpSession session);

	public ModelAndView viewListMinutes(MinutesSearchVO intminutesSearchVO, int pageNo);

	public ModelAndView minutesInit();
}
