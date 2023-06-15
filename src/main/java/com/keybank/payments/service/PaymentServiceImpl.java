package com.keybank.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.keybank.payments.entity.Payments;
import com.keybank.payments.model.PaymentDaoRequest;
import com.keybank.payments.model.PaymentDaoResponse;
import com.keybank.payments.model.PaymentRequest;
import com.keybank.payments.model.PaymentResponse;
import com.keybank.payments.model.PaymentStatusInformation;
import com.keybank.payments.model.StripeRequest;
import com.keybank.payments.model.StripeResponse;
import com.keybank.payments.publisher.KafkaMessagePublisher;

import com.keybank.payments.repository.PaymentRepository;
import com.keybank.payments.serviceclient.StripeService;

@Component
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	StripeService stripeService;
	@Autowired
	KafkaMessagePublisher messagePublisher;
	@Autowired
	PaymentRepository paymentRepository;

	
	@Override
	public PaymentResponse doPayments(PaymentRequest paymentRequest) throws JsonProcessingException {
		PaymentResponse paymentResponse=new PaymentResponse();
		//get the Request from controller
		//Prepare the request from for stripe
		StripeRequest stripeRequest=new StripeRequest(); 
		//call the StripeService and get the response[1st backend call]
		StripeResponse  stripeResponse=stripeService.doPayment(stripeRequest);
		//update the payment status in database and also mqs like Kafka system.
		Payments payments=new Payments();
		payments.setId(paymentRequest.getCustomerDetails().getCustomerId());
		payments.setAckNum(paymentRequest.getCustomerDetails().getAckNum());
		payments.setAddress(paymentRequest.getCustomerDetails().getAddress());
		payments.setAmount(paymentRequest.getPaymentInfo().getAmount());
		payments.setCustomerName(paymentRequest.getCustomerDetails().getName());
		payments.setDueDate(paymentRequest.getPaymentInfo().getDueDate());
		payments.setPaymentDate(paymentRequest.getPaymentInfo().getPaymentDate());
		payments.setEmailId(paymentRequest.getCustomerDetails().getEmailId());
		payments.setMobNum(paymentRequest.getCustomerDetails().getMobNum());
		//payments.setStatus(paymentRequest.getCustomerDetails().getStatus());
		payments.setStatus(stripeResponse.getStatus());
	
		
		paymentRepository.save(payments);//[2nd backend call]
		System.out.println("data saved successfully");
		PaymentStatusInformation paymentStatusInformation=new PaymentStatusInformation();
		paymentStatusInformation.setAckNum(stripeResponse.getAckNum());
		paymentStatusInformation.setAddress(paymentRequest.getCustomerDetails().getAddress());
		paymentStatusInformation.setCustName(paymentRequest.getCustomerDetails().getName());
		paymentStatusInformation.setDescription("");
		paymentStatusInformation.setEmailId(paymentRequest.getCustomerDetails().getEmailId());
		paymentStatusInformation.setMobNum(paymentRequest.getCustomerDetails().getMobNum());
		paymentStatusInformation.setStatus(stripeResponse.getStatus());
		paymentStatusInformation.setPayments(payments);
		messagePublisher.pushPaymentStatus(paymentStatusInformation);////[3rd backend call]
		System.out.println("sent message to kafka successfully");
		paymentResponse.setAckNum(stripeResponse.getAckNum());
		paymentResponse.setRespCode("0");
		paymentResponse.setRespMsg("success");
		paymentResponse.setStatus(stripeResponse.getStatus());
		return paymentResponse;
	}

}
