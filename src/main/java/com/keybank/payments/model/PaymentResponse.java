package com.keybank.payments.model;

import lombok.Data;

@Data
public class PaymentResponse {
	private String respCode;
	private String respMsg;
	private String status;
	private String ackNum;

}
