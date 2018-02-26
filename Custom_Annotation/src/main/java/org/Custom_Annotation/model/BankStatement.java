package org.Custom_Annotation.model;

import java.util.List;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.CrossValidate;
import org.Custom_Annotation.annotations.Email;
import org.Custom_Annotation.annotations.EqualFields;
import org.Custom_Annotation.annotations.Validate;
import org.Custom_Annotation.annotations.ValidationType;

@Validate
@CrossValidate
public class BankStatement extends Document{
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.ALPHANEUMERIC})
	private String accountNumber;
	
	@CheckFor(type= {ValidationType.NOT_NULL})
	@EqualFields(baseField = "customerName", matchClass = PanCard.class, matchField = "fullname")
	private String customerName;
	
	private List<Transaction> transactions;
	
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.ADDRESS_FIELD_LENGTH})
	@EqualFields(baseField = "address", matchClass = Aadhar.class, matchField = "address")
	private String address;
	
	@CheckFor(type= {ValidationType.NOT_NULL})//, ValidationType.NUMBER
	private String mobileNumber;
	
	@CheckFor(type= {ValidationType.EMAIL})
	private String email;
	
	
	public BankStatement() {}
	public BankStatement(String accountNumber, String customerName, List<Transaction> transactions, String address,
			String mobileNumber, String email) {
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.transactions = transactions;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
