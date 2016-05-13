package com.ktds.sems.education.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.JsonResponseVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
								  "/applicationContext.xml"
								, "/educationContext.xml"
								, "/memberContext.xml"
								, "/menuContext.xml"
								, "/rootContext.xml"})
public class EducationServiceTest {

	@Autowired
	private EducationService educationService;
	
	@Test
	public void validCategoryIdTest(){
		String categoryId = "PAID";
		String categoryType = "medium";
		String isExist = educationService.validCategoryId(categoryId, categoryType);
		assertTrue(isExist == "true");
	}
	
	@Test
	public void validCategoryNameTest(){
		String categoryName = "유료과정";
		String categoryType = "medium";
		String isExist = educationService.validCategoryName(categoryName, categoryType);
		assertTrue(isExist == "true");
	}
	
	@Test
	public void addNewCategoryTest(){
		String categoryId = "PAID";
		String categoryType = "large";
		List<CategoryVO> categories = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int prevCategoryCount = categories.size();
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(response.getResult());
		
		List<CategoryVO> categories2 = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int nextCategoryCount = categories2.size();
		assertTrue(nextCategoryCount - prevCategoryCount == 1);
	}
	
	@Test
	public void deleteCategoryTest(){
		String categoryId = "PAID";
		String categoryType = "large";
		List<CategoryVO> categories = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int prevCategoryCount = categories.size();
		
		String deleteCategoryId = "TEST";
		String deleteCategoryType = "medium";
		
		String response = educationService.deleteCategory(deleteCategoryId, deleteCategoryType);
		assertNotNull(response);
		assertTrue(Boolean.parseBoolean(response));
		
		categories = (ArrayList) educationService.getChildCategory(categoryId, categoryType).getData();
		int nextCategoryCount = categories.size();
		assertTrue(prevCategoryCount - nextCategoryCount == 1);
	}
	
	@Test
	public void addNewCategoryTestWithError1(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
//		categoryVO.setCategoryId("TEST");
		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(!response.getResult());
		
		assertTrue(errors.getErrorCount() == 1);
		
	}
	
	@Test
	public void addNewCategoryTestWithError2(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TEST");
//		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(!response.getResult());
		
		assertTrue(errors.getErrorCount() == 1);
		
	}
	
	@Test
	public void addNewCategoryTestWithError3(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("PAID");
		categoryVO.setCategoryType("medium");
//		categoryVO.setCategoryId("TEST");
//		categoryVO.setCategoryName("Test");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO response = educationService.addNewCategory(categoryVO, errors);
		assertNotNull(response);
		assertTrue(!response.getResult());
		
		assertTrue(errors.getErrorCount() == 2);
		
	}
	
	@Test
	public void viewCategoryPage(){
		ModelAndView view = educationService.viewCategoryPage();
		String viewName = view.getViewName();
		assertNotNull(view);
		assertTrue(viewName == "education/category");
	}
	
	@Test
	public void getChildCategoryTest(){
		String categoryId = "PAID";
		String categoryType = "large";
		JsonResponseVO jsonResponseVO = educationService.getChildCategory(categoryId, categoryType);
		assertTrue(jsonResponseVO.getResult());
	}
	
	@Test
	public void modifyCategoryTest(){
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryId("TSMO");
		categoryVO.setCategoryName("Test modify");
		
		BindingResult errors = new BeanPropertyBindingResult(categoryVO,  "newCategoryForm");
		CategoryVOValidator validator = new CategoryVOValidator();
		validator.validate(categoryVO, errors);
		
		JsonResponseVO jsonResponseVO = educationService.modifyCategory(categoryVO, errors);
		assertNotNull(jsonResponseVO);
		assertTrue(jsonResponseVO.getResult());
	}
	
	public class CategoryVOValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return CategoryVO.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			CategoryVO categoryVO = (CategoryVO) target;
			
			String categoryId = categoryVO.getCategoryId();
			if ( categoryId == null || categoryId.length() == 0 ) {
				errors.rejectValue("categoryId", "field.required", "error default message");
			}
			
			String categoryName = categoryVO.getCategoryName();
			if ( categoryName == null || categoryName.length() == 0 ) {
				errors.rejectValue("categoryName", "field.required", "error default message");
			}
		}
		
	}

}
