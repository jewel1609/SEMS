package com.ktds.sems.education.service;




import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.vo.EduAttendanceSearchVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;


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

	public ModelAndView getAllReportArticle(EduReportSearchVO eduReportSearchVO, int pageNo);

	public ModelAndView getAllQnaArticle(EduQnaSearchVO qnaArticleSearchVO, int pageNo);

	public ModelAndView getAllEduFileArticle(EduFileSearchVO eduFileSearchVO, int pageNo
			, EduNoticeSearchVO eduNoticeSearchVO);
	
	public int changeEducationApplyState(String educationHistoryId);

	public ModelAndView writeEduFileNoticeAction(EduNoticeVO eduNoticeVO, Errors errors,  HttpSession session);

	public ModelAndView doActionDelete(String educationId, HttpSession session);

	public ModelAndView viewEducationAttendancePage(EduAttendanceSearchVO eduAttendanceSearchVO, int pageNo);

	public ModelAndView viewReportHistoryPage(EduReportSearchVO reportSearchVO, int pageNo);
	
	public ModelAndView viewDetail(String memberId, String eduNoticeId);

	public String doDeleteEduNotice(String educationId, String eduNoticeId);

	public String massiveDeleteNoice(String educationId, String[] deleteNoiceIds);

	public ModelAndView viewModifyNoticePage(String eduNoticeId);

	public ModelAndView doEduFileNoticeModify(String educationId, String eduNoticeId, EduNoticeVO eduNoticeVO,
			Errors errors, HttpSession session);




}
