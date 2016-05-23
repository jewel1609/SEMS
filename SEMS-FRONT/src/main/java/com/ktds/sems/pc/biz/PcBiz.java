package com.ktds.sems.pc.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public interface PcBiz {

	public List<EducationVO> getEduListByMember(MemberVO memberVO);

	public boolean reportProblemPc(ReportedPcVO reportedPcVO);

	public String getNowDate();

	public int getNextReportedPcIdSeq();

	public List<UsedPcVO> getUsedPcListByMember(MemberVO memberVO);

}
