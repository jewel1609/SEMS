package com.ktds.sems.pc.biz.impl;

import java.util.List;

import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcBizImpl implements PcBiz {

	private PcDAO pcDAO;

	public void setPcDAO(PcDAO pcDAO) {
		this.pcDAO = pcDAO;
	}

	@Override
	public int getTotalUsedPcCount(UsedPcSearchVO usedPcSearchVO) {
		return pcDAO.getTotalUsedPcCount(usedPcSearchVO);
	}

	@Override
	public List<UsedPcVO> getUsedPcList(UsedPcSearchVO usedPcSearchVO) {
		return pcDAO.getUsedPcList(usedPcSearchVO);
	}

	@Override
	public int getTotalReportedPcCount(ReportedPcSearchVO reportedPcSearchVO) {
		return pcDAO.getTotalReportedPcCount(reportedPcSearchVO);
	}

	@Override
	public List<ReportedPcVO> getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO) {
		return pcDAO.getReportedPcListWithPaging(reportedPcSearchVO);
	}

	@Override
	public boolean changeReportedState(ReportedPcVO reportedPcVO) {
		return pcDAO.changeReportedState(reportedPcVO) > 0;
	}

	@Override
	public List<EducationPlaceVO> getEducationPlaceList() {
		return pcDAO.getEducationPlaceList();
	}

}
