package com.ktds.sems.member.biz;

import java.util.List;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public interface MemberBiz {

	public GrdtTpVO getAllGrtdList();

	public List<MbrTpVO> getAllMbrTpList();

}
