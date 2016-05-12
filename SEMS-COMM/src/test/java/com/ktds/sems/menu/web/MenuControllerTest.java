package com.ktds.sems.menu.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml", "/menuContext.xml" })
public class MenuControllerTest {

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
