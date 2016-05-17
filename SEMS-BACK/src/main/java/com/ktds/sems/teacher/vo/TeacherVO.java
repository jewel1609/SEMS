package com.ktds.sems.teacher.vo;

import javax.validation.constraints.NotNull;





public class TeacherVO{

	private String memberId;
	private String companyName;
	private String businessNumber;

	@NotNull(message="연차를 입력 해주세요!")
	private int annual;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public int getAnnual() {
		return annual;
	}
	public void setAnnual(int annual) {
		this.annual = annual;
	}
	
}
