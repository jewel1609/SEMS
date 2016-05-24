package com.ktds.sems.pc.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcDAOImpl extends SqlSessionDaoSupport implements PcDAO {

	@Override
	public int getTotalUsedPcCount(UsedPcSearchVO usedPcSearchVO) {
		return getSqlSession().selectOne("PcDAO.getTotalUsedPcCount", usedPcSearchVO);
	}

	@Override
	public List<UsedPcVO> getUsedPcList(UsedPcSearchVO usedPcSearchVO) {
		return getSqlSession().selectList("PcDAO.getUsedPcList", usedPcSearchVO);
	}

	@Override
	public int getTotalReportedPcCount(ReportedPcSearchVO reportedPcSearchVO) {
		return getSqlSession().selectOne("PcDAO.getTotalReportedPcCount", reportedPcSearchVO);
	}

	@Override
	public List<ReportedPcVO> getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO) {
		return getSqlSession().selectList("PcDAO.getReportedPcListWithPaging", reportedPcSearchVO);
	}

	@Override
	public int changeReportedState(ReportedPcVO reportedPcVO) {
		return getSqlSession().update("PcDAO.changeReportedState", reportedPcVO);
	}

}
