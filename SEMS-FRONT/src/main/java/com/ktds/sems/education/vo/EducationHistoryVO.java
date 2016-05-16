package com.ktds.sems.education.vo;

public class EducationHistoryVO extends EducationVO {

	private String educationHistoryId;
	private String educationId;
	private String memberId;
	private String educationHistoryDate;
	private String state;
	private String comment;
	private String feedback;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
