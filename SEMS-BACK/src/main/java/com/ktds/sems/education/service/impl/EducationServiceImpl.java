package com.ktds.sems.education.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EduAttendanceListVO;
import com.ktds.sems.education.vo.EduAttendanceSearchVO;
import com.ktds.sems.education.vo.EduAttendanceVO;
import com.ktds.sems.education.vo.EduFileListVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduFileVO;
import com.ktds.sems.education.vo.EduQnaListVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportListVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.Paging;

public class EducationServiceImpl implements EducationService {
	
	private Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);	
	
	private EducationBiz educationBiz;
	private MemberBiz memberBiz;
	private FileBiz fileBiz;

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	public void setEducationBiz(EducationBiz educationBiz) {
		this.educationBiz = educationBiz;
	}

	public void setFileBiz(FileBiz fileBiz) {
		this.fileBiz = fileBiz;
	}
	
	@Override
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors, MultipartHttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		
		MultipartFile file = request.getFile("file");
		
		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt)+".xlsx";
		educationVO.setSalt(salt);
		
		String filePath = "D:\\"+saltFileName;
		
		if ( memberType.equals("ADM") ) {
			if (educationVO.getEducationId() == null) {
				if (errors.hasErrors()) {
					view.setViewName("education/eduregister");
					view.addObject("educationVO", educationVO);
					view.addObject("costList", educationBiz.costCodeList());
					view.addObject("typeList", educationBiz.typeCodeList());
					view.addObject("categoryList", educationBiz.categoryCodeList());
					return view;
					
				} else {
					
					boolean result = educationBiz.writeNewEducation(educationVO);

					if ( !file.isEmpty() && result) {
						
						if ( fileName.toLowerCase().endsWith(".xlsx") ) {
							
							File files = new File(filePath);
							
							try {
								file.transferTo(files);
								
								FileVO fileVO = new FileVO();
								fileVO.setArticleId(educationVO.getEducationId());
								fileVO.setFileName(fileName);
								fileVO.setFileLocation(filePath);
								fileBiz.doWriteFile(fileVO);
								
							} catch (IllegalStateException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						view.setViewName("redirect:/list");
						
					} else {
						throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
					}
				}
			}
		}else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}

		return view;

	}

	@Override
	public ModelAndView getOneEducationForUpdate(String educationId) {
		
		EducationVO educationVO = educationBiz.getOneEducation(educationId);
		
		if ( educationVO == null ) {
			throw new RuntimeException("해당 강의가 없습니다.");
		}
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("education/update");
		view.addObject("educationVO", educationVO);
		view.addObject("costList", educationBiz.costCodeList());
		view.addObject("typeList", educationBiz.typeCodeList());
		view.addObject("categoryList", educationBiz.categoryCodeList());
		
		return view;
	}

	@Override
	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors, MultipartHttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		HttpSession session = request.getSession();
		String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
		
		MultipartFile file = request.getFile("file");
		
		String fileName = file.getOriginalFilename();
		String salt = SHA256Util.generateSalt();
		String saltFileName = SHA256Util.getEncrypt(fileName, salt)+".xlsx";
		educationVO.setSalt(salt);
		
		String filePath = "D:\\"+saltFileName;	
		
		String educationId = educationVO.getEducationId();
		
		if ( memberType.equals("ADM") ) {
			if ( errors.hasErrors() ) {
				view.setViewName("education/update"+"/"+educationId);
				view.addObject("educationVO", educationVO);
				view.addObject("costList", educationBiz.costCodeList());
				view.addObject("typeList", educationBiz.typeCodeList());
				view.addObject("categoryList", educationBiz.categoryCodeList());
				return view;
			}
			else{
				boolean result = educationBiz.modifyNewEducation(educationVO);
				
				if ( result) {
					if ( !file.isEmpty() && fileName.toLowerCase().endsWith(".xlsx") ) {
						
						File files = new File(filePath);
						
						try {
							file.transferTo(files);
							
							FileVO fileVO = new FileVO();
							fileVO.setArticleId(educationVO.getEducationId());
							fileVO.setFileName(fileName);
							fileVO.setFileLocation(filePath);
							
							fileBiz.updateFile(fileVO);
							
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}					
					view.setViewName("redirect:/detail/" + educationId);
				}
				else {
					throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
			}
		}
		else {
			throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		}
		
		view.addObject("educationVO", educationVO);
		return view;
	}
	
	@Override
	public ModelAndView getAllEduCode() {
		ModelAndView view = new ModelAndView();
		view.addObject("costList", educationBiz.costCodeList());
		view.addObject("typeList", educationBiz.typeCodeList());
		view.addObject("categoryList", educationBiz.categoryCodeList());
		view.setViewName("education/eduregister");
		return view;
	}

	@Override
	public ModelAndView getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo) {
		
		EducationHistoryListVO eduHistoryListVO = new EducationHistoryListVO();
		Paging paging = new Paging(15,15);
		eduHistoryListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int eduHistoryCount = educationBiz.getAllEduHistoryCount(eduHistorySearchVO);
		logger.info("eduHistoryCount"+eduHistoryCount);
		if(eduHistoryCount == 0 ){
			eduHistoryCount ++;
		}
		paging.setTotalArticleCount(eduHistoryCount);
		eduHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		eduHistorySearchVO.setEndIndex(paging.getEndArticleNumber());	
		
		ModelAndView view = new ModelAndView();
		List<EducationHistoryVO> eduHistoryList = educationBiz.getAllEducationHistory(eduHistorySearchVO);
		eduHistoryListVO.setEducationHistoryList(eduHistoryList);
		view.addObject("eduHistoryListVO", eduHistoryListVO);
		logger.info("eduHistoryListSize"+eduHistoryList.size());
		view.setViewName("education/eduManage");
		return view;
	}

	@Override
	public ModelAndView getJCEduHistory(EducationHistorySearchVO eduHistorySearchVO, int pageNo) {
		
		logger.info("1" +eduHistorySearchVO.getSearchKeyword());
		logger.info("2" +eduHistorySearchVO.getSearchType());
		logger.info("3" +eduHistorySearchVO.getSearchDate());
		
		EducationHistoryListVO eduHistoryListVO = new EducationHistoryListVO();
		Paging paging = new Paging(15,15);
		eduHistoryListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int eduHistoryCount = educationBiz.getJCEduHistoryCount(eduHistorySearchVO);
		logger.info("eduHistoryCount"+eduHistoryCount);
		if(eduHistoryCount == 0 ){
			eduHistoryCount ++;
		}
		paging.setTotalArticleCount(eduHistoryCount);
		eduHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		eduHistorySearchVO.setEndIndex(paging.getEndArticleNumber());	
		
		ModelAndView view = new ModelAndView();
		List<EducationHistoryVO> eduHistoryList = educationBiz.getJCEducationHistory(eduHistorySearchVO);
		eduHistoryListVO.setEducationHistoryList(eduHistoryList);
		
		view.addObject("eduHistoryListVO", eduHistoryListVO);
		logger.info("eduHistoryListSize"+eduHistoryList.size());
		view.setViewName("education/checkApplicant");
		return view;
	}

	@Override
	public ModelAndView applyJoinEducationByMemberId(String educationHistoryId) {
		
		ModelAndView view = new ModelAndView();
		
		String state = educationBiz.getStateByEducationHistroyId(educationHistoryId);
		String changeState = "";
		if( state.equals("EDU_JN_A") ){
			changeState="EDU_JN_C";
		}
		else if( state.equals("EDU_CL_A") ){
			changeState="EDU_CL_C";
		}
		else if( state.equals("EDU_GU_A") ){
			changeState="EDU_GU_C";
		}
		boolean result = educationBiz.applyJoinEducationByMemberId(educationHistoryId, changeState);
		
		if ( result ) {
			view.setViewName("redirect:/educationHistory");
			return view;
		}
		
		return view;
	}

	@Override
	public ModelAndView cancelJoinEducationByMemberId(String educationHistoryId, String memberId, String description) {
		
		ModelAndView view = new ModelAndView();
		
		String state = educationBiz.getStateByEducationHistroyId(educationHistoryId);
		String changeState = "";
		if( state.equals("EDU_JN_A") ){
			changeState="EDU_JN_R";
		}
		else if( state.equals("EDU_CL_A") ){
			changeState="EDU_CL_R";
		}
		else if( state.equals("EDU_GU_A") ){
			changeState="EDU_GU_R";
		}
		
		/**
		 * Email 여기서 보내도록
		 */
		boolean result = educationBiz.cancelJoinEducationByMemberId(educationHistoryId, changeState);
		
		if ( result ) {
			view.setViewName("redirect:/educationHistory");
			return view;
		}
		
		return view;
	}

	/**
	 * Q&A게시판/ 과제게시판/ 강의자료게시판
	 */

	@Override
	public ModelAndView getAllReportArticle(EduReportSearchVO eduReportSearchVO, int pageNo) {
		EduReportListVO eduReportListVO = new EduReportListVO();
		Paging paging = new Paging(20,20);
		
		eduReportListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduReportCount(eduReportSearchVO);
		
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportCount);
		
		eduReportSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduReportSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<EduReportVO> eduReport = educationBiz.getAllEduReport(eduReportSearchVO);
		eduReportListVO.setEduReportList(eduReport);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/eduReportPage");
		view.addObject("eduReportListVO", eduReportListVO);
		view.addObject("eduReportSearchVO", eduReportSearchVO);
		
		return view;
	}

	@Override
	public ModelAndView getAllQnaArticle(EduQnaSearchVO eduQnaSearchVO, int pageNo) {
		EduQnaListVO eduQnaListVO = new EduQnaListVO();
		Paging paging = new Paging(20,20);
		
		eduQnaListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduQnaCount(eduQnaSearchVO);
		
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportCount);
		
		eduQnaSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduQnaSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<EduQnaVO> eduQna = educationBiz.getAllEduQna(eduQnaSearchVO);
		eduQnaListVO.setEduQnaList(eduQna);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/eduQnaPage");
		view.addObject("eduQnaListVO", eduQnaListVO);
		view.addObject("eduQnaSearchVO", eduQnaSearchVO);
		
		return view;
	}

	@Override
	public ModelAndView getAllEduFileArticle(EduFileSearchVO eduFileSearchVO, int pageNo) {
		EduFileListVO eduFileListVO = new EduFileListVO();
		Paging paging = new Paging(20,20);
		
		eduFileListVO.setPaging(paging);
		int totalReportCount = educationBiz.getTotalEduFileCount(eduFileSearchVO);
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalReportCount);
		
		eduFileSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduFileSearchVO.setEndIndex(paging.getEndArticleNumber());

		List<EduFileVO> eduFile = educationBiz.getAllEduFile(eduFileSearchVO);
		eduFileListVO.setEduFileList(eduFile);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/eduFilePage");
		view.addObject("eduFileListVO", eduFileListVO);
		view.addObject("eduFileSearchVO", eduFileSearchVO);
		
		return view;
	}

	@Override
	public void rejectionMailAction(String educationHistoryId, String memberId, String description) {
		
		MemberVO member = memberBiz.getOneMember(memberId);
		String email = member.getEmail();
		String name = member.getName();
		educationBiz.sendEmailRejection( educationHistoryId, memberId, description , email , name );
	}

	@Override
	public int changeEducationApplyState(String educationHistoryId) {
		return educationBiz.changeEducationApplyState(educationHistoryId);
	}

	@Override
	public ModelAndView viewEducationAttendancePage(EduAttendanceSearchVO eduAttendanceSearchVO, int pageNo) {

		EduAttendanceListVO attendanceListVO = new EduAttendanceListVO();
		Paging paging = new Paging(10,10);
		attendanceListVO.setPaging(paging);
		
		int totalAttendanceCount = educationBiz.getTotalAttendanceCount(eduAttendanceSearchVO);
		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(totalAttendanceCount);
		
		eduAttendanceSearchVO.setStartIndex(paging.getStartArticleNumber());
		eduAttendanceSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<EduAttendanceVO> attendanceList = educationBiz.getAllAttendance(eduAttendanceSearchVO);
		attendanceListVO.setAttendanceList(attendanceList);
		
		List<MemberVO> trainees = educationBiz.getAllMemberInEducation();
		List<TeamVO> teams = educationBiz.getAllTeamInEducation();	
		List<EducationVO> educations = educationBiz.getAllEducation();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("education/attendance");
		view.addObject("attendanceListVO", attendanceListVO);
		view.addObject("eduTrainees", trainees);
		view.addObject("eduTeams", teams);
		view.addObject("educations", educations);
		return view;
	}

	@Override
	public ModelAndView doActionDelete(String educationId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MockHttpSession memberSession = new MockHttpSession();
		MemberVO memberVO = (MemberVO) memberSession.getAttribute(Session.MEMBER);
		String memberType = (String) memberSession.getAttribute(Session.MEMBER_TYPE);
		
		logger.info("1" + memberVO.getId());
		logger.info("2" +memberVO.getMemberType());
		logger.info("4" + educationId);
		
		if(memberType.equals("ADM")) {
			if(educationId != null){
				memberVO.setId(memberVO.getId());
				memberVO.setMemberType(memberType);
				boolean checkPass = educationBiz.doActionDeleteBeforeCheck(memberVO);
				logger.info("1" + memberVO.getId());
				logger.info("2" +memberVO.getMemberType());
				logger.info("3" + checkPass);
				logger.info("4" + educationId);
				if ( checkPass) {
					educationBiz.doActionDelete(educationId);
					educationBiz.emailNoticeForUser(educationId);
					view.setViewName("/");
				} else {
					return new ModelAndView("redirect:/");
				}
			} else {
				throw new RuntimeException("삭제할 교육을 선택해주세요.");
			}
		} else {
			throw new RuntimeException("접근권한이 없습니다.");
		}
		return view;
	}
}