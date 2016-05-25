package com.ktds.sems.education.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.vo.EduReplyListVO;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationReportListVO;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class EducationServiceTest extends SemsTestCase {

	@Autowired
	private EducationBiz educationBiz;
	@Autowired
	private EducationService educationService;
	
//	@Test
	public void doCancelEducation() {
		
		String educationId = "ED-20160510-000011";
		String loginMember = "cocomo12";
		
		//성공하면 result에 true
		boolean result = educationBiz.doCancelEducation(educationId , loginMember);
	
		// 실패하면 에러
		// True 면 에러
		assertTrue(result);
	}
	
/*	@Test
	public void getOneEducationDetail(){
		String educationId = "ED-20160510-000004";
		int totalEduReplyCount = educationBiz.getEduReplyCount(educationId);
		int pageNo = 3;
		EduReplyListVO eduReplyListVO = new EduReplyListVO();
		Paging paging = new Paging(10,10);
		
		eduReplyListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		totalEduReplyCount = educationBiz.getEduReplyCount(educationId);
		
		paging.setTotalArticleCount(totalEduReplyCount);
		
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		EducationVO education = educationBiz.getOneEducationDetail(educationId);
		
		List<FileVO> fileList = fileBiz.getOneFileId(educationId);
		
		List<QNAVO> qnas = educationBiz.getAllCommentByEducationId(educationId, searchVO);
		
		eduReplyListVO.setQnaList(qnas);
		
		MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		boolean isApply =  true;
		if (educationBiz.isApplyMemberByEducationId(educationId, loginMember.getId()) > 0 ){
			isApply = false;
		}
		else{
			isApply = true;
		}
	}*/
	
	@Test
	public void getOneEducationDetailTest(){
		EducationVO educationVO = new EducationVO();
		String educationId= "ED-20160510-000011";
		educationBiz.getOneEducationDetail(educationId);
	}
	public void getAllCommentByEducationIdTest(){
		
	}
	@Test
	public void getAllEducationList() {
		EducationListVO educationListVO = new EducationListVO();
		Paging paging = new Paging(15,15);
		educationListVO.setPaging(paging);
		paging.setPageNumber(0 + "");
		
		int totalEducationCount = educationBiz.getTotalEducationCount();
		assertTrue(totalEducationCount > 0);
		
		paging.setTotalArticleCount(totalEducationCount);
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		List<EducationVO> educationList = educationBiz.getAllEducationList(searchVO);
		assertTrue(educationList.size()>0);
		
	}
	
	
	@Test
	public void doSearchList() {
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationTitle("2");
		
		EducationListVO searchedListVO = new EducationListVO();
		Paging paging = new Paging(15,15);
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(0 + "");
		
		int searchedEducationCount = educationBiz.getSearchedEducationCount(educationVO);
		assertTrue( searchedEducationCount > 0);
		
		paging.setTotalArticleCount(searchedEducationCount);
		
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
	
		// searchList 에러
		List<EducationVO> searchedEducations = educationBiz.doSearchList(educationVO, searchVO);
		assertTrue( searchedEducations.size() >0);
	}
	
	@Test
	public void getOneEducationDetail() {
		
		String educationId = "ED-20160512-000066";
		
		EduReplyListVO eduReplyListVO = new EduReplyListVO();
		Paging paging = new Paging(10,10);
	
		eduReplyListVO.setPaging(paging);
		paging.setPageNumber(0 + "");
		
		int totalEduReplyCount = educationBiz.getEduReplyCount(educationId);
		assertTrue( totalEduReplyCount > 0);
		paging.setTotalArticleCount(totalEduReplyCount);
		
		EducationSearchVO searchVO = new EducationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		educationId = "ED-20160519-000227";
		EducationVO education = educationBiz.getOneEducationDetail(educationId);
		assertNotNull(education);
	//	List<FileVO> fileList = fileBiz.getOneFileId(educationId);
		educationId = "ED-20160512-000066";
		List<QNAVO> qnas = educationBiz.getAllCommentByEducationId(educationId, searchVO);
		assertTrue(qnas.size() > 0);
		eduReplyListVO.setQnaList(qnas);
		
		
		//이미 신청된 회원인지 비교해서 boolean 값 보내기
		//MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		String loginMember = "huhu";
	//	assertTrue(educationBiz.isApplyMemberByEducationId(educationId, loginMember) > 0 );
		
	}
	
	@Test
	public void viewRequestRetractionPageTest(){
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String educationId = "ED-20160513-000173";
		
		ModelAndView view = educationService.viewRequestRetractionPage(session, educationId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "education/retraction");
	}
	
	@Test(expected=RuntimeException.class)
	public void viewRequestRetractionPageTestWithError1(){
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String educationId = "NO-EXIST-EDUCATION-ID";
		
		educationService.viewRequestRetractionPage(session, educationId);
	}
	
	@Test
	public void viewRequestRetractionPageTestWithError2(){
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String educationId = "ED-20160513-000166";
		
		ModelAndView view = educationService.viewRequestRetractionPage(session, educationId);
		assertNotNull(view);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/member/myPage/course");
	}

	@Test
	public void doRequestRetractionTest(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "ED-20160513-000173");
		request.setParameter("retractionMessage", "하기싫어요");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String result = educationService.doRequestRetraction(request, session);

		assertNotNull(result);
		assertEquals(result, "redirect:/member/myPage");
	}
	
	@Test(expected=RuntimeException.class)
	public void doRequestRetractionTestWithError1(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "NO-EXIST-EDUCATION-ID");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		
		educationService.doRequestRetraction(request, session);
	}
	
	@Test
	public void doRequestRetractionTestWithError2(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("educationId", "ED-20160513-000166");
		
		MockHttpSession session = new MockHttpSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test04");
		session.setAttribute(Session.MEMBER, memberVO);
		
		String result = educationService.doRequestRetraction(request, session);
		assertNotNull(result);
		assertEquals(result, "redirect:/member/myPage/course");
	}

	@Test
	public void doReReplyInsertTest(){
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		
		String replyId = "RP-20160513-000096";
		String eduId = "ED-20160513-000130";
		String id = "test02";
		String description = "JUNIT TEST DESCRIPTION";
		
		String checkStr = educationService.doReReplyInsert(replyId, eduId, id, description, session);
		assertNotNull(checkStr);
	}
	
	@Test
	public void plusReReplyLikeTest(){
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		String replyId = "RP-20160513-000094";
		
		String checkStr = educationService.plusReReplyLike(replyId, session);
		assertNotNull(checkStr);
	}
	
	@Test
	public void plusReReplyDislikeTest(){
		MockHttpSession session = new MockHttpSession();
		MemberVO sessionMember = new MemberVO();
		sessionMember.setId("admin01");
		session.setAttribute("_MEMBER_", sessionMember);
		
		String replyId = "RP-20160517-000204";
		String checkStr = educationService.plusReReplyDislike(replyId, session);
		assertNotNull(checkStr);
	}
	
	@Test
	public void showMyQNAListTest() {
		
		QNASearchVO qnaSearchVO = new QNASearchVO();
		MockHttpSession session = new MockHttpSession();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test02");
		
		session.setAttribute(Session.MEMBER, memberVO);
		
		ModelAndView view = educationService.showMyQNAList(qnaSearchVO, session);
		
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void showMyQNADetailTest() {
		String replyId = "RP-20160517-000204";
		MockHttpSession session = new MockHttpSession();
		
		ModelAndView view = educationService.showMyQNADetail(replyId, session);
		if(view != null) {
			
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "myPage/myQNADetail");
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void viewReportListPageTest() {
		
		Paging paging = new Paging();
		EducationReportSearchVO educationReportSearchVO = new EducationReportSearchVO();
		educationReportSearchVO.setPageNo(0);
		educationReportSearchVO.setEducationId("ED-20160519-000233");
		educationReportSearchVO.setSearchType("searchTypeTest");
		educationReportSearchVO.setSearchKeyword("Test");
		educationReportSearchVO.setStartDate("2222-02-22");
		educationReportSearchVO.setEndDate("2222-02-22");
		
		int totalEduReportCount = educationBiz.getTotalEducationReportCount(educationReportSearchVO);
		assertTrue( totalEduReportCount > 0);
		paging.setTotalArticleCount(totalEduReportCount);
		
		paging.setPageNumber("0");
		educationReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		educationReportSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EducationReportVO> educationReportList = educationBiz.getAllEducationReportList(educationReportSearchVO);
		assertNotNull(educationReportList);
		assertTrue(educationReportList.size()>0);
		
		EducationReportListVO educationReportListVO = new EducationReportListVO();
		educationReportListVO.setEducationReportList(educationReportList);
		educationReportListVO.setPaging(paging);
		
		ModelAndView view = educationService.viewReportListPage(educationReportSearchVO);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/reportList");
		}
		 else {
				fail("fail");
		}
	}
	
	@Test
	public void viewReportWriteTest() {
		
		MockHttpSession session = new MockHttpSession();
		
		MemberVO loginMember = new MemberVO();
		loginMember.setId("gangsa3");
		loginMember.setMemberType("TR");
		session.setAttribute(Session.MEMBER, loginMember);
		
		String educationId = "ED-20160519-000233";
		
		EducationReportVO educationReportVO = new EducationReportVO();
		educationReportVO.setEducationId(educationId);
		educationReportVO.setStartDate("2016-05-25");
		
		ModelAndView view = educationService.viewReportWrite(educationId, session);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/reportWrite");
		}
		else {
			fail("fail");
		}
		
	}
	
	
}
