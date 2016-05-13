package com.ktds.sems.education.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.vo.EduReplyListVO;
import com.ktds.sems.education.vo.EducationListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

import kr.co.hucloud.utilities.web.Paging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml"})
public class EducationServiceTest {

	@Autowired
	private EducationBiz educationBiz;
	
	@Test
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

		EducationVO education = educationBiz.getOneEducationDetail(educationId);
		assertNotNull(education);
	//	List<FileVO> fileList = fileBiz.getOneFileId(educationId);
		
		List<QNAVO> qnas = educationBiz.getAllCommentByEducationId(educationId, searchVO);
		assertTrue(qnas.size() > 0);
		eduReplyListVO.setQnaList(qnas);
		
		
		//이미 신청된 회원인지 비교해서 boolean 값 보내기
		//MemberVO loginMember = (MemberVO)session.getAttribute("_MEMBER_");
		String loginMember = "huhu";
	//	assertTrue(educationBiz.isApplyMemberByEducationId(educationId, loginMember) > 0 );
		
	}

}
