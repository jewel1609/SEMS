package com.ktds.sems.team.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class MinutesVO {

	private int minutesId;
	private String memberId;
	private String minutesDate;
	
	@NotEmpty(message = "회의 안건은 필수 입력 값 입니다!")
	private String minutesAgenda;
	
	@NotEmpty(message = "참석자는 필수 입력 값 입니다!")
	private String attendance;
	
	@NotEmpty(message = "회의 장소는 필수 입력 값 입니다!")
	private String minutesPlace;
	
	@NotEmpty(message = "회의 내용은 필수 입력 값 입니다!")
	private String minutesContent;
	
	@NotEmpty(message = "결정 사항은 필수 입력 값 입니다!")
	private String decisionSubject;
	private String remarks;
	
	public int getMinutesId() {
		return minutesId;
	}
	public void setMinutesId(int minutesId) {
		this.minutesId = minutesId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMinutesDate() {
		return minutesDate;
	}
	public void setMinutesDate(String minutesDate) {
		this.minutesDate = minutesDate;
	}
	public String getMinutesAgenda() {
		return minutesAgenda;
	}
	public void setMinutesAgenda(String minutesAgenda) {
		this.minutesAgenda = minutesAgenda;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getMinutesPlace() {
		return minutesPlace;
	}
	public void setMinutesPlace(String minutesPlace) {
		this.minutesPlace = minutesPlace;
	}
	public String getMinutesContent() {
		return minutesContent;
	}
	public void setMinutesContent(String minutesContent) {
		this.minutesContent = minutesContent;
	}
	public String getDecisionSubject() {
		return decisionSubject;
	}
	public void setDecisionSubject(String decisionSubject) {
		this.decisionSubject = decisionSubject;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
