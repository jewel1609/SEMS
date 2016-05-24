package com.ktds.sems.pc.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.pc.dao.PcDAO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcVO;

public class PcDAOImpl extends SqlSessionDaoSupport implements PcDAO {

	@Override
	public List<EducationVO> getEduListByMember(MemberVO memberVO) {
		return getSqlSession().selectList("PcDAO.getEduListByMember", memberVO);
	}

	/**
	 * PC 고장 신고하는 기능
	 * 이기연
	 */
	public int reportProblemPc(ReportedPcVO reportedPcVO) {
		return getSqlSession().insert("PcDAO.reportProblemPc", reportedPcVO);
	}
	
	@Override
	public int getNextReportedPcIdSeq() {
		return getSqlSession().selectOne("PcDAO.getNextReportedPcIdSeq");
	}
	
	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("PcDAO.getNowDate");
	}

	@Override
	public List<UsedPcVO> getUsedPcListByMember(MemberVO memberVO) {
		return getSqlSession().selectList("PcDAO.getUsedPcListByMember", memberVO);
	}

}
