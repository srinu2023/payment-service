package com.keybank.payments.model;

import lombok.Data;

@Data
public class StripeResponse {
	private String ackNum;
	private String status;
	private String description;

}
