package com.keybank.payments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="payments")
@Data
public class Payments {
	private int id;
	private String status;
	private String ackNum;
	@Column(name="custname")
	private String customerName;
	@Column(name="mobnum")
	private long mobNum;
	private String address;
	private String emailId;
	private float amount;
	@Column(name="duedate")
	private String dueDate;
	@Column(name="Paymentdate")
	private String PaymentDate;
	

}
