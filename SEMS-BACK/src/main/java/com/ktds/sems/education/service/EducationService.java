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

	public ModelAndView applyJoinEducationByMemberId(String educationId, String memberId);

	public ModelAndView cancelJoinEducationByMemberId(String educationId, String memberId, String description);

	public ModelAndView completeCancelEducationByMemberId(String educationHistoryId);
	
	public ModelAndView denyCancelEducationByMemberId(String educationHistoryId);
	
	public ModelAndView completeGiveUpEducationByMemberId(String educationHistoryId);
	
	public ModelAndView denyGiveUpEducationByMemberId(String educationHistoryId);	


}
