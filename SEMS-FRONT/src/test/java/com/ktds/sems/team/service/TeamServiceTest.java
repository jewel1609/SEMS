package com.ktds.sems.team.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamsListVO;
import com.ktds.sems.team.vo.TeamsListsVO;

@Transactional
public class TeamServiceTest extends SemsTestCase {

	@Autowired
	private TeamService teamService;

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
				teamService.doDeleteTeamListByMemberId(memberId);
			}
		});
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
}
