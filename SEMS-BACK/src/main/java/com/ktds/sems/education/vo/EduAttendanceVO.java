package com.ktds.sems.education.vo;

public class EduAttendanceVO extends EducationVO{
	
	private String attendanceDate;
	private String memberName;
	private String teamId;
	private String teamName;
	private String attendanceTime;
	private String offTime;
	private String attendanceState;
	
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	public String getAttendanceState() {
		return attendanceState;
	}
	public void setAttendanceState(String attendanceState) {
		this.attendanceState = attendanceState;
	}

}
