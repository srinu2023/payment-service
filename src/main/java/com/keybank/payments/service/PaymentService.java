package com.keybank.payments.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.keybank.payments.model.PaymentRequest;
import com.keybank.payments.model.PaymentResponse;

public interface PaymentService {
	PaymentResponse doPayments(PaymentRequest paymentRequest) throws JsonProcessingException;

}
