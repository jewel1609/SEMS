package com.ktds.sems.education.biz;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class EducationBizTest extends SemsTestCase{

	@Autowired
	private EducationBiz educationBiz;
	
	@Test
	public void hasApplyHistoryTest(){
		String memberId = "test04";
		String educationId = "ED-20160516-000185";
		boolean hasApplyHistory = educationBiz.hasApplyHistory(memberId, educationId);
		assertNotNull(hasApplyHistory);
		assertTrue(hasApplyHistory);
	}
	
	@Test
	public void isEducationStartedTest(){
		String educationId = "ED-20160516-000185";
		boolean isEducationStarted = educationBiz.isEducationStarted(educationId);
		assertNotNull(isEducationStarted);
		assertTrue(!isEducationStarted);
	}

	// 교육 아이디 ED-20160513-000173에 대한 멤버 test04의 신청 내역이 있고, 교육이 시작 전이어야 제대로된 테스트 진행.
	@Test
	public void doRequestRetractionTest(){
		String educationId = "ED-20160513-000173";
		String retractionMsg = "듣기 싫어요";
		String memberId = "test04";
		boolean result = educationBiz.doRequestRetraction(educationId, retractionMsg, memberId);
		assertNotNull(result);
		assertTrue(result);
	}
	
	@Test
	public void getNowDateTest(){
		String checkStr = educationBiz.getNowDate();
		assertNotNull(checkStr);
	}
	
	@Test
	public void getNextReplySeq(){
		int checkInt = educationBiz.getNextReplySeq();
		assertTrue(checkInt > 0);
	}
	
	@Test
	public void doReReplyInsertTest(){
		QNAVO qnaVO = new QNAVO();
		String realReplyId = "JUNIT Test replyId44";
		String replyId = "RP-20160513-000086";
		String eduId = "ED-20160513-000130";
		String description = "JUNIT TEST DESCRIPTION";
		//강의ID
		qnaVO.setEduId(eduId);
		//대댓글 ID
		qnaVO.setReplyId(realReplyId);
		//댓글ID
		qnaVO.setParentReplyId(replyId);
		//내용
		qnaVO.setDescription(description);
		// 답글 쓴 아이디를 집어넣음
		qnaVO.setMbrId("admin01");
		
		boolean checkBoolean = educationBiz.doReReplyInsert(qnaVO);
		assertTrue(checkBoolean);
		assertTrue(educationBiz.doReReplyDelete(qnaVO));
	}

	@Test
	public void getEmailTest(){
		String id = "test02";
		String checkStr = educationBiz.getEmail(id);
		assertNotNull(checkStr);
	}
	
	@Test
	public void getSelectedQNA(){
		QNAVO qnaVO = new QNAVO();
		String replyId = "RP-20160513-000096";
		qnaVO = educationBiz.getSelectedQNA(replyId);
		assertNotNull(qnaVO);
	}
	
	@Test
	public void getNextReReplyEvalTest(){
		int checkInt = educationBiz.getNextReReplyEval();
		assertTrue(checkInt >0);
	}
	
	@Test
	public void checkReReplyEvalTest(){
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		//댓글ID
		reRplyEvalVO.setReplyId("RP-20160517-000202");
		
		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId("test01");
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId("RE-20160517-000015");
		
		boolean checkBoolean = educationBiz.checkReReplyEval(reRplyEvalVO);
		assertTrue(checkBoolean);
	}
	
	/*
	 * 새로운 Reply Id값으로 설정해야함
	 * 
	 * @Test
	public void insertReReplyEvalTest(){
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		//댓글ID
		reRplyEvalVO.setReplyId("RP-20160517-000202");
		
		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId("test01");
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId("JUNIT TEST EVAL ID2");
		
		boolean checkBoolean = educationBiz.insertReReplyEval(reRplyEvalVO);
		assertTrue(checkBoolean);
		assertTrue(educationBiz.deleteReReplyEval(reRplyEvalVO));
	}*/
	
	@Test
	public void plusReReplyLikeTest(){
		String replyId = "RP-20160517-000202";
		boolean checkBoolean = educationBiz.plusReReplyLike(replyId);
		assertTrue(checkBoolean);
	}
	
	/*
	 * 새로운 Reply Id값으로 설정해야함
	 * 
	 * @Test
	public void insertReReplyEvalByDislikeTest(){
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		//댓글ID
		reRplyEvalVO.setReplyId("RP-20160517-000202");
		
		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId("test01");
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId("JUNIT TEST EVAL ID");
		
		boolean checkBoolean = educationBiz.insertReReplyEvalByDislike(reRplyEvalVO);
		assertTrue(checkBoolean);
		assertTrue(educationBiz.deleteReReplyEval(reRplyEvalVO));
	}*/
	
	@Test
	public void plusReReplyDislikeTest(){
		String replyId = "RP-20160517-000202";
		boolean checkBoolean = educationBiz.plusReReplyDislike(replyId);
		assertTrue(checkBoolean);
	}
	
	@Test
	public void getAllQNAListTest() {
		
		QNASearchVO qnaSearchVO = new QNASearchVO();
		qnaSearchVO.setId("test02");
		
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		
		int totalCount = educationBiz.getTotalQNACount(qnaSearchVO);
		paging.setTotalArticleCount(totalCount);
		
		qnaSearchVO.setPageNo(0);
		qnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		qnaSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<QNAVO> qnaList = educationBiz.getAllQNAList(qnaSearchVO);
		
		if(qnaList != null) {
			
			for (QNAVO qnavo : qnaList) {
				assertNotNull(qnavo.getReplyId());
				assertNotNull(qnavo.getEduId());
				assertNotNull(qnavo.getCreatedDate());
				assertNotNull(qnavo.getDescription());
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void getSelectedQNATest() {
		String replyId = "RP-20160517-000202";
		QNAVO qnaVO = educationBiz.getSelectedQNA(replyId);
		if ( qnaVO != null ) {
			assertNotNull(qnaVO);
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void getSelectedQNAAnswerTest() {
		//List<QNAVO>
		String replyId = "RP-20160517-000202";
		List<QNAVO> qnaList= educationBiz.getSelectedQNAAnswer(replyId);
		
		if(qnaList != null) {
			
			for (QNAVO qnavo : qnaList) {
				assertNotNull(qnavo.getReplyId());
				assertNotNull(qnavo.getEduId());
				assertNotNull(qnavo.getParentReplyId());
				assertNotNull(qnavo.getCreatedDate());
				assertNotNull(qnavo.getOrderNo());
				assertNotNull(qnavo.getDescription());
				assertNotNull(qnavo.getMbrId());
				assertNotNull(qnavo.getLikeCnt());
				assertNotNull(qnavo.getDislikeCnt());
			}
			
		} else {
			fail("fail");
		}
		
	}
	
	@Test
	public void exportQNAListAsExcelTest() {
		String memberId = "test02";
		boolean test = educationBiz.exportQNAListAsExcel(memberId);
		assertTrue(test);
	}
}
