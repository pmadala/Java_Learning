package org.Custom_Annotation.model;

import java.util.Date;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.ValidDate;
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;

@Validate
public class Aadhar extends Document{
	
	@CheckFor(type=ValidationType.NOT_NULL)
	private String fullname;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.Gender})
	private String gender;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.ADDRESS_FIELD_LENGTH})
	private String address;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.AADHAR_NUMBER_LENGTH})
	private Long aadharNumber;
	
	@ValidDate
	private Date dob;
	
	public Aadhar(String fullName, String gender, String address, Long aadharNumber, Date dob) {
		this.fullname = fullName;
		this.gender = gender;
		this.address = address;
		this.aadharNumber = aadharNumber;
		this.dob = dob;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(Long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
}
