package com.ktds.sems.member.dao;

import java.util.List;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public interface MemberDAO {

	public List<GrdtTpVO> getAllGrtdList();

	public List<MbrTpVO> getAllMbrTpList();

	public void doGrdtDelete(String cdId);
	
	public int doGrdtModify(GrdtTpVO grdtTpVO);

	public int doGrdtInsert(GrdtTpVO grdtTpVO);

	public int isExistData(GrdtTpVO grdtTpVO);
	public void doInsertMbrTp(MbrTpVO newMbrTpVO);

	public void doMbrTpDelete(String cdId);

	public void doMbrTpModify(MbrTpVO mbrTpVO);


}
