package com.keybank.payments.model;

import lombok.Data;

@Data
public class StripeRequest {
	private String name;
	private String emailId;
	private String cardNum;
	private String cvv;
	private String nameOnCard;
	private String expMonth;
	private String expYear;
	private float amount;
	private String currency;
	private String customeId;
	

}
