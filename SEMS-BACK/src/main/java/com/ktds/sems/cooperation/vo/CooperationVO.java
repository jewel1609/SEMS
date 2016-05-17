package com.ktds.sems.cooperation.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class CooperationVO {

	private String cooperationId;
	
	@NotEmpty(message = "필수 정보입니다.")
	private String cooperationTitle;
	
	@NotEmpty(message = "필수 정보입니다.")
	private String cooperationLocation;
	
	@NotEmpty(message = "필수 정보입니다.")
	private String cooperationNumber;
	
	@NotEmpty(message = "필수 정보입니다.")
	private String representativeName;
	
	@NotEmpty(message = "필수 정보입니다.")
	private String managerPhoneNumber;
	
	@NotEmpty(message = "필수 정보입니다.")
	private String cooperationPhoneNumber;
	
	@NotEmpty(message = "필수 정보입니다.")
	private String managerEmail;

	private String cooperationType;

	public String getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getCooperationTitle() {
		return cooperationTitle;
	}

	public void setCooperationTitle(String cooperationTitle) {
		this.cooperationTitle = cooperationTitle;
	}

	public String getCooperationLocation() {
		return cooperationLocation;
	}

	public void setCooperationLocation(String cooperationLocation) {
		this.cooperationLocation = cooperationLocation;
	}

	public String getCooperationNumber() {
		return cooperationNumber;
	}

	public void setCooperationNumber(String cooperationNumber) {
		this.cooperationNumber = cooperationNumber;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	public String getCooperationPhoneNumber() {
		return cooperationPhoneNumber;
	}

	public void setCooperationPhoneNumber(String cooperationPhoneNumber) {
		this.cooperationPhoneNumber = cooperationPhoneNumber;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getCooperationType() {
		return cooperationType;
	}

	public void setCooperationType(String cooperationType) {
		this.cooperationType = cooperationType;
	}
	
	
	
	
	
}
