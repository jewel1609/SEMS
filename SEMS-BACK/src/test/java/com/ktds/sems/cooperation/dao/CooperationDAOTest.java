package com.ktds.sems.cooperation.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/fileContext.xml"
		,"/memberContext.xml", "/rootContext.xml", "/cooperationContext.xml" })
public class CooperationDAOTest {

	@Autowired
	private CooperationDAO cooperationDAO;
	
	@Test
	public void getTotalCooperationTest() {
		Map<String,String> searchInfo = new HashMap<String,String>();
		searchInfo.put("searchType", "1");
		searchInfo.put("searchKeyword", "e");
		
		int result = cooperationDAO.getTotalCooperationCount(searchInfo);
		assertNotNull(result);
	}
	
	@Test
	public void nextCooSeqTest() {
		int result = cooperationDAO.nextCooSeq();
		assertNotNull(result);
	}
	@Test
	public void nowDateTest() {
		String result = cooperationDAO.nowDate();
		assertNotNull(result);
	}
	@Test
	public void doRegisterCooTest() {
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationId("JunitTest");
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setCooperationNumber("JunitTest");
		cooperationVO.setRepresentativeName("JunitTest");
		cooperationVO.setManagerPhoneNumber("JunitTest");
		cooperationVO.setCooperationPhoneNumber("JunitTest");
		cooperationVO.setManagerEmail("JunitTest");
		cooperationVO.setCooperationType("JunitTest");
		int result = cooperationDAO.doRegisterCoo(cooperationVO);
		assertNotNull(result);
	}
	@Test
	public void isExistCooperationTitleTest() {
		String result = cooperationDAO.isExistCooperationTitle("JunitTest");
		assertNotNull(result);
	}
	@Test
	public void getTotalCooperationCountTest() {
		int result = cooperationDAO.getTotalCooperationCount();
		assertNotNull(result);
	}
	@Test
	public void getAllCooperationTest() {
		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setPageNo(10);
		searchVO.setStartIndex(10);
		searchVO.setEndIndex(10);
		searchVO.setSearchKeyword("e");
		searchVO.setSearchKeyword("1");
		List<CooperationVO> resultList = cooperationDAO.getAllCooperation(searchVO);
		assertNotNull(resultList);
		assertTrue(resultList.size()>0);
	}
	@Test
	public void getOneCooperationTest() {
		CooperationVO cooperationVO = cooperationDAO.getOneCooperation("JunitTest");
		assertNotNull(cooperationVO);
	}
	
	@Test
	public void doDeleteCooperation() {
		int result = cooperationDAO.doDeleteCooperation("JunitTest");
		assertNotNull(result);
	}
	
	
	
	

}
