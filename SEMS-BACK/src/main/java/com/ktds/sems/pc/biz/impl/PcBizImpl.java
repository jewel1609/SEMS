package com.ktds.sems.pc.biz.impl;

import java.util.List;

import com.ktds.sems.education.vo.EducationPlaceVO;
import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.PcVO;
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
	public void doRegistClassInformation(PcVO pcVO) {
		pcVO.setEducationPlaceId(eduPlaceIDReconstructor());
		pcDAO.doRegistClassInformation(pcVO);
	}

	@Override
	public void doRegistClassCommonObject(PcVO pcVO) {
		pcVO.setPcId(pcIDReconstructor());
		pcDAO.doRegistClassCommonObject(pcVO);
	}

	@Override
	public List<EducationPlaceVO> getEducationPlaceList() {
		return pcDAO.getEducationPlaceList();
	}
	
	private String eduPlaceIDReconstructor() {
		String resultId = null;
		String sysdate = pcDAO.getSysdate();
		int eduPlaceSeq = pcDAO.nextPcSequence();
		
		resultId = "EP-"+sysdate+"-"+lpad(eduPlaceSeq, 6);
		return resultId;
	}
	
	private String pcIDReconstructor() {
		String resultId = null;
		String sysdate = pcDAO.getSysdate();
		int pcSeq = pcDAO.nextEducationPlaceSequence();
		
		resultId = "PI-"+sysdate+"-"+lpad(pcSeq, 6);
		return resultId;
	}
	
	private String lpad(int allSequencesReConstructor, int size) {
		String sequences = String.valueOf(allSequencesReConstructor);
		int length = sequences.length();
		int needLength = size - length;
		
		for (int i = 0; i < needLength; i++) {
			sequences = "0"+sequences;
		}
		return sequences;
	}
}
