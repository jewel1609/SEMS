package com.ktds.sems.education.vo;

public class EduReportSearchVO extends EducationVO {
	
	private int pageNo;
	private int startIndex;
	private int endIndex;
	
	private String searchTrainee;
	private String searchTeam;
	public String getSearchTrainee() {
		return searchTrainee;
	}
	public void setSearchTrainee(String searchTrainee) {
		this.searchTrainee = searchTrainee;
	}
	public String getSearchTeam() {
		return searchTeam;
	}
	public void setSearchTeam(String searchTeam) {
		this.searchTeam = searchTeam;
	}
	private String searchKeyword;
	private String searchType;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
}
