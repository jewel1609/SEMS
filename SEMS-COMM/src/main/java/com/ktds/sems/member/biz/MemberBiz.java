package com.ktds.sems.member.biz;

import java.util.List;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public interface MemberBiz {

	public List<GrdtTpVO> getAllGrtdList();

	public List<MbrTpVO> getAllMbrTpList();

	public void doGrdtDelete(String cdId);

	public void doGrdtModify(GrdtTpVO grdtTpVO);

	public void doInsertMbrTp(MbrTpVO newMbrTpVO);

	public void doMbrTpDelete(String cdId);

	public void doMbrTpModify(MbrTpVO mbrTpVO);

	public void doGrdtInsert(GrdtTpVO grdtTpVO);

}
