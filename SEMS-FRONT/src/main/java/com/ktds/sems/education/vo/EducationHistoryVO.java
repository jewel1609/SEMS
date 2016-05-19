package com.ktds.sems.education.vo;

public class EducationHistoryVO extends EducationVO {

	private String educationHistoryId;
	private String educationId;
	private String memberId;
	private String educationHistoryDate;
	private String state;
	private String ip;
	private String cmnt;
	private String fdbk;

	private String cdNm;

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getEducationHistoryId() {
		return educationHistoryId;
	}

	public void setEducationHistoryId(String educationHistoryId) {
		this.educationHistoryId = educationHistoryId;
	}

	public String getEducationId() {
		return educationId;
	}

	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getEducationHistoryDate() {
		return educationHistoryDate;
	}

	public void setEducationHistoryDate(String educationHistoryDate) {
		this.educationHistoryDate = educationHistoryDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCmnt() {
		return cmnt;
	}

	public void setCmnt(String cmnt) {
		this.cmnt = cmnt;
	}

	public String getFdbk() {
		return fdbk;
	}

	public void setFdbk(String fdbk) {
		this.fdbk = fdbk;
	}

}
