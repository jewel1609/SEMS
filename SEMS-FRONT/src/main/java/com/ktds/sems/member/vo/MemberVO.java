package com.ktds.sems.member.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberVO {

	@NotEmpty(message="���̵�� �ʼ� �Է°� �Դϴ�.")
	@Length(min=5, message="���̵�� 4�ڸ� ���ϸ� ���� �� �����ϴ�.")
	private String id;
	
	@NotEmpty(message="��й�ȣ�� �ʼ� �Է°� �Դϴ�.")
	@Length(min=10, message="Password�� 9�ڸ� ���ϸ� ���� �� �����ϴ�.")
	private String password;
	
	@NotEmpty(message="�̸��� �ʼ� �Է°� �Դϴ�.")
	private String name;
	
	@NotEmpty(message="�̸��ϴ� �ʼ� �Է°� �Դϴ�.")
	private String email;
	
	@NotEmpty(message="���� �з��� �ʼ� �Է°� �Դϴ�.")
	private String highestEducationLevel;
	
	@NotEmpty(message="�б��� �ʼ� �Է°� �Դϴ�.")
	private String universityName;
	
	@NotEmpty(message="�а��� �ʼ� �Է°� �Դϴ�.")
	private String majorName;
	
	@NotEmpty(message="���� ���д� �ʼ� �Է°� �Դϴ�.")
	private String graduationType;
	
	@NotEmpty(message="��������� �ʼ� �Է°� �Դϴ�.")
	private String birthDate;
	
	@NotEmpty(message="��ȭ��ȣ�� �ʼ� �Է°� �Դϴ�.")
	private String phoneNumber;
	
	@NotEmpty(message="��й�ȣ�� �ʼ� �Է°� �Դϴ�.")
	private String memberType;

	private String salt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHighestEducationLevel() {
		return highestEducationLevel;
	}
	public void setHighestEducationLevel(String highestEducationLevel) {
		this.highestEducationLevel = highestEducationLevel;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getGraduationType() {
		return graduationType;
	}
	public void setGraduationType(String graduationType) {
		this.graduationType = graduationType;
	}
	public String getBrithDate() {
		return birthDate;
	}
	public void setBrithDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
