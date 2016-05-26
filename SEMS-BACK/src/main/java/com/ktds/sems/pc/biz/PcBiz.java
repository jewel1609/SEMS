package com.ktds.sems.pc.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public interface PcBiz {

	public int getTotalUsedPcCount(UsedPcSearchVO usedPcSearchVO);

	public List<UsedPcVO> getUsedPcList(UsedPcSearchVO usedPcSearchVO);

	public int getTotalReportedPcCount(ReportedPcSearchVO reportedPcSearchVO);

	public List<ReportedPcVO> getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO);

	public boolean changeReportedState(ReportedPcVO reportedPcVO);

	public String doRegistEduPlace(PcVO pcVO);

	public void doRegistPC(PcVO pcVO);

	public List<EducationPlaceVO> getEducationPlaceList();

	public void doActionDeleteEduPlace(String educationPlaceId);

	public void doActionDeleteEduPC(String pcId);

}
