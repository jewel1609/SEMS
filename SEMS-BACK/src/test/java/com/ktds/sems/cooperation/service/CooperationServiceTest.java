package com.ktds.sems.cooperation.service;

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

public class CooperationServiceTest extends SemsTestCase {

	@Autowired
	private CooperationService cooperationService;
	
	@Test
	public void viewRegistCooPageTest() {
		ModelAndView view = cooperationService.viewRegistCooPage();
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
		ModelAndView view = cooperationService.doRegisterCoo(cooperationVO, errors);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "redirect:/cooList");
	}

	@Test
	public void getAllCooperationListTest() {
		int pageNo = 1;
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("searchType", "1");
		request.setParameter("searchKeyword", "e");
		
		ModelAndView view = cooperationService.getAllCooperationList(pageNo, request);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationList");
	}
	@Test
	public void getOneCooperationTest() {
		ModelAndView view = cooperationService.getOneCooperation("JunitTest");
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationDetail");
	}
	@Test
	public void doDeleteCooperationTest() {
		String result = cooperationService.doDeleteCooperation("CO-20160516-000003");
		assertEquals(result, "redirect:/cooList");
	}
	@Test
	public void viewModifyCooPageTest() {
		ModelAndView view = cooperationService.viewModifyCooPage("JunitTest");
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
		ModelAndView view = cooperationService.doModifyCoo(cooperationVO, errors);
		
		String viewName = view.getViewName();
		assertNotNull(viewName);
		assertEquals(viewName, "cooperation/cooperationDetail");
	}

}
