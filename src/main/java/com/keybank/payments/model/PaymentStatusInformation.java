package com.keybank.payments.model;

import com.keybank.payments.entity.Payments;

import lombok.Data;

@Data
public class PaymentStatusInformation {
	private String status;
	private String ackNum;
	private String custName;
	private String emailId;
	private long mobNum;
	private String address;
	private String description;
	private Payments payments;
}
