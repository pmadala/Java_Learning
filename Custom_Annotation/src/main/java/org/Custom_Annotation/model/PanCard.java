package org.Custom_Annotation.model;

import java.util.Date;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.ValidDate;
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;

@Validate
public class PanCard extends Document{
	@CheckFor(type= {ValidationType.NOT_NULL})
	private String fullname;
	
	private String fatherName;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.PAN_NUMBER_LENGTH, ValidationType.PAN_NUMBER_LENGTH})
	private String panNumber;
	
	@CheckFor(type= {ValidationType.NOT_NULL})
	private String issuedBy;
	
	@CheckFor(type= {ValidationType.DATE})
	@ValidDate
	private String dob;
	
	public PanCard() {}
	public PanCard(String fullname, String fatherName, String panNumber, String issuedBy, String date) {
		this.fullname = fullname;
		this.fatherName = fatherName;
		this.panNumber = panNumber;
		this.issuedBy = issuedBy;
		this.dob = dob;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getIssuedBy() {
		return issuedBy;
	}
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
}
