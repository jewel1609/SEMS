package com.ktds.sems.education.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.vo.EduReplyListVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml" })
public class EducationServiceTest {

	@Autowired
	private EducationBiz educationBiz;
/*	@Autowired
	private FileBiz fileBiz;*/
	
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
		educationVO.setEducationId(educationId);
		educationBiz.getOneEducationDetail(educationId);
	}
	
}
