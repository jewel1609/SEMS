package com.ktds.sems.education.service;




import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationVO;


public interface EducationService {
	
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors , MultipartHttpServletRequest request);

	public ModelAndView getOneEducationForUpdate(String educationId);

	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors, MultipartHttpServletRequest request);

	public ModelAndView getAllEduCode();

	public ModelAndView getAllEducationHistory(int pageNo);

	public ModelAndView getJCEduHistory(int pageNo);

	public ModelAndView applyJoinEducationByMemberId(String educationHistoryId);

	public ModelAndView cancelJoinEducationByMemberId(String educationHistoryId, String memberId, String description);

}
