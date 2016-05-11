package com.ktds.sems.member.biz.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ktds.sems.common.LoginStore;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;

public class MemberBizImpl implements MemberBiz{

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List<GrdtTpVO> getAllGrtdList() {
		return memberDAO.getAllGrtdList();
	}

	@Override
	public List<MbrTpVO> getAllMbrTpList() {
		List<MbrTpVO> mbrtpList = memberDAO.getAllMbrTpList();
		return mbrtpList;
	}

	@Override
	public void doInsertMbrTp(MbrTpVO newMbrTpVO) {
		memberDAO.doInsertMbrTp(newMbrTpVO);
	}

	@Override
	public void doGrdtDelete(String cdId) {
		memberDAO.doGrdtDelete(cdId);
		
	}

	@Override
	public int doGrdtModify(GrdtTpVO grdtTpVO) {
		return memberDAO.doGrdtModify(grdtTpVO);
		
	}

	@Override
	public int doGrdtInsert(GrdtTpVO grdtTpVO) {
		return memberDAO.doGrdtInsert(grdtTpVO);
	}

	@Override
	public int isExistData(GrdtTpVO grdtTpVO) {
		return memberDAO.isExistData(grdtTpVO);
	}
	public void doMbrTpDelete(String cdId) {
		memberDAO.doMbrTpDelete(cdId);
	}

	@Override
	public void doMbrTpModify(MbrTpVO mbrTpVO) {
		memberDAO.doMbrTpModify(mbrTpVO);
		
	}
	/* Highest Edu */
	@Override
	public List<HighestEduTpVO> getAllHighestEduList() {
		return memberDAO.getAllHighestEduList();
	}

	@Override
	public boolean doHighestEduDelete(String cdId) {
		return memberDAO.doHighestEduDelete(cdId) > 0;
	}

	@Override
	public boolean doHighestEduModify(HighestEduTpVO highestEduTpVO) {
		return memberDAO.doHighestEduModify(highestEduTpVO) > 0;
	}

	@Override
	public int isExistHighestEduData(HighestEduTpVO highestEduTpVO) {
		return memberDAO.isExistHighestEduData(highestEduTpVO);
	}

	@Override
	public int doHighestEduInsert(HighestEduTpVO highestEduTpVO) {
		return memberDAO.doHighestEduInsert(highestEduTpVO);
	}
	@Override
	public int isExistCdNmData(GrdtTpVO grdtTpVO) {
		return memberDAO.isExistCdNmData(grdtTpVO);
	}

	@Override
	public boolean isExistId(String id) {
		String memberId = memberDAO.isExistId(id);
		if ( memberId == null ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAccountLock(String id) {
		return memberDAO.isAccountLock(id);
	}

	@Override
	public boolean loginSuccess(String id) {
		return memberDAO.loginSuccess(id) > 0;
	}

	@Override
	public boolean needToChangPassword(String id) {
		String checkStr = memberDAO.needToChangPassword(id);
		if(checkStr != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean plusLoginFailCount(String id) {
		return memberDAO.plusLoginFailCount(id) > 0;
	}

	@Override
	public boolean updateAccountLock(String id) {
		return memberDAO.updateAccountLock(id) > 0;
	}

	@Override
	public boolean login(HttpSession session, MemberVO loginVO) {

		// SHA256 이용해 암호화
		String memberSalt = memberDAO.getSaltById(loginVO.getId());
		String inputPassword = loginVO.getPassword();
		String newPassword = SHA256Util.getEncrypt(inputPassword, memberSalt);
		loginVO.setPassword(newPassword);

		MemberVO memberVO = memberDAO.login(loginVO);

		if (memberVO != null) {

			// 이미 로그인 되어 있다면, 기존 로그인 세션을 종료
			LoginStore loginStore = LoginStore.getInstance();
			if (loginStore.get(loginVO.getId()) != null) {
				loginStore.logout(loginVO.getId());
			}
			
			session.setAttribute(Session.MEMBER, memberVO);
			session.setAttribute(Session.MEMBER_TYPE, memberVO.getMemberType());
			
			// 로그인 세션 유지 시간 10분
			session.setMaxInactiveInterval(10 * 60);

			// 새로운 로그인 세션 입력
			loginStore.add(loginVO.getId(), session);
		}

		return memberVO != null;
	}
}
