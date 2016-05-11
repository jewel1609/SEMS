package com.ktds.sems.education.service.impl;



import java.io.File;
import java.io.IOException;


import javax.servlet.http.HttpSession;


import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.service.EducationService;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;



import com.ktds.sems.file.biz.FileBiz;
import com.ktds.sems.file.vo.FileVO;



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

		MultipartFile file = request.getFile("file");
		String path ="D:\\"+file.getOriginalFilename();
		File files = new File(path);
		
		if (educationVO.getEducationId() == null) {
			if (errors.hasErrors()) {
				view.setViewName("education/eduregister");
				view.addObject("educationVO", educationVO);
				return view;
				
			} else {
				boolean result = educationBiz.writeNewEducation(educationVO);

				if (file.getOriginalFilename() != "" && result) {
					
					try {
						file.transferTo(files);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
					
					FileVO fileVO = new FileVO();
					fileVO.setArticleId(educationVO.getEducationId());
					fileVO.setFileName(file.getOriginalFilename());
					fileVO.setFileLocation(path);
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
		view.setViewName("education/detail");
		view.addObject("educationVO", educationVO);
		return view;
	}

	@Override
	public ModelAndView modifyNewEducation(EducationVO educationVO, Errors errors, HttpSession session) {
		
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
		//}
		//else {
			//throw new RuntimeException("접근 가능한 권한이 아닙니다.");
		//}
		
		view.addObject("educationVO", educationVO);
		return view;
	}

}
