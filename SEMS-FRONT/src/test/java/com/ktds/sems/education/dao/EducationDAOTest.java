package com.ktds.sems.education.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaListVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EducationQNABBSSearchVO;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplySearchVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.Testable;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.ReportReplySearchVO;
import com.ktds.sems.education.vo.ReportReplyVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class EducationDAOTest extends SemsTestCase{
	@Autowired
	private EducationDAO educationDAO;
	
	@Before
	public void setUp() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				EducationReportVO educationReportVO = new EducationReportVO();
				educationReportVO.setArticleId("testArticleId");
				educationReportVO.setEducationId("testEducationId");
				educationReportVO.setMemberId("testMemeberId");
				educationReportVO.setTitle("testTitle");
				educationReportVO.setContents("testContents");
				educationReportVO.setStartDate("2016-05-26");
				educationReportVO.setEndDate("2016-05-30");
				educationDAO.doReportWriteAction(educationReportVO);
			}
		});
	}
	
	@After
	public void tearDown() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				EducationReportVO educationReportVO = new EducationReportVO();
				educationReportVO.setArticleId("testArticleId");
				educationDAO.deleteReport(educationReportVO);
			}
		});
	}
	
	@Test
	public void getAllEducationListTest(){
		int pageNo = 0;
		int startIndex = 0;
		int endIndex = 8;
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setPageNo(pageNo);
		searchVO.setStartIndex(startIndex);
		searchVO.setEndIndex(endIndex);
		
		assertNotNull( educationDAO.getAllEducationList(searchVO) );
	}
	
	@Test
	public void getTotalEducationCountTest(){
		educationDAO.getTotalEducationCount();
	}
	
	@Test
	public void getOneEducationDetailTest(){
		String educationId= "ED-20160526-000315";
		assertNotNull( educationDAO.getOneEducationDetail(educationId));
	}
	
	@Test
	public void getSearchedEducationCountTest(){
		EducationVO educationVO = new EducationVO();
		
		educationVO.setStartDate("2016-05-10");
		educationVO.setEndDate("2016-05-30");
		educationVO.setCost("CSTC");
		educationVO.setEducationType("TIMD");
		educationVO.setEducationTitle("JUNIT...");
		
		assertNotNull(educationDAO.getSearchedEducationCount( educationVO));
	}
	
	/*@Test 
	public void getMemberRegInfoTest(){
		String id = "cocomo12";
		List<String> test = new ArrayList<>();
		test = educationDAO.getMemberRegInfo(id);
		// sql 내 메소드 존재X
	}*/
	
	@Test
	public void doSearchListTest(){
		EducationVO educationVO = new EducationVO();
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(9);
		
		educationVO.setStartDate( "2016-05-10" );
		educationVO.setEndDate( "2016-05-30" );
		educationVO.setEducationTitle( "CSTC" );
		educationVO.setEducationType( "TIMD" );
		educationVO.setCost( "JUNIT..." );
		
		assertNotNull (educationDAO.doSearchList( educationVO , searchVO));
	}
	
	// qnaVO에 set된 pk data 값은 테스트시 변경 요망.
	/*@Test
	//@Test
	public void writeNewCommentTest(){
		QNAVO qnaVO = new QNAVO();
//		qnaVO.setReplyId("RP-20160813-000088");
		qnaVO.setReplyId( String.valueOf( educationDAO.getNextReplySeq() ));
		qnaVO.setEduId("ED-20160913-000130");
		qnaVO.setParentReplyId("0");
		qnaVO.setOrderNo(4);
		qnaVO.setDescription("Junit Test");
		qnaVO.setMbrId("test");
		qnaVO.setCreatedDate("2016/05/13 오전 10:22:27");
		
		assertTrue(educationDAO.insertNewComment(qnaVO) > 0);
	}*/
	
	/*@Test
	public void doApplyEducationTest(){
		String educationId = "ED-20160518-000204";
		String id = "test02";
		assertNotNull( educationDAO.doApplyEducation(educationId, id) > 0);
	}*/
	
	// doApplyEducation ( 위 메소드 )와 동일한 educationId 사용중. 이 메소드 동작 시 doApplyEducation는 동작을 안함
	@Test
	public void doCancelEducationTest(){
		String educationId = "ED-20160512-000088";
		String id = "test02";
		assertNotNull(educationDAO.doCancelEducation(educationId, id) > 0);
	}
	
	@Test
	public void getNowDateTest(){
		assertNotNull(educationDAO.getNowDate());
	}
	
	@Test
	public void getNextReplySeqTest(){
		assertNotNull(educationDAO.getNextReplySeq());
	}
	
	// doCancelEducationTest, doApplyEducationTest 와 같은 educationId, id 사용중
