package com.ktds.sems.team.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.team.service.TeamService;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

@Controller
public class TeamController {
	private Logger logger = LoggerFactory.getLogger(TeamController.class);
	private TeamService teamService;

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	@RequestMapping("/team/teamBBS/board")
	public ModelAndView viewTeamBBSPage(@RequestParam(required = false, defaultValue = "0") int pageNo) {
		return teamService.viewTeamBBSPage(pageNo);
	}
	
	@RequestMapping("/team/teamBBS/write")
	public String viewWriteTeamBBSPage() {
		return "team/writeTeamBBS";
	}

	@RequestMapping(value ="/team/teamBBS/doWrite", method = RequestMethod.POST)
	public String doWriteTeamBBSAction(@Valid TeamBBSVO teamBBSVO, Errors errors, MultipartHttpServletRequest request, HttpSession session) {
		
		if (teamBBSVO.getIsNotice() != null){
			if(teamBBSVO.getIsNotice().equals("on")){
				teamBBSVO.setIsNotice("Y");	
			}
		}else{
			teamBBSVO.setIsNotice("N");	
		}
		return teamService.addNewTeamBBSArticle(teamBBSVO, errors, request , session);
	}
	
	@RequestMapping("/team/teamBBS/detail/{teamBBSId}")
	public ModelAndView viewTeamBBSDetailPage(@PathVariable String teamBBSId, @RequestParam(required = false, defaultValue = "0") int pageNo 
			, HttpSession session){
		return teamService.viewTeamBBSDetailPage(teamBBSId, pageNo ,session);
	}
	
	@RequestMapping("/teamList")
	public ModelAndView viewTeamListPage(TeamSearchVO teamSearchVO) {
		return teamService.getAllTeamListPage(teamSearchVO);
	}
	
	@RequestMapping("/team/teamDetail/{teamId}")
	public ModelAndView getOneTeamDetail(@PathVariable String teamId){
		return teamService.getOneTeamDetail(teamId);
		
	}
	
	@RequestMapping("/team/teamBBS/like/{teamBBSId}")
	public String doLikeBBSAction(@PathVariable String teamBBSId, HttpSession session) {
		return teamService.doLikeBBSAction(teamBBSId, session);
	}
	
	@RequestMapping("/team/teamBBS/dislike/{teamBBSId}")
	public String doDislikeBBSAction(@PathVariable String teamBBSId, HttpSession session) {
		return teamService.doDislikeBBSAction(teamBBSId, session);
	}
	
	@RequestMapping("/searchInitBtn")
	public String teamSearchInit(){
		return "redirect:/teamList";
	}
	
	@RequestMapping("/team/writeMinutes/{teamId}")
	public ModelAndView viewWriteMinutesPage(@PathVariable String teamId) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("team/writeMinutes");
		view.addObject("teamId", teamId);
		return view;
	}
	
	@RequestMapping(value ="/team/teamBBS/detail/doWriteReply", method = RequestMethod.POST)
	public ModelAndView doWriteBBSReplyAction(@Valid TeamBBSReplyVO teamBBSReplyVO, 
			@RequestParam(required = false, defaultValue = "0") int pageNo ,HttpSession session ){
		logger.info(teamBBSReplyVO.getDescript()+"");
		return teamService.doWriteBBSReplyAction(teamBBSReplyVO, pageNo, session);
	}
	
	@RequestMapping("/team/doWriteAction/{teamId}")
	public ModelAndView doWriteAction(@PathVariable String teamId, @Valid MinutesVO minutesVO, Errors errors, HttpSession session){
		return teamService.writeNewMinutes(teamId, minutesVO, errors, session);
	}
	
	@RequestMapping("/team/listMinutes")
	public ModelAndView viewListMinutesPage(MinutesSearchVO  minutesSearchVO, @RequestParam(required=false, defaultValue="0") int pageNo) {
		
		return teamService.viewListMinutes(minutesSearchVO, pageNo);
	}
	
	@RequestMapping("/member/minutesInit")
	public ModelAndView minutesInit() {
		return teamService.minutesInit();
	}
}
