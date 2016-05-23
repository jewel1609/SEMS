package com.ktds.sems.pc.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public interface PcBiz {

	List<EducationVO> getEduListByMember(MemberVO memberVO);

	List<UsedPcVO> getUsedPcListByMember(MemberVO memberVO);

}
