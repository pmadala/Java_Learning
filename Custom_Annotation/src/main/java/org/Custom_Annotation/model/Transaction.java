package org.Custom_Annotation.model;

import java.util.Date;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.ValidDate;
import org.Custom_Annotation.annotations.ValidationType;

public class Transaction extends Document{
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.NUMBER})
	private String id;

	private String description;
	
	private String amount;
	
	@ValidDate
	private String transactionTime;
	
	public Transaction() {}
	public Transaction(String id, String description, String amount, String date) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.transactionTime = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
}
