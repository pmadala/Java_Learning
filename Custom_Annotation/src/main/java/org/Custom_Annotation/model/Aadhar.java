package org.Custom_Annotation.model;

import java.util.Date;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.CrossValidate;
import org.Custom_Annotation.annotations.EqualFields;
import org.Custom_Annotation.annotations.ValidDate;
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;

@Validate
@CrossValidate
public class Aadhar extends Document{
	
	@CheckFor(type= {ValidationType.NOT_NULL})
	@EqualFields(baseField= "fullname" ,matchClass = PanCard.class, matchField = "fullname")
	private String fullname;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.Gender})
	private String gender;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.ADDRESS_FIELD_LENGTH})
	private String address;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.AADHAR_NUMBER_LENGTH})
	private String aadharNumber;
	
	@CheckFor(type= {ValidationType.DATE})
	@ValidDate
	@EqualFields(baseField="dob", matchClass = PanCard.class, matchField = "dob")
	private String dob;
	
	public Aadhar() {}
	
	public Aadhar(String fullName, String gender, String address, String aadharNumber, String dob) {
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
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
}
