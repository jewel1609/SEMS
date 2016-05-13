package com.ktds.sems.education.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.sems.education.vo.CategoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
								  "/applicationContext.xml"
								, "/educationContext.xml"
								, "/memberContext.xml"
								, "/menuContext.xml"
								, "/rootContext.xml"})
public class EducationDAOTest {

	@Autowired
	private EducationDAO educationDAO;
	
	@Test
	public void validCategoryIdTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("pppTESTppp");
		categoryVO.setCategoryType("medium");
		
		int result = educationDAO.validCategoryId(categoryVO);
		assertTrue(result == 0);
	}
	
	@Test
	public void validCategoryNameTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryName("pppTESTppp");
		categoryVO.setCategoryType("medium");
		
		int result = educationDAO.validCategoryName(categoryVO);
		assertTrue(result == 0);
	}
	
	@Test
	public void addNewCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("pppTestppp");
		int result = educationDAO.addNewCategory(categoryVO);
		assertTrue(result == 1);
	}
	
	@Test
	public void deleteCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		int result = educationDAO.deleteCategory(categoryVO);
		assertTrue(result == 1);
	}
	
	@Test
	public void getAllLargeCategoryTest(){
		List<CategoryVO> largeCategories = educationDAO.getAllLargeCategory();
		assertNotNull(largeCategories);
	}
	
	@Test
	public void getChildCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryType("large");
		categoryVO.setCategoryId("PAID");
		List<CategoryVO> childCategories = educationDAO.getChildCategory(categoryVO);
		assertNotNull(childCategories);
	}
	
	@Test
	public void modifyCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TSMO");
		categoryVO.setCategoryName("Test modify");
		int result = educationDAO.modifyCategory(categoryVO);
		assertTrue(result == 1);
	}

}
