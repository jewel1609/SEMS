package com.ktds.sems.member.biz.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.sems.common.LoginStore;
import com.ktds.sems.common.Session;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.excel.option.WriteOption;
import kr.co.hucloud.utilities.excel.write.ExcelWrite;

public class MemberBizImpl implements MemberBiz {

	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean addNewMember(MemberVO member) {
		return memberDAO.addNewMember(member) > 0;
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

	@Override
	public boolean isAccountLock(String id) {
		return memberDAO.isAccountLock(id);
	}

	@Override
	public boolean loginSuccess(String id) {
		return memberDAO.loginSuccess(id) > 0;
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
	public MemberVO getOneMember(String id) {
		return memberDAO.getOneMember(id);
	}

	@Override
	public void resetModifyLockAndCount(String id) {
		memberDAO.resetModifyLockAndCount(id);
	}

	@Override
	public void plusModifyFailCount(String sessionId) {
		memberDAO.plusModifyFailCount(sessionId);
	}

	@Override
	public void updateModifyAccountLock(String sessionId) {
		memberDAO.updateModifyAccountLock(sessionId);
	}

	@Override
	public boolean isModifyAccountLock(String sessionId) {
		return memberDAO.isModifyAccountLock(sessionId) > 0;
	}

	@Override
	public String getNowDate() {
		return memberDAO.getNowDate();
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

	/**
	 * @author 이기연
	 */
	@Override
	public void saveLoginHistoryAsExcel(String memberId) {
		
		WriteOption wo = new WriteOption();
		wo.setSheetName("로그인 내역");
		wo.setFileName("로그인 내역.xlsx");
		wo.setFilePath("D:\\");
		List<String> titles = new ArrayList<String>();

		titles.add("LGI_HTR_ID");
		titles.add("MBR_ID");
		titles.add("LGI_IP");
		titles.add("LGI_DT");
		titles.add("LGO_DT");
		wo.setTitles(titles);
		
		List<String[]> contents = new ArrayList<String[]>();

		// LoginHistory 만들기
		try {
			List<LoginHistoryVO> loginHistory = memberDAO.saveLoginHistoryAsExcel(memberId);
			Iterator<LoginHistoryVO> tempIterator = loginHistory.iterator();

			// TODO while문으로 null을 만날 때 까지 while문을 돌려야 할 것 같다
			while (tempIterator.hasNext())

			{
				// TODO String[] 타입인데... 이걸 수정해바야 할 것 같다.
				// 하나씩 String[]에 담는 것 그리고 add
				LoginHistoryVO tempLoginHistoryVO = new LoginHistoryVO();
				tempLoginHistoryVO = tempIterator.next();

				String[] content = new String[5];

				content[0] = tempLoginHistoryVO.getLgiHtrId() + "";
				content[1] = tempLoginHistoryVO.getId();
				content[2] = tempLoginHistoryVO.getLgiIp();
				content[3] = tempLoginHistoryVO.getLgiDt();
				content[4] = tempLoginHistoryVO.getLgoDt();

				contents.add(content);
			}

			wo.setContents(contents);

			File excelFile = ExcelWrite.write(wo);

		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public boolean stampLoginTime(HttpServletRequest request, MemberVO loginVO) {
		// TODO Auto-generated method stub

		// 새로운 loginHistoryVO 생성해서 넣기 (1개의 object만 파라미터로 줄 수 있기 때문)
		LoginHistoryVO newLoginHistoryVO = new LoginHistoryVO();
		
		newLoginHistoryVO.setId(loginVO.getId());
		newLoginHistoryVO.setLgiIp(request.getRemoteHost());
		
		return memberDAO.stampLoginTime(newLoginHistoryVO) > 0;
		
	}

	@Override
	public int getTotalLoginHisotryCount() {
		return memberDAO.getTotalLoginHisotryCount();
	}

	@Override
	public List<LoginHistoryVO> getAllLoginHistory(LoginHistorySearchVO loginHistorySearchVO) {
		return memberDAO.getAllLoginHistory(loginHistorySearchVO);
	}

	@Override
	public void modifyMemberInfo(MemberVO member) {
		memberDAO.modifyMemberInfo(member);
	}

	@Override
	public boolean isExistId(String id) {
		
		String memberId = memberDAO.isExistId(id);
		
		if ( memberId == null ) {
			return true;
		}
		else {
			return false;
		}
	}

}

