package com.ktds.sems.education.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.sems.SemsTestCase;

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

}
