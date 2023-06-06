package com.keybank.payments.service;

import com.keybank.payments.model.PaymentRequest;
import com.keybank.payments.model.PaymentResponse;

public interface PaymentService {
	PaymentResponse doPayments(PaymentRequest paymentRequest);

}
