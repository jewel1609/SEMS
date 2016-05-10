package com.ktds.sems.member.dao;

import java.util.List;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public interface MemberDAO {

	public GrdtTpVO getAllGrtdList();

	public List<MbrTpVO> getAllMbrTpList();

}
