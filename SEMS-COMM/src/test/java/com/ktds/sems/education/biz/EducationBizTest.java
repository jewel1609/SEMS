package com.ktds.sems.education.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.TimeVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EducationBizTest extends SemsTestCase{

	@Autowired
	private EducationBiz educationBiz;
	
	//costTime
	@Test
	public void ainsertEduCostTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		boolean insertEduCost = educationBiz.insertEduCost(cost);
		
		assertTrue(insertEduCost == true);
	}
	
	@Test
	public void modifyEduCostTest() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES2");
		
		boolean modifyEduCost = educationBiz.modifyEduCost(cost);
		
		assertTrue(modifyEduCost == true);
	}
	
	@Test
	public void getAllEduCostTest() {
		List<CostVO> costList = educationBiz.getAllEduCost();
		assertNotNull(costList);
		assertTrue(costList.size() >=0);
	}

	@Test
	public void zdeleteEduCostTest() {
		boolean deleteEduCost = educationBiz.deleteEduCost("TES1");
		assertTrue(deleteEduCost == true);
	}
	
	@Test
	public void getEduCostByCdIdTest() {
		CostVO cost = educationBiz.getEduCostByCdId("TES1");
		assertNotNull(cost);
	}
	
	@Test
	public void bisExistCostTestError1() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		boolean isExistCost = educationBiz.isExistCost(cost);
		assertTrue(isExistCost == true);
	}
	
	@Test
	public void bisExistCostTestError2() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES2");
		
		boolean isExistCost = educationBiz.isExistCost(cost);
		assertTrue(isExistCost == true);
	}
	
	@Test
	public void bisExistCostTestError3() {
		CostVO cost = new CostVO();
		cost.setCdId("TES2");
		cost.setCdNm("TES1");
		
		boolean isExistCost = educationBiz.isExistCost(cost);
		assertTrue(isExistCost == true);
	}
	
	@Test
	public void bisExistCostTestError4() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		boolean isExistCost = educationBiz.isExistCost(cost);
		assertTrue(isExistCost == true);
	}
	
	@Test
	public void bisExistCostNmTestError1() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES3");
		
		boolean isExistCostNm = educationBiz.isExistCostNm(cost);
		assertTrue(isExistCostNm == false);
	}
	
	@Test
	public void bisExistCostNmTestError2() {
		CostVO cost = new CostVO();
		cost.setCdId("TES1");
		cost.setCdNm("TES1");
		
		boolean isExistCostNm = educationBiz.isExistCostNm(cost);
		assertTrue(isExistCostNm == true);
	}
	
	// timeTest
	@Test
	public void ainsertEduTimeTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		boolean insertEduTime = educationBiz.insertEduTime(time);
		assertTrue(insertEduTime == true);
	}
	
	@Test
	public void modifyEduTimeTest() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES2");
		
		boolean modifyEduTime = educationBiz.modifyEduTime(time);
		assertTrue(modifyEduTime == true);
	}
	
	@Test
	public void getAllEduTimeTest() {
		List<TimeVO> timeList = educationBiz.getAllEduTime();
		assertNotNull(timeList);
		assertTrue(timeList.size() >=0);
	}

	@Test
	public void zdeleteEduTimeTest() {
		boolean deleteEduTime = educationBiz.deleteEduTime("TES1");
		assertTrue(deleteEduTime == true);
	}
	
	@Test
	public void bisExistTimeTestError1() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		boolean isExistTime = educationBiz.isExistTime(time);
		assertTrue(isExistTime == true);
	}
	
	@Test
	public void bisExistTimeTestError2() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES2");
		
		boolean isExistTime = educationBiz.isExistTime(time);
		assertTrue(isExistTime == true);
	}
	
	@Test
	public void bisExistTimeTestError3() {
		TimeVO time = new TimeVO();
		time.setCdId("TES2");
		time.setCdNm("TES1");
	
		boolean isExistTime = educationBiz.isExistTime(time);
		assertTrue(isExistTime == true);
	}
	
	@Test
	public void bisExistTimeTestError4() {
		TimeVO time = new TimeVO();
		time.setCdId("TES2");
		time.setCdNm("TES2");
		
		boolean isExistTime = educationBiz.isExistTime(time);
		assertTrue(isExistTime == false);
	}
	
	@Test
	public void bisExistTimeNmTestError1() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES3");
		
		boolean isExistTimeNm = educationBiz.isExistTimeNm(time);
		assertTrue(isExistTimeNm == false);
	}
	
	@Test
	public void bisExistTimeNmTestError2() {
		TimeVO time = new TimeVO();
		time.setCdId("TES1");
		time.setCdNm("TES1");
		
		boolean isExistTimeNm = educationBiz.isExistTimeNm(time);
		assertTrue(isExistTimeNm == true);
	}
	
	@Test
	public void validCategoryIdTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("ZCS");
		categoryVO.setCategoryType("medium");
		Boolean isExist = educationBiz.validCategoryId(categoryVO);
		assertTrue(isExist);
	}
	
	@Test
	public void validCategoryNameTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryName("데이터베이스");
		categoryVO.setCategoryType("medium");
		Boolean isExist = educationBiz.validCategoryName(categoryVO);
		assertTrue(isExist);
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
		categoryVO.setCategoryId("ZCS");
		categoryVO.setCategoryType("large");
		List<CategoryVO> childCategories = educationBiz.getChildCategory(categoryVO);
		assertNotNull(childCategories);
	}
	
	@Test
	public void modifyCategoryTest(){
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId("ZCS");
		categoryVO.setCategoryType("medium");
		categoryVO.setCategoryName("데이터베이스");
		boolean result = educationBiz.modifyCategory(categoryVO);
		assertTrue(result);
	}
	

}
