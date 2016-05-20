package com.ktds.sems.education.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

@Transactional
public class EducationServiceTest extends SemsTestCase {

	@Autowired
	private EducationService educationeService;
	
	@Test
	public void getOneEducationForUpdateTest(){
		String educationId = "ED-20160513-000173";
		ModelAndView view = educationeService.getOneEducationForUpdate(educationId);
		String viewName = view.getViewName();
		assertNotNull(view);
		assertTrue(viewName == "education/update");
	}

	@Test
	public void doEducationModifyTest() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-08-20");
		educationVO.setEndDate("2016-08-20");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		assertNotNull(view);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/detail/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError1() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		//educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError2() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		//educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError3() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		//educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError4() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		//educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/detail/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError5() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		//educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError6() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		//educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError7() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		//educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError8() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		//educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError9() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		//educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError10() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		//educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError11() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		//educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError12() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		//educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	@Test
	public void doEducationModifyTestWithError14() {
		EducationVO educationVO = new EducationVO();
		
		educationVO.setEducationId("ED-20160513-000173");
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(30);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("JUNIT...");
		educationVO.setEndDate("JUNIT...");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		//educationVO.setCost("CSTC");
		
		BindingResult errors = new BeanPropertyBindingResult(educationVO,"modifyForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		
		Path path = Paths.get("");
		String name = "file";
		String originalFileName = "file";
		String contentType = "text/plain";
		
		byte[] content = null;
		
		try {
		    content = Files.readAllBytes(path);
		}
		catch (final IOException e) {
			
		}
		
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.modifyNewEducation(educationVO, errors, request);
		
		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/update/ED-20160513-000173");
		} else {
			fail("fail...");
		}
	}
	
	/*
	 * D:\\핸드폰.xlsx 해당 파일이 있어야 insert 가능합니다.
	 * 
	 * 
	@Test
	public void writeNewEducationTest() {
		// List 보는건 차 후에 test 시도

		// EducationId 가 없을 경우 ..
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");

		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/list");
		} else {
			fail("Fail");
		}
	}
	
	@Test
	public void doWriteActionTestWithError() {
		// List 보는건 차 후에 test 시도

		// EducationCategory 가 없을 경우 ..
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError2() {
		// List 보는건 차 후에 test 시도

		// setEducationTitle 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);
		
		

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError3() {
		// List 보는건 차 후에 test 시도

		// MemberId 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}


	@Test
	public void doWriteActionTestWithError5() {
		// List 보는건 차 후에 test 시도

		// EducationLocation 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError6() {
		// List 보는건 차 후에 test 시도

		// EducationCurriculum 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError7() {
		// List 보는건 차 후에 test 시도

		// EducationIntroduce 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError8() {
		// List 보는건 차 후에 test 시도

		// StartDate 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰.xlsx";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError9() {
		// List 보는건 차 후에 test 시도

		// EndDate 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰.xlsx";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError10() {
		// List 보는건 차 후에 test 시도

		// StartTime 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError11() {
		// List 보는건 차 후에 test 시도

		// EndTime 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEducationType("TIMM");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);

		HttpSession session = null;
		session = request.getSession();

		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError12() {
		// List 보는건 차 후에 test 시도

		// EducationType 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setCost("CSTC");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}

	@Test
	public void doWriteActionTestWithError13() {
		// List 보는건 차 후에 test 시도

		// Cost 없는경우
		EducationVO educationVO = new EducationVO();
		educationVO.setEducationCategory("ZCS");
		educationVO.setEducationTitle("JUNIT...");
		educationVO.setMemberId("JUNIT...");
		educationVO.setMaxMember(32);
		educationVO.setEducationLocation("JUNIT...");
		educationVO.setEducationCurriculum("JUNIT...");
		educationVO.setEducationIntroduce("JUNIT...");
		educationVO.setStartDate("2016-01-01");
		educationVO.setEndDate("2016-01-01");
		educationVO.setStartTime("01:00");
		educationVO.setEndTime("01:00");
		educationVO.setEducationType("TIMM");

		BindingResult errors = new BeanPropertyBindingResult(educationVO, "writeForm");
		EducationValidator validator = new EducationValidator();
		validator.validate(educationVO, errors);

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

		Path path = Paths.get("D:\\핸드폰.xlsx");
		String name = "file";
		String originalFileName = "핸드폰";
		String contentType = "text/plain";

		byte[] content = null;

		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {

		}

		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		request.addFile(file);
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("_MEMBER_TYPE_", "ADM");
		
		ModelAndView view = educationeService.writeNewEducation(educationVO, errors, request);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduregister");
			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("Fail");
		}
	}
	
	*/
	
