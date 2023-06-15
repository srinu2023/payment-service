package com.keybank.payments.model;



import lombok.Data;

@Data
public class PaymentRequest {
	private CustomerDetails CustomerDetails;
	private PaymentInfo paymentInfo;

}
