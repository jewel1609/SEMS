package com.ktds.sems.education.vo;

import java.util.List;

import kr.co.hucloud.utilities.web.Paging;

public class EduAttendanceListVO {
	
	List<EduAttendanceVO> attendanceList;
	Paging paging;
	
	public List<EduAttendanceVO> getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(List<EduAttendanceVO> attendanceList) {
		this.attendanceList = attendanceList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

}
