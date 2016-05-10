package com.ktds.sems.education.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;

import kr.co.hucloud.utilities.web.MultipartHttpServletRequest;
import kr.co.hucloud.utilities.web.MultipartHttpServletRequest.MultipartFile;

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
	public ModelAndView writeNewEducation(EducationVO educationVO, Errors errors, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		MultipartFile file = multipartRequest.getFile("file");

		if (educationVO.getEducationId() == null) {
			if (errors.hasErrors()) {
				view.setViewName("education/eduregister");
				view.addObject("educationVO", educationVO);
				return view;
				
			} else {
				boolean result = educationBiz.writeNewEducation(educationVO);

				if (file.getFileName() != "" && result) {

					FileVO fileVO = new FileVO();
					fileVO.setArticleId(educationVO.getEducationId());
					fileVO.setFileName(file.getFileName());
					fileVO.setFileLocation("D:\\???");
					fileBiz.doWriteFile(fileVO);

					view.setViewName("redirect:/list");
					
				} else {
					throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
				}
			}
		}

		return view;

	}

	@Override
	public ModelAndView getOneEducation(String educationId) {

		EducationVO educationVO = educationBiz.getOneEducation(educationId);

		ModelAndView view = new ModelAndView();
		view.setViewName("");
		view.addObject("educationVO", educationVO);
		return view;
	}

	@Override
	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors) {

		ModelAndView view = new ModelAndView();
		if (errors.hasErrors()) {
			view.setViewName("");
			view.addObject("educationVO", educationVO);
			return view;
		} else {
			boolean result = educationBiz.modifyNewEducation(educationVO);
			String educationId = educationVO.getEducationId();
			if (result) {
				view.setViewName("redirect:/detail/" + educationId);
			} else {
				throw new RuntimeException("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
		view.addObject("educationVO", educationVO);
		return view;
	}

}
