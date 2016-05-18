package com.ktds.sems.cooperation.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/fileContext.xml"
		,"/memberContext.xml", "/rootContext.xml", "/cooperationContext.xml" })
public class CooperationBizTest {

	@Autowired
	private CooperationBiz cooperationBiz;
	
	@Test
	public void getTotalCooperationCountTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("searchType", "1");
		request.setParameter("searchKeyword", "e");
		int result = cooperationBiz.getTotalCooperationCount(request);
		assertNotNull(result);
	}
	
	@Test
	public void doRegisterCooTest() {
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setCooperationNumber("JunitTest");
		cooperationVO.setRepresentativeName("JunitTest");
		cooperationVO.setManagerPhoneNumber("JunitTest");
		cooperationVO.setCooperationPhoneNumber("JunitTest");
		cooperationVO.setManagerEmail("JunitTest");
		cooperationVO.setCooperationType("JunitTest");
		boolean result = cooperationBiz.doRegisterCoo(cooperationVO);
		assertTrue(result);
	}

	@Test
	public void getAllCooperationTest() {
		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setPageNo(10);
		searchVO.setStartIndex(10);
		searchVO.setEndIndex(10);
		searchVO.setSearchKeyword("e");
		searchVO.setSearchKeyword("1");
		List<CooperationVO> resultList = cooperationBiz.getAllCooperation(searchVO);
		assertNotNull(resultList);
	}
	
	@Test
	public void getOneCooperationTest() {
		String cooperationId ="CO-20160518-000021";
		CooperationVO coo = cooperationBiz.getOneCooperation(cooperationId);
		assertNotNull(coo);
	}
	@Test
	public void doDeleteCooperationTest() {
		String cooperationId ="CO-20160518-000021";
		boolean result = cooperationBiz.doDeleteCooperation(cooperationId);
		assertTrue(result);
	}
	@Test
	public void isExistCooperationTitleTest() {
		String cooperationTitle ="tod";
		boolean result = cooperationBiz.isExistCooperationTitle(cooperationTitle);
		assertTrue(result);
	}
	@Test
	public void doModifyCooTest() {
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationId("CO-20160518-000022");
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setManagerEmail("JunitTest");
		cooperationVO.setCooperationType("JunitTest1");
		boolean result = cooperationBiz.doModifyCoo(cooperationVO);
		assertTrue(result);
	}
	
	
}
