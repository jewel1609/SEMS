package com.ktds.sems.team.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.team.vo.MinutesSearchVO;
import com.ktds.sems.team.vo.MinutesVO;
import com.ktds.sems.team.vo.TeamBBSListVO;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class TeamBizTest extends SemsTestCase {

	@Autowired
	private TeamBiz teamBiz;
	

	@Before
	public void setUp() {

		testHelper(new Testable() {

			@Override
			public void preparedTest() {

				if(!teamBiz.bulidTeam("testEducationId", "testTeamName") ) {
					throw new RuntimeException("일시적인 에러가 발생했습니다.");
				}
				
				TeamsListVO teamsListVO = new TeamsListVO();
				teamsListVO.setTeamListId("testTeamListId");
				teamsListVO.setMbrId("testMemberId");
				
				if(!teamBiz.insertMember(teamsListVO) ) {
					throw new RuntimeException("일시적인 에러가 발생했습니다.");
				}
			}
		});
	}

	@After
	public void tearDown() {

		testHelper(new Testable() {

			@Override
			public void preparedTest() {

				// 넣은 Team_List 데이터 지우기
				teamBiz.doDeleteTeamListByMemberId("testMemberId");
			}
		});
	}
	
	@Test
	public void getAllTeamListTest() {
		TeamSearchVO searchVO = new TeamSearchVO();
		
		searchVO.setSearchKeyword("");
		searchVO.setSearchType("teamId");
		searchVO.setStartIndex(0);
		searchVO.setEndIndex(10);
		
		List<TeamVO> teamList = teamBiz.getAllTeamList(searchVO);
		if (teamList != null){
			for (TeamVO teamVO : teamList) {
				assertNotNull(teamVO.getTeamId());
				assertNotNull(teamVO.getTeamNumber());
				assertNotNull(teamVO.getTeamDate());
				assertNotNull(teamVO.getEducationId());
			}
		}
		else{
			fail("Fail...");
		}
	}
	
	@Test
	public void getSaltByIdTest() {
		String sessionId = "test02";
		
		String result = teamBiz.getSaltById(sessionId);
		assertNotNull(result);
	}
	
	@Test
	public void getPasswordByIdTest() {
		String sessionId = "test02";
		
		String result = teamBiz.getPasswordById(sessionId);
		assertNotNull(result);
	}
	
	@Test
	public void isReplyByTeamBBSIdTest() {
		String teamBBSId = "TBBS-20160512-000054";
		
		boolean result = teamBiz.isReplyByTeamBBSId(teamBBSId);
		assertTrue(result);
	}

	
	@Test
	public void getOneTeamDetailTest(){
		String teamListId = "testTeamListId";
		List<TeamsListVO> teamsList = teamBiz.getOneTeamDetail(teamListId);
		if(teamsList != null) {
			for (TeamsListVO teamsListVO : teamsList) {
				assertNotNull(teamsListVO.getTeamListId());
				assertNotNull(teamsListVO.getTeamId());
				assertNotNull(teamsListVO.getMbrId());
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void getAllEduMember(){
		List<MemberVO> members = new ArrayList<MemberVO>();
		String educationId = "junitId";
		
		members = teamBiz.getAllEduMember(educationId);
		if (members != null){
			assertNotNull(members);
			assertTrue(members.size() >0);
			}
		else{
			fail("Fail...");
		}
	}
	
	@Test
	public void getTotalMinutesCountForAdmin(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		assertTrue(teamBiz.getTotalMinutesCountForAdmin(minutesSearchVO) > 0);
	}

	@Test
	public void getAllMinutes(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		List<MinutesVO> minutes = new ArrayList<MinutesVO>();
		
		minutesSearchVO.setEndIndex(10);
		minutesSearchVO.setStartIndex(1);
		
		minutes = teamBiz.getAllMinutes(minutesSearchVO);
		
		if (minutes != null){
			assertNotNull(minutes);
			assertTrue(minutes.size() >0);
		} else {
			fail("Fail...");
		}
	}
	
	@Test
	public void doSearchListTest(){
		TeamBBSVO teamBBSVO = new TeamBBSVO();
		Paging paging = new Paging(15,15);
		TeamBBSListVO searchedListVO = new TeamBBSListVO();
		
		searchedListVO.setPaging(paging);
		paging.setPageNumber(0 + "");
		
		teamBBSVO.setCreatedDate( "2016/05" );
		teamBBSVO.setMemberId("test02");
		teamBBSVO.setDescript("sdgasdgsdg");
		
		TeamSearchVO searchVO = new TeamSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());	
		
		assertNotNull(teamBiz.doSearchList(teamBBSVO, searchVO));
	}
	
	@Test
	public void getSearchedBBSCountTest(){
		assertNotNull(teamBiz.getSearchedBBSCount());
	}
	
	@Test
	public void getStartYearTest(){
		assertNotNull(teamBiz.getStartYear());
	}
	
	@Test
	public void getEndYearTest(){
		assertNotNull(teamBiz.getEndYear());
	}
	
	@Test
	public void getFileInfoTest(){
		String teamBBSId = "TBBS-20160512-000054";
		assertNotNull(teamBiz.getFileInfo(teamBBSId));
	}
	
}