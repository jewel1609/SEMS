package com.ktds.sems.education.biz;

import static org.junit.Assert.assertNotNull;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/educationContext.xml", "/fileContext.xml", "/rootContext.xml", "/memberContext.xml" })
public class EducationBizTest {

	@Autowired
	private EducationBiz educationBiz;

	@Test
	public void getOneEducationTest() {
		String educationId = "ED-20160512-000069";
		EducationVO educationVO = educationBiz.getOneEducation(educationId);
		assertNotNull(educationVO);
	}

}
