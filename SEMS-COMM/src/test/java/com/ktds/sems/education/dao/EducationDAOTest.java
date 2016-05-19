package com.ktds.sems.education.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EducationDAOTest extends SemsTestCase{

	@Autowired
	private EducationDAO educationDAO;
	
	// costTest
	@Test
	public void ainsertEduCostTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		int insertEduCost = educationDAO.insertEduCost(cost);
		assertTrue(insertEduCost > 0);
	}
	
	@Test
	public void modifyEduCostTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES2");
		
		int modifyEduCost = educationDAO.modifyEduCost(cost);
		assertTrue(modifyEduCost >0);
	}
	
	@Test
	public void getAllEduCostTest() {
		List<CostVO> costList = educationDAO.getAllEduCost();
		assertNotNull(costList);
		assertTrue(costList.size() >=0);
	}

	@Test
	public void zdeleteEduCostTest() {
		int deleteEduCost = educationDAO.deleteEduCost("TES1");
		assertTrue(deleteEduCost > 0);
	}
	
	@Test
	public void getEduCostByCdIdTest() {
		CostVO cost = educationDAO.getEduCostByCdId("TES1");
		assertNotNull(cost);
	}
	
	@Test
	public void bisExistCostTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		int isExistCost = educationDAO.isExistCost(cost);
		assertTrue(isExistCost >0);
	}
	
	@Test
	public void bisExistCostNmTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		int isExistCostNm = educationDAO.isExistCostNm(cost);
		assertTrue(isExistCostNm > 0);
	}
	
	// timeTest
	@Test
	public void ainsertEduTimeTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		int insertEduTime = educationDAO.insertEduTime(time);
		assertTrue(insertEduTime > 0);
	}
	
	@Test
	public void modifyEduTimeTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES2");
		
		int modifyEduTime = educationDAO.modifyEduTime(time);
		assertTrue(modifyEduTime >0);
	}
	
	@Test
	public void getAllEduTimeTest() {
		List<TimeVO> timeList = educationDAO.getAllEduTime();
		assertNotNull(timeList);
		assertTrue(timeList.size() >=0);
	}

	@Test
	public void zdeleteEduTimeTest() {
		int deleteEduTime = educationDAO.deleteEduTime("TES1");
		assertTrue(deleteEduTime > 0);
	}
	
	@Test
	public void getEduTimeByCdIdTest() {
		TimeVO time = educationDAO.getEduTimeByCdId("TES1");
		assertNotNull(time);
	}
	
	@Test
	public void bisExistTimeTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		int isExistTime = educationDAO.isExistTime(time);
		assertTrue(isExistTime >0);
	}
	
	@Test
	public void bisExistTimeNmTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		int isExistTimeNm = educationDAO.isExistTimeNm(time);
		assertTrue(isExistTimeNm > 0);
	}
	
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
