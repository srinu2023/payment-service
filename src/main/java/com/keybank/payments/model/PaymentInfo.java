package com.keybank.payments.model;

import lombok.Data;

@Data
public class PaymentInfo {
	private float amount;
	private String dueDate;
	private String paymentDate;
	

}