//	@Test
	public void isApplyMemberByEducationIdTest(){
		Map<String, String> paramMap = new HashMap<String, String>();
		String educationId = "ED-20160512-000088";
		String id = "test02";
		paramMap.put("educationId", educationId);
		paramMap.put("id", id);
		
		assertNotNull( educationDAO.isApplyMemberByEducationId(educationId, id) );
	}
	
	// isApplyMemberByEducationIdTest 의 주석과 동일
	@Test
	public void getEduReplyCountTest(){
		String educationId = "ED-20160512-000088";
		assertNotNull(educationDAO.getEduReplyCount(educationId));
	}
	
	@Test
	public void getAllCommentByEducationIdTest(){
		String educationId = "ED-20160512-000088";
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(9);
		
		assertNotNull( educationDAO.getAllCommentByEducationId(educationId, searchVO));
	}
	
	@Test
	public void getApplyHistoryTest(){
		String memberId = "test04";
		String educationId = "ED-20160516-000181";
		List<EducationVO> educations = educationDAO.getApplyHistory(memberId, educationId);
		assertTrue(educations.size() > 0);
	}

	@Test
	public void doRequestRetractionTest(){
		String educationId = "ED-20160516-000181";
		String retractionMsg = "듣기 싫어요";
		String memberId = "test04";
		int result = educationDAO.doRequestRetraction(educationId, retractionMsg, memberId);
		assertNotNull(result);
		assertTrue(result == 1);
	}
	
	//@Test
	public void doReReplyInsertTest(){
		QNAVO qnaVO = new QNAVO();
		String realReplyId = "JUNIT Test replyId22";
		String replyId = "RP-20160513-000086";
		String eduId = "ED-20160513-000130";
		String description = "JUNIT DAO DESCRIPTION";
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
		
		int checkInt = educationDAO.doReReplyInsert(qnaVO);
		assertTrue(checkInt > 0);
		assertTrue(educationDAO.doReReplyDelete(qnaVO) > 0);
	}
	
	@Test
	public void getEmailTest(){
		assertNotNull(educationDAO.getEmail("test02"));
	}
	
	@Test
	public void getNextReReplyEvalTest(){
		assertNotNull(educationDAO.getNextReReplyEval());
	}
	
	@Test
	public void plusReReplyLikeTest(){
		assertTrue(educationDAO.plusReReplyLike("RP-20160517-000202") > 0);
	}
	
	@Test
	public void plusReReplyDislikeTest(){
		assertTrue(educationDAO.plusReReplyDislike("RP-20160517-000202") > 0);
	}

	//@Test
	public void insertReReplEvalTest(){
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		//댓글ID
		reRplyEvalVO.setReplyId("RP-20160517-000202");
		
		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId("test01");
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId("JUNIT TEST EVAL ID3");
		
		assertTrue(educationDAO.insertReReplyEval(reRplyEvalVO) > 0);
		assertTrue(educationDAO.deleteReReplyEval(reRplyEvalVO) > 0);
	}
	
