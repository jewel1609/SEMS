package com.ktds.sems.education.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml"})
public class EducationDAOTest {
	@Autowired
	private EducationDAO educationDAO;
	
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
		String educationId= "ED-20160510-000011";
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
	@Test
	public void writeNewCommentTest(){
		QNAVO qnaVO = new QNAVO();
		
		qnaVO.setReplyId("RP-20160813-000088");
		qnaVO.setEduId("ED-20160913-000130");
		qnaVO.setParentReplyId("0");
		qnaVO.setOrderNo(4);
		qnaVO.setDescription("Junit Test");
		qnaVO.setMbrId("test");
		qnaVO.setCreatedDate("2016/05/13 오전 10:22:27");
		
		assertTrue(educationDAO.insertNewComment(qnaVO) > 0);
	}
	
	@Test
	public void doApplyEducationTest(){
		String educationId = "ED-20160512-000088";
		String id = "test02";
		assertNotNull( educationDAO.doApplyEducation(educationId, id) > 0);
	}
	
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
	@Test
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
}

