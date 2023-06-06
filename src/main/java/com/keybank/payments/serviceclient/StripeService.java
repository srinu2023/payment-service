package com.keybank.payments.serviceclient;

import com.keybank.payments.model.StripeRequest;
import com.keybank.payments.model.StripeResponse;

public interface StripeService {
	StripeResponse doPayment(StripeRequest stripeRequest);
}
