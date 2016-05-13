package com.ktds.sems.education.service.impl;



import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;


import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;



import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;

import kr.co.hucloud.utilities.SHA256Util;



public class EducationServiceImpl implements EducationService {
	
	private EducationBiz educationBiz;
	private FileBiz fileBiz;

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
								
							} catch (IllegalStateException | IOException e) {
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
							
						} catch (IllegalStateException | IOException e) {
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

}
