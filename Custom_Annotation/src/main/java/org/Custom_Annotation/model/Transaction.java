package org.Custom_Annotation.model;

import java.util.Date;

import org.Custom_Annotation.annotations.CheckFor;
import org.Custom_Annotation.annotations.ValidDate;
import org.Custom_Annotation.annotations.ValidationType;

public class Transaction {
	@CheckFor(type= {ValidationType.NOT_NULL, ValidationType.NUMBER})
	private String id;

	private String description;
	
	private double amount;
	
	@ValidDate
	private Date transactionTime;
	
	public Transaction(String id, String description, double amount, Date date) {
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
}
