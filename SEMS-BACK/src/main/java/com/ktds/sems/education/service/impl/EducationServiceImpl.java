package com.ktds.sems.education.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationVO;
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
}