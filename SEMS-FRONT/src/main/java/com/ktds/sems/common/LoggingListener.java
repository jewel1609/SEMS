package com.ktds.sems.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.LoginHistoryVO;

public class LoggingListener implements HttpSessionListener {

	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		session.setMaxInactiveInterval(60 * 30);// 초단위로 세션유지 시간을 설정합니다
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		LoginHistoryVO loginHistoryForlogout = (LoginHistoryVO) session.getAttribute("_LOGIN_HISTORY_");
		if (loginHistoryForlogout != null) {
			memberDAO.stampLogoutTime(loginHistoryForlogout);
		} 
	}

}
