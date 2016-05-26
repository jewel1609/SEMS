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
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSReplyVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

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

		teamBBSId = lpad(teamBBSId, 6, "0");
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

	@Override
	public List<TeamBBSVO> getTeamBBSList(TeamSearchVO searchVO) {
		return teamDAO.getTeamBBSList(searchVO);
	}

	@Override
	public int getSearchedBBSCount() {
		return teamDAO.getSearchedBBSCount();
	}

	@Override
	public List<TeamsListVO> getOneTeamDetail(String teamId) {
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
		
		bbbsHistoryId = lpad(bbbsHistoryId, 6, "0");
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
	public String getLikeState(TeamBBSVO bbs) {
		return teamDAO.getLikeState(bbs);
	}
	@Override
	public String getDislikeState(TeamBBSVO bbs) {
		return teamDAO.getDislikeState(bbs);
	}
	@Override
	public boolean addLikeCount(TeamBBSVO bbs) {
		return teamDAO.addLikeCount(bbs) > 0;
	}
	@Override
	public boolean addDislikeCount(TeamBBSVO bbs) {
		return teamDAO.addDislikeCount(bbs) > 0;
	}
	@Override
	public boolean writeBBSReply(TeamBBSReplyVO replyVO) {
		String teamBBSReplyId = String.valueOf(teamDAO.getNextTeamBBSReplySeq());
		String sysdate = teamDAO.getSysDate();
		teamBBSReplyId = lpad(teamBBSReplyId, 6, "0");
		teamBBSReplyId = "TBRP" + '-' + sysdate + '-' + teamBBSReplyId;
		replyVO.setReplyId(teamBBSReplyId);
		return teamDAO.writeBBSReply(replyVO) > 0;
	}
	@Override
	public List<TeamBBSReplyVO> getTeamBBSReplies(String teamBBSId) {
		return teamDAO.getTeamBBSReplies(teamBBSId);
	}
	@Override
	public boolean writeBBSReReply(TeamBBSReplyVO replyVO) {
		String teamBBSReplyId = String.valueOf(teamDAO.getNextTeamBBSReplySeq());
		String sysdate = teamDAO.getSysDate();
		teamBBSReplyId = lpad(teamBBSReplyId, 6 ,"0");
		teamBBSReplyId = "TBRP" + '-' + sysdate + '-' + teamBBSReplyId;
		replyVO.setReplyId(teamBBSReplyId);
		
		logger.info("orderNobefore"+replyVO.getParentReplyId());
		int orderNo = teamDAO.getNextOrderNoByParentId(replyVO.getParentReplyId());
		logger.info("orderNo"+orderNo);
		replyVO.setOrderNo(orderNo);
		
		return teamDAO.writeBBSReReply(replyVO) > 0;
	}
	@Override
	public boolean writeNewMinutes(MinutesVO minutesVO) {
		
		int  nextMinutesId = teamDAO.nextMinutesSeq();
		
		String MinutesId = "MINU-" + lpad(nextMinutesId + "", 3, "0"); 
		minutesVO.setMinutesId(MinutesId);
		System.out.println("Biz MinuteId"+MinutesId);
		
		return teamDAO.insertNewMinutes(minutesVO) > 0;
	}

	private String lpad(String source, int length, String defValue) {
		
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for ( int i = 0 ; i < needLength ; i++ ) {
			source = defValue + source;
		}
		return source;
	}

	@Override
	public List<MinutesVO> getAllMinutesList(MinutesSearchVO minutesSearchVO) {
		return teamDAO.getAllMinutesList(minutesSearchVO);
	}

	@Override
	public int getTotalMinutesCount(MinutesSearchVO minutesSearchVO) {
		return teamDAO.getTotalMinutesCount(minutesSearchVO);
	}

}
