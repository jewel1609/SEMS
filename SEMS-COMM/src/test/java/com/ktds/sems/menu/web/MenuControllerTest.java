package com.ktds.sems.menu.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;

public class MenuControllerTest extends SemsTestCase{

	@Autowired
	private MenuController menuController;
	
	@Test
	public void viewMenuPageTest() {
		
		ModelAndView view = menuController.viewMenuPage();
		assertNotNull(view);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "menu/menuManage");
		}
		else {
			fail("view is null");
		}
		
	}
	
	@Test
	public void upMenuListTest() {
		
		int sortNumber = 1;
		int codeId = 2;
		
		String upMenuListTest = menuController.upMenuList(sortNumber, codeId);
		assertNotNull(upMenuListTest);
		 
		if ( upMenuListTest != null ) {
			assertEquals("redirect:/menu", upMenuListTest);
		}
		else {
			fail("String is null");
		}
		
	}
	
	@Test
	public void downMenuList() {
		
		int sortNumber = 1;
		int codeId = 2;
		
		String downMenuList = menuController.downMenuList(sortNumber, codeId);
		assertNotNull(downMenuList);
		
		if ( downMenuList != null ) {
			assertEquals("redirect:/menu", downMenuList);
		}
		else {
			fail("String is null");
		}
	}
	
}
