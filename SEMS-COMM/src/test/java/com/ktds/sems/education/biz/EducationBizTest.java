package com.ktds.sems.education.biz;

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
public class EducationBizTest {

	@Autowired
	private EducationBiz educationBiz;
	
	@Test
	public void validCategoryIdTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("pppTestppp");
		categoryVO.setCategoryType("medium");
		Boolean isExist = educationBiz.validCategoryId(categoryVO);
		assertTrue(!isExist);
	}
	
	@Test
	public void validCategoryNameTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryName("pppTestppp");
		categoryVO.setCategoryType("medium");
		Boolean isExist = educationBiz.validCategoryName(categoryVO);
		assertTrue(!isExist);
	}
	
	@Test
	public void addNewCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("pppTestppp");
		boolean result = educationBiz.addNewCategory(categoryVO);
		assertTrue(result);
	}
	
	@Test
	public void deleteCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("pppTestppp");
		boolean result = educationBiz.deleteCategory(categoryVO);
		assertTrue(result);
	}
	
	@Test
	public void getAllLargeCategoryTest(){
		List<CategoryVO> largeCategories = educationBiz.getAllLargeCategory();
		assertNotNull(largeCategories);
	}
	
	@Test
	public void getChildCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("PAID");
		categoryVO.setCategoryType("large");
		List<CategoryVO> childCategories = educationBiz.getChildCategory(categoryVO);
		assertNotNull(childCategories);
	}
	
	@Test
	public void modifyCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("TSMO");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryName("Test");
		boolean result = educationBiz.modifyCategory(categoryVO);
		assertTrue(result);
	}

}
