package com.ktds.sems.teacher.vo;

public class EducationHistoryVO {
	
	private String id;
	private String startDate;
	private String endDate;
	private String educationName;
	private String educationLocation;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEducationName() {
		return educationName;
	}
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	public String getEduationLocation() {
		return educationLocation;
	}
	public void setEduationLocation(String educationLocation) {
		this.educationLocation = educationLocation;
	}

}
