package com.ktds.sems.menu.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.menu.vo.MenuManageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/memberContext.xml", "/rootContext.xml", "/menuContext.xml" })
public class MenuDAOTest {

   @Autowired
   private MenuDAO menuDAO;
   
   @Test
   public void getMenuCategoryListTest() {
      List<MenuManageVO> menu = menuDAO.getMenuCategoryList();
      assertNotNull(menu);
      assertTrue(menu.size() > 0);
   }

}