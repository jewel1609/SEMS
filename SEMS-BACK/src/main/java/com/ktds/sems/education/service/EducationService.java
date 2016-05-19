package com.ktds.sems.education.service;




import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationVO;


public interface EducationService {
	
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors , MultipartHttpServletRequest request);

	public ModelAndView getOneEducationForUpdate(String educationId);

	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors, MultipartHttpServletRequest request);

	public ModelAndView getAllEduCode();

	public ModelAndView getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo);

	public ModelAndView getJCEduHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo);

	public ModelAndView applyJoinEducationByMemberId(String educationHistoryId);

	public void rejectionMailAction(String educationHistoryId, String memberId, String description);

	public ModelAndView cancelJoinEducationByMemberId(String educationHistoryId, String memberId, String description);
}
