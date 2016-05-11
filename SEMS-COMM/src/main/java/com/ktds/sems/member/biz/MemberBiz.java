package com.ktds.sems.member.biz;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

public interface MemberBiz {

	public List<GrdtTpVO> getAllGrtdList();

	public List<MbrTpVO> getAllMbrTpList();

	public void doGrdtDelete(String cdId);

	public int doGrdtModify(GrdtTpVO grdtTpVO);

	public int doGrdtInsert(GrdtTpVO grdtTpVO);

	public int isExistData(GrdtTpVO grdtTpVO);
	
	public int doInsertMbrTp(MbrTpVO newMbrTpVO);

	public void doMbrTpDelete(String cdId);

	public void doMbrTpModify(MbrTpVO mbrTpVO);

	public List<HighestEduTpVO> getAllHighestEduList();

	public boolean doHighestEduDelete(String cdId);

	public boolean doHighestEduModify(HighestEduTpVO highestEduTpVO);

	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO);

	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO);

	public int isExistCdNmData(GrdtTpVO grdtTpVO);

	public boolean isExistId(String id);

	public boolean isAccountLock(String id);

	public boolean loginSuccess(String id);

	public boolean needToChangPassword(String id);

	public boolean plusLoginFailCount(String id);

	public boolean updateAccountLock(String id);

	public boolean login(HttpSession session, MemberVO loginVO);

	public int isExistMbrTpData(MbrTpVO newMbrTpVO);

}
