package com.ktds.sems.member.dao;

import java.util.List;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

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

	public List<HighestEduTpVO> getAllHighestEduList();

	public int doHighestEduDelete(String cdId);

	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO);

	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO);

	public int doHighestEduModify(HighestEduTpVO highestEduTpVO);

	public int isExistCdNmData(GrdtTpVO grdtTpVO);

	public String isExistId(String id);

	public boolean isAccountLock(String id);

	public int loginSuccess(String id);

	public String needToChangPassword(String id);

	public int plusLoginFailCount(String id);

	public int updateAccountLock(String id);

	public String getSaltById(String id);

	public MemberVO login(MemberVO loginVO);


}
