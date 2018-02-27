package org.Custom_Annotation.model;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.CrossFieldValidationType;
import org.Custom_Annotation.annotations.CrossValidate;
import org.Custom_Annotation.annotations.EqualFields;
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;

@Validate
@CrossValidate
public class Aadhar extends Document{
	
	@CheckFor(type= {ValidationType.NOT_NULL})
	@EqualFields(matchField = CrossFieldValidationType.NAME_CONSISTANCY_VALIDATION)
	private String fullname;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.Gender})
	private String gender;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.ADDRESS_FIELD_LENGTH})
	@EqualFields(matchField = CrossFieldValidationType.ADDRESS_CONSISTANCY_VALIDATION)
	private String address;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.AADHAR_NUMBER_LENGTH})
	private String aadharNumber;
	
	@CheckFor(type= {ValidationType.DATE})
	@EqualFields(matchField = CrossFieldValidationType.DOB_CONSISTANCY_VALIDATION)
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
