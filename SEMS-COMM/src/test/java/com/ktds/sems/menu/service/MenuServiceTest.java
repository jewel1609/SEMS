package com.ktds.sems.menu.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.menu.vo.MenuManageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml", "/menuContext.xml" })
public class MenuServiceTest {

	@Autowired
	private MenuService menuService;
	
	@Test
	public void viewMenuManagePageTest() {
		
		ModelAndView view = menuService.viewMenuManagePage();
		assertNotNull(view);
		
		List<MenuManageVO> menuList = (List<MenuManageVO>) view.getModelMap().get("menuList");
		assertNotNull(menuList);
		assertTrue(menuList.size() > 0);
		
	}
	
	@Test
	public void upMenuListTest() {
		
		int sortNumber = 1;
		int codeId = 2;
		
		String upMenuListTest = menuService.upMenuList(sortNumber, codeId);
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
		
		String downMenuList = menuService.downMenuList(sortNumber, codeId);
		assertNotNull(downMenuList);
		
		if ( downMenuList != null ) {
			assertEquals("redirect:/menu", downMenuList);
		}
		else {
			fail("String is null");
		}
	}

}
