package com.keybank.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.keybank.payments.model.PaymentRequest;
import com.keybank.payments.model.PaymentResponse;
import com.keybank.payments.service.PaymentService;
import com.keybank.payments.validator.PaymentValidator;

@RestController
@RequestMapping("/v1")
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	@Autowired
	PaymentValidator paymentValidator;
	@PostMapping("/payments")
	public PaymentResponse doPayment(@RequestBody PaymentRequest paymentRequest,
			@RequestHeader("client_id") String clientId,
			 @RequestHeader("channel_id") String channelId,
			 @RequestHeader("request_id") String requestId,
			 @RequestHeader("message_ts") String messageTs,
			 @RequestHeader("ipaddress") String ipaddress,
			 @RequestHeader("auth_token") String authToken) throws JsonProcessingException{
		 
		//1.validate the Request
		 paymentValidator.validateRequest(paymentRequest);
		//2.Prepare the serviceRequest
		//3.Call the service and get the Response
		 PaymentResponse response= paymentService.doPayments(paymentRequest);
		//4.Prepare the Controller/consumer Response
		return response;
	}
}
