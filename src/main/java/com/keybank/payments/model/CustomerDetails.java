package com.keybank.payments.model;

import lombok.Data;

@Data
public class CustomerDetails {
	private int customerId;
	private String name;
	private String ackNum;
	private String address;
	private long mobNum;
	private String emailId;
	private String city;
	private String status;
	private long pinCode;

}
