package com.ktds.sems.education.vo;

public class EduAttendanceVO {
	
	private String memberId;
	private String memberName;
	private String educationId;
	private String educationTitle;
	private String educationStartTime;
	private String educationEndTime;
	private String attendanceTime;
	private String offTime;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getEducationId() {
		return educationId;
	}
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}
	public String getEducationTitle() {
		return educationTitle;
	}
	public void setEducationTitle(String educationTitle) {
		this.educationTitle = educationTitle;
	}
	public String getEducationStartTime() {
		return educationStartTime;
	}
	public void setEducationStartTime(String educationStartTime) {
		this.educationStartTime = educationStartTime;
	}
	public String getEducationEndTime() {
		return educationEndTime;
	}
	public void setEducationEndTime(String educationEndTime) {
		this.educationEndTime = educationEndTime;
	}
	public String getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public String getOffTime() {
		return offTime;
	}
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}

}
