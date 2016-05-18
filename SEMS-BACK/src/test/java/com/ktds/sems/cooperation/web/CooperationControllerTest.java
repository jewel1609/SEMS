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
import com.ktds.sems.cooperation.vo.CooperationVO;

public class CooperationControllerTest extends SemsTestCase {

	@Autowired
	private CooperationController cooperationController;
	
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
		cooperationVO.setCooperationType("JunitTest");
		BindingResult errors = new BeanPropertyBindingResult(cooperationVO,"registerForm");
		ModelAndView view = cooperationController.doRegisterCoo(cooperationVO, errors);
		assertNotNull(view);
	}
	
	@Test
	public void viewModifyCooPageTest() {
		String cooperationId = "JunitTest";
		ModelAndView view = cooperationController.viewModifyCooPage(cooperationId);
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/modifyCoo");
	}
	
	@Test
	public void doModifyCooTest() {
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setCooperationId("CO-20160517-000008");
		cooperationVO.setCooperationTitle("JunitTest");
		cooperationVO.setCooperationLocation("JunitTest");
		cooperationVO.setCooperationNumber("JunitTest");
		cooperationVO.setRepresentativeName("JunitTest");
		cooperationVO.setManagerPhoneNumber("JunitTest");
		cooperationVO.setCooperationPhoneNumber("JunitTest");
		cooperationVO.setManagerEmail("JunitTest");
		cooperationVO.setCooperationType("JunitTest");
		
		BindingResult errors = new BeanPropertyBindingResult(cooperationVO,"registerForm");
		ModelAndView view = cooperationController.doModifyCoo(cooperationVO, errors);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationDetail");
	}
	
	@Test
	public void viewCooListPageTest() {
		int pageNo = 1;
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("searchType", "1");
		request.setParameter("searchKeyword", "e");
		
		ModelAndView view = cooperationController.viewCooListPage(pageNo, request);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationList");
	}
	@Test
	public void viewCooDetailPageTest() {
		ModelAndView view = cooperationController.viewCooDetailPage("JunitTest");
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationDetail");
	}
	@Test
	public void doDeleteCooperationTest() {
		String result = cooperationController.doDeleteCooperation("CO-20160516-000003");
		assertEquals(result, "redirect:/cooList");
	}
}
