package com.keybank.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keybank.payments.dao.PaymentDao;
import com.keybank.payments.model.PaymentDaoRequest;
import com.keybank.payments.model.PaymentDaoResponse;
import com.keybank.payments.model.PaymentRequest;
import com.keybank.payments.model.PaymentResponse;
import com.keybank.payments.model.PaymentStatus;
import com.keybank.payments.model.StripeRequest;
import com.keybank.payments.model.StripeResponse;
import com.keybank.payments.publisher.KafkaMessagePublisher;
import com.keybank.payments.serviceclient.StripeService;

@Component
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	StripeService stripeService;
	@Autowired
	KafkaMessagePublisher kafkaMessagePublisher;
	@Autowired
	PaymentDao paymentDao;
	//@Autowired
	
	//KafkaMessagePublisher kafkaMsgPublisher;
	
	@Override
	public PaymentResponse doPayments(PaymentRequest paymentRequest) {
		PaymentResponse paymentResponse=new PaymentResponse();
		//get the Request from controller
		//Prepare the request from for stripe
		StripeRequest stripeRequest=new StripeRequest(); 
		//call the StripeService and get the response
		StripeResponse  stripeResponse=stripeService.doPayment(stripeRequest);
		//update the payment status in database and also mqs like Kafka system.
		PaymentDaoRequest daoReq=new PaymentDaoRequest();
		PaymentDaoResponse daoResponse=paymentDao.updatesPaymentsInfo(daoReq);
		PaymentStatus status=new PaymentStatus();
		kafkaMessagePublisher.pushPaymentStatus(status);
		return paymentResponse;
	}

}