	public class EducationValidator implements Validator {
		
		@Override
		public boolean supports(Class<?> clazz) {
			return EducationVO.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			
			EducationVO educationVO = (EducationVO) target;
			
			String educationCategory = educationVO.getEducationCategory();
			if (educationCategory == null || educationCategory.length() == 0) {
				errors.rejectValue("educationCategory", "field.required", "error default message");
			}
			
			String educationTitle = educationVO.getEducationTitle();
			if (educationTitle == null || educationTitle.length() == 0) {
				errors.rejectValue("educationTitle", "field.required", "error default message");
			}
			
			String memberId = educationVO.getMemberId();
			if (memberId == null || memberId.length() == 0) {
				errors.rejectValue("memberId", "field.required", "error default message");
			}
			
			String educationLocation = educationVO.getEducationLocation();
			if (educationLocation == null || educationLocation.length() == 0) {
				errors.rejectValue("educationLocation", "field.required", "error default message");
			}
			
			String educationCurriculum = educationVO.getEducationCurriculum();
			if (educationCurriculum == null || educationCurriculum.length() == 0) {
				errors.rejectValue("educationCurriculum", "field.required", "error default message");
			}
			
			String educationIntroduce = educationVO.getEducationIntroduce();
			if (educationIntroduce == null || educationIntroduce.length() == 0) {
				errors.rejectValue("educationIntroduce", "field.required", "error default message");
			}
			
			String startDate = educationVO.getStartDate();
			if (startDate == null || startDate.length() == 0) {
				errors.rejectValue("startDate", "field.required", "error default message");
			}
			
			String endDate = educationVO.getEndDate();
			if (endDate == null || endDate.length() == 0) {
				errors.rejectValue("endDate", "field.required", "error default message");
			}
			
			String startTime = educationVO.getStartTime();
			if (startTime == null || startTime.length() == 0) {
				errors.rejectValue("startTime", "field.required", "error default message");
			}
			
			String endTime = educationVO.getEndTime();
			if (endTime == null || endTime.length() == 0) {
				errors.rejectValue("endTime", "field.required", "error default message");
			}
			
			String educationType = educationVO.getEducationType();
			if (educationType == null || educationType.length() == 0) {
				errors.rejectValue("educationType", "field.required", "error default message");
			}
			
			String cost = educationVO.getCost();
			if (cost == null || cost.length() == 0) {
				errors.rejectValue("cost", "field.required", "error default message");
			}
		}
	}
	
	@Test
	public void getAllEducationHistoryTest() {
		
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("양지한");
		
		ModelAndView view = educationeService.getAllEducationHistory(educationHistorySearchVO, 0);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/eduManage");
		
		EducationHistoryListVO EduHistoryListVO = (EducationHistoryListVO) view.getModel().get("eduHistoryListVO");
		assertNotNull(EduHistoryListVO);
		
		Paging paging = EduHistoryListVO.getPaging();
		assertNotNull(paging);
		assertTrue(paging.getTotalArticleCount() > 0);
		
		List<EducationHistoryVO> eduHistory = EduHistoryListVO.getEducationHistoryList();
		assertNotNull(eduHistory);
		assertTrue(eduHistory.size() > 0);
		}
		else {
			fail("getAllEducationHistoryTest Fail");
		}
	}
	
	@Test
	public void getJCEduHistoryTest() {
		
		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setMemberId("test04");
		
		ModelAndView view = educationeService.getJCEduHistory(educationHistorySearchVO, 0);
		
		if ( view != null ) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "education/checkApplicant");
		
		EducationHistoryListVO EduHistoryListVO = (EducationHistoryListVO) view.getModel().get("eduHistoryListVO");
		assertNotNull(EduHistoryListVO);
		
		Paging paging = EduHistoryListVO.getPaging();
		assertNotNull(paging);
		assertTrue(paging.getTotalArticleCount() > 0);
		
		List<EducationHistoryVO> eduHistory = EduHistoryListVO.getEducationHistoryList();
		assertNotNull(eduHistory);
		assertTrue(eduHistory.size() > 0);
		}
		else {
			fail("getJCEduHistoryTest Fail");
		}
	}
}
