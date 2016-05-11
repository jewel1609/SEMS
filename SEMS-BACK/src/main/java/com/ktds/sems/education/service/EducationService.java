package com.ktds.sems.education.service;




import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;


public interface EducationService {
	
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors , MultipartHttpServletRequest request);

	public ModelAndView getOneEducation(String educationId);

	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors, MultipartHttpServletRequest request);

	public ModelAndView getAllEduCode();
}
