package com.ktds.sems.member.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginHistorySearchVO extends LoginHistoryVO{
	
	private int pageNo;
	private int startIndex;
	private int endIndex;
	@NotEmpty(message="설정을 다시해주세요.")
	private String beginDate;
	@NotEmpty(message="설정을 다시해주세요.")
	private String closeDate;

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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
}