//	@Test
	public void insertReReplEvalByDislikeTest(){
		ReRplyEvalVO reRplyEvalVO = new ReRplyEvalVO();
		//댓글ID
		reRplyEvalVO.setReplyId("RP-20160517-000204");
		
		// 좋아요 누른 아이디
		reRplyEvalVO.setMbrId("test03");
		
		// REPLY_EVAL_ID (pk)
		reRplyEvalVO.setReplyEvalId("JUNIT DISLIKE 4");
		
		assertTrue(educationDAO.insertReReplyEvalByDislike(reRplyEvalVO) > 0);
		assertTrue(educationDAO.deleteReReplyEval(reRplyEvalVO) > 0);
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
		
		assertTrue(educationDAO.checkReReplyEval(reRplyEvalVO) > 0);
	}
	
	@Test
	public void getAllQNAListTest() {
		QNASearchVO qnaSearchVO = new QNASearchVO();
		qnaSearchVO.setId("test02");
		
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		
		int totalCount = educationDAO.getTotalQNACount(qnaSearchVO);
		paging.setTotalArticleCount(totalCount);
		
		qnaSearchVO.setPageNo(0);
		qnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		qnaSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<QNAVO> qnaList = educationDAO.getAllQNAList(qnaSearchVO);
		
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
		QNAVO qnaVO = educationDAO.getSelectedQNA(replyId);
		
		if (qnaVO != null) {
			assertNotNull(qnaVO.getReplyId());
			assertNotNull(qnaVO.getEduId());
			assertNotNull(qnaVO.getParentReplyId());
			assertNotNull(qnaVO.getOrderNo());
			assertNotNull(qnaVO.getDescription());
			assertNotNull(qnaVO.getMbrId());
			assertNotNull(qnaVO.getLikeCnt());
			assertNotNull(qnaVO.getDislikeCnt());
			assertNotNull(qnaVO.getCreatedDate());
		} else {
			fail("fail");
		}
		
	}
	
	@Test
	public void getSelectedQNAAnswerTest() {
		String replyId = "RP-20160517-000202";
		List<QNAVO> qnaList = educationDAO.getSelectedQNAAnswer(replyId);
		
		if(qnaList != null) {
			
			for (QNAVO qnaVO : qnaList) {
				assertNotNull(qnaVO.getReplyId());
				assertNotNull(qnaVO.getEduId());
				assertNotNull(qnaVO.getParentReplyId());
				assertNotNull(qnaVO.getOrderNo());
				assertNotNull(qnaVO.getDescription());
				assertNotNull(qnaVO.getMbrId());
				assertNotNull(qnaVO.getLikeCnt());
				assertNotNull(qnaVO.getDislikeCnt());
				assertNotNull(qnaVO.getCreatedDate());
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void exportQNAListAsExcelTest() {
		//List<QNAVO> return type
		String memberId = "test02";
		
		List<QNAVO> qnaList = educationDAO.exportQNAListAsExcel(memberId);
		
		if(qnaList != null) {
			
			for (QNAVO qnaVO : qnaList) {
				assertNotNull(qnaVO.getReplyId());
				assertNotNull(qnaVO.getEduId());
				assertNotNull(qnaVO.getParentReplyId());
				assertNotNull(qnaVO.getOrderNo());
				assertNotNull(qnaVO.getDescription());
				assertNotNull(qnaVO.getMbrId());
				assertNotNull(qnaVO.getLikeCnt());
				assertNotNull(qnaVO.getDislikeCnt());
				assertNotNull(qnaVO.getCreatedDate());
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void getTotalReportReplyCountTest(){
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("test02");
		int count = educationDAO.getTotalReportReplyCount(reportReplySearchVO);
		assertNotNull(count);
		assertTrue(count > 0);
	}
	
	@Test
	public void getTotalReportReplyCountOfTeacherTest(){
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("gangsa3");
		int count = educationDAO.getTotalReportReplyCountOfTeacher(reportReplySearchVO);
		assertNotNull(count);
		assertTrue(count > 0);
	}
	
	@Test
	public void getAllReportReplyTest(){
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("test02");
		reportReplySearchVO.setStartIndex(1);
		reportReplySearchVO.setEndIndex(10);
		List<ReportReplyVO> reports = educationDAO.getAllReportReply(reportReplySearchVO);
		assertNotNull(reports);
		assertTrue(reports.size() >= 0);
	}
	
	@Test
	public void getAllReportReplyOfTeacherTest(){
		ReportReplySearchVO reportReplySearchVO = new ReportReplySearchVO();
		reportReplySearchVO.setMbrId("gangsa3");
		reportReplySearchVO.setStartIndex(1);
		reportReplySearchVO.setEndIndex(10);
		List<ReportReplyVO> reports = educationDAO.getAllReportReplyOfTeacher(reportReplySearchVO);
		assertNotNull(reports);
		assertTrue(reports.size() > 0);
	}
	
	
	/**
	 * Education Report
	 * Search startDate
	 */
	@Test
	public void getAllEducationReportListTest_SearchStartDate( ) {
		
		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setPageNo(0);
		
		Paging paging = new Paging();
		paging.setPageNumber(educationReportSearchVO.getPageNo() + "");
		
		int totalCount = educationDAO.getTotalEducationReportCount(educationReportSearchVO);
		paging.setTotalArticleCount(totalCount);
		
		educationReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationReportSearchVO.setEndIndex(paging.getEndArticleNumber());
		educationReportSearchVO.setEducationId("testEducationId");
		educationReportSearchVO.setSearchType("startDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");
		
		List<EducationReportVO> educationReportList = educationDAO.getAllEducationReportList(educationReportSearchVO);
		if(educationReportList != null) {
			for (EducationReportVO educationReportVO : educationReportList) {
				
				assertNotNull(educationReportVO.getArticleId());
				assertNotNull(educationReportVO.getEducationId());
				assertNotNull(educationReportVO.getMemberId());
				assertNotNull(educationReportVO.getTitle());
				assertNotNull(educationReportVO.getStartDate());
				assertNotNull(educationReportVO.getEndDate());
				
			}
		} else {
			fail("fail");
		}
	}
	
	/**
	 * Education Report
	 * Search endDate
	 */
	@Test
	public void getAllEducationReportListTest_SearchEndDate( ) {
		
		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setPageNo(0);
		
		Paging paging = new Paging();
		paging.setPageNumber(educationReportSearchVO.getPageNo() + "");
		
		int totalCount = educationDAO.getTotalEducationReportCount(educationReportSearchVO);
		paging.setTotalArticleCount(totalCount);
		
		educationReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationReportSearchVO.setEndIndex(paging.getEndArticleNumber());
		educationReportSearchVO.setEducationId("testEducationId");
		educationReportSearchVO.setSearchType("endDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");
		
		List<EducationReportVO> educationReportList = educationDAO.getAllEducationReportList(educationReportSearchVO);
		if(educationReportList != null) {
			for (EducationReportVO educationReportVO : educationReportList) {
				
				assertNotNull(educationReportVO.getArticleId());
				assertNotNull(educationReportVO.getEducationId());
				assertNotNull(educationReportVO.getMemberId());
				assertNotNull(educationReportVO.getTitle());
				assertNotNull(educationReportVO.getStartDate());
				assertNotNull(educationReportVO.getEndDate());
				
			}
		} else {
			fail("fail");
		}
	}
	
	/**
	 * Education Report
	 * Search title
	 */
	@Test
	public void getTotalEducationReportCountTest_SearchTitle() {
	
		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setSearchType("title");
		educationReportSearchVO.setSearchKeyword("test");
		
		int totalCount = educationDAO.getTotalEducationReportCount(educationReportSearchVO);
		assertTrue(totalCount > 0);
	}
	
	/**
	 * Education Report
	 * Search startDate
	 */
	@Test
	public void getTotalEducationReportCountTest_SearchStartDate() {
		
		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setSearchType("startDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");
		
		int totalCount = educationDAO.getTotalEducationReportCount(educationReportSearchVO);
		assertTrue(totalCount > 0);
	}
	
	/**
	 * Education Report
	 * Search endDate
	 */
	@Test
	public void getTotalEducationReportCountTest_SearchEndDate() {
		
		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setSearchType("endDate");
		educationReportSearchVO.setStartDate("2015-05-01");
		educationReportSearchVO.setEndDate("2017-05-01");
		
		int totalCount = educationDAO.getTotalEducationReportCount(educationReportSearchVO);
		assertTrue(totalCount > 0);
	}

	/**
	 * Education Report
	 * Get One Education Report Test
	 */
	@Test
	public void getOneEducationReportTest() {
		
		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("testArticleId");
		
		EducationReportVO newEducationReportVO = educationDAO.getOneEducationReport(educationReportVO);
		if(newEducationReportVO != null) {
			assertNotNull(newEducationReportVO.getArticleId());
			assertNotNull(newEducationReportVO.getEducationId());
			assertNotNull(newEducationReportVO.getMemberId());
			assertNotNull(newEducationReportVO.getTitle());
			assertNotNull(newEducationReportVO.getStartDate());
			assertNotNull(newEducationReportVO.getEndDate());
			
		} else {
			fail("fail");
		}
	}
	
	/**
	 * Education Report
	 * Get One Education Report Test - Error
	 */
	@Test
	public void getOneEducationReportTest_Error() {
		
		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setArticleId("NO_TEST_DATE");
		
		EducationReportVO newEducationReportVO = educationDAO.getOneEducationReport(educationReportVO);
		if(newEducationReportVO != null) {
			fail("fail");
		}
	}
	
	@Test
	public void getTotalEduQnaCountTest() {
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		int result = educationDAO.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(result>0);
	}
	
	@Test
	public void getAllEduQnaTest() {
		EduQnaListVO eduQnaListVO = new EduQnaListVO();
		EduQnaSearchVO eduQnaSearchVO = new EduQnaSearchVO();
		eduQnaSearchVO.setEducationId("ED-20160513-000166");
		Paging paging = new Paging(20,20);
		
		eduQnaListVO.setPaging(paging);
		int totalReportCount = educationDAO.getTotalEduQnaCount(eduQnaSearchVO);
		assertTrue(totalReportCount>0);
		
		paging.setPageNumber(0 + "");
		paging.setTotalArticleCount(totalReportCount);
		
		eduQnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduQnaSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EduQnaVO> list = educationDAO.getAllEduQna(eduQnaSearchVO);
		
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void confirmMemberOfEduTest() {
		String memberId = "test02";
		String educationId = "ED-20160513-000166";
		
		int confirmMemberOfEdu = educationDAO.confirmMemberOfEdu(educationId, memberId);
		assertTrue(confirmMemberOfEdu>0);
	}
	
	@Test
	public void insertEduQnaTest() {
		EduQnaVO eduQnaVO = new EduQnaVO();
		String memberId = "test02";
		String eduQnaId = "JUNIT TEST";
		
		eduQnaVO.setMemberId(memberId);
		eduQnaVO.setEduQnaId(eduQnaId);
		eduQnaVO.setEducationId("ED-20160513-000166");
		eduQnaVO.setTitle("JUNIT TEST");
		eduQnaVO.setContents("JUNIT TEST");
		
		int result = educationDAO.insertEduQna(eduQnaVO);
		assertTrue(result>0);
	}
	
	@Test
	public void getNextEqbSeqTest() {
		int nextEqbSeq = educationDAO.getNextEqbSeq();
		assertTrue(nextEqbSeq>0);
	}
	
	@Test
	public void detailOfEduQnaTest() {
		EduQnaVO eduQnaVO = educationDAO.detailOfEduQna("EQ-20160525-000073");
		assertNotNull(eduQnaVO);
	}
	
	@Test
	public void addHitsToEduQnaTest() {
		int result = educationDAO.addHitsToEduQna("EQ-20160525-000073");
		assertTrue(result>0);
	}
	
	@Test
	public void addQnaEduReplyLikeTest() {
		int resultTwo = educationDAO.addQnaEduReplyLike("ER-20160525-000904");
		assertTrue(resultTwo>0);
	}
	
	@Test
	public void addQnaEduReplyDisLikeTest() {
		int resultTwo = educationDAO.addQnaEduReplyDisLike("ER-20160525-000904");
		assertTrue(resultTwo>0);
	}
	
	@Test
	public void getTotalQnaEduReplyCountTest() {
		int totalReportCount = educationDAO.getTotalQnaEduReplyCount("EQ-20160525-000073");
		assertTrue(totalReportCount>0);
	}
	
	@Test
	public void getAllEduFileNoticeTest(){
		
		EduNoticeSearchVO eduNoticeSearchVO = new EduNoticeSearchVO();
		eduNoticeSearchVO.setEducationId(educationDAO.getOneEducationId());
		
		List<EduNoticeVO> eduNoticeList = educationDAO.getAllEduFileNotice(eduNoticeSearchVO);
		
		assertNotNull(eduNoticeList);
		assertTrue(eduNoticeList.size() > 0);
	}
	
	@Test
	public void getOneNoticeTest(){
		
		String eduNoticeId =  educationDAO.getOneEduNoticeId();
		EduNoticeVO eduNotice = educationDAO.getOneNotice(eduNoticeId);
		assertNotNull(eduNotice);
		
	}
	
	@Test
	public void getAllEducationQNAListTest() {
		EducationQNABBSSearchVO searchVO = new EducationQNABBSSearchVO();
		searchVO.setEducationId("ED-20160519-000233");
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");

		int totalCount = educationDAO.getTotalEducationQNACount(searchVO);
		paging.setTotalArticleCount(totalCount);

		searchVO.setPageNo(0);
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EducationQNABBSVO> eduQnaBBSList = educationDAO.getAllEducationQNAList(searchVO);
		assertNotNull(eduQnaBBSList);
		
	}
	
	@Test
	public void getTotalEducationQNACountTest() {
		EducationQNABBSSearchVO searchVO = new EducationQNABBSSearchVO();
		searchVO.setEducationId("ED-20160519-000233");

		int result = educationDAO.getTotalEducationQNACount(searchVO);
		assertNotNull(result);
	}
	
	@Test
	public void getOneQNABBSByAtcIdTest() {
		String atcId = "AT-20160524-000034";
		
		EducationQNABBSVO eduQNABBSVO = educationDAO.getOneQNABBSByAtcId(atcId);
		assertNotNull(eduQNABBSVO);
	}
	
	@Test
	public void getAllQNAReplyListByAtcIdTest() {
		EducationQNAReplySearchVO searchVO = new EducationQNAReplySearchVO();
		searchVO.setAtcId("AT-20160524-000034");
		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		String atcId = "AT-20160524-000034";
		int totalCount = educationDAO.getTotalQNAReplyCountByAtcId(atcId);
		paging.setTotalArticleCount(totalCount);

		searchVO.setPageNo(0);
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EducationQNAReplyVO> eduQNAReplyList = educationDAO.getAllQNAReplyListByAtcId(searchVO);
		assertNotNull(eduQNAReplyList);
	}
	
	@Test
	public void getTotalQNAReplyCountByAtcIdTest() {
		String atcId = "AT-20160524-000034";
		int result = educationDAO.getTotalQNAReplyCountByAtcId(atcId);
		assertNotNull(result);
	}
	
	@Test
	public void plusRecommendReplyTest() {
		String replyId = "ER-20160525-000869";
		int result = educationDAO.plusRecommendReply(replyId);
		assertNotNull(result);
	}
	
	@Test
	public void plusOpposeReplyTest() {
		String replyId = "ER-20160525-000869";
		int result = educationDAO.plusOpposeReply(replyId);
		assertNotNull(result);
	}
	
	@Test
	public void updateAdoptReplyTest() {
		String replyId = "ER-20160525-000869";
		int result = educationDAO.updateAdoptReply(replyId);
		assertNotNull(result);
	}
	
	@Test
	public void checkAdoptReplyTest() {
		String replyId = "ER-20160525-000869";
		int result = educationDAO.checkAdoptReply(replyId);
		assertNotNull(result);
	}
}








