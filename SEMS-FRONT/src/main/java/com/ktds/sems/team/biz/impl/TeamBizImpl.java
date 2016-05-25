package com.ktds.sems.team.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ktds.sems.file.dao.FileDAO;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.dao.TeamDAO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;
import com.ktds.sems.team.vo.TeamBBSVO;

import kr.co.hucloud.utilities.SHA256Util;

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
	public int getTotalTeamCount() {
		return teamDAO.getTotalteamCount();
	}

	@Override
	public List<TeamVO> getAllTeamList(TeamSearchVO searchVO) {
		return teamDAO.getAllTeamList(searchVO);
	}

	@Override
	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS, MultipartHttpServletRequest request) {

		String teamBBSId = String.valueOf(teamDAO.getNextTeamBBSSeq());
		String sysdate = teamDAO.getSysDate();

		// 따로 teamId을 받아와야하나 테스트용으로 만듬
		String teamId = "cannon";

		teamBBS.setTeamId(teamId);

		teamBBSId = lpad(teamBBSId);
		teamBBSId = "TBBS" + '-' + sysdate + '-' + teamBBSId;
		teamBBS.setTeamBBSId(teamBBSId);

		MultipartFile file = request.getFile("file");
		
		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt);
		
		String filePath = "D:\\" + saltFileName;
		
		if ( !file.isEmpty() ) {
			
			File files = new File(filePath);
			
			try {
				file.transferTo(files);
				
				FileVO fileVO = new FileVO();
				fileVO.setArticleId(teamBBS.getTeamBBSId());
				fileVO.setFileName(fileName);
				fileVO.setFileLocation(filePath);
				
				fileDAO.doWriteFile(fileVO);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		

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

	@Override
	public TeamsListVO getOneTeamDetail(String teamId) {
		return teamDAO.getOneTeamDetail(teamId);
	}

	public TeamBBSVO getTeamBBS(String teamBBSId) {
		return teamDAO.getTeamBBS(teamBBSId);
	}
	@Override
	public boolean addHitsRecord(TeamBBSVO bbs) {
		
		boolean success = false;
		
		String bbbsHistoryId = String.valueOf(teamDAO.getNextBBSHistorySeq());
		String sysdate = teamDAO.getSysDate();
		
		bbbsHistoryId = lpad(bbbsHistoryId);
		bbbsHistoryId = "BHTR" + '-' + sysdate + '-' + bbbsHistoryId;
		bbs.setBbsHistoryId(bbbsHistoryId);
		
		int bbsCount =  teamDAO.isAlreadyCheckBBS(bbs);
		// 처음으로 들렸던 기록이라면 기록한다.
		if( bbsCount == 0 ) {
			success =  (teamDAO.addHitsRecord(bbs) > 0);
		} 
		return success;
	
	}
	@Override
	public boolean checkDislikeByTeamBBSVO(TeamBBSVO bbs) {
		return teamDAO.checkDislikeByTeamBBSVO(bbs) > 0;
	}
	@Override
	public boolean addLikeRecord(TeamBBSVO bbs) {
		return teamDAO.addLikeRecord(bbs) > 0;
	}
	@Override
	public boolean checkLikeByTeamBBSVO(TeamBBSVO bbs) {
		return teamDAO.checkLikeByTeamBBSVO(bbs) > 0;
	}
	@Override
	public boolean addDislikeRecord(TeamBBSVO bbs) {
		return teamDAO.addDislikeRecord(bbs) > 0;
	}
	@Override
	public boolean addNewTeamBBSArticle(TeamBBSVO teamBBS) {
		return false;
	}
	
}
