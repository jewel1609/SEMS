package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;

public interface EducationBiz {

	List<EducationVO> doSearchList(EducationVO educationVO);

}
