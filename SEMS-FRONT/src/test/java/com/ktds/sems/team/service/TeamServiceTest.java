package com.ktds.sems.team.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.team.biz.TeamBiz;
import com.ktds.sems.team.vo.TeamBBSVO;
import com.ktds.sems.Testable;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamsListVO;
import com.ktds.sems.team.vo.TeamsListsVO;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.team.vo.MinutesSearchVO;

@Transactional
public class TeamServiceTest extends SemsTestCase {
	
	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamBiz teamBiz;

	@Before
	public void setUp() {
		
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				
				// Team_List 테이블에 데이터 넣기
				String[] insertMemberIds = {"testMemberId"};
				String educationId = "testEducationId";
				String teamName = "testTeamName";
				String teamListId = "testTeamListId";
				
				teamService.massiveInsertMember(insertMemberIds, educationId, teamName, teamListId);
			}
		});
	}
	
	@After
	public void tearDown() {
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				
				// 넣은 Team_List 데이터 지우기
				String memberId = "testMemberId";
				String teamName = "testTeamName";
				teamService.doDeleteTeamListByMemberId(memberId, teamName);
			}
		});
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void viewModifyPageTest(){
		String teamBBSId = "TBBS-20160501-000050";
		TeamBBSVO teamBBS = teamBiz.getTeamBBS(teamBBSId);
		assertNotNull(teamBBS);
		
		ModelAndView view = teamService.viewModifyPage(teamBBSId);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/modifyBBS");
		}
		else {
			fail("fail");
		}
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void checkPasswordTest() {
		
		String teamBBSId = "TBBS-20160501-000050";
		String type = "modify";
		
		ModelAndView view = teamService.checkPassword(teamBBSId, type);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/checkPassword");
		}
		else {
			fail("fail");
		}
				
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void getSaltByIdTest(){
		String sessionId = "test02";
		
		String view = teamService.getSaltById(sessionId);
		assertNotNull(view);
	}


	/**
	 * 전씨
	 */
	@Test
	public void getPasswordByIdTest(){
		String sessionId = "test02";
		
		String view = teamService.getPasswordById(sessionId);
		assertNotNull(view);
	}
	
	/**
	 * 지한오빠
	 */
	@Test
	public void doDeleteBBSTest(){
		
	}
	
	/**
	 * 전씨
	 */
	@Test
	public void isReplyByTeamBBSIdTest() {
		String teamBBSId = "TBBS-20160512-000054";
		
		boolean result = teamBiz.isReplyByTeamBBSId(teamBBSId);
		assertTrue(result);
	}
	
	@Test
	public void getAllTeamListPageTest() {

		TeamSearchVO teamSearchVO = new TeamSearchVO();
		teamSearchVO.setPageNo(0);
		teamSearchVO.setSearchType("teamId");
		teamSearchVO.setSearchKeyword("0");

		ModelAndView view = teamService.getAllTeamListPage(teamSearchVO);

		if (view != null) {

			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "team/teamList");

		} else {
			fail("fail");
		}
	}

	@Test
	public void getOneTeamDetail() {

		String teamListId = "teamListId";

		ModelAndView view = teamService.getOneTeamDetail(teamListId);
		if(view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			
			TeamsListsVO teamsListsVO = (TeamsListsVO) view.getModel().get("teamsListsVO");
			List<TeamsListVO> teamsListList = teamsListsVO.getTeamsListsVO();

			if( teamsListList != null) {
				for (TeamsListVO teamsListVO : teamsListList) {
					assertNotNull(teamsListVO.getTeamListId());
				}
			} else {
				fail("fail");
			}
			
		} else {
			fail("fail");
		}
	}
	
	@Test
	public void getAllEduMember(){
		
		ModelAndView view = new ModelAndView();
		String educationId = "junitId";
		MockHttpSession session = new MockHttpSession();
		
		MemberTypeVO memberType = new MemberTypeVO();
		memberType.setCdId("TR");
		session.setAttribute(Session.MEMBER_TYPE, memberType.getCdId());
		
		view.setViewName("education/registTeam");
		
		if (teamService.getAllEduMember(educationId, session) != null) {
			assertEquals(view.getViewName(), 
					teamService.getAllEduMember(educationId, session).getViewName());
		} else {
			fail("fail");
		}
	}
	
//	@Test
//	public void massiveInsertMember(){
//		ModelAndView view = new ModelAndView();
//		String educationId = "JunitTest";
//		String teamName = "JunitTest";
//		String[] insertMemberIds = {"JunitTest","JunitTest2"};
//		String teamListId = "JunitTest";
//		
//		view.setViewName("team/teamBoard");
//		if( teamService.massiveInsertMember(insertMemberIds, educationId, teamName, teamListId) != null ){
//			assertEquals(view.getViewName(), teamService.massiveInsertMember(insertMemberIds, educationId, teamName, teamListId).getViewName());
//		} else {
//			fail("fail");
//		}
//	}

	@Test
	public void getAllMinutes(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		MockHttpSession session = new MockHttpSession();
		ModelAndView view = new ModelAndView();
		MemberTypeVO memberType = new MemberTypeVO();
		memberType.setCdId("TR");
		session.setAttribute(Session.MEMBER_TYPE, memberType.getCdId());
		
		view.setViewName("team/minutesList");
		
		if(teamService.getAllMinutes(minutesSearchVO, 0, session) != null){
			assertEquals(view.getViewName(), teamService.getAllMinutes(minutesSearchVO, 0, session).getViewName());
		} else {
			fail("fail");
		}
	}
	
}
