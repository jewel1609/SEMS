package com.ktds.sems.team.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;

public class TeamBizImpl implements TeamBiz {
	private Logger logger = LoggerFactory.getLogger(TeamBizImpl.class);

	private TeamDAO teamDAO;
	private FileDAO fileDAO;

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	@Override
	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS) {

		String teamBBSId = String.valueOf(teamDAO.getNextTeamBBSSeq());
		String sysdate = teamDAO.getSysDate();

		// 따로 teamId을 받아와야하나 테스트용으로 만듬
		String teamId = "cannon";

		teamBBS.setTeamId(teamId);

		teamBBSId = lpad(teamBBSId);
		teamBBSId = "TBBS" + '-' + sysdate + '-' + teamBBSId;
		teamBBS.setTeamBBSId(teamBBSId);

		// 에러 검출용
		logger.info("teamBBSId" + teamBBS.getTeamBBSId());
		logger.info("title" + teamBBS.getTitle());
		logger.info("descript" + teamBBS.getDescript());
		logger.info("MemberId" + teamBBS.getMemberId());
		logger.info("teamId" + teamBBS.getTeamId());
		logger.info("notice" + teamBBS.getIsNotice());

		return teamDAO.addNewTeamBBSArticle(teamBBS) > 0;
	}

	private String lpad(String teamBBSId) {

		int length = teamBBSId.length();
		for (int i = 0; i < 6 - length; i++) {
			teamBBSId = '0' + teamBBSId;
		}

		return teamBBSId;
	}

	@Override
	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO) {
		return teamDAO.getTeamBBSList(searchVO);
	}

	@Override
	public int getSearchedBBSCount() {
		return teamDAO.getSearchedBBSCount();
	}

}
