package com.ktds.sems.cooperation.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.cooperation.dao.CooperationDAO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

public class CooperationControllerTest extends SemsTestCase {

	@Autowired
	private CooperationController cooperationController;
	@Autowired
	private CooperationDAO cooperationDAO;
	
	@Test
	public void viewRegistCooPageTest() {
		ModelAndView view = cooperationController.viewRegistCooPage();
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/registerCoo");
		
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
		cooperationVO.setCooperationType("COO_COPR");
		BindingResult errors = new BeanPropertyBindingResult(cooperationVO,"registerForm");
		ModelAndView view = cooperationController.doRegisterCoo(cooperationVO, errors);
		assertNotNull(view);
	}
	
	@Test
	public void viewModifyCooPageTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		ModelAndView view = cooperationController.viewModifyCooPage(cooperationId);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/modifyCoo");
	}
	
	@Test
	public void doModifyCooTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationId(cooperationId);
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setCooperationNumber("JunitTest2");
		
		BindingResult errors = new BeanPropertyBindingResult(cooperationVO,"registerForm");
		ModelAndView view = cooperationController.doModifyCoo(cooperationVO, errors);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/cooDetail/"+cooperationId);
	}
	
	@Test
	public void viewCooListPageTest() {
		int pageNo = 1;

		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setEndIndex(5);
		searchVO.setPageNo(1);
		searchVO.setSearchKeyword("e");
		searchVO.setSearchType("1");
		searchVO.setStartIndex(1);
		
		ModelAndView view = cooperationController.viewCooListPage(searchVO, pageNo);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationList");
	}
	@Test
	public void viewCooDetailPageTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		ModelAndView view = cooperationController.viewCooDetailPage(cooperationId);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationDetail");
	}
	@Test
	public void doDeleteCooperationTest() {
		String cooperationId = cooperationDAO.getOneCooperationId();
		String result = cooperationController.doDeleteCooperation(cooperationId);
		assertEquals(result, "redirect:/cooList");
	}
}
