package com.ktds.sems.education.biz;

import com.ktds.sems.education.vo.EducationVO;

public interface EducationBiz {

	EducationVO getOneEducation(String educationId);

	boolean modifyNewEducation(EducationVO educationVO);

}
