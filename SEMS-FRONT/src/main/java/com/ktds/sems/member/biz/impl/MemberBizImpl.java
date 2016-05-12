package com.ktds.sems.member.biz.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;

import com.ktds.sems.common.LoginStore;
import com.ktds.sems.common.Session;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.excel.option.WriteOption;
import kr.co.hucloud.utilities.excel.write.ExcelWrite;

public class MemberBizImpl implements MemberBiz {
	
	private JavaMailSender mailSender;
	private MemberDAO memberDAO;
	

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean addNewMember(MemberVO member) {
		return memberDAO.addNewMember(member) > 0;
	}

	@Override
	public boolean login(HttpSession session, MemberVO loginVO, HttpServletRequest request) {

		// SHA256 이용해 암호화
		String memberSalt = memberDAO.getSaltById(loginVO.getId());
		String inputPassword = loginVO.getPassword();
		String newPassword = SHA256Util.getEncrypt(inputPassword, memberSalt);
		loginVO.setPassword(newPassword);

		MemberVO memberVO = memberDAO.login(loginVO);

		if (memberVO != null) {

			// 이미 로그인 되어 있다면, 기존 로그인 세션을 종료
			LoginStore loginStore = LoginStore.getInstance();
			HttpSession loginedSession = loginStore.get(loginVO.getId());
			if (loginedSession != null) {
				loginStore.logout(loginVO.getId());
				stampLogoutTime(session);
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
		if (checkStr != null) {
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

		titles.add("로그인 내역 순번");
		titles.add("로그인 아이디");
		titles.add("로그인 IP");
		titles.add("로그인 시간");
		titles.add("로그아웃 시간");
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
	public boolean stampLoginTime(HttpSession session, HttpServletRequest request, MemberVO loginVO) {
		// 새로운 loginHistoryVO 생성해서 넣기 (1개의 object만 파라미터로 줄 수 있기 때문)

		// ID 저장해서 logout 시에 사용  
		int nextLoginHistoryId = memberDAO.nextLoginHistorySeq();
		LoginHistoryVO newLoginHistoryVO = new LoginHistoryVO();

		newLoginHistoryVO.setLgiHtrId(nextLoginHistoryId);
		newLoginHistoryVO.setId(loginVO.getId());
		newLoginHistoryVO.setLgiIp(request.getRemoteHost());

		// 세션 생성 - 로그아웃을 위한
		session.setAttribute(Session.LOGIN_HISTORY, newLoginHistoryVO);

		return memberDAO.stampLoginTime(newLoginHistoryVO) > 0;
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public boolean stampLogoutTime(HttpSession session) {
		LoginHistoryVO newLoginHistoryVO = new LoginHistoryVO();
		newLoginHistoryVO = (LoginHistoryVO) session.getAttribute("_LOGIN_HISTORY_");

		// 찍고 세션 없애기 
		session.removeAttribute("_LOGIN_HISTORY_");
		
		return memberDAO.stampLogoutTime(newLoginHistoryVO) > 0;
	}

	@Override
	public int getTotalLoginHisotryCount() {
		return memberDAO.getTotalLoginHisotryCount();
	}

	@Override
	public void attend(MemberVO loginVO) {
		
		
		/*회원별 강의*/
		List<EducationVO> eduListByMember = new ArrayList<EducationVO>();		
		eduListByMember = memberDAO.getEduListByMember(loginVO);
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		/*현재 시간*/
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat onlyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(date);
		long calTodayTime = cal3.getTimeInMillis();
		String nowTime = dateFormat.format(date);
		
		// 강의 리스트에서 StartDate ~ EndDate 맞는 강의 가져옴
		for (EducationVO educationVO : eduListByMember) {
			
			String startDate = educationVO.getStartDate() + " " + educationVO.getStartTime();
			String endDate = educationVO.getEndDate() +" "+ educationVO.getEndTime();
			
			String lastNowDate = memberDAO.getLastDate(educationVO);
			String nowDate = onlyDateFormat.format(date);
			
			try {
				Date eduStartDate = dateFormat.parse(startDate);
				Date eduEndDate = dateFormat.parse(endDate);
				
				cal.setTime(eduStartDate);
				long calEduStartDate = cal.getTimeInMillis();
				cal2.setTime(eduEndDate);
				long calEduEndDate = cal2.getTimeInMillis();
				
				if (calEduStartDate < calTodayTime && calTodayTime < calEduEndDate) {
					
					// 시간 체크 StartTime-1 ~ (EndTime-StartTime)/2 맞는지 체크
					
					Calendar cal4 = Calendar.getInstance();
					Date eduStartTime = timeFormat.parse(educationVO.getStartTime());
					cal4.setTime(eduStartTime);
					long calEduStartTime = cal4.getTimeInMillis();
					
					Calendar cal5 = Calendar.getInstance();
					Date eduEndTime = timeFormat.parse(educationVO.getEndTime());
					cal5.setTime(eduEndTime);
					long calEduEndTime = cal5.getTimeInMillis();
					
					cal4.add(Calendar.HOUR, -1);
					long calEduBeforeOneHour = cal4.getTimeInMillis();
					long calEduHalfTime = calEduEndTime - ((calEduEndTime-calEduStartTime)/2);
					
					Date todayTime = timeFormat.parse(timeFormat.format(date));
					cal3.setTime(todayTime);
					long calNowTime = cal3.getTimeInMillis();
					
					if ( calEduBeforeOneHour < calNowTime && calNowTime < calEduHalfTime ) {
						
						if ( !lastNowDate.equals(nowDate) ) {
							AttendVO attendVO = new AttendVO();
							attendVO.setEducationId(educationVO.getEducationId());
							attendVO.setMemberId(educationVO.getMemberId());
							attendVO.setAttendTime(nowTime);
							
							memberDAO.insertAttendByMember(attendVO);
							
						}
						
						break;
						
					}
					
				}
				
			} catch (ParseException e) {}
			
		}
		
		
		
		
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
		
		if ( memberId != null ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getSaltById(String id) {
		return memberDAO.getSaltById(id);
	}

	@Override
	public String getPasswordById(String id) {
		return memberDAO.getPasswordById(id);
	}

	@Override
	public boolean isResign(String id) {
		String memberId = memberDAO.isResign(id);
		
		if ( memberId != null ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<String> getGraduationType() {
		return memberDAO.getGraduationType();
	}

}
