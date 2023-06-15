package com.keybank.payments.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keybank.payments.model.PaymentStatusInformation;

@Component
public class KafkaMessagePublisher {
@Autowired
KafkaTemplate<String, Object> kafkaTemplate;
private String TOPIC_NAME="o2payments";

public void pushPaymentStatus(PaymentStatusInformation paymentStatusInformation) throws JsonProcessingException {
	// TODO: write the kafka Publisher code to push the message into  kafka
	ObjectMapper mapper=new ObjectMapper();
	//here we are getting so much details so not sending as tease, obj will be converting as json string
	String message=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(paymentStatusInformation);
	kafkaTemplate.send(TOPIC_NAME,message);
	
}
}
