package com.ktds.sems.pc.dao;

import java.util.List;

import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public interface PcDAO {

	public int getTotalUsedPcCount(UsedPcSearchVO usedPcSearchVO);

	public List<UsedPcVO> getUsedPcList(UsedPcSearchVO usedPcSearchVO);

	public int getTotalReportedPcCount(ReportedPcSearchVO reportedPcSearchVO);

	public List<ReportedPcVO> getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO);

	public int changeReportedState(ReportedPcVO reportedPcVO);

	public int nextEducationPlaceSequence();
	
	public int nextPcSequence();

	public List<EducationPlaceVO> getEducationPlaceList();

	public String getSysdate();

	public void doRegistEduPlace(PcVO pcVO);

	public void doRegistPC(PcVO pcVO);

	
}
