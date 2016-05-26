package com.ktds.sems.team.dao;

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
import com.ktds.sems.team.vo.TeamSearchVO;
import com.ktds.sems.team.vo.TeamVO;
import com.ktds.sems.team.vo.TeamsListVO;

@Transactional
public class TeamDAOTest extends SemsTestCase {
	@Autowired
	private TeamDAO teamDAO;

	@Before
	public void setUp() {

		testHelper(new Testable() {

			@Override
			public void preparedTest() {

				if(!teamDAO.bulidTeam("testEducationId", "testTeamName") ) {
					throw new RuntimeException("일시적인 에러가 발생했습니다.");
				}

				TeamsListVO teamsListVO = new TeamsListVO();
				teamsListVO.setTeamListId("testTeamListId");
				teamsListVO.setMbrId("testMemberId");

				if(!teamDAO.insertMember(teamsListVO) ) {
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
				teamDAO.doDeleteTeamListByMemberId("testMemberId");
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

		List<TeamVO> teamList = teamDAO.getAllTeamList(searchVO);
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
	public void getOneTeamDetailTest(){
		String teamId = "testTeamListId";
		List<TeamsListVO> teamsList = teamDAO.getOneTeamDetail(teamId);
		if (teamsList != null) {
			for (TeamsListVO teamsListVO : teamsList) {
				assertNotNull(teamsListVO.getMbrId());
				assertNotNull(teamsListVO.getTeamId());
				assertNotNull(teamsListVO.getTeamListId());
			}

		}else {
			fail("fail");
		}
	}

	@Test
	public void getAllEduMember(){
		String educationId = "junitId";
		List<MemberVO> members = new ArrayList<MemberVO>();
		members = teamDAO.getAllEduMember(educationId);

		if (members != null ) {
			assertNotNull(members);
			assertTrue(members.size() > 0 );
		}
		else {
			fail("Fail...");
		}
	}

	@Test
	public void getTotalMinutesCountForAdmin(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		assertTrue(teamDAO.getTotalMinutesCountForAdmin(minutesSearchVO) > 0);
	}

	@Test
	public void getAllMinutes(){
		MinutesSearchVO minutesSearchVO = new MinutesSearchVO();
		List<MinutesVO> minutes = new ArrayList<MinutesVO>();

		minutesSearchVO.setEndIndex(10);
		minutesSearchVO.setStartIndex(1);

		minutes = teamDAO.getAllMinutes(minutesSearchVO);

		if (minutes != null ) {
			assertNotNull(minutes);
			assertTrue(minutes.size() > 0 );
		} else {
			fail("Fail...");
		}
	}
}
