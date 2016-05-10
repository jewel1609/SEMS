package com.ktds.sems.member.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberVO {

	@NotEmpty(message="아이디는 필수 입력 값 입니다!")
	@Length(min=5, message="아이디는 영문, 숫자 조합의 4자리 이상 입력해야 합니다!")
	private String id;

	@NotEmpty(message="비밀번호는 필수 입력 값 입니다!")
	@Length(min=10, message="비밀번호는 영문, 숫자, 특수문자 조합의 10자리 이상 입력해야 합니다!")
	private String password;
	
	@NotEmpty(message="이름은 필수 입력 값 입니다!")
	private String name;

	@NotEmpty(message="이메일은 필수 입력 값 입니다!")
	private String email;
	
	@NotEmpty(message="최종 학력는 필수 입력 값 입니다!")
	private String highestEducationLevel;

	@NotEmpty(message="학교는 필수 입력 값 입니다!")
	private String universityName;
	
	@NotEmpty(message="학과는 필수 입력 값 입니다!")
	private String majorName;
	
	@NotEmpty(message="졸업 구분은 필수 입력 값 입니다!")
	private String graduationType;
	
	@NotEmpty(message="생년월일는 필수 입력 값 입니다!")
	private String birthDate;

	@NotEmpty(message="전화 번호는 필수 입력 값 입니다!")
	private String phoneNumber;

	@NotEmpty(message="회원 종류는 필수 입력 값 입니다!")
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
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
